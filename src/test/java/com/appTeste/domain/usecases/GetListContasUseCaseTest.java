package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;

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

@ContextConfiguration(classes = {GetListContasUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GetListContasUseCaseTest {
    @MockBean
    private ContaRepository contaRepository;

    @Autowired
    private GetListContasUseCase getListContasUseCase;

    /**
     * Method under test: {@link GetListContasUseCase#getListConta(int, int)}
     */
    @Test
    void testGetListConta() {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        when(contaRepository.listAll(Mockito.<Pageable>any())).thenReturn(contaList);

        // Act
        List<Conta> actualListConta = getListContasUseCase.getListConta(10, 3);

        // Assert
        verify(contaRepository).listAll(isA(Pageable.class));
        assertTrue(actualListConta.isEmpty());
        assertSame(contaList, actualListConta);
    }
}
