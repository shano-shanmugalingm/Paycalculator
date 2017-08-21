package au.com.payroll.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents the Tax Table
 * @author Senthur Shanmugalingm
 */
@Entity
public class IncomeTaxChart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long incomeStart;
    private Long incomeEnd;
    private Long taxableAmount;
    private Double taxUnitRate;
    private Double taxableThreshold;


    private FinancialYear financialYear;

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    public IncomeTaxChart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncomeStart() {
        return incomeStart;
    }

    public void setIncomeStart(Long incomeStart) {
        this.incomeStart = incomeStart;
    }

    public Long getIncomeEnd() {
        return incomeEnd;
    }

    public void setIncomeEnd(Long incomeEnd) {
        this.incomeEnd = incomeEnd;
    }

    public Long getTaxableAmount() {
        return taxableAmount;
    }

    public void setTaxableAmount(Long taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public Double getTaxUnitRate() {
        return taxUnitRate;
    }

    public void setTaxUnitRate(Double taxUnitRate) {
        this.taxUnitRate = taxUnitRate;
    }

    public Double getTaxableThreshold() {
        return taxableThreshold;
    }

    public void setTaxableThreshold(Double taxableThreshold) {
        this.taxableThreshold = taxableThreshold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof IncomeTaxChart)) return false;

        IncomeTaxChart incomeTax = (IncomeTaxChart) o;

        return new EqualsBuilder()
                .append(id, incomeTax.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
