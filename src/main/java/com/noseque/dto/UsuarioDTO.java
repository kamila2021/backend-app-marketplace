package com.noseque.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String telephone;
    private boolean accountLocked;
    private boolean enabled;
    private List<Long> roles;
}
