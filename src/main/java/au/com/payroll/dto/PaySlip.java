package au.com.payroll.dto;

import java.math.BigDecimal;

/**
 * Represents an Employee's Monthly Pay Details (Salary Slip Details)
 *
 * @author Senthur Shanmugalingm
 */
public class PaySlip extends EmployeeDetail {

    private String payPeriod;
    private long grossIncome;
    private long incomeTax;
    private long netIncome;
    private long superAnnuation;

    public String getPayPeriod() {
        return payPeriod;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
    }

    public long getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(long grossIncome) {
        this.grossIncome = grossIncome;
    }

    public long getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(long incomeTax) {
        this.incomeTax = incomeTax;
    }

    public long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }

    public long getSuperAnnuation() {
        return superAnnuation;
    }

    public void setSuperAnnuation(long superAnnuation) {
        this.superAnnuation = superAnnuation;
    }

    @Override
    public String toString() {
        return "PaySlip for the Period of : " + payPeriod + "\n" +
                "Name of Employee : " + super.toString() + "\n" +
                "Gross Income : " + grossIncome + "\n" +
                "Income Tax : " + incomeTax + "\n" +
                "Net Income : " + netIncome + "\n" +
                "Super Contribution : " + superAnnuation + "\n" ;
    }
}
