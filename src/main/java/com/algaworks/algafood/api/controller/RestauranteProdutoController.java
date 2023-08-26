package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.ProdutoAssembler;
import com.algaworks.algafood.api.model.DTO.ProdutoDTO;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.ProdutoService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
    @Autowired
    RestauranteService restauranteService;

    @Autowired
    private ProdutoAssembler assembler;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoDTO> listar(@RequestParam(required = false) boolean incluirInativos, @PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        if(incluirInativos){
            return assembler.toCollectionDTO(produtoRepository.findAllByRestaurante(restaurante));
        }
        return assembler.toCollectionDTO(produtoRepository.findAtivosByRestaurante(restaurante));
    }

    @GetMapping("{produtoId}")
    public ProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        List<Produto> produtos = restauranteService.buscar(restauranteId).getProdutos();

        Produto item = produtos.stream()
            .filter(produto -> produto.getId().equals(produtoId))
            .findFirst()
            .orElseThrow(() -> new ProdutoNaoEncontradoException(
                String.format("Não existe um cadastro de produtos com código %d para o restaurante de código %d", produtoId, restauranteId)
            ));

        return assembler.toDTO(item);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        Produto produto = assembler.toEntity(produtoInput);
        produto.setRestaurante(restaurante);
        return assembler.toDTO(produtoService.adicionar(produto));
    }

    @PutMapping("{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        Produto produto = assembler.toEntity(produtoInput);
        produto.setRestaurante(restaurante);
        return assembler.toDTO(produtoService.atualizar(produtoId, produto));
    }
}


