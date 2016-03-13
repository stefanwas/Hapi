package com.hapi.invoice.front.service;

import com.hapi.invoice.front.model.Invoice;
import com.hapi.invoice.front.model.Item;
import com.hapi.invoice.pdf.ReportData;
import com.hapi.invoice.pdf.ReportDataItem;
import com.hapi.invoice.pdf.ReportFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class InvoiceGenerator {

    private ReportFactory reportFactory;

    public byte[] generateInvoiceAsPdf(Invoice invoice) throws Exception {

        ReportData reportData = convertToReportData(invoice);
        byte[] reportContent = this.reportFactory.createReport(reportData);

        return reportContent;
    }

    private ReportData convertToReportData(Invoice invoice) {

        ReportData reportData = new ReportData();

        reportData.setInvoiceNumber(invoice.getInvoiceNumber());
        reportData.setSellDate(invoice.getSellDate());
        reportData.setIssueDate(invoice.getIssueDate());
        reportData.setSellerInfo(invoice.getSellerInfo());
        reportData.setBuyerInfo(invoice.getBuyerInfo());
        reportData.setPaymentPeriod(invoice.getPaymentPeriod());
        reportData.setPaymentForm(invoice.getPaymentForm());
        reportData.setIssuerName(invoice.getIssuerName());
        reportData.getItems().addAll(convertToReportItemData(invoice.getItems()));

        return reportData;
    }

    private Collection<ReportDataItem> convertToReportItemData(List<Item> items) {

        List<ReportDataItem> reportDataItems = new ArrayList<>();

        for (Item item : items) {
            ReportDataItem dataItem = new ReportDataItem();

            dataItem.setName(item.getName());
            //TODO convert other fields

            reportDataItems.add(dataItem);
        }

        return reportDataItems;
    }

    //TODO remove it
    public Invoice createFakeInvoice() {
        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber("FV/2014/09");
        invoice.setIssueDate("2014.09.15");
        invoice.setSellDate("2014.09.10");
        invoice.setPaymentForm("przelew");
        invoice.setPaymentPeriod("14 dni");
        invoice.setIssuerName("Stefan Telefan");

        for (int i = 0; i < 3; i++) {
            invoice.getItems().add(createFakeItem());
        }

        return invoice;
    }

    private Item createFakeItem() {
        Item item = new Item();

//        item.setName("Ziemniaki");
//        item.setAmount(100);
//        item.setPrice(4.5f);
//        item.setValue(450);
//        item.setVatPercent(23);
//        item.setVatValue(135);
//        item.setTotalValue(650.5f);

        return item;
    }

    public void setReportFactory(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }
}
