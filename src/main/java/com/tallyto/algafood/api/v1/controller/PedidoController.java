package com.tallyto.algafood.api.v1.controller;

import com.tallyto.algafood.api.v1.assembler.PedidoModelAssembler;
import com.tallyto.algafood.api.v1.assembler.PedidoResumoAssembler;
import com.tallyto.algafood.api.v1.model.PedidoModel;
import com.tallyto.algafood.api.v1.model.PedidoResumoModel;
import com.tallyto.algafood.api.v1.model.input.PedidoInput;
import com.tallyto.algafood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.tallyto.algafood.core.data.PageWrapper;
import com.tallyto.algafood.core.data.PageableTranslator;
import com.tallyto.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.tallyto.algafood.domain.exception.NegocioException;
import com.tallyto.algafood.domain.model.Pedido;
import com.tallyto.algafood.domain.model.Usuario;
import com.tallyto.algafood.domain.model.filter.PedidoFilter;
import com.tallyto.algafood.domain.repository.PedidoRepository;
import com.tallyto.algafood.domain.service.EmissaoPedidoService;
import com.tallyto.algafood.infrastructure.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoModelAssembler assembler;

    @Autowired
    private PedidoResumoAssembler resumoAssembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                                   @PageableDefault(size = 10) Pageable pageable) {
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
            PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);

        pedidosPage = new PageWrapper<>(pedidosPage, pageable);


        return pagedResourcesAssembler.toModel(pedidosPage, resumoAssembler);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = assembler.toEntity(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return assembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/{codidoPedido}")
    public PedidoModel buscar(@PathVariable String codidoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codidoPedido);

        return assembler.toModel(pedido);
    }

    private Pageable traduzirPageable(Pageable apiPageable) {
        var mapeamento = ImmutableMap.of(
            "nomeCliente", "cliente.nome",
            "codigo", "codigo",
            "restaurante.nome", "restaurante.nome",
            "valorTotal", "valorTotal"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }
}
