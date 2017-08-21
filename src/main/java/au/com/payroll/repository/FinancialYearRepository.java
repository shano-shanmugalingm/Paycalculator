package au.com.payroll.repository;

import au.com.payroll.domain.FinancialYear;
import au.com.payroll.domain.IncomeTaxChart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by senthurshanmugalingm on 15/06/2017.
 */
public interface FinancialYearRepository extends CrudRepository<FinancialYear, Long> {

    @Query("select c from FinancialYear c where c.incomeStart <= :income and c.incomeEnd >= :income")
    FinancialYear getFinancialYear(@Param("date") Date date);

}