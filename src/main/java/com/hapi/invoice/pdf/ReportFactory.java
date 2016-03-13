package com.hapi.invoice.pdf;


import com.hapi.invoice.front.model.Invoice;
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

    private JasperReport loadReportTemplate() throws JRException {
        InputStream input = this.getClass().getResourceAsStream(REPORT_TEMPLATE);
        JasperDesign design = JRXmlLoader.load(input);
        JasperReport report = JasperCompileManager.compileReport(design);
        return report;
    }

    public byte[] createReport(Invoice invoice) throws Exception {

        JasperPrint print = fillReportWithData(this.reportTemplate, invoice);
        byte[] output = exportToPdfAsStream(print);
        return output;
    }

    private JasperPrint fillReportWithData(JasperReport reportTemplate, Invoice invoice) throws JRException {
        JRBeanCollectionDataSource invoiceItems = new JRBeanCollectionDataSource(invoice.getItems());

        Map<String, Object> reportParams = new HashMap<>();
        reportParams.put("items", invoiceItems);
        reportParams.putAll(createInvoiceParams(invoice));

        JasperPrint print = JasperFillManager.fillReport(reportTemplate, reportParams, invoiceItems);

        return print;
    }

    private Map<String, Object> createInvoiceParams(Invoice invoice) {

        Map<String, Object> params = new HashMap<>();

        params.put("invoiceNumber", invoice.getInvoiceNumber());
        params.put("sellDate", invoice.getSellDate());
        params.put("issueDate", invoice.getIssueDate());

        params.put("sellerInfo", invoice.getSellerInfo());
        params.put("buyerInfo", invoice.getBuyerInfo());

        params.put("totalNetValue", invoice.getTotalNetValue());
        params.put("totalVatValue", invoice.getTotalVatValue());
        params.put("totalGrossValue", invoice.getTotalGrossValue());
        params.put("totalGrossValueText", invoice.getTotalGrossValueText());

        params.put("paymentPeriod", invoice.getPaymentPeriod());
        params.put("paymentForm", invoice.getPaymentForm());
        params.put("issuerName", invoice.getIssuerName());

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
