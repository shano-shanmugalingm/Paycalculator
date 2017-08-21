package au.com.payroll.service;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import au.com.payroll.service.handler.PayCalculationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

/**
 * The Class responsible for kick starting the Payroll Calculation Process.
 * This class will validate the Pay Details object to ensure the required parameters are set.
 *
 * @author Senthur Shanmugalingm
 */
public class PayrollCalculatorServiceImpl implements PayrollCalculatorService {

    private static Pattern payPeriodPattern = Pattern.compile("([1-9]|10|11|12)/20[0-9]{2}$");

    @Autowired
    private PayCalculationHandler payCalculationHandler;

    @Override
    public PaySlip processPay(EmployeePayDetail payDetail) {

        validate(payDetail);
        return payCalculationHandler.calculate(payDetail, null);
    }

    /**
     * Validates the {@link EmployeePayDetail} payDetail object.
     * @throws IllegalArgumentException when validation fails.
     * */
    private void validate(EmployeePayDetail payDetail) {
        Assert.notNull(payDetail, "Employee Pay Details cannot be null");

        Assert.hasText(payDetail.getFirstName(), "Employee's First Name cannot be null or Empty");
        Assert.hasText(payDetail.getLastName(), "Employee's Last Name cannot be null or Empty");
        Assert.hasText(payDetail.getPayPeriod(), "Employee's Pay period cannot be null or Empty and should follow month/year format");

        Assert.isTrue(payPeriodPattern.matcher(payDetail.getPayPeriod()).matches(), "Employee's Pay Period should be in month/year format");

        Assert.isTrue(payDetail.getAnnualSalary() > 0, "Employee's Annual Salary should be a positive value");
        Assert.isTrue(payDetail.getSuperRate() > 0, "Employee's Super Rate should be a positive value");
    }
}
