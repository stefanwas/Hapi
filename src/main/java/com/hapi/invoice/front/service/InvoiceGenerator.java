package com.hapi.invoice.front.service;

import com.hapi.invoice.front.model.Invoice;
import com.hapi.invoice.front.model.Item;
import com.hapi.invoice.pdf.ReportFactory;

public class InvoiceGenerator {

    private ReportFactory reportFactory;

    public byte[] generateInvoiceAsPdf(Invoice invoice) throws Exception {

        byte[] reportContent = this.reportFactory.createReport(invoice);

        return reportContent;
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
//        item.setGrossValue(650.5f);

        return item;
    }

    public void setReportFactory(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }
}
