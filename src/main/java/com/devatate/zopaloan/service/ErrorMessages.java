package com.devatate.zopaloan.service;

public class ErrorMessages {
    public static final String NOT_ENOUGH_LENDER_AMOUNT = "NOT ENOUGH LENDER AMOUNT";
    public static final String LESS_THAN_MINIMUM_AMOUNT = "LESS THAN MINUMUM AMOUNT, PLEASE REQUEST MORE THAN $"+ LenderServiceImpl.MIN_REQUEST_AMOUNT;
    public static final String HIGHER_THAN_MAXIMUM_AMOUNT = "MORE THAN MAXIMUM AMOUNT, PLEASE REQUEST LESS THAN $"+ LenderServiceImpl.MAX_REQUEST_AMOUNT;
    public static final String REQUEST_NOT_DIVIDER = "REQUEST NOT DIVIDER OF "+ LenderServiceImpl.INCREMENTAL_REQUEST_AMOUNT + " PLEASE REQUEST A LOAN OF ANY $"+ LenderServiceImpl.INCREMENTAL_REQUEST_AMOUNT;

}
