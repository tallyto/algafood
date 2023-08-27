package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.model.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {
    @Autowired
    private VendaQueryService vendaQueryService;
    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro){
        return vendaQueryService.consultarVendasDiarias(filtro);
    }
}
