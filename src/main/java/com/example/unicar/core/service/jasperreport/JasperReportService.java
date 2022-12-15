package com.example.unicar.core.service.jasperreport;

import com.example.unicar.core.exception.JasperReportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

@Component
@RequiredArgsConstructor
public class JasperReportService {

    private final ExportReportService exportService;

    private static final String DATA_FIM = "data_fim";
    private static final String DATA_INICIO = "data_inicio";

    private static final String DATA_INVALIDA = "A data inserida é inválida";
    private static final String INFORMAR_DATA = "Por favor, informe a data";

    public static final String ENTRADAS_SAIDAS = "EntradasSaidas";

    public InputStream relatorioEntradasSaidas(ParametrosJasperReport parametros) {
        final Map<String, Object> parametrosReport = new HashMap<>();
        addData(parametrosReport, parametros);

        return exportService.exportReport(ENTRADAS_SAIDAS, parametrosReport, parametros.getFormato());
    }
    private void addData(Map<String, Object> parametrosReport, ParametrosJasperReport filtro) {
        parametrosReport.put(DATA_INICIO, Date.valueOf(formatDate(filtro.getDataInicio())));
        parametrosReport.put(DATA_FIM, Date.valueOf(formatDate(filtro.getDataFim())));
    }

    private String formatDate(String date) {
        if (isNullOrEmpty(date)) {
            throw new JasperReportException(INFORMAR_DATA);
        }

        try {
            return new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        } catch (ParseException e) {
            throw new JasperReportException(DATA_INVALIDA);
        }
    }
}

