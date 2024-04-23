package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateContaUseCase {

    private ContaRepository contaRepository;

    public Conta execute(Conta conta) {
        return contaRepository.update(conta);
    }
}
