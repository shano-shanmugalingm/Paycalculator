package au.com.payroll.service.handler;

import au.com.payroll.commons.PayrollConstants;
import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Class that is responsible for performing Gross Income Calculation
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public class GrossIncomeCalculationHandler implements PayCalculationHandler {

    @Autowired
    private PayCalculationHandler incomeTaxCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        paySlip.setGrossIncome(Math.round(new Double(payDetail.getAnnualSalary()) / PayrollConstants.MONTHS_OF_YEAR));
        return incomeTaxCalculationHandler.calculate(payDetail, paySlip);
    }
}
