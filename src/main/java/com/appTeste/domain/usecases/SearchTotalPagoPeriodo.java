package com.appTeste.domain.usecases;

import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SearchTotalPagoPeriodo {

    @Autowired
    private ContaRepository contaRepository;

    public BigDecimal execute(LocalDate dataInicio, LocalDate dataFim) {
        return contaRepository.getPaidAmount(dataInicio, dataFim);
    }
}
