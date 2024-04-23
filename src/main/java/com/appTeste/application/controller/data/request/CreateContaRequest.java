package com.appTeste.application.controller.data.request;


import com.appTeste.domain.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateContaRequest {
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private String situacao;

    public Conta toConta() {
        return Conta.builder()
                .dataVencimento(this.dataVencimento)
                .dataPagamento(this.dataPagamento)
                .valor(this.valor)
                .descricao(this.descricao)
                .situacao(this.situacao)
                .build();
    }
}
