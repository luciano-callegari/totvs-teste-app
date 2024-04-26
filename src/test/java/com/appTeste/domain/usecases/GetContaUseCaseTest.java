package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
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

@ContextConfiguration(classes = {GetContaUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GetContaUseCaseTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private GetContaUseCase getContaUseCase;

    /**
     * Method under test: {@link GetContaUseCase#getConta(Long)}
     */
    @Test
    void testGetConta() {
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
        getContaUseCase.getConta(1L);

        // Assert
        verify(contaRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link GetContaUseCase#getConta(Long)}
     */
    @Test
    void testGetConta2() {
        // Arrange
        when(contaRepository.findById(Mockito.<Long>any()))
                .thenThrow(new ValidationException("An error occurred", "An error occurred"));

        // Act and Assert
        assertThrows(ValidationException.class, () -> getContaUseCase.getConta(1L));
        verify(contaRepository).findById(eq(1L));
    }
}
