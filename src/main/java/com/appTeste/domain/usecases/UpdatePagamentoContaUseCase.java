package com.appTeste.domain.usecases;

import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class UpdatePagamentoContaUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public Conta execute(Long id) {
        Conta conta = contaRepository.findById(id);
        if (ObjectUtils.isEmpty(conta.getId())) {
            throw new ValidationException(String.format("Conta não encontrada (id:%s)", id), "1000");
        }
        if (ObjectUtils.isEmpty(conta.getDataPagamento())) {
            conta.setDataAlteracao(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
            conta.setDataPagamento(LocalDate.now());
            conta.setSituacao(SituacaoEnum.PAGO);

            return contaRepository.updatePagamento(conta);
        }
        return conta;
    }
}
