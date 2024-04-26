package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchContasUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> execute(LocalDate dataVencimento, String descricao, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by("dataVencimento").ascending()
                        .and(Sort.by("id").ascending()));
        return contaRepository.searchContas(dataVencimento, descricao, pageable);
    }
}
