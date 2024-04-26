package com.appTeste.domain.usecases;

import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class GetContaUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public Conta getConta(Long id) {
        Conta conta = contaRepository.findById(id);
        if (ObjectUtils.isEmpty(conta.getId())) {
            throw new ValidationException(String.format("Conta não encontrada (id:%s)", id), "1000");
        }
        return conta;
    }
}
