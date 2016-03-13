package com.hapi.invoice.front.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @XmlElement
    private String invoiceNumber;
    @XmlElement
    private final List<Item> items = new ArrayList<>();
    @XmlElement
    private String sellDate;
    @XmlElement
    private String issueDate;
    @XmlElement
    private String sellerInfo;
    @XmlElement
    private String buyerInfo;
    @XmlElement
    private String paymentPeriod;
    @XmlElement
    private String paymentForm;
    @XmlElement
    private String totalNetValue;
    @XmlElement
    private String totalVatValue;
    @XmlElement
    private String totalGrossValue;
    @XmlElement
    private String totalGrossValueText;
    @XmlElement
    private String issuerName;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public List<Item> getItems() {
        return items;
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

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getTotalNetValue() {
        return totalNetValue;
    }

    public void setTotalNetValue(String totalNetValue) {
        this.totalNetValue = totalNetValue;
    }

    public String getTotalVatValue() {
        return totalVatValue;
    }

    public void setTotalVatValue(String totalVatValue) {
        this.totalVatValue = totalVatValue;
    }

    public String getTotalGrossValue() {
        return totalGrossValue;
    }

    public void setTotalGrossValue(String totalGrossValue) {
        this.totalGrossValue = totalGrossValue;
    }

    public String getTotalGrossValueText() {
        return totalGrossValueText;
    }

    public void setTotalGrossValueText(String totalGrossValueText) {
        this.totalGrossValueText = totalGrossValueText;
    }
}
