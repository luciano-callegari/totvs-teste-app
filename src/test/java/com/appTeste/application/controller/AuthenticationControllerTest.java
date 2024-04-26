package com.appTeste.application.controller;

import static org.mockito.Mockito.when;

import com.appTeste.application.controller.data.request.CadastroRequest;
import com.appTeste.application.controller.data.request.LoginRequest;
import com.appTeste.application.service.TokenService;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.model.types.UserRole;
import com.appTeste.domain.usecases.user.UsuarioCadastroUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthenticationController.class, AuthenticationManager.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthenticationControllerTest {
    @Autowired
    private AuthenticationController authenticationController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UsuarioCadastroUseCase usuarioCadastroUseCase;

    /**
     * Method under test: {@link AuthenticationController#cadastro(CadastroRequest)}
     */
    @Test
    void testCadastro() throws Exception {
        // Arrange
        Usuario buildResult = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(usuarioCadastroUseCase.execute(Mockito.<Usuario>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/auth/cadastro")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CadastroRequest("Login", "senha", UserRole.ADMIN)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
