package com.devatate.zopaloan.service;


import com.devatate.zopaloan.entity.BusinessLogicException;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;

import java.util.List;

public interface LenderService {
    public List<Lender> findBestRateForBorrower(List<Lender> lenderList, Money requestedAmount) throws BusinessLogicException;
    public void assertIsRequestAmountWithinRange(Money money) throws BusinessLogicException;
    public void assertIsRequestedAmountWithinIncrementalRange(Money money) throws BusinessLogicException;
}
