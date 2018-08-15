package com.devatate.zopaloan.entity;

import java.math.BigDecimal;

public class Lender {
    private String name;
    private BigDecimal rate;
    private Money available;
    private Money transientAvailableForRequest;


    public Lender(String name, BigDecimal rate, Money available, Money transientAvailableForRequest) {
        this.name = name;
        this.rate = rate;
        this.available = available;
        this.transientAvailableForRequest = transientAvailableForRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Money getAvailable() {
        return available;
    }

    public void setAvailable(Money available) {
        this.available = available;
    }

    public Money getTransientAvailableForRequest() {
        return transientAvailableForRequest;
    }

    public void setTransientAvailableForRequest(Money transientAvailableForRequest) {
        this.transientAvailableForRequest = transientAvailableForRequest;
    }
}
