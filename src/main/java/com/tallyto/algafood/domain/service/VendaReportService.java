package com.tallyto.algafood.domain.service;

import com.tallyto.algafood.domain.model.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset);
}
