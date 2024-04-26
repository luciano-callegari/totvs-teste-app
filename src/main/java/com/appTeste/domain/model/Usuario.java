package com.appTeste.domain.model;

import com.appTeste.domain.model.types.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Usuario {
    private String login;
    private String password;
    private UserRole role;

    public Usuario(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
