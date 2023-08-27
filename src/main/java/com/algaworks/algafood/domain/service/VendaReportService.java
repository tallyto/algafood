package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
