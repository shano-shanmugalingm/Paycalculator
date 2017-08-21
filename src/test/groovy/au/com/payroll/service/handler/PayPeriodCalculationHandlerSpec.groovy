package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class PayPeriodCalculationHandlerSpec extends Specification {

    PayCalculationHandler payPeriodCalculationHandler

    def setup() {
        payPeriodCalculationHandler = new PayPeriodCalculationHandler()

        payPeriodCalculationHandler.grossIncomeCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should call Gross Income Calculator to calculate Gross Income, when Valid Employee Details are passed"() {
        when :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']
        payPeriodCalculationHandler.calculate(new EmployeePayDetail(employeeParams), new PaySlip())

        then :
        payPeriodCalculationHandler.grossIncomeCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should calculate the pay period and return a valid Pay Slip, when valid Employee Details are passed"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = payPeriodCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.payPeriod == '01 December 2015 - 31 December 2015'
    }

}
