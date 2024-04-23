package com.appTeste.application.controller.data.request;

import com.appTeste.domain.model.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateContaRequest {
    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private String situacao;

    public Conta toConta() {
        return Conta.builder()
                .id(this.id)
                .dataCriacao(this.dataCriacao)
                .dataVencimento(this.dataVencimento)
                .dataPagamento(this.dataPagamento)
                .valor(this.valor)
                .descricao(this.descricao)
                .situacao(this.situacao)
                .build();
    }
}
