package com.appTeste.application.controller;

import com.appTeste.application.controller.data.request.CadastroRequest;
import com.appTeste.application.controller.data.request.LoginRequest;
import com.appTeste.application.controller.data.response.LoginResponse;
import com.appTeste.application.repository.entity.UserEntity;
import com.appTeste.application.service.TokenService;
import com.appTeste.domain.usecases.user.UsuarioCadastroUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioCadastroUseCase usuarioCadastroUseCase;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity cadastro(@RequestBody CadastroRequest data) {
        usuarioCadastroUseCase.execute(data.toUsuario());

        return ResponseEntity.ok().build();
    }
}
