package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FotoProdutoAssembler;
import com.algaworks.algafood.api.model.DTO.FotoProdutoDTO;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private CatalogoFotoProdutoService fotoProdutoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoAssembler assembler;

    @GetMapping
    public FotoProdutoDTO buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        FotoProduto fotoExistente = fotoProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return assembler.toDTO(fotoExistente);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
                                        @PathVariable Long produtoId, @Valid FotoProdutoInput input) throws IOException {

        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = input.getArquivo();

        FotoProduto foto = new FotoProduto();
        foto.setProduto(produto);
        foto.setDescricao(input.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = fotoProdutoService.salvar(foto, arquivo.getInputStream());

        return assembler.toDTO(fotoSalva);
    }
}
