package com.hapi.invoice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    @XmlElement
    private String name;
    @XmlElement
    private float amount;
    @XmlElement
    private float price;
    @XmlElement
    private float value;
    @XmlElement
    private float vatPercent;
    @XmlElement
    private float vatValue;
    @XmlElement
    private float totalValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(float totalValue) {
        this.totalValue = totalValue;
    }
}
