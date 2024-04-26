package com.appTeste.application.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.appTeste.application.controller.data.request.CreateContaRequest;
import com.appTeste.application.controller.data.request.UpdateContaRequest;
import com.appTeste.domain.model.Conta;
import com.appTeste.domain.model.types.SituacaoEnum;
import com.appTeste.domain.usecases.CreateContaUseCase;
import com.appTeste.domain.usecases.GetContaUseCase;
import com.appTeste.domain.usecases.GetListContasUseCase;
import com.appTeste.domain.usecases.ImportContasUseCase;
import com.appTeste.domain.usecases.SearchContasUseCase;
import com.appTeste.domain.usecases.SearchTotalPagoPeriodo;
import com.appTeste.domain.usecases.UpdateContaUseCase;
import com.appTeste.domain.usecases.UpdatePagamentoContaUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ContaController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ContaControllerDiffblueTest {
    @Autowired
    private ContaController contaController;

    @MockBean
    private CreateContaUseCase createContaUseCase;

    @MockBean
    private GetContaUseCase getContaUseCase;

    @MockBean
    private GetListContasUseCase getListContasUseCase;

    @MockBean
    private ImportContasUseCase importContasUseCase;

    @MockBean
    private SearchContasUseCase searchContasUseCase;

    @MockBean
    private SearchTotalPagoPeriodo searchTotalPagoPeriodo;

    @MockBean
    private UpdateContaUseCase updateContaUseCase;

    @MockBean
    private UpdatePagamentoContaUseCase updatePagamentoContaUseCase;

    /**
     * Method under test: {@link ContaController#addNew(CreateContaRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddNew() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.appTeste.application.controller.data.request.CreateContaRequest["dataVencimento"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        CreateContaRequest createContaRequest = new CreateContaRequest();
        createContaRequest.setDataPagamento(LocalDate.of(1970, 1, 1));
        createContaRequest.setDataVencimento(LocalDate.of(1970, 1, 1));
        createContaRequest.setDescricao("Descricao");
        createContaRequest.setValor(new BigDecimal("2.3"));
        String content = (new ObjectMapper()).writeValueAsString(createContaRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/conta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(contaController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ContaController#importCsv(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testImportCsv() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   org.springframework.web.multipart.MultipartException: Current request is not a multipart request
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ContaController contaController = new ContaController();

        // Act
        contaController.importCsv(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link ContaController#getAll(int, int)}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(getListContasUseCase.getListConta(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContaController#getAll(int, int)}
     */
    @Test
    void testGetAll2() throws Exception {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult);
        when(getListContasUseCase.getListConta(anyInt(), anyInt())).thenReturn(contaList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}]"));
    }

    /**
     * Method under test: {@link ContaController#getAll(int, int)}
     */
    @Test
    void testGetAll3() throws Exception {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult);
        Conta.ContaBuilder builderResult2 = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult2 = builderResult2.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult2 = dataAlteracaoResult2.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult2 = dataCriacaoResult2.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult2 = dataPagamentoResult2.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult2 = situacaoResult2.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult2);
        when(getListContasUseCase.getListConta(anyInt(), anyInt())).thenReturn(contaList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"},{\"id\":1,\"dataCriacao"
                                        + "\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],\"dataPagamento\":[1970,1,1"
                                        + "],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}]"));
    }

    /**
     * Method under test: {@link ContaController#getById(Long)}
     */
    @Test
    void testGetById() throws Exception {
        // Arrange
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(getContaUseCase.getConta(Mockito.<Long>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/conta/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}"));
    }

    /**
     * Method under test:
     * {@link ContaController#getPaidAmount(LocalDate, LocalDate)}
     */
    @Test
    void testGetPaidAmount() throws Exception {
        // Arrange
        when(searchTotalPagoPeriodo.execute(Mockito.<LocalDate>any(), Mockito.<LocalDate>any()))
                .thenReturn(new BigDecimal("2.3"));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/search/totalPago");
        MockHttpServletRequestBuilder paramResult = getResult.param("dataFim", String.valueOf(LocalDate.of(1970, 1, 1)));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("dataInicio",
                String.valueOf(LocalDate.of(1970, 1, 1)));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"valorPeriodo\":2.3}"));
    }

    /**
     * Method under test:
     * {@link ContaController#searchContas(LocalDate, String, int, int)}
     */
    @Test
    void testSearchContas() throws Exception {
        // Arrange
        when(searchContasUseCase.execute(Mockito.<LocalDate>any(), Mockito.<String>any(), anyInt(), anyInt()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/search");
        MockHttpServletRequestBuilder paramResult = getResult
                .param("dataVencimento", String.valueOf(LocalDate.of(1970, 1, 1)))
                .param("descricao", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ContaController#searchContas(LocalDate, String, int, int)}
     */
    @Test
    void testSearchContas2() throws Exception {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult);
        when(searchContasUseCase.execute(Mockito.<LocalDate>any(), Mockito.<String>any(), anyInt(), anyInt()))
                .thenReturn(contaList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/search");
        MockHttpServletRequestBuilder paramResult = getResult
                .param("dataVencimento", String.valueOf(LocalDate.of(1970, 1, 1)))
                .param("descricao", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}]"));
    }

    /**
     * Method under test:
     * {@link ContaController#searchContas(LocalDate, String, int, int)}
     */
    @Test
    void testSearchContas3() throws Exception {
        // Arrange
        ArrayList<Conta> contaList = new ArrayList<>();
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult);
        Conta.ContaBuilder builderResult2 = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult2 = builderResult2.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult2 = dataAlteracaoResult2.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult2 = dataCriacaoResult2.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult2 = dataPagamentoResult2.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult2 = situacaoResult2.valor(new BigDecimal("2.3")).build();
        contaList.add(buildResult2);
        when(searchContasUseCase.execute(Mockito.<LocalDate>any(), Mockito.<String>any(), anyInt(), anyInt()))
                .thenReturn(contaList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/conta/search");
        MockHttpServletRequestBuilder paramResult = getResult
                .param("dataVencimento", String.valueOf(LocalDate.of(1970, 1, 1)))
                .param("descricao", "foo");
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("pageSize", String.valueOf(1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"},{\"id\":1,\"dataCriacao"
                                        + "\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],\"dataPagamento\":[1970,1,1"
                                        + "],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}]"));
    }

    /**
     * Method under test: {@link ContaController#update(UpdateContaRequest, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDate` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.appTeste.application.controller.data.request.UpdateContaRequest["dataVencimento"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UpdateContaRequest updateContaRequest = new UpdateContaRequest();
        updateContaRequest.setDataPagamento(LocalDate.of(1970, 1, 1));
        updateContaRequest.setDataVencimento(LocalDate.of(1970, 1, 1));
        updateContaRequest.setDescricao("Descricao");
        updateContaRequest.setValor(new BigDecimal("2.3"));
        String content = (new ObjectMapper()).writeValueAsString(updateContaRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/conta/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(contaController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ContaController#updateSituacao(Long)}
     */
    @Test
    void testUpdateSituacao() throws Exception {
        // Arrange
        Conta.ContaBuilder builderResult = Conta.builder();
        Conta.ContaBuilder dataAlteracaoResult = builderResult.dataAlteracao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataCriacaoResult = dataAlteracaoResult.dataCriacao(LocalDate.of(1970, 1, 1).atStartOfDay());
        Conta.ContaBuilder dataPagamentoResult = dataCriacaoResult.dataPagamento(LocalDate.of(1970, 1, 1));
        Conta.ContaBuilder situacaoResult = dataPagamentoResult.dataVencimento(LocalDate.of(1970, 1, 1))
                .descricao("Descricao")
                .id(1L)
                .situacao(SituacaoEnum.PENDENTE);
        Conta buildResult = situacaoResult.valor(new BigDecimal("2.3")).build();
        when(updatePagamentoContaUseCase.execute(Mockito.<Long>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/conta/pagamento/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(contaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"dataCriacao\":[1970,1,1,0,0],\"dataAlteracao\":[1970,1,1,0,0],\"dataVencimento\":[1970,1,1],"
                                        + "\"dataPagamento\":[1970,1,1],\"valor\":2.3,\"descricao\":\"Descricao\",\"situacao\":\"PENDENTE\"}"));
    }
}
