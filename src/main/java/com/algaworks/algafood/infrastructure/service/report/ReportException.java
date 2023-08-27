package com.algaworks.algafood.infrastructure.service.report;

public class ReportException extends RuntimeException {
    public ReportException() {
    }

    public ReportException(String message) {
        super(message);
    }

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }
}
