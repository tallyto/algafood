package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome) {
        return cozinhaRepository.findAllByNome(nome);
    }

    @GetMapping("/cozinhas/unicas-por-nome")
    public Optional<Cozinha> cozinhasUnicasPorNome(@RequestParam("nome") String nome) {

        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/existe-por-nome")
    public List<Cozinha> cozinhasExistePorNome(@RequestParam("nome") String nome) {

        return cozinhaRepository.findByNomeContaining(nome);
    }

    @GetMapping("restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(@RequestParam("taxaInicial") BigDecimal taxaInicial,
                                                      @RequestParam("taxaFinal") BigDecimal taxaFinal) {

        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("restaurantes/por-nome")
    public List<Restaurante> restaurantesPorNome(@RequestParam("nome") String nome,
                                                      @RequestParam("cozinhaId") Long cozinhaId) {

        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }
}
