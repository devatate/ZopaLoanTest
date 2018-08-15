package com.devatate.zopaloan.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    public static final RoundingMode DEFAULT_ROUNDING_MODE;
    private BigDecimal amount;

    private Money(BigDecimal amount){
        this.amount = amount;
        this.amount = amount.setScale(2, DEFAULT_ROUNDING_MODE);
    }

    public static Money createMoney(BigDecimal amount){
        return new Money(amount);
    }

    public static Money createMoney(Double amount){
        return createMoney(new BigDecimal(amount));
    }

    public int hashCode() {
        return this.amount.hashCode();
    }

    public boolean equals(Object other) {
        return other != null && other.getClass().isAssignableFrom(this.getClass()) && ((Money)other).getAmount().compareTo(getAmount())==0;
    }

    static {
        DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;
    }

    private void setAmount(BigDecimal amount) {
        this.amount = amount.setScale(2);
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Money addMoney(Money other){
        return this.createMoney(this.amount.add(other.getAmount()));
    }

    public Money substract(Money subtractedMoney) {
        return this.createMoney(this.amount.subtract(subtractedMoney.getAmount()));
    }

    public Money multiply(Money multipliedMoney) {
        return this.createMoney(this.amount.multiply(multipliedMoney.getAmount()));
    }

    public Money multiply(BigDecimal number) {
        return this.createMoney(this.amount.multiply(number));
    }

    public boolean isGreaterThanZero() {
        return getAmount().compareTo(new BigDecimal(0))>0;
    }

    public boolean isGreaterThanOrEqualToZero() {
        return getAmount().compareTo(new BigDecimal(0))>=0;
    }

    @Override
    public String toString() {
        return "$"+amount;
    }
}
