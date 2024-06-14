package com.noseque.services;

import lombok.Data;
import lombok.Generated;

@Data
public class UserDetailsRole {
    Long id;
    String name;

    @Generated
    public UserDetailsRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
