package com.appTeste.application.repository.jpa;

import com.appTeste.application.repository.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ContaRepositoryJpa extends JpaRepository<ContaEntity, Long> {
    @Query("SELECT SUM(c.valor) FROM conta c WHERE c.dataPagamento BETWEEN :dataInicio AND :dataFim")
    BigDecimal findTotalPaidAmountByDateRange(@Param("dataInicio") LocalDateTime dataInicio,
                                              @Param("dataFim") LocalDateTime dataFim);
}