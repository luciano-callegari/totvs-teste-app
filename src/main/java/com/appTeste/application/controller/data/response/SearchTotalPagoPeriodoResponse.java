package com.appTeste.application.controller.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class SearchTotalPagoPeriodoResponse {

    private BigDecimal valorPeriodo;

    public SearchTotalPagoPeriodoResponse(BigDecimal valorPeriodo) {
        this.valorPeriodo = valorPeriodo;
    }
}
