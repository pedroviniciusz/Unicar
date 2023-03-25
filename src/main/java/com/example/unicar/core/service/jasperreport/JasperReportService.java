package com.example.unicar.core.service.jasperreport;

import com.example.unicar.core.exception.JasperReportException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.example.unicar.core.constantes.Constantes.*;
import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

@Service
@RequiredArgsConstructor
public class JasperReportService {

    private final ExportReportService exportService;

    public InputStream relatorioEntradasSaidas(ParametrosJasperReport parametros) {
        final Map<String, Object> reportMap = new HashMap<>();
        addData(reportMap, parametros);

        return exportService.exportReport(ENTRADAS_SAIDAS, reportMap, parametros.getFormato());
    }
    private void addData(Map<String, Object> reportMap, ParametrosJasperReport parametros) {
        reportMap.put(DATA_INICIO, Date.valueOf(formatDate(parametros.getDataInicio())));
        reportMap.put(DATA_FIM, Date.valueOf(formatDate(parametros.getDataFim())));
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

