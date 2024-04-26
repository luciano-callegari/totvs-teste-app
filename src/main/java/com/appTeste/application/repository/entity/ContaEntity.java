package com.appTeste.application.repository.entity;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Table(name = "conta")
@Entity(name = "conta")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime dataCriacao;

    public LocalDateTime dataAlteracao;

    public LocalDate dataVencimento;

    public LocalDate dataPagamento;

    public BigDecimal valor;

    public String descricao;

    @Enumerated(EnumType.STRING)
    public SituacaoEnum situacao;

    public static ContaEntity fromConta(Conta conta) {
        return ContaEntity.builder()
                .id(conta.getId())
                .dataCriacao(conta.getDataCriacao())
                .dataAlteracao(conta.getDataAlteracao())
                .dataVencimento(conta.getDataVencimento())
                .dataPagamento(conta.getDataPagamento())
                .valor(conta.getValor())
                .descricao(conta.getDescricao())
                .situacao(conta.getSituacao())
                .build();
    }

    public Conta toConta() {
        return Conta.builder()
                .id(this.id)
                .dataCriacao(this.dataCriacao)
                .dataAlteracao(this.dataAlteracao)
                .dataVencimento(this.dataVencimento)
                .dataPagamento(this.dataPagamento)
                .valor(this.valor)
                .descricao(this.descricao)
                .situacao(this.getSituacao())
                .build();
    }

    public void setNewSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public void setNewDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNewPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setNewDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setNewValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setNewDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
