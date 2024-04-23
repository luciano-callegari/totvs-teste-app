package com.appTeste.application.controller.data.response;

import com.appTeste.domain.model.Conta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateContaResponse {
    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private String situacao;

    public CreateContaResponse(Conta conta) {
        this.id = conta.getId();
        this.dataCriacao = conta.getDataCriacao();
        this.dataVencimento = conta.getDataVencimento();
        this.dataPagamento = conta.getDataPagamento();
        this.valor = conta.getValor();
        this.descricao = conta.getDescricao();
        this.situacao = conta.getSituacao();
    }
}
