package com.noseque.dto;

import com.noseque.entities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UsuarioDTO {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String telephone;
    private boolean accountLocked;
    private boolean enabled;
    private List<Integer> roles;
}
