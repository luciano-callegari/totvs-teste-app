package com.appTeste.domain.usecases;

import com.appTeste.domain.model.Conta;
import com.appTeste.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportContasUseCase {

    @Value("${appconfi.iso8601.dateformat}")
    private String iso8601_dateformat;
    @Autowired
    private CreateContaUseCase createContaUseCase;

    @Transactional
    public List<Conta> execute(MultipartFile file) {
        List<String> linhas = parseCsvFileToLines(file);
        List<Conta> contas = parseCsvLinesToContas(linhas);
        List<Conta> contasCriadas = contas.stream()
                .map(conta -> createContaUseCase.execute(conta))
                .toList();
        return contasCriadas;
    }

    private List<Conta> parseCsvLinesToContas(List<String> linhas) {
        List<Conta> contas = linhas.stream()
                .map(linha -> parseConta(linha))
                .toList();
        return contas;
    }

    private Conta parseConta(String linha) {
        List<String> campos = Arrays.asList(linha.split(",", -1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(iso8601_dateformat);
        Conta conta = Conta.builder()
                .dataVencimento(LocalDate.parse(campos.get(0), formatter))
                .dataPagamento(!ObjectUtils.isEmpty(campos.get(1)) ? LocalDate.parse(campos.get(1), formatter) : null)
                .valor(new BigDecimal(campos.get(2)))
                .descricao(campos.get(3))
                .build();
        return conta;
    }

    private List<String> parseCsvFileToLines(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.lines().collect(Collectors.toList());
    }
}
