package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@ContextConfiguration(classes = {CreateContaUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CreateContaUseCaseTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private CreateContaUseCase createContaUseCase;

    /**
     * Method under test: {@link CreateContaUseCase#execute(Conta)}
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
        when(contaRepository.create(Mockito.<Conta>any())).thenReturn(buildResult);
        Conta conta = new Conta();

        // Act
        createContaUseCase.execute(conta);

        // Assert
        verify(contaRepository).create(isA(Conta.class));
        assertEquals(SituacaoEnum.PENDENTE, conta.getSituacao());
    }

    /**
     * Method under test: {@link CreateContaUseCase#execute(Conta)}
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
        when(contaRepository.create(Mockito.<Conta>any())).thenReturn(buildResult);
        Conta.ContaBuilder builderResult2 = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult2 = builderResult2.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult2 = dataAlteracaoResult2.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult2 = dataCriacaoResult2.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult2 = dataPagamentoResult2.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta conta = situacaoResult2.valor(new BigDecimal("2.3")).build();

        // Act
        createContaUseCase.execute(conta);

        // Assert
        verify(contaRepository).create(isA(Conta.class));
        assertEquals(SituacaoEnum.PAGO, conta.getSituacao());
    }
}
