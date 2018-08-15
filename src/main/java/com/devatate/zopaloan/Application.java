package com.devatate.zopaloan;

import com.devatate.zopaloan.entity.BusinessLogicException;
import com.devatate.zopaloan.entity.FixedPriceResponseDTO;
import com.devatate.zopaloan.entity.Lender;
import com.devatate.zopaloan.entity.Money;
import com.devatate.zopaloan.service.FixedAmountMonthlyLoanCalculator;
import com.devatate.zopaloan.service.FixedAmountMonthlyLoanCalculatorImpl;
import com.devatate.zopaloan.service.LenderService;
import com.devatate.zopaloan.service.LenderServiceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final Integer RATE_SCALE =3;

    public static void main(String[] args) throws IOException{
        if(args.length != 2){System.out.println("Argument Mismatch! It should be : filename.csv , Amount");return;}

        String filename = args[0];
        Money requestedAmount = Money.createMoney(Double.parseDouble(args[1]));

        List<Lender> lenderList = readFromFile(filename);
        LenderService service = new LenderServiceImpl();
        try {
            List<Lender> lenderListForRequest = service.findBestRateForBorrower(lenderList, requestedAmount);
            FixedAmountMonthlyLoanCalculator fixedAmountMonthlyLoanCalculator = new FixedAmountMonthlyLoanCalculatorImpl();
            FixedPriceResponseDTO fixedPriceResponseDTO = fixedAmountMonthlyLoanCalculator.calculateMonthlyCompoundFixedAmount(lenderListForRequest, requestedAmount);
            System.out.print(fixedPriceResponseDTO);

        } catch (BusinessLogicException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Lender> readFromFile(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ="";
        bufferedReader.readLine();
        List<Lender> lenderList = new ArrayList<Lender>();
        while((line = bufferedReader.readLine()) != null){
            String[] csvValues = line.split(",");
            BigDecimal rate = new BigDecimal(csvValues[1]);
            rate.setScale(RATE_SCALE );
            Money availableAmount = Money.createMoney(Double.parseDouble(csvValues[2]));
            lenderList.add(new Lender(csvValues[0],rate, availableAmount, availableAmount));
        }
        return lenderList;
    }
}
