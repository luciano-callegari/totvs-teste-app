package com.appTeste.application.controller.data.request;

import com.appTeste.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContaRequest {
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;

    public Conta toConta(Long id) {
        return Conta.builder()
                .id(id)
                .dataVencimento(this.dataVencimento)
                .dataPagamento(this.dataPagamento)
                .valor(this.valor)
                .descricao(this.descricao)
                .build();
    }
}
