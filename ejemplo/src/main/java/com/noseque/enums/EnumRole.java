package com.noseque.enums;

import org.springframework.security.core.GrantedAuthority;

public enum EnumRole implements GrantedAuthority {
    USUARIO,
    PRESTADOR,
    ADMINISTRADOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
    public String getName() {
        return this.name();
    }
}

