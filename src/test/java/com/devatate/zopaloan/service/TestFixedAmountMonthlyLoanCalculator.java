package com.devatate.zopaloan.service;

import com.devatate.zopaloan.Application;
import com.devatate.zopaloan.entity.FixedPriceResponseDTO;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class TestFixedAmountMonthlyLoanCalculator {

    @Test
    public void test() throws IOException{
        List<Lender> lenders = createLenderList();
        FixedPriceResponseDTO fixedPriceResponseDTO = new FixedAmountMonthlyLoanCalculatorImpl()
                .calculateMonthlyCompoundFixedAmount(lenders, Money.createMoney(1000d));
        Assert.assertEquals( createExpectedStr()
                , fixedPriceResponseDTO.toString());
    }

    private List<Lender> createLenderList() throws IOException {
        List<Lender> lenders = Application.readFromFile("src\\main\\resources\\market.csv");
        new LenderServiceImpl().sortLendersByRate(lenders);
        Lender first = lenders.get(0);
        Lender second = lenders.get(1);
        lenders = new ArrayList<Lender>();
        lenders.add(first);
        lenders.add(second);
        return lenders;
    }

    private String createExpectedStr() {
        return "Requested amount: $1000.00"+System.lineSeparator()+
                "Rate: 0.070%"+ System.lineSeparator()+
                "Monthly repayment: $30.88" + System.lineSeparator()+
                "Total repayment: $1111.68" + System.lineSeparator();
    }
}
