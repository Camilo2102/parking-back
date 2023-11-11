package com.example.parking.controller;

import com.example.parking.dto.PasswordChangeDTO;
import com.example.parking.dto.RegisterUserDTO;
import com.example.parking.models.Credential;
import com.example.parking.models.ParkingUser;
import com.example.parking.utils.EmailUtil;
import com.example.parking.utils.EncryptUtil;
import com.example.parking.utils.IdGeneratorUtil;
import com.example.parking.utils.TokenUtil;
import com.auth0.jwt.interfaces.Claim;
import com.example.parking.constants.MessageConstants;
import com.example.parking.constants.StatusConstants;
import com.example.parking.service.CredentialService;
import com.example.parking.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


//ANotacion necesaria para decir que es un controlador y llamar las rutas
@RestController
@RequestMapping("/auth")
@Transactional
public class CredentialController extends GeneralController<Credential> {

    private final CredentialService credentialService;
    private final UserService userService;

    @Autowired
    public CredentialController(CredentialService credentialService, UserService userService) {
        super(credentialService);
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @Override
    @Hidden
    public List<Credential> getAll(Credential credential) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    @Hidden
    public long getAllCount() throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    @Hidden
    public Credential getByID(String id) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    @Hidden
    public List<Credential> getAll(int pageNumber, int pageSize) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    @Hidden
    public Credential create(Credential credential) throws Exception {
        throw new Exception(MessageConstants.NOT_IMPLEMENTED_ROUTE);
    }

    @Override
    public Credential update(Credential credential) throws Exception {
        Credential credentialEncrypted = credentialWithEncryptedPassword(credential);
        return credentialService.save(credentialEncrypted);
    }

    private Credential credentialWithEncryptedPassword(Credential credential) {
        String hashedPassword = EncryptUtil.encryptValue(credential.getPassword());
        credential.setPassword(hashedPassword);
        return credential;
    }

    private Credential credentialWithEncryptedCode(Credential credential) {
        String hashedCode = EncryptUtil.encryptValue(credential.getCode());
        credential.setCode(hashedCode);
        return credential;
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Credential credential) throws Exception {
        Map<String, String> response = new HashMap<>();

        Optional<Credential> credentialFound = credentialService.findByUserAndMail(credential.getMail());

        if (credentialFound.isEmpty()) {
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }


        boolean state = EncryptUtil.checkValues(credential.getPassword(), credentialFound.get().getPassword());
        if (!state) {
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        Credential credentialData = credentialFound.get();
        ParkingUser user = userService.getByCredential(credentialData);

        if (user.getActive().equals("N")) {
            throw new Exception(StatusConstants.UNAUTHORIZED);
        }

        Map<String, String> userData = new HashMap<>();
        userData.put("id", user.getId());
        userData.put("role", String.valueOf(user.getRole()));

        String token = TokenUtil.generateToken(userData, 30);

        response.put(StatusConstants.STATUS, StatusConstants.AUTHORIZED);
        response.put("token", token);
        response.put("user", user.getId());
        response.put("role", user.getRole());
        return response;
    }

    @GetMapping("/validateCredential")
    public boolean validateUserName(@RequestParam("credential") String credential) {
        return credentialService.findByUserAndMail(credential).isPresent();
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterUserDTO registerUserDTO) throws Exception {
        Map<String, String> response = new HashMap<>();

        String code = IdGeneratorUtil.generateUUID(6);
        registerUserDTO.getUser().setId(IdGeneratorUtil.generateUUID());
        registerUserDTO.getUser().setId(IdGeneratorUtil.generateUUID());

        registerUserDTO.getCredential().setId(IdGeneratorUtil.generateUUID());

        registerUserDTO.getCredential().setCode(code);

        Credential encryptedCredential = credentialWithEncryptedCode(registerUserDTO.getCredential());
        Credential createdCredential = credentialService.save(encryptedCredential);

        ParkingUser user = registerUserDTO.getUser();
        user.setCredential(createdCredential);

        String temporalToken = EmailUtil.sendPasswordChangeMail(user, createdCredential.getMail(), code);

        userService.save(user);

        response.put("message", MessageConstants.SUCCESS_MESSAGE);
        response.put("temporalToken", temporalToken);
        return response;
    }

    @PostMapping("/registerUser")
    public Map<String, String> registerUser(@RequestBody ParkingUser user) throws Exception {
        Map<String, String> response = new HashMap<>();

        user.setId(IdGeneratorUtil.generateUUID());
        user.getCredential().setId(IdGeneratorUtil.generateUUID());

        String code = IdGeneratorUtil.generateUUID(6);
        user.getCredential().setCode(code);

        Credential encryptedCredential = credentialWithEncryptedCode(user.getCredential());
        Credential createdCredential = credentialService.save(encryptedCredential);

        user.setCredential(createdCredential);

        EmailUtil.sendPasswordChangeMail(user, createdCredential.getMail(), code);

        userService.save(user);

        response.put("message", MessageConstants.SUCCESS_MESSAGE);
        return response;
    }



    @GetMapping("/recoverPassword")
    public Map<String, String> recoverPassword(@RequestParam("mail") String mail) throws Exception {
        Map<String, String> response = new HashMap<>();

        Optional<Credential> credentialFound = credentialService.findByUserAndMail(mail);

        if (credentialFound.isEmpty()) {
            throw new Exception("Mail not found");
        }

        Credential credential = credentialFound.get();

        String code = IdGeneratorUtil.generateUUID(6);
        credential.setCode(code);

        Credential encryptedCredential = credentialWithEncryptedCode(credential);
        credentialService.save(encryptedCredential);

        ParkingUser userFound = userService.getByCredential(credential);

        EmailUtil.sendPasswordChangeMail(userFound, mail, code, true);

        response.put("message", "Email send successfully");
        return response;
    }


    @PostMapping("/enableUser")
    public Map<String, String> enableUser(@RequestBody PasswordChangeDTO passwordChangeDTO) throws Exception {
        Map<String, String> response = new HashMap<>();

        Map<String, Claim> payload;
        payload = TokenUtil.validateToken(passwordChangeDTO.getToken());

        String id = payload.get("id").asString();

        ParkingUser userFind = userService.findById(id);

        boolean isValid = EncryptUtil.checkValues(passwordChangeDTO.getCode(), userFind.getCredential().getCode());

        if (!isValid) {
            throw new Exception(MessageConstants.FAILED_MESSAGE);
        }

        userFind.getCredential().setPassword(passwordChangeDTO.getPassword());
        userFind.getCredential().setCode(null);

        Credential encryptedCredential = credentialWithEncryptedPassword(userFind.getCredential());
        userFind.setCredential(encryptedCredential);

        userFind.setActive("S");

        userService.save(userFind);

        response.put("message", "valid");

        return response;
    }

    @DeleteMapping("/disableUser")
    public Map<String, String> deleteUser(@RequestParam("id") String id) throws Exception {
        ParkingUser findUser = userService.findById(id);
        findUser.setActive("N");
        userService.save(findUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", MessageConstants.SUCCESS_MESSAGE);
        return response;
    }

    @GetMapping("/validateToken")
    public Map<String, String> validateToken(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "valid");
        return response;
    }
}
