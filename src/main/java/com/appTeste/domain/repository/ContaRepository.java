package com.appTeste.domain.repository;

import com.appTeste.domain.model.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ContaRepository {
    Conta create(Conta conta);
    Conta update(Conta conta);
    List<Conta> listAll();
    Conta getById(Long id);
    Conta updateSituacao(Long id, String situacao);
    BigDecimal getPaidAmount(LocalDateTime dataInicio, LocalDateTime dataFim);
}
