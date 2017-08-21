package au.com.payroll.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Date;

/**
 * Created by senthurshanmugalingm on 15/06/2017.
 */
@Entity
public class FinancialYear {

    @Id
    long id;

    Date startDate;
    Date endDate;


    Collection<IncomeTaxChart> finYearTaxCharts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<IncomeTaxChart> getFinYearTaxCharts() {
        return finYearTaxCharts;
    }

    public void setFinYearTaxCharts(Collection<IncomeTaxChart> finYearTaxCharts) {
        this.finYearTaxCharts = finYearTaxCharts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FinancialYear)) return false;

        FinancialYear that = (FinancialYear) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
