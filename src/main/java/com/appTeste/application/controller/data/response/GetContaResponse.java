package com.appTeste.application.controller.data.response;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GetContaResponse {

    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private SituacaoEnum situacao;

    public GetContaResponse(Conta conta) {
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
