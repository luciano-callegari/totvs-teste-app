package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateSituacaoContaUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public Conta execute(Long id, String situacao) {
        return contaRepository.updateSituacao(id, situacao);
    }
}
