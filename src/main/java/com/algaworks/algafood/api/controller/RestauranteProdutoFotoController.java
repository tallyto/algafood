package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId,
                              @PathVariable Long produtoId, @Valid FotoProdutoInput input){
        var nomeArquivo = UUID.randomUUID().toString() + "_" + input.getArquivo().getOriginalFilename();

        var arquivoFoto = Path.of("C:\\Users\\rodrigues\\Desktop\\Fotos", nomeArquivo);

        System.out.println(input.getDescricao());
        System.out.println(arquivoFoto);
        System.out.println(input.getArquivo().getContentType());

        try {
            input.getArquivo().transferTo(arquivoFoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
