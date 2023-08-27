package com.algaworks.algafood.infrastructure.service.report;

import com.algaworks.algafood.domain.model.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaReportService implements VendaReportService {
    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filter, String timeOffset) {
        return new byte[0];
    }
}
