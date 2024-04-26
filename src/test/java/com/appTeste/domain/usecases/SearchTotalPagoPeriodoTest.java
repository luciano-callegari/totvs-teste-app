package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

@ContextConfiguration(classes = {SearchTotalPagoPeriodo.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SearchTotalPagoPeriodoTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private SearchTotalPagoPeriodo searchTotalPagoPeriodo;

    /**
     * Method under test:
     * {@link SearchTotalPagoPeriodo#execute(LocalDate, LocalDate)}
     */
    @Test
    void testExecute() {
        // Arrange
        BigDecimal bigDecimal = new BigDecimal("2.3");
        when(contaRepository.getPaidAmount(Mockito.<LocalDate>any(), Mockito.<LocalDate>any())).thenReturn(bigDecimal);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        // Act
        BigDecimal actualExecuteResult = searchTotalPagoPeriodo.execute(dataInicio, LocalDate.of(2024, 1, 1));

        // Assert
        verify(contaRepository).getPaidAmount(isA(LocalDate.class), isA(LocalDate.class));
        assertEquals(new BigDecimal("2.3"), actualExecuteResult);
        assertSame(bigDecimal, actualExecuteResult);
    }
}
