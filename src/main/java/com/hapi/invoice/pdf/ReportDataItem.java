package com.hapi.invoice.pdf;

public class ReportDataItem {

    private String itemName;
    private float amount;
    private float price;
    private float value;
    private float vatPercent;
    private float vatValue;
    private float totalValue;

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(float vatPercent) {
        this.vatPercent = vatPercent;
    }

    public float getVatValue() {
        return vatValue;
    }

    public void setVatValue(float vatValue) {
        this.vatValue = vatValue;
    }



}
