package com.appTeste.application.controller;

import com.appTeste.application.controller.data.request.CreateContaRequest;
import com.appTeste.application.controller.data.request.UpdateContaRequest;
import com.appTeste.application.controller.data.response.CreateContaResponse;
import com.appTeste.application.controller.data.response.GetContaResponse;
import com.appTeste.application.controller.data.response.SearchTotalPagoPeriodoResponse;
import com.appTeste.application.controller.data.response.UpdateContaResponse;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.usecases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private CreateContaUseCase createContaUseCase;
    @Autowired
    private UpdateContaUseCase updateContaUseCase;
    @Autowired
    private UpdatePagamentoContaUseCase updatePagamentoContaUseCase;
    @Autowired
    private GetListContasUseCase getListContasUseCase;
    @Autowired
    private GetContaUseCase getContaUseCase;
    @Autowired
    private SearchTotalPagoPeriodo searchTotalPagoPeriodo;
    @Autowired
    private SearchContasUseCase searchContasUseCase;
    @Autowired
    private ImportContasUseCase importContasUseCase;

    @PostMapping
    public CreateContaResponse addNew(@RequestBody CreateContaRequest data) {
        Conta conta = createContaUseCase.execute(data.toConta());
        return new CreateContaResponse(conta);
    }

    @PutMapping(path = "/{id}")
    public UpdateContaResponse update(@RequestBody UpdateContaRequest data,
                                      @PathVariable(value = "id") Long id) {
        Conta conta = updateContaUseCase.execute(data.toConta(id));
        return new UpdateContaResponse(conta);
    }

    @PutMapping(path = "/pagamento/{id}")
    public UpdateContaResponse updateSituacao(@PathVariable(value = "id") Long id) {
        Conta conta = updatePagamentoContaUseCase.execute(id);
        return new UpdateContaResponse(conta);
    }

    @GetMapping("/list")
    public List<GetContaResponse> getAll(
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize) {
        List<Conta> contas = getListContasUseCase.getListConta(pageNumber, pageSize);
        return contas.stream()
                .map(GetContaResponse::new)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public GetContaResponse getById(@PathVariable(value = "id") Long id) {
        Conta conta = getContaUseCase.getConta(id);
        return new GetContaResponse(conta);
    }

    @GetMapping(path = "/search/totalPago")
    public SearchTotalPagoPeriodoResponse getPaidAmount(
            @RequestParam(value = "dataInicio") LocalDate dataInicio,
            @RequestParam(value = "dataFim") LocalDate dataFim) {
        return new SearchTotalPagoPeriodoResponse(searchTotalPagoPeriodo.execute(dataInicio, dataFim));
    }

    @GetMapping(path = "/search")
    public List<GetContaResponse> searchContas(
            @RequestParam(value = "dataVencimento") LocalDate dataVencimento,
            @RequestParam(value = "descricao") String descricao,
            @RequestParam(value = "pageNumber") int pageNumber,
            @RequestParam(value = "pageSize") int pageSize) {
        List<Conta> contas = searchContasUseCase.execute(dataVencimento, descricao, pageNumber, pageSize);

        return contas.stream()
                .map(GetContaResponse::new)
                .toList();
    }

    @PostMapping(path = "/import")
    public List<CreateContaResponse> importCsv(@RequestParam("file") MultipartFile file) {
        return importContasUseCase.execute(file).stream()
                .map(CreateContaResponse::new)
                .toList();
    }
}
