package com.appTeste.application.controller;

import com.appTeste.application.controller.data.request.CadastroRequest;
import com.appTeste.application.controller.data.request.LoginRequest;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.usecases.user.UsuarioCadastroUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioCadastroUseCase usuarioCadastroUseCase;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody CadastroRequest data) {
        usuarioCadastroUseCase.execute(data.toUsuario());

        return ResponseEntity.ok().build();
    }
}
