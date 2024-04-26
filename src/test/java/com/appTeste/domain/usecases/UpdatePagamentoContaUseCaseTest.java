package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import com.appTeste.domain.repository.ContaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UpdatePagamentoContaUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UpdatePagamentoContaUseCaseTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private UpdatePagamentoContaUseCase updatePagamentoContaUseCase;

    /**
     * Method under test: {@link UpdatePagamentoContaUseCase#execute(Long)}
     */
    @Test
    void testExecute() {
        // Arrange
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaRepository.findById(Mockito.<Long>any())).thenReturn(buildResult);

        // Act
        updatePagamentoContaUseCase.execute(1L);

        // Assert
        verify(contaRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link UpdatePagamentoContaUseCase#execute(Long)}
     */
    @Test
    void testExecute2() {
        // Arrange
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaRepository.updatePagamento(Mockito.<Conta>any())).thenReturn(buildResult);
        Conta.ContaBuilder builderResult2 = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult2 = builderResult2.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult2 = dataAlteracaoResult2.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay())
                .dataPagamento(null);
        Conta.ContaBuilder situacaoResult2 = dataPagamentoResult2.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult2 = situacaoResult2.valor(new BigDecimal("2.3")).build();
        when(contaRepository.findById(Mockito.<Long>any())).thenReturn(buildResult2);

        // Act
        updatePagamentoContaUseCase.execute(1L);

        // Assert
        verify(contaRepository).findById(eq(1L));
        verify(contaRepository).updatePagamento(isA(Conta.class));
    }

    /**
     * Method under test: {@link UpdatePagamentoContaUseCase#execute(Long)}
     */
    @Test
    void testExecute3() {
        // Arrange
        when(contaRepository.updatePagamento(Mockito.<Conta>any()))
                .thenThrow(new ValidationException("An error occurred", "An error occurred"));
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay())
                .dataPagamento(null);
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaRepository.findById(Mockito.<Long>any())).thenReturn(buildResult);

        // Act and Assert
        assertThrows(ValidationException.class, () -> updatePagamentoContaUseCase.execute(1L));
        verify(contaRepository).findById(eq(1L));
        verify(contaRepository).updatePagamento(isA(Conta.class));
    }
}
