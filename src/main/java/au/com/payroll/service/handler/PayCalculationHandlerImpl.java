package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This Class is responsible for initiating the Pay calculations.
 * This class will create a pay slip object with initial details.
 *
 * @author Senthur Shanmugalingm
 */
public class PayCalculationHandlerImpl implements PayCalculationHandler {

    @Autowired
    PayCalculationHandler payPeriodCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        paySlip = new PaySlip();
        paySlip.setFirstName(payDetail.getFirstName());
        paySlip.setLastName(payDetail.getLastName());

        return payPeriodCalculationHandler.calculate(payDetail, paySlip);
    }
}
