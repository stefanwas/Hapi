package com.hapi.invoice.pdf;

import java.util.ArrayList;
import java.util.List;

public class ReportData {

    private String invoiceNumber;
    private final List<ReportDataItem> items = new ArrayList<>();
    private String sellDate;
    private String issueDate;
    private String sellerInfo;
    private String buyerInfo;
    private String paymentPeriod;
    private String paymentForm;
    private String issuer;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<ReportDataItem> getItems() {
        return items;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }



}
