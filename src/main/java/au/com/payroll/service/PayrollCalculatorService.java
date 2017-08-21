package au.com.payroll.service;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * Implement this Interface to Process the Payroll.
 * The Payroll Processing should kick start from this Interface's Implementation.
 *
 * @author Senthur Shanmugalingm
 */
public interface PayrollCalculatorService {

    /**
     * This method will process the Pay and return a Valid Pay Slip Object
     *
     * @param payDetail {@link EmployeePayDetail}
     * @return {@link PaySlip}
     *
     * */
    PaySlip processPay(EmployeePayDetail payDetail);

}
