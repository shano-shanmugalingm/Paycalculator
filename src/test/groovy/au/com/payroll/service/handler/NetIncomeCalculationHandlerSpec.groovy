package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification


/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class NetIncomeCalculationHandlerSpec extends Specification {

    PayCalculationHandler netIncomeCalculationHandler

    def setup() {
        netIncomeCalculationHandler = new NetIncomeCalculationHandler()

        netIncomeCalculationHandler.superCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should call Super Calculator to calculate Super, when a valid Employee Pay Details are provided"() {
        when :
        String[] employeeParams = ['FirstName', 'LastName', '72000', '9.0', '12/2015']
        netIncomeCalculationHandler.calculate(new EmployeePayDetail(employeeParams), new PaySlip(grossIncome: 6000, incomeTax: 1000))

        then :
        netIncomeCalculationHandler.superCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should calculate Net Income using Gross Income and Income Tax, when a valid Employee Pay Details are provided"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '72000', '9.0', '12/2015']

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = netIncomeCalculationHandler.calculate(payDetail, new PaySlip(grossIncome: 6000, incomeTax: 1000))

        then :
        paySlip.netIncome == 5000
    }
}