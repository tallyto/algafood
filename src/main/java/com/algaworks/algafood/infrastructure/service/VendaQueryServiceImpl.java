package com.algaworks.algafood.infrastructure.service;

import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.model.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaQueryServiceImpl implements VendaQueryService {
    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {
        return null;
    }
}
