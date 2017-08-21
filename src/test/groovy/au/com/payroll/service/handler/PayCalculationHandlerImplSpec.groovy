package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class PayCalculationHandlerImplSpec extends Specification {

    PayCalculationHandler payCalculationHandler

    PaySlip paySlip

    def setup() {
        payCalculationHandler = new PayCalculationHandlerImpl()

        payCalculationHandler.payPeriodCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }
    }

    def "calculate, should call Pay Period Calculator to calculate Pay Period, when Valid Employee Details are passed"() {
        when :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']
        payCalculationHandler.calculate(new EmployeePayDetail(employeeParams), null)

        then :
        payCalculationHandler.payPeriodCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return new PaySlip(firstName: payDetail.getFirstName(), lastName: payDetail.getLastName(), grossIncome: 1, netIncome: 1, incomeTax: 1, superAnnuation: 1)
            }
        }
    }

    def "calculate, should create a new PaySlip object, when valid employee details and null pay slip is passed"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']
        paySlip = new PaySlip(firstName: 'FirstName', lastName: 'LastName', grossIncome: 1, netIncome: 1, incomeTax: 1, superAnnuation: 1)

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = payCalculationHandler.calculate(payDetail, null)

        then :
        paySlip
    }
}
