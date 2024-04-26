package com.appTeste.application.controller.data.request;

import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.model.types.UserRole;

public record CadastroRequest(String login, String password, UserRole role) {
    public Usuario toUsuario() {
        return Usuario.builder()
                .login(this.login)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
