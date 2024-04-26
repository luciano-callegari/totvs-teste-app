package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListContasUseCase {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> getListConta(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(
                pageNumber, pageSize, Sort.by("dataVencimento").ascending()
                        .and(Sort.by("id").ascending()));
        return contaRepository.listAll(pageable);
    }
}
