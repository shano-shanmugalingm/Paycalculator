package au.com.payroll.dto;

/**
 * Represents an Employee's Annual Salary (package) Details
 *
 * @author Senthur Shanmugalingm.
 */
public class EmployeePayDetail extends EmployeeDetail {

    private long annualSalary;
    private double superRate;
    private String payPeriod;

    public EmployeePayDetail(String[] employeeParams) {
        setFirstName(employeeParams[0]);
        setLastName(employeeParams[1]);

        annualSalary = new Long(employeeParams[2]);
        superRate = new Double(employeeParams[3]);
        payPeriod = employeeParams[4];
    }

    public long getAnnualSalary() {
        return annualSalary;
    }

    public double getSuperRate() {
        return superRate;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

}
