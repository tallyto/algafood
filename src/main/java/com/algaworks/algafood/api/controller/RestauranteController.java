package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.DTO.RestauranteDTO;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.api.model.view.RestauranteView;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler assembler;

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        List<RestauranteDTO> restaurantesDTO = assembler.toCollectionDTO(restaurantes);
        return ResponseEntity.ok(restaurantesDTO);
    }

//    @GetMapping
//    public MappingJacksonValue viewDinamica(@RequestParam(required = false) String projecao) {
//        List<Restaurante> restaurantes = restauranteRepository.findAll();
//        List<RestauranteDTO> restauranteDTOS = assembler.toCollectionDTO(restaurantes);
//        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteDTOS);
//
//        if (projecao != null) {
//            if (projecao.equals("apenasNome")) {
//                restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//            } else if (projecao.equals("resumo")) {
//                restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//            }
//        }
//
//        return restaurantesWrapper;
//
//    }

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping("/resumido")
    public List<RestauranteDTO> listarResumido() {
        return assembler.toCollectionDTO(restauranteRepository.findAll());
    }

    @JsonView(RestauranteView.ApenasNome.class)
    @GetMapping("/apenas-nome")
    public List<RestauranteDTO> listarApenasNome() {
        return assembler.toCollectionDTO(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = restauranteService.buscar(restauranteId);
        return assembler.toDTO(restaurante);
    }

    @PostMapping
    public RestauranteDTO adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
        Restaurante restaurante = assembler.toEntity(restauranteInput);
        return assembler.toDTO(restauranteService.salvar(restaurante));
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restaurante) {

        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

        assembler.copyToEntity(restaurante, restauranteAtual);

        return assembler.toDTO(restauranteService.salvar(restauranteAtual));
    }

    @PatchMapping("/{restauranteId}")
    public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId,
                                           @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        Restaurante restauranteAtual = restauranteService.buscar(restauranteId);

        merge(campos, restauranteAtual, request);
        validate(restauranteAtual, "restaurante");

        return assembler.toDTO(restauranteService.salvar(restauranteAtual));
    }

    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId) {
        restauranteService.ativar(restauranteId);
    }


    @DeleteMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId) {
        restauranteService.inativar(restauranteId);
    }

    @PutMapping("/{restauranteId}/abertura")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abertura(@PathVariable Long restauranteId) {
        restauranteService.abertura(restauranteId);
    }


    @PutMapping("/{restauranteId}/fechamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechamento(@PathVariable Long restauranteId) {
        restauranteService.fechamento(restauranteId);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            this.restauranteService.ativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
        try {
            this.restauranteService.inativar(restauranteIds);
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino,
                       HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                assert field != null;
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }

}
