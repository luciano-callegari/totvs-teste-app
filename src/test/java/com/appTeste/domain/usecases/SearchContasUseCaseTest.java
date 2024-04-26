package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SearchContasUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SearchContasUseCaseTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private SearchContasUseCase searchContasUseCase;

    /**
     * Method under test:
     * {@link SearchContasUseCase#execute(LocalDate, String, int, int)}
     */
    @Test
    void testExecute() {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        when(contaRepository.searchContas(Mockito.<LocalDate>any(), Mockito.<String>any(), Mockito.<Pageable>any()))
                .thenReturn(contaList);

        // Act
        List<Conta> actualExecuteResult = searchContasUseCase.execute(LocalDate.of(2024, 1, 1), "Descricao", 10, 3);

        // Assert
        verify(contaRepository).searchContas(isA(LocalDate.class), eq("Descricao"), isA(Pageable.class));
        assertTrue(actualExecuteResult.isEmpty());
        assertSame(contaList, actualExecuteResult);
    }
}
