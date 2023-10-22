package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.model.filter.PedidoFilter;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.spec.PedidoSpecs;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoModelAssembler assembler;

    @Autowired
    private PedidoResumoAssembler resumoAssembler;

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta,  separadas por vírgula",
            name = "campos", paramType = "query", type = "string")

    })
    @GetMapping
    public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,
                                             @PageableDefault(size = 10) Pageable pageable) {
        pageable = traduzirPageable(pageable);

        Page<Pedido> pedidosPage = pedidoRepository.findAll(
            PedidoSpecs.usandoFiltro(filtro), pageable);

        List<PedidoResumoModel> pedidosResumoModel = resumoAssembler
            .toCollectionModel(pedidosPage.getContent());

        return new PageImpl<>(
            pedidosResumoModel, pageable, pedidosPage.getTotalElements());
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

    @ApiImplicitParams({
        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta,  separadas por vírgula",
            name = "campos", paramType = "query", type = "string")
    })
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
