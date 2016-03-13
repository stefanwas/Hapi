package com.hapi.invoice.pdf;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ReportFactory {

    public static final String REPORT_TEMPLATE = "/templates/invoice_template.jrxml";
    private JasperReport reportTemplate;

    public void init() throws JRException {
        this.reportTemplate = loadReportTemplate();
    }

    public byte[]  createReport(ReportData reportData) throws Exception {

        JasperPrint print = fillReportWithData(this.reportTemplate, reportData);
        byte[] output = exportToPdfAsStream(print);

        return output;
    }

    private JasperReport loadReportTemplate() throws JRException {
        InputStream input = this.getClass().getResourceAsStream(REPORT_TEMPLATE);
        JasperDesign design = JRXmlLoader.load(input);
        JasperReport report = JasperCompileManager.compileReport(design);
        return report;
    }

    private JasperPrint fillReportWithData(JasperReport jasperReport, ReportData reportData) throws JRException {
        JRBeanCollectionDataSource invoiceItems = new JRBeanCollectionDataSource(reportData.getItems());
        Map<String, Object> invoiceParams = extractReportParams(reportData);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, invoiceParams, invoiceItems);

        return print;
    }

    private Map<String, Object> extractReportParams(ReportData reportData) {

        Map<String, Object> params = new HashMap<>();

        //TODO fill it
        params.put("invoiceNumber", reportData.getInvoiceNumber());
        params.put("sellDate", reportData.getSellDate());
        params.put("issueDate", reportData.getIssueDate());
        params.put("sellerInfo", reportData.getSellerInfo());
        params.put("buyerInfo", reportData.getBuyerInfo());

        return params;
    }

    private byte[] exportToPdfAsStream(JasperPrint print) throws JRException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        JRExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);

        exporter.exportReport();

        return output.toByteArray();
    }
}
