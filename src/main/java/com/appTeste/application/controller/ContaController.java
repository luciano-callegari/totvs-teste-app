package com.appTeste.application.controller;

import com.appTeste.application.controller.data.request.CreateContaRequest;
import com.appTeste.application.controller.data.request.UpdateContaRequest;
import com.appTeste.application.controller.data.response.CreateContaResponse;
import com.appTeste.application.controller.data.response.UpdateContaResponse;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.usecases.CreateContaUseCase;
import com.appTeste.domain.usecases.UpdateContaUseCase;
import com.appTeste.domain.usecases.UpdateSituacaoContaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private CreateContaUseCase createContaUseCase;
    @Autowired
    private UpdateContaUseCase updateContaUseCase;
    @Autowired
    private UpdateSituacaoContaUseCase updateSituacaoContaUseCase;

    @PostMapping
    public CreateContaResponse addNew(@RequestBody CreateContaRequest data) {
        Conta conta = createContaUseCase.execute(data.toConta());
        return new CreateContaResponse(conta);
    }

    @PutMapping(path = "/{id}/")
    public UpdateContaResponse update(@RequestBody UpdateContaRequest data) {
        Conta conta = updateContaUseCase.execute(data.toConta());
        return new UpdateContaResponse(conta);
    }

    @PutMapping(path = "/situacao/{id}")
    public UpdateContaResponse updateSituacao(
            @RequestParam("id") Long id,
            @RequestParam("situacao") String situacao) {
        Conta conta = updateSituacaoContaUseCase.execute(id, situacao);
        return new UpdateContaResponse(conta);
    }

    @GetMapping("/list")
    public void getAll() {

    }

    public void getById() {

    }

    public void getPaidAmount() {

    }

}
