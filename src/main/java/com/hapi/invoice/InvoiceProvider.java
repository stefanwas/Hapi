package com.hapi.invoice;

import com.hapi.invoice.report.ReportData;
import com.hapi.invoice.report.ReportDataItem;
import com.hapi.invoice.report.ReportFactory;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class InvoiceProvider {

    private ReportFactory reportFactory;

    public OutputStream generateInvoiceAsPdf(Invoice invoice) throws Exception {

        ReportData reportData = convertToReportData(invoice);
        OutputStream reportContent = this.reportFactory.createReport(reportData);

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

            dataItem.setName(item.getName());
            //TODO convert other fields

            reportDataItems.add(dataItem);
        }

        return reportDataItems;
    }

    public void setReportFactory(ReportFactory reportFactory) {
        this.reportFactory = reportFactory;
    }
}
