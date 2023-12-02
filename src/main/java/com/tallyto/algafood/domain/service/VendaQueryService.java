package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.model.dto.VendaDiaria;
import com.tallyto.algafood.domain.model.filter.VendaDiariaFilter;

import java.util.List;

public interface VendaQueryService {
    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
