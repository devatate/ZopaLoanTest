package com.devatate.zopaloan.service;

import com.devatate.zopaloan.entity.FixedPriceResponseDTO;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;

import java.util.List;

public interface FixedAmountMonthlyLoanCalculator {
    public FixedPriceResponseDTO calculateMonthlyCompoundFixedAmount(List<Lender> lenderList, Money requestAmount);
}
