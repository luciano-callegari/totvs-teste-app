package com.appTeste.application.repository.jpa;

import com.appTeste.application.repository.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface ContaRepositoryJpa extends JpaRepository<ContaEntity, Long> {
    @Query(
            value = "SELECT SUM(c.valor) FROM conta c WHERE c.data_vencimento BETWEEN :dataInicio AND :dataFim AND c.situacao = :situacao",
            nativeQuery = true)
    BigDecimal findTotalPaidAmountByDateRange(@Param("dataInicio") LocalDate dataInicio,
                                              @Param("dataFim") LocalDate dataFim,
                                              @Param("situacao") String situacao);
}