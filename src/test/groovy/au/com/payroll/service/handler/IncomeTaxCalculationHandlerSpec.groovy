package au.com.payroll.service.handler

import au.com.payroll.domain.IncomeTaxChart
import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import au.com.payroll.repository.IncomeTaxRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class IncomeTaxCalculationHandlerSpec extends Specification {

    PayCalculationHandler incomeTaxCalculationHandler

    IncomeTaxChart incomeTaxChart

    def setup() {
        incomeTaxCalculationHandler = new IncomeTaxCalculationHandler()

        incomeTaxCalculationHandler.netIncomeCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }

        incomeTaxCalculationHandler.incomeTaxRepository = Stub(IncomeTaxRepository) {
            findByTaxBracket(*_) >> {Long income ->
                return incomeTaxChart

            }
        }

    }

    def "calculate, should call Net Income Calculator, when valid Employee Pay Details are provided"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '83000', '9.0', '12/2015']
        incomeTaxChart = new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)

        when :
        incomeTaxCalculationHandler.calculate(new EmployeePayDetail(employeeParams), new PaySlip())

        then :
        incomeTaxCalculationHandler.netIncomeCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should call Income Tax Repository to obtain the Tax Chart to Calculate Income Tax, when valid Employee Pay Details are provided"() {
        when :
        String[] employeeParams = ['FirstName', 'LastName', '83000', '9.0', '12/2015']
        incomeTaxCalculationHandler.calculate(new EmployeePayDetail(employeeParams), new PaySlip())

        then :
        incomeTaxCalculationHandler.incomeTaxRepository = Mock(IncomeTaxRepository) {
            1 * findByTaxBracket(*_) >> {Long income ->
                return new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)
            }
        }
    }


    def "calculate, should calculate the Income Tax and return a valid pay slip object, when valid Employee Annual Salary provided"() {
        given :
        String annualSalary = '83000'
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']
        incomeTaxChart = new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = incomeTaxCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.incomeTax == 1555
    }

    @Unroll
    def "calculate, should calculate the Income Tax and #testFor to whole number(#incomeTax), when Annual Salary is #annualSalary"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']
        incomeTaxChart = new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = incomeTaxCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.incomeTax == incomeTax

        where :
        annualSalary | incomeTax | testFor
        '100000'     | 2079      | 'Round Up'
        '88630'      | 1728      | 'Round Down'
    }

    def "calculate, should return Pay Slip object with 0 Income Tax, when Tax table's Taxable Income and Tax Unit Rate is 0"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '120000', '10.0', '12/2015']
        incomeTaxChart = new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 0, taxUnitRate: 0, taxableThreshold: 0)

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = incomeTaxCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.incomeTax == 0
    }
}
