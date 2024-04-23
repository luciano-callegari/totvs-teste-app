package com.appTeste.application.controller.data.response;

import com.appTeste.domain.model.Conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateContaResponse {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private String situacao;

    public UpdateContaResponse(Conta conta) {
        this.id = conta.getId();
        this.dataCriacao = conta.getDataCriacao();
        this.dataAlteracao = conta.getDataAlteracao();
        this.dataVencimento = conta.getDataVencimento();
        this.dataPagamento = conta.getDataPagamento();
        this.valor = conta.getValor();
        this.descricao = conta.getDescricao();
        this.situacao = conta.getSituacao();
    }
}
