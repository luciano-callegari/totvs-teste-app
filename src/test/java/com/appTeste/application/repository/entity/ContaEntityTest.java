package com.appTeste.application.repository.entity;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.appTeste.domain.model.Conta;

import org.junit.jupiter.api.Test;

class ContaEntityTest {
    /**
     * Method under test: {@link ContaEntity#fromConta(Conta)}
     */
    @Test
    void testFromConta() {
        // Arrange and Act
        ContaEntity actualFromContaResult = ContaEntity.fromConta(new Conta());

        // Assert
        assertNull(actualFromContaResult.getSituacao());
        assertNull(actualFromContaResult.getId());
        assertNull(actualFromContaResult.getDescricao());
        assertNull(actualFromContaResult.getValor());
        assertNull(actualFromContaResult.getDataPagamento());
        assertNull(actualFromContaResult.getDataVencimento());
        assertNull(actualFromContaResult.getDataAlteracao());
        assertNull(actualFromContaResult.getDataCriacao());
    }

    /**
     * Method under test: {@link ContaEntity#toConta()}
     */
    @Test
    void testToConta() {
        // Arrange and Act
        Conta actualToContaResult = (new ContaEntity()).toConta();

        // Assert
        assertNull(actualToContaResult.getSituacao());
        assertNull(actualToContaResult.getId());
        assertNull(actualToContaResult.getDescricao());
        assertNull(actualToContaResult.getValor());
        assertNull(actualToContaResult.getDataPagamento());
        assertNull(actualToContaResult.getDataVencimento());
        assertNull(actualToContaResult.getDataAlteracao());
        assertNull(actualToContaResult.getDataCriacao());
    }
}
