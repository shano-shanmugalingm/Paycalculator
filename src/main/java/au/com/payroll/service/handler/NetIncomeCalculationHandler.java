package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Class that is responsible for Calculating Net Income
 *
 * @author Senthur Shanmugalingm
 */
public class NetIncomeCalculationHandler implements PayCalculationHandler {

    @Autowired
    private PayCalculationHandler superCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        paySlip.setNetIncome(paySlip.getGrossIncome() - paySlip.getIncomeTax());
        return superCalculationHandler.calculate(payDetail, paySlip);
    }
}
