package com.example.unicar.core.service.jasperreport;

import org.springframework.http.MediaType;

public enum Formato {

    PDF, XLSX;

    public String getExtensao() {
        return switch (this) {
            case PDF -> ".pdf";
            case XLSX -> ".xlsx";
        };
    }

    public MediaType getMediaType() {
        return switch (this) {
            case PDF -> MediaType.APPLICATION_PDF;
            case XLSX -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }

}
