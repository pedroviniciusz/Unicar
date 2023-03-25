package com.example.unicar.web.controller;

import com.example.unicar.core.exception.JasperReportException;
import com.example.unicar.core.service.jasperreport.ParametrosJasperReport;
import com.example.unicar.core.service.jasperreport.JasperReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

import static com.example.unicar.core.constantes.Constantes.ENTRADAS_SAIDAS;
import static com.example.unicar.core.constantes.Constantes.RELATORIO_INEXISTENTE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/relatorios")
public class JasperReportRestController extends BaseRestController{

    private final JasperReportService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{report}")
    public ResponseEntity<Resource> getRelatorios(@PathVariable String report, ParametrosJasperReport parametrosJasperReport) {
        InputStream inputStream = switch (report){
            case ENTRADAS_SAIDAS -> service.relatorioEntradasSaidas(parametrosJasperReport);
            default -> throw new JasperReportException(RELATORIO_INEXISTENTE);
        };

        return writeResponseBody(inputStream, report, parametrosJasperReport.getFormato());

    }

}
