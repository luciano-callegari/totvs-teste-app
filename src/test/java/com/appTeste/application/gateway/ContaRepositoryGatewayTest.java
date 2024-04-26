package com.appTeste.application.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.application.repository.entity.ContaEntity;
import com.appTeste.application.repository.jpa.ContaRepositoryJpa;
import com.appTeste.application.repository.jpa.SearchContaRepositoryJpa;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ContaRepositoryGateway.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ContaRepositoryGatewayTest {
    @Autowired
    private ContaRepositoryGateway contaRepositoryGateway;

    @MockBean
    private ContaRepositoryJpa contaRepositoryJpa;

    @MockBean
    private SearchContaRepositoryJpa searchContaRepositoryJpa;

    /**
     * Method under test: {@link ContaRepositoryGateway#create(Conta)}
     */
    @Test
    void testCreate() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity);

        // Act
        Conta actualCreateResult = contaRepositoryGateway.create(new Conta());

        // Assert
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
        assertEquals("2024-01-01", actualCreateResult.getDataPagamento().toString());
        assertEquals("2024-01-01", actualCreateResult.getDataVencimento().toString());
        assertEquals("2024-01-01", actualCreateResult.getDataAlteracao().toLocalDate().toString());
        assertEquals("Descricao", actualCreateResult.getDescricao());
        assertNull(actualCreateResult.getId());
        assertNull(actualCreateResult.getDataCriacao());
        assertEquals(SituacaoEnum.PENDENTE, actualCreateResult.getSituacao());
        BigDecimal expectedValor = new BigDecimal("2.3");
        assertEquals(expectedValor, actualCreateResult.getValor());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#create(Conta)}
     */
    @Test
    void testCreate2() {
        // Arrange
        ContaEntity contaEntity = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity).setNewValor(Mockito.<BigDecimal>any());
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity);

        // Act
        contaRepositoryGateway.create(new Conta());

        // Assert
        verify(contaEntity).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity).setNewDescricao(eq("Descricao"));
        verify(contaEntity).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity).setNewValor(isA(BigDecimal.class));
        verify(contaEntity).toConta();
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#create(Conta)}
     */
    @Test
    void testCreate3() {
        // Arrange
        ContaEntity contaEntity = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity).setNewValor(Mockito.<BigDecimal>any());
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity);
        Conta conta = mock(Conta.class);
        when(conta.getSituacao()).thenReturn(SituacaoEnum.PENDENTE);
        when(conta.getId()).thenReturn(1L);
        when(conta.getDescricao()).thenReturn("Descricao");
        when(conta.getValor()).thenReturn(new BigDecimal("2.3"));
        when(conta.getDataPagamento()).thenReturn(LocalDate.of(2024, 1, 1));
        when(conta.getDataVencimento()).thenReturn(LocalDate.of(2024, 1, 1));
        when(conta.getDataAlteracao()).thenReturn(LocalDate.of(2024, 1, 1).atStartOfDay());
        when(conta.getDataCriacao()).thenReturn(LocalDate.of(2024, 1, 1).atStartOfDay());

        // Act
        contaRepositoryGateway.create(conta);

        // Assert
        verify(contaEntity).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity).setNewDescricao(eq("Descricao"));
        verify(contaEntity).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity).setNewValor(isA(BigDecimal.class));
        verify(contaEntity).toConta();
        verify(conta).getDataAlteracao();
        verify(conta).getDataCriacao();
        verify(conta).getDataPagamento();
        verify(conta).getDataVencimento();
        verify(conta).getDescricao();
        verify(conta).getId();
        verify(conta).getSituacao();
        verify(conta).getValor();
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#update(Conta)}
     */
    @Test
    void testUpdate() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ContaEntity contaEntity2 = new ContaEntity();
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        Conta actualUpdateResult = contaRepositoryGateway.update(new Conta());

        // Assert
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
        assertEquals("2024-01-01", actualUpdateResult.getDataPagamento().toString());
        assertEquals("2024-01-01", actualUpdateResult.getDataVencimento().toString());
        assertEquals("2024-01-01", actualUpdateResult.getDataAlteracao().toLocalDate().toString());
        assertEquals("Descricao", actualUpdateResult.getDescricao());
        assertNull(actualUpdateResult.getId());
        assertNull(actualUpdateResult.getDataCriacao());
        assertEquals(SituacaoEnum.PENDENTE, actualUpdateResult.getSituacao());
        BigDecimal expectedValor = new BigDecimal("2.3");
        assertEquals(expectedValor, actualUpdateResult.getValor());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#update(Conta)}
     */
    @Test
    void testUpdate2() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        ContaEntity contaEntity2 = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity2.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity2).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity2).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity2).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity2).setNewValor(Mockito.<BigDecimal>any());
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        contaRepositoryGateway.update(new Conta());

        // Assert
        verify(contaEntity2).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity2).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity2).setNewDescricao(eq("Descricao"));
        verify(contaEntity2).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity2).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity2).setNewValor(isA(BigDecimal.class));
        verify(contaEntity2).toConta();
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#update(Conta)}
     */
    @Test
    void testUpdate3() {
        // Arrange
        ContaEntity contaEntity = mock(ContaEntity.class);
        doNothing().when(contaEntity).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity).setNewValor(Mockito.<BigDecimal>any());
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        ContaEntity contaEntity2 = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity2.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity2).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity2).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity2).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity2).setNewValor(Mockito.<BigDecimal>any());
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        contaRepositoryGateway.update(new Conta());

        // Assert
        verify(contaEntity2).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity, atLeast(1)).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        verify(contaEntity2).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity, atLeast(1)).setNewDataVencimento(Mockito.<LocalDate>any());
        verify(contaEntity, atLeast(1)).setNewDescricao(Mockito.<String>any());
        verify(contaEntity2).setNewDescricao(eq("Descricao"));
        verify(contaEntity2).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity, atLeast(1)).setNewPagamento(Mockito.<LocalDate>any());
        verify(contaEntity, atLeast(1)).setNewSituacao(Mockito.<SituacaoEnum>any());
        verify(contaEntity2).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity2).setNewValor(isA(BigDecimal.class));
        verify(contaEntity, atLeast(1)).setNewValor(Mockito.<BigDecimal>any());
        verify(contaEntity2).toConta();
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#listAll(Pageable)}
     */
    @Test
    void testListAll() {
        // Arrange
        when(searchContaRepositoryJpa.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        // Act
        List<Conta> actualListAllResult = contaRepositoryGateway.listAll(null);

        // Assert
        verify(searchContaRepositoryJpa).findAll((Pageable) isNull());
        assertTrue(actualListAllResult.isEmpty());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#listAll(Pageable)}
     */
    @Test
    void testListAll2() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ArrayList<ContaEntity> content = new ArrayList<>();
        content.add(contaEntity);
        PageImpl<ContaEntity> pageImpl = new PageImpl<>(content);
        when(searchContaRepositoryJpa.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        List<Conta> actualListAllResult = contaRepositoryGateway.listAll(null);

        // Assert
        verify(searchContaRepositoryJpa).findAll((Pageable) isNull());
        assertEquals(1, actualListAllResult.size());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#listAll(Pageable)}
     */
    @Test
    void testListAll3() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ContaEntity contaEntity2 = new ContaEntity();
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("com.appTeste.application.repository.entity.ContaEntity");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PAGO);
        contaEntity2.setNewValor(new BigDecimal("2.3"));

        ArrayList<ContaEntity> content = new ArrayList<>();
        content.add(contaEntity2);
        content.add(contaEntity);
        PageImpl<ContaEntity> pageImpl = new PageImpl<>(content);
        when(searchContaRepositoryJpa.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        List<Conta> actualListAllResult = contaRepositoryGateway.listAll(null);

        // Assert
        verify(searchContaRepositoryJpa).findAll((Pageable) isNull());
        assertEquals(2, actualListAllResult.size());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#findById(Long)}
     */
    @Test
    void testFindById() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        Optional<ContaEntity> ofResult = Optional.of(contaEntity);
        when(contaRepositoryJpa.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Conta actualFindByIdResult = contaRepositoryGateway.findById(1L);

        // Assert
        verify(contaRepositoryJpa).findById(eq(1L));
        assertEquals("2024-01-01", actualFindByIdResult.getDataPagamento().toString());
        assertEquals("2024-01-01", actualFindByIdResult.getDataVencimento().toString());
        assertEquals("2024-01-01", actualFindByIdResult.getDataAlteracao().toLocalDate().toString());
        assertEquals("Descricao", actualFindByIdResult.getDescricao());
        assertNull(actualFindByIdResult.getId());
        assertNull(actualFindByIdResult.getDataCriacao());
        assertEquals(SituacaoEnum.PENDENTE, actualFindByIdResult.getSituacao());
        BigDecimal expectedValor = new BigDecimal("2.3");
        assertEquals(expectedValor, actualFindByIdResult.getValor());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#findById(Long)}
     */
    @Test
    void testFindById2() {
        // Arrange
        ContaEntity contaEntity = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity).setNewValor(Mockito.<BigDecimal>any());
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        Optional<ContaEntity> ofResult = Optional.of(contaEntity);
        when(contaRepositoryJpa.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        contaRepositoryGateway.findById(1L);

        // Assert
        verify(contaEntity).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity).setNewDescricao(eq("Descricao"));
        verify(contaEntity).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity).setNewValor(isA(BigDecimal.class));
        verify(contaEntity).toConta();
        verify(contaRepositoryJpa).findById(eq(1L));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#findById(Long)}
     */
    @Test
    void testFindById3() {
        // Arrange
        Optional<ContaEntity> emptyResult = Optional.empty();
        when(contaRepositoryJpa.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act
        Conta actualFindByIdResult = contaRepositoryGateway.findById(1L);

        // Assert
        verify(contaRepositoryJpa).findById(eq(1L));
        assertNull(actualFindByIdResult.getSituacao());
        assertNull(actualFindByIdResult.getId());
        assertNull(actualFindByIdResult.getDescricao());
        assertNull(actualFindByIdResult.getValor());
        assertNull(actualFindByIdResult.getDataPagamento());
        assertNull(actualFindByIdResult.getDataVencimento());
        assertNull(actualFindByIdResult.getDataAlteracao());
        assertNull(actualFindByIdResult.getDataCriacao());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#updatePagamento(Conta)}
     */
    @Test
    void testUpdatePagamento() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ContaEntity contaEntity2 = new ContaEntity();
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        Conta actualUpdatePagamentoResult = contaRepositoryGateway.updatePagamento(new Conta());

        // Assert
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
        assertEquals("2024-01-01", actualUpdatePagamentoResult.getDataPagamento().toString());
        assertEquals("2024-01-01", actualUpdatePagamentoResult.getDataVencimento().toString());
        assertEquals("2024-01-01", actualUpdatePagamentoResult.getDataAlteracao().toLocalDate().toString());
        assertEquals("Descricao", actualUpdatePagamentoResult.getDescricao());
        assertNull(actualUpdatePagamentoResult.getId());
        assertNull(actualUpdatePagamentoResult.getDataCriacao());
        assertEquals(SituacaoEnum.PENDENTE, actualUpdatePagamentoResult.getSituacao());
        BigDecimal expectedValor = new BigDecimal("2.3");
        assertEquals(expectedValor, actualUpdatePagamentoResult.getValor());
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#updatePagamento(Conta)}
     */
    @Test
    void testUpdatePagamento2() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        ContaEntity contaEntity2 = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity2.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity2).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity2).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity2).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity2).setNewValor(Mockito.<BigDecimal>any());
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        contaRepositoryGateway.updatePagamento(new Conta());

        // Assert
        verify(contaEntity2).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity2).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity2).setNewDescricao(eq("Descricao"));
        verify(contaEntity2).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity2).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity2).setNewValor(isA(BigDecimal.class));
        verify(contaEntity2).toConta();
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test: {@link ContaRepositoryGateway#updatePagamento(Conta)}
     */
    @Test
    void testUpdatePagamento3() {
        // Arrange
        ContaEntity contaEntity = mock(ContaEntity.class);
        doNothing().when(contaEntity).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity).setNewValor(Mockito.<BigDecimal>any());
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));
        ContaEntity contaEntity2 = mock(ContaEntity.class);
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(2024, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(2024, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(2024, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(contaEntity2.toConta()).thenReturn(buildResult);
        doNothing().when(contaEntity2).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        doNothing().when(contaEntity2).setNewDataVencimento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewDescricao(Mockito.<String>any());
        doNothing().when(contaEntity2).setNewPagamento(Mockito.<LocalDate>any());
        doNothing().when(contaEntity2).setNewSituacao(Mockito.<SituacaoEnum>any());
        doNothing().when(contaEntity2).setNewValor(Mockito.<BigDecimal>any());
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("Descricao");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity2.setNewValor(new BigDecimal("2.3"));
        when(contaRepositoryJpa.save(Mockito.<ContaEntity>any())).thenReturn(contaEntity2);
        when(contaRepositoryJpa.getReferenceById(Mockito.<Long>any())).thenReturn(contaEntity);

        // Act
        contaRepositoryGateway.updatePagamento(new Conta());

        // Assert
        verify(contaEntity2).setNewDataAlteracao(isA(LocalDateTime.class));
        verify(contaEntity, atLeast(1)).setNewDataAlteracao(Mockito.<LocalDateTime>any());
        verify(contaEntity2).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity).setNewDataVencimento(isA(LocalDate.class));
        verify(contaEntity2).setNewDescricao(eq("Descricao"));
        verify(contaEntity).setNewDescricao(eq("Descricao"));
        verify(contaEntity2).setNewPagamento(isA(LocalDate.class));
        verify(contaEntity, atLeast(1)).setNewPagamento(Mockito.<LocalDate>any());
        verify(contaEntity, atLeast(1)).setNewSituacao(Mockito.<SituacaoEnum>any());
        verify(contaEntity2).setNewSituacao(eq(SituacaoEnum.PENDENTE));
        verify(contaEntity2).setNewValor(isA(BigDecimal.class));
        verify(contaEntity).setNewValor(isA(BigDecimal.class));
        verify(contaEntity2).toConta();
        verify(contaRepositoryJpa).getReferenceById(isNull());
        verify(contaRepositoryJpa).save(isA(ContaEntity.class));
    }

    /**
     * Method under test:
     * {@link ContaRepositoryGateway#getPaidAmount(LocalDate, LocalDate)}
     */
    @Test
    void testGetPaidAmount() {
        // Arrange
        BigDecimal bigDecimal = new BigDecimal("2.3");
        when(contaRepositoryJpa.findTotalPaidAmountByDateRange(Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<String>any())).thenReturn(bigDecimal);
        LocalDate dataInicio = LocalDate.of(2024, 1, 1);

        // Act
        BigDecimal actualPaidAmount = contaRepositoryGateway.getPaidAmount(dataInicio, LocalDate.of(2024, 1, 1));

        // Assert
        verify(contaRepositoryJpa).findTotalPaidAmountByDateRange(isA(LocalDate.class), isA(LocalDate.class), eq("PAGO"));
        assertEquals(new BigDecimal("2.3"), actualPaidAmount);
        assertSame(bigDecimal, actualPaidAmount);
    }

    /**
     * Method under test:
     * {@link ContaRepositoryGateway#searchContas(LocalDate, String, Pageable)}
     */
    @Test
    void testSearchContas() {
        // Arrange
        when(searchContaRepositoryJpa.findAllByDataVencimentoAndDescricaoContaining(Mockito.<LocalDate>any(),
                Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(new ArrayList<>());

        // Act
        List<Conta> actualSearchContasResult = contaRepositoryGateway.searchContas(LocalDate.of(2024, 1, 1), "Descricao",
                null);

        // Assert
        verify(searchContaRepositoryJpa).findAllByDataVencimentoAndDescricaoContaining(isA(LocalDate.class),
                eq("Descricao"), isNull());
        assertTrue(actualSearchContasResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link ContaRepositoryGateway#searchContas(LocalDate, String, Pageable)}
     */
    @Test
    void testSearchContas2() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ArrayList<ContaEntity> contaEntityList = new ArrayList<>();
        contaEntityList.add(contaEntity);
        when(searchContaRepositoryJpa.findAllByDataVencimentoAndDescricaoContaining(Mockito.<LocalDate>any(),
                Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(contaEntityList);

        // Act
        List<Conta> actualSearchContasResult = contaRepositoryGateway.searchContas(LocalDate.of(2024, 1, 1), "Descricao",
                null);

        // Assert
        verify(searchContaRepositoryJpa).findAllByDataVencimentoAndDescricaoContaining(isA(LocalDate.class),
                eq("Descricao"), isNull());
        assertEquals(1, actualSearchContasResult.size());
    }

    /**
     * Method under test:
     * {@link ContaRepositoryGateway#searchContas(LocalDate, String, Pageable)}
     */
    @Test
    void testSearchContas3() {
        // Arrange
        ContaEntity contaEntity = new ContaEntity();
        contaEntity.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewDescricao("Descricao");
        contaEntity.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity.setNewSituacao(SituacaoEnum.PENDENTE);
        contaEntity.setNewValor(new BigDecimal("2.3"));

        ContaEntity contaEntity2 = new ContaEntity();
        contaEntity2.setNewDataAlteracao(LocalDate.of(2024, 1, 1).atStartOfDay());
        contaEntity2.setNewDataVencimento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewDescricao("com.appTeste.application.repository.entity.ContaEntity");
        contaEntity2.setNewPagamento(LocalDate.of(2024, 1, 1));
        contaEntity2.setNewSituacao(SituacaoEnum.PAGO);
        contaEntity2.setNewValor(new BigDecimal("2.3"));

        ArrayList<ContaEntity> contaEntityList = new ArrayList<>();
        contaEntityList.add(contaEntity2);
        contaEntityList.add(contaEntity);
        when(searchContaRepositoryJpa.findAllByDataVencimentoAndDescricaoContaining(Mockito.<LocalDate>any(),
                Mockito.<String>any(), Mockito.<Pageable>any())).thenReturn(contaEntityList);

        // Act
        List<Conta> actualSearchContasResult = contaRepositoryGateway.searchContas(LocalDate.of(2024, 1, 1), "Descricao",
                null);

        // Assert
        verify(searchContaRepositoryJpa).findAllByDataVencimentoAndDescricaoContaining(isA(LocalDate.class),
                eq("Descricao"), isNull());
        assertEquals(2, actualSearchContasResult.size());
    }
}
