package au.com.payroll.service.handler;

import au.com.payroll.commons.PayrollConstants;
import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This Class is responsible for calculating the Payment Period.
 *
 * @author Senthur Shanmugalingm
 */
public class PayPeriodCalculationHandler implements PayCalculationHandler {

    @Autowired
    private PayCalculationHandler grossIncomeCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        String[] payPeriodDetails = payDetail.getPayPeriod().split("/");
        LocalDate payFrom = new LocalDate(new Integer(payPeriodDetails[1]), new Integer(payPeriodDetails[0]), 1);
        LocalDate payTo = new LocalDate(new Integer(payPeriodDetails[1]), new Integer(payPeriodDetails[0]), 1).plusMonths(1).withDayOfMonth(1).minusDays(1);

        paySlip.setPayPeriod(String.format("%s - %s", PayrollConstants.DATE_TIME_FORMAT.print(payFrom), PayrollConstants.DATE_TIME_FORMAT.print(payTo)));
        return grossIncomeCalculationHandler.calculate(payDetail, paySlip);
    }
}
