package com.devatate.zopaloan.service;


import com.devatate.zopaloan.entity.BusinessLogicException;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
public class TestLenderService {

    @Test
    public void testLessThanMinimum(){
        boolean failed = false;
        try {
            new LenderServiceImpl().findBestRateForBorrower(null, Money.createMoney(LenderServiceImpl.MIN_REQUEST_AMOUNT).substract(Money.createMoney(1d)));
        }catch (BusinessLogicException e){
            failed = true;
            Assert.assertEquals(ErrorMessages.LESS_THAN_MINIMUM_AMOUNT, e.getMessage());
        }
        Assert.assertTrue(failed);
    }

    @Test
    public void testHigherThanMinimum(){
        boolean failed = false;
        try {
            new LenderServiceImpl().findBestRateForBorrower(null, Money.createMoney(LenderServiceImpl.MAX_REQUEST_AMOUNT).addMoney(Money.createMoney(1d)));
        }catch (BusinessLogicException e){
            failed = true;
            Assert.assertEquals(ErrorMessages.HIGHER_THAN_MAXIMUM_AMOUNT, e.getMessage());
        }
        Assert.assertTrue(failed);
    }

    @Test
    public void testNotDivider(){
        boolean failed = false;
        try {
            new LenderServiceImpl().findBestRateForBorrower(new ArrayList<Lender>(), Money.createMoney(LenderServiceImpl.MAX_REQUEST_AMOUNT).substract(Money.createMoney(1d)));
        }catch (BusinessLogicException e){
            failed = true;
            Assert.assertEquals(ErrorMessages.REQUEST_NOT_DIVIDER, e.getMessage());

        }
        Assert.assertTrue(failed);
    }

    @Test
    public void testNotAvailableMoney() throws BusinessLogicException {
        boolean failed = false;
        ArrayList<Lender> lenders = new ArrayList<Lender>();
        createLender("ugur2", 0.075, 540d, lenders);
        createLender("ugur4", 0.082, 1680d, lenders);
        createLender("ugur0", 0.069, 480.03, lenders);
        createLender("ugur3", 0.082, 680d, lenders);
        createLender("ugur1", 0.071, 540d, lenders);
        try {
            new LenderServiceImpl().findBestRateForBorrower(lenders, Money.createMoney(15000d));

        }catch (BusinessLogicException e){
            failed = true;
            Assert.assertEquals(ErrorMessages.NOT_ENOUGH_LENDER_AMOUNT, e.getMessage());
        }
        Assert.assertTrue(failed);
    }

    @Test
    public void testFindBestLenders() throws BusinessLogicException {
        ArrayList<Lender> lenders = new ArrayList<Lender>();
        createLender("ugur2", 0.075, 540d, lenders);
        createLender("ugur4", 0.082, 1680d, lenders);
        createLender("ugur0", 0.069, 480.03, lenders);
        createLender("ugur3", 0.082, 680d, lenders);
        createLender("ugur1", 0.071, 540d, lenders);
        List<Lender> bestRateForBorrower = new LenderServiceImpl().findBestRateForBorrower(lenders, Money.createMoney(1000d));
        Assert.assertEquals(bestRateForBorrower.size(),2);
        Assert.assertEquals(bestRateForBorrower.get(0).getName(),"ugur0");
        Assert.assertEquals(bestRateForBorrower.get(1).getName(),"ugur1");
        Assert.assertEquals(bestRateForBorrower.get(0).getTransientAvailableForRequest(),Money.createMoney(480.03d));
        Assert.assertEquals(bestRateForBorrower.get(1).getTransientAvailableForRequest(),Money.createMoney(519.97));
    }

    private Lender createLender(String name, Double rate, Double available, List<Lender> lenderList){

        Lender lender = new Lender(name, new BigDecimal(rate), Money.createMoney(available)
                , Money.createMoney(available));
        lenderList.add(lender);
        return lender;
    }
}
