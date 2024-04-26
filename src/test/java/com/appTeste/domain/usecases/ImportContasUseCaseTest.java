package com.appTeste.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ImportContasUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ImportContasUseCaseTest {
    @MockBean
    private CreateContaUseCase createContaUseCase;

    @Autowired
    private ImportContasUseCase importContasUseCase;

    /**
     * Method under test: {@link ImportContasUseCase#execute(MultipartFile)}
     */
    @Test
    void testExecute() throws IOException {
        // Arrange, Act and Assert
        assertTrue(
                importContasUseCase.execute(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{}))).isEmpty());
    }

    /**
     * Method under test: {@link ImportContasUseCase#execute(MultipartFile)}
     */
    @Test
    void testExecute2() throws IOException {
        // Arrange
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> importContasUseCase.execute(file));
        verify(file).getInputStream();
    }
}
