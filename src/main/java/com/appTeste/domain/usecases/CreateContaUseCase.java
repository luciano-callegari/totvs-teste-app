package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CreateContaUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public Conta execute(Conta conta) {
        conta.setDataCriacao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        conta.setSituacao(ObjectUtils.isEmpty(conta.getDataPagamento()) ? SituacaoEnum.PENDENTE : SituacaoEnum.PAGO);
        return contaRepository.create(conta);
    }
}
