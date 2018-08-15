package com.devatate.zopaloan.entity;

import java.math.BigDecimal;

public class FixedPriceResponseDTO {
    private Money requestedAmount;
    private BigDecimal rate;
    private Money monthlyRepayment;
    private Money totalRepayment;

    public FixedPriceResponseDTO(Money requestedAmount, BigDecimal rate, Money monthlyRepayment, Money totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public Money getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Money requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Money getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(Money monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public Money getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(Money totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Requested amount: "+getRequestedAmount()+System.lineSeparator());
        result.append("Rate: "+getRate()+"%"+System.lineSeparator());
        result.append("Monthly repayment: "+getMonthlyRepayment()+System.lineSeparator());
        result.append("Total repayment: "+getTotalRepayment()+System.lineSeparator());
        return result.toString();
    }
}
