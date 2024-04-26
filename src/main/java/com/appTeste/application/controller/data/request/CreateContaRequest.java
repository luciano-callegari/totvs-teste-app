package com.appTeste.application.controller.data.request;


import com.appTeste.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContaRequest {
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;

    public Conta toConta() {
        return Conta.builder()
                .dataVencimento(this.dataVencimento)
                .dataPagamento(this.dataPagamento)
                .valor(this.valor)
                .descricao(this.descricao)
                .build();
    }
}
