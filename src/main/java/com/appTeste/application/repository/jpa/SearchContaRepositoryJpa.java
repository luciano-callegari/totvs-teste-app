package com.appTeste.application.repository.jpa;

import com.appTeste.application.repository.entity.ContaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SearchContaRepositoryJpa extends PagingAndSortingRepository<ContaEntity, Long> {

    List<ContaEntity> findAllByDataVencimentoAndDescricaoContaining(LocalDate dataVencimento, String descricao, Pageable pageable);
}