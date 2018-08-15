package com.devatate.zopaloan.service;

import com.devatate.zopaloan.entity.BusinessLogicException;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LenderServiceImpl implements  LenderService{

    public static final BigDecimal MIN_REQUEST_AMOUNT = new BigDecimal(1000L);
    public static final BigDecimal MAX_REQUEST_AMOUNT = new BigDecimal(15000L);
    public static final Integer INCREMENTAL_REQUEST_AMOUNT = 100;

    public List<Lender> findBestRateForBorrower(List<Lender> lenderList, Money requestedAmount) throws BusinessLogicException{
        assertIsRequestAmountWithinRange(requestedAmount);
        assertIsRequestedAmountWithinIncrementalRange(requestedAmount);
        sortLendersByRate(lenderList);
        return findBestLendersForIncomingRequest(lenderList, requestedAmount);
    }

    public void sortLendersByRate(List<Lender> lenderList) {
        Collections.sort(lenderList, new Comparator<Lender>() {
            public int compare(Lender o1, Lender o2) {
                return o1.getRate().compareTo(o2.getRate());
            }
        });
    }

    public List<Lender> findBestLendersForIncomingRequest(List<Lender> lenderListOrderedByRate, Money requestedAmount) throws BusinessLogicException {
        Money remainingAmount = requestedAmount;
        List<Lender> lendersForIncomingRequest = new ArrayList<Lender>();
        for(Lender lender: lenderListOrderedByRate){
            remainingAmount= remainingAmount.substract(lender.getAvailable());
            lendersForIncomingRequest.add(lender);
            if(!remainingAmount.isGreaterThanZero()){
                lender.setTransientAvailableForRequest(remainingAmount.addMoney(lender.getAvailable()));
                break;
            }
        }
        if(remainingAmount.isGreaterThanZero()){
            throw new BusinessLogicException(ErrorMessages.NOT_ENOUGH_LENDER_AMOUNT);
        }
        return lendersForIncomingRequest;
    }

    public void assertIsRequestAmountWithinRange(Money money) throws BusinessLogicException{
        if(!money.substract(Money.createMoney(MIN_REQUEST_AMOUNT)).isGreaterThanOrEqualToZero()){
            throw new BusinessLogicException(ErrorMessages.LESS_THAN_MINIMUM_AMOUNT);
        }else if (!Money.createMoney(MAX_REQUEST_AMOUNT).substract(money).isGreaterThanOrEqualToZero()){
            throw new BusinessLogicException(ErrorMessages.HIGHER_THAN_MAXIMUM_AMOUNT);
        }
    }

    public void assertIsRequestedAmountWithinIncrementalRange(Money money) throws BusinessLogicException{
        if(money.multiply(Money.createMoney(100d)).getAmount().intValue() % (INCREMENTAL_REQUEST_AMOUNT*100) != 0){
            throw new BusinessLogicException(ErrorMessages.REQUEST_NOT_DIVIDER);
        }
    }
}
