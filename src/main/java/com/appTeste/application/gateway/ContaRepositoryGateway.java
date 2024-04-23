package com.appTeste.application.gateway;

import com.appTeste.application.repository.entity.ContaEntity;
import com.appTeste.application.repository.jpa.ContaRepositoryJpa;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaRepositoryGateway implements ContaRepository {

    private ContaRepositoryJpa repositoryJpa;

    @Override
    public Conta create(Conta conta) {
        ContaEntity contaEntity = ContaEntity.fromConta(conta);
        contaEntity.setNewDataCriacao();
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public Conta update(Conta conta) {
        ContaEntity contaEntity = ContaEntity.fromConta(conta);
        contaEntity.setNewDataAlteracao();
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public List<Conta> listAll() {
        return repositoryJpa.findAll().stream()
                .map(ContaEntity::toConta)
                .toList();
    }

    @Override
    public Conta getById(Long id) {
        return repositoryJpa.findById(id)
                .orElseThrow()
                .toConta();
    }

    @Override
    public Conta updateSituacao(Long id, String situacao) {
        ContaEntity contaEntity = repositoryJpa.getReferenceById(id);
        contaEntity.setNewSituacao(situacao);
        return repositoryJpa.save(contaEntity).toConta();
    }

    @Override
    public BigDecimal getPaidAmount(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return repositoryJpa.findTotalPaidAmountByDateRange(dataInicio, dataFim);
    }
}
