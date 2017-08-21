package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class GrossIncomeCalculationHandlerSpec extends Specification {

    PayCalculationHandler grossIncomeCalculationHandler

    def setup() {
        grossIncomeCalculationHandler = new GrossIncomeCalculationHandler()

        grossIncomeCalculationHandler.incomeTaxCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should call Income Tax Calculator, when valid Employee Pay details are provided"() {
        when :
        String[] employeeParams = ['FirstName', 'LastName', '12000', '9.0', '12/2015']
        grossIncomeCalculationHandler.calculate(new EmployeePayDetail(employeeParams), new PaySlip())

        then :
        grossIncomeCalculationHandler.incomeTaxCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should calculate Monthly Gross Income and return a valid pay slip object, when valid Employee Pay Detail Annual Salary is provided"() {
        given :
        String annualSalary = '12000'
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = grossIncomeCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.grossIncome == 1000
    }

    @Unroll
    def "calculate, should calculate Monthly Gross Income and #testFor to whole number(#grossIncome), when Annual Salary of #annualSalary provided "() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = grossIncomeCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.grossIncome == grossIncome

        where :
        annualSalary | grossIncome | testFor
        '13000'      | 1083        | 'Round Down'
        '14000'      | 1167        | 'Round Up'
    }
}
