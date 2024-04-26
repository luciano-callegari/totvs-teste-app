package com.appTeste.application.gateway;

import com.appTeste.application.repository.entity.ContaEntity;
import com.appTeste.application.repository.jpa.ContaRepositoryJpa;
import com.appTeste.application.repository.jpa.SearchContaRepositoryJpa;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContaRepositoryGateway implements ContaRepository {

    @Autowired
    private ContaRepositoryJpa repositoryJpa;
    @Autowired
    private SearchContaRepositoryJpa searchContaRepositoryJpa;

    @Override
    public Conta create(Conta conta) {
        ContaEntity contaEntity = ContaEntity.fromConta(conta);
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public Conta update(Conta conta) {
        ContaEntity contaEntity = repositoryJpa.getReferenceById(conta.getId());
        contaEntity.setNewDataAlteracao(conta.getDataAlteracao());
        contaEntity.setNewDataVencimento(conta.getDataVencimento());
        contaEntity.setNewPagamento(conta.getDataPagamento());
        contaEntity.setNewValor(conta.getValor());
        contaEntity.setNewDescricao(conta.getDescricao());
        contaEntity.setNewSituacao(conta.getSituacao());
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public List<Conta> listAll(Pageable pageable) {
        return searchContaRepositoryJpa.findAll(pageable).stream()
                .map(ContaEntity::toConta)
                .toList();
    }

    @Override
    public Conta findById(Long id) {
        return repositoryJpa.findById(id)
                .orElseGet(() -> new ContaEntity())
                .toConta();
    }

    @Override
    public Conta updatePagamento(Conta conta) {
        ContaEntity contaEntity = repositoryJpa.getReferenceById(conta.getId());
        contaEntity.setNewDataAlteracao(conta.getDataAlteracao());
        contaEntity.setNewPagamento(conta.getDataPagamento());
        contaEntity.setNewSituacao(conta.getSituacao());
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public BigDecimal getPaidAmount(LocalDate dataInicio, LocalDate dataFim) {
        return repositoryJpa.findTotalPaidAmountByDateRange(dataInicio, dataFim, SituacaoEnum.PAGO.name());
    }

    @Override
    public List<Conta> searchContas(LocalDate dataVencimento, String descricao, Pageable pageable) {
        return searchContaRepositoryJpa
                .findAllByDataVencimentoAndDescricaoContaining(dataVencimento, descricao, pageable)
                .stream()
                .map(ContaEntity::toConta)
                .toList();
    }
}
