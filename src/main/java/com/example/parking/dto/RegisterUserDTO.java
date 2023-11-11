package com.example.parking.dto;

import com.example.parking.models.Credential;
import com.example.parking.models.ParkingUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private Credential credential;
    private ParkingUser user;
}
