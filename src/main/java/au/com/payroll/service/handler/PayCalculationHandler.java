package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * A Generic Interface to be implemented for calculating Employee's Monthly Pay Details
 *
 * @author  senthurshanmugalingm
 */
public interface PayCalculationHandler {

    /**
     * This method will calculate the Employee's Salary and Salary related details.
     *
     * @param payDetail {@link EmployeePayDetail}
     * @param paySlip {@link PaySlip}
     *
     * @return Calculated Pay Slip {@link PaySlip}
     *
     * */
    PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip);

}
