package com.devatate.zopaloan.service;

import com.devatate.zopaloan.entity.FixedPriceResponseDTO;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;

import java.math.BigDecimal;
import java.util.List;

public class FixedAmountMonthlyLoanCalculatorImpl implements FixedAmountMonthlyLoanCalculator {

    private static BigDecimal NOF_MONTH_IN_A_YEAR = new BigDecimal(12);
    private static Integer  NOF_MONTH_TO_PAY = 36;
    private static Integer SCALE= 9;

    public FixedPriceResponseDTO calculateMonthlyCompoundFixedAmount(List<Lender> lenderList, Money requestAmount){
        Money monthlyResult = Money.createMoney(0d);
        BigDecimal sumRateMultipliedByMoney = new BigDecimal(0);
        for(Lender lender : lenderList){
            monthlyResult = monthlyResult.addMoney(calculateForMonthForOneLender(lender));
            sumRateMultipliedByMoney = sumRateMultipliedByMoney.add((lender.getRate().multiply(lender.getTransientAvailableForRequest().getAmount())));
        }
        sumRateMultipliedByMoney = sumRateMultipliedByMoney.divide(requestAmount.getAmount(), 3, BigDecimal.ROUND_HALF_UP);
        return new FixedPriceResponseDTO(requestAmount, sumRateMultipliedByMoney ,monthlyResult, monthlyResult.multiply(new BigDecimal(NOF_MONTH_TO_PAY)));
    }

    public Money calculateForMonthForOneLender(Lender lender){
        BigDecimal c = lender.getRate().divide(NOF_MONTH_IN_A_YEAR, SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal onePlusCPowerN = (c.add(new BigDecimal(1))).pow(NOF_MONTH_TO_PAY);
        BigDecimal amortizationNumber = c.multiply(onePlusCPowerN).divide((onePlusCPowerN.subtract(new BigDecimal(1))), SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal result  = lender.getTransientAvailableForRequest().getAmount().multiply(amortizationNumber);
        return Money.createMoney(result);
    }


}
