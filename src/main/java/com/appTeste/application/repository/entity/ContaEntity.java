package com.appTeste.application.repository.entity;

import com.appTeste.domain.model.Conta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public LocalDateTime dataVencimento;

    public LocalDateTime dataPagamento;

    public BigDecimal valor;

    public String descricao;

    public String situacao;

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

    public void setNewDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setNewDataAlteracao() {
        this.dataAlteracao = LocalDateTime.now();
    }

    public void setNewSituacao(String situacao) {
        this.situacao = situacao;
    }
}
