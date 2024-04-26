package com.appTeste.domain.repository;

import com.appTeste.domain.model.Conta;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ContaRepository {
    Conta create(Conta conta);
    Conta update(Conta conta);
    List<Conta> listAll(Pageable pageable);
    Conta findById(Long id);
    BigDecimal getPaidAmount(LocalDate dataInicio, LocalDate dataFim);
    Conta updatePagamento(Conta conta);
    List<Conta> searchContas(LocalDate dataVencimento, String descricao, Pageable pageable);
}
