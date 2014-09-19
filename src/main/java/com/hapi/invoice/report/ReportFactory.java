package com.hapi.invoice.report;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ReportFactory {

    public static final String REPORT_TEMPLATE = "/templates/invoice_template.jrxml";

    public OutputStream createReport(ReportData reportData) throws Exception {

        JasperReport report = loadReportTemplate();
        JasperPrint print = fillReportWithData(report, reportData);
        OutputStream output = exportToPdfAsStream(print);

        return output;
    }

    private JasperReport loadReportTemplate() throws JRException {
        InputStream input = this.getClass().getResourceAsStream(REPORT_TEMPLATE);
        JasperDesign design = JRXmlLoader.load(input);
        JasperReport report = JasperCompileManager.compileReport(design);
        return report;
    }

    private JasperPrint fillReportWithData(JasperReport report, ReportData reportData) throws JRException {
        JRBeanCollectionDataSource invoiceItems = new JRBeanCollectionDataSource(reportData.getItems());
        Map<String, Object> invoiceParams = extractReportParams(reportData);

        JasperPrint print = JasperFillManager.fillReport(report, invoiceParams, invoiceItems);

        return print;
    }

    private Map<String, Object> extractReportParams(ReportData reportData) {

        Map<String, Object> params = new HashMap<>();

        //TODO fill it
        params.put("invoiceNumber", reportData.getInvoiceNumber());

        return params;
    }

    private OutputStream exportToPdfAsStream(JasperPrint print) throws JRException {
        OutputStream output = new ByteArrayOutputStream();
        JRExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);

        exporter.exportReport();

        return output;
    }
}
