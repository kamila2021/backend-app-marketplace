package com.noseque.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UsuarioInput {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String telephone;
    private boolean accountLocked;
    private boolean enabled;


}
