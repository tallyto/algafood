package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.model.filter.VendaDiariaFilter;

import java.util.List;

public interface VendaQueryService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter);
}
