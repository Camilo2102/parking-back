package co.com.parking.dto;

import co.com.parking.models.Credential;
import co.com.parking.models.ParkingUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private Credential credential;
    private ParkingUser user;
}
