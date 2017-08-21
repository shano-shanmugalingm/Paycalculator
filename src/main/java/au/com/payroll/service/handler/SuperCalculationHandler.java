package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * This Class is responsible for Calculating the Super Annuation
 *
 * @author Senthur Shanmugalingm
 */
public class SuperCalculationHandler implements PayCalculationHandler {

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {
        paySlip.setSuperAnnuation(Math.round((paySlip.getGrossIncome() * payDetail.getSuperRate()) / 100));
        return paySlip;
    }
}
