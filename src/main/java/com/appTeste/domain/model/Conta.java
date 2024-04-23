package com.appTeste.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conta {
    private Long id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;
    private String descricao;
    private String situacao;

    public Conta(Conta conta) {
    }
}
