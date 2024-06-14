package com.noseque.dto;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String telephone;
}