package com.hapi.invoice;

import com.hapi.invoice.report.ReportData;
import com.hapi.invoice.report.ReportDataItem;
import com.hapi.invoice.report.ReportFactory;

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
        //TODO convert other fields
        reportData.getItems().addAll(convertToReportItemData(invoice.getItems()));

        return reportData;
    }

    private Collection<? extends ReportDataItem> convertToReportItemData(List<Item> items) {

        List<ReportDataItem> reportDataItems = new ArrayList<>();

        for (Item item : items) {
            ReportDataItem dataItem = new ReportDataItem();

            dataItem.setItemName(item.getName());
            //TODO convert other fields

            reportDataItems.add(dataItem);
        }

        return reportDataItems;
    }

    public Invoice createFakeInvoice() {
        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber("FV/2014/09");
        invoice.setIssueDate("2014.09.15");
        invoice.setSellDate("2014.09.10");
        invoice.setPaymentForm("przelew");
        invoice.setPaymentPeriod("14 dni");
        invoice.setIssuer("Wojciech Krzysztofik");

        for (int i = 0; i < 3; i++) {
            invoice.getItems().add(createFakeItem());
        }

        return invoice;
    }

    private Item createFakeItem() {
        Item item = new Item();

        item.setName("Ziemniaki");
        item.setAmount(100);
        item.setPrice(4.5f);
        item.setValue(450);
        item.setVatPercent(23);
        item.setVatValue(135);
        item.setTotalValue(650.5f);

        return item;
    }

    public void setReportFactory(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }
}
