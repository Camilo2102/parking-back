package com.example.parking.service;

import com.example.parking.models.Credential;
import com.example.parking.models.ParkingUser;
import com.example.parking.repository.ParkingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService extends GeneralService<ParkingUser> {
    private final ParkingUserRepository userRepository;

    @Autowired
    public UserService(ParkingUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public ParkingUser getByCredential(Credential credential) {
        return userRepository.findByCredential(credential);
    }

}
