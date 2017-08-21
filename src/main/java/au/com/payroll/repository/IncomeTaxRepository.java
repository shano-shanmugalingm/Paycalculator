package au.com.payroll.repository;

import au.com.payroll.domain.FinancialYear;
import au.com.payroll.domain.IncomeTaxChart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Income Tax Table Querying CRUD Repository
 *
 * @author Senthur Shanmugalingm.
 */
public interface IncomeTaxRepository extends CrudRepository<IncomeTaxChart, Long> {

    /**
     * Obtains the Income Tax Chart for the provided Annual Salary
     * @param income
     * @return {@link IncomeTaxChart}
     *
     * */
    @Query("select c from IncomeTaxChart c where c.incomeStart <= :income and c.incomeEnd >= :income")
    IncomeTaxChart findByTaxBracket(@Param("income") Long income);

}
