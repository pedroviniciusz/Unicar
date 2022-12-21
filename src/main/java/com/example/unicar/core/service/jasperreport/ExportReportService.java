package com.example.unicar.core.service.jasperreport;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExportReportService {

    private final DataSource dataSource;

    public InputStream exportReport(String report, Map<String, Object> reportMap, Formato formato) {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/" + report + ".jrxml");
            JasperReport reportCompilado = JasperCompileManager.compileReport(inputStream);

            JasperPrint reportPreenchido = JasperFillManager.fillReport(reportCompilado, reportMap, dataSource.getConnection());

            if (formato.equals(Formato.XLSX)) {
                JRXlsxExporter exporter = new JRXlsxExporter();
                final ByteArrayOutputStream out = getByteArrayOutputStream(reportPreenchido, exporter);
                setConfiguration(exporter);
                exporter.exportReport();

                final byte[] bytes = out.toByteArray();
                return new ByteArrayInputStream(bytes);
            } else {
                byte[] bytes = JasperExportManager.exportReportToPdf(reportPreenchido);
                return new ByteArrayInputStream(bytes);
            }
        } catch (JRException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteArrayOutputStream getByteArrayOutputStream(JasperPrint reportPreenchido, JRXlsxExporter exporter) {
        exporter.setExporterInput(new SimpleExporterInput(reportPreenchido));
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        return out;
    }

    private void setConfiguration(JRXlsxExporter exporter) {
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
    }
}
