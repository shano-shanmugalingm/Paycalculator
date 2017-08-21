package au.com.payroll.service

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import au.com.payroll.service.handler.PayCalculationHandler
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class PayrollCalculatorServiceImplSpec extends Specification {

    PayrollCalculatorService payrollCalculatorService

    def setup() {
        payrollCalculatorService = new PayrollCalculatorServiceImpl()
    }

    def "processPay, should throw Exception, when the EmployeePayDetail object is null"() {
        when :
        payrollCalculatorService.processPay(null)

        then :
        IllegalArgumentException e = thrown()
        e.message == 'Employee Pay Details cannot be null'
    }

    @Unroll
    def "processPay, should throw Exception(#expectedMessage), when #paramUnderTst is #testFor"() {
        given :
        String[] employeeParams = data
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)

        when :
        payrollCalculatorService.processPay(payDetail)

        then :
        IllegalArgumentException e = thrown()
        e.message == expectedMessage

        where :
        data                                                 | expectedMessage                                                                      | paramUnderTst   | testFor
        [null, 'LastName', '80000', '9.0', '5/2017']         | 'Employee\'s First Name cannot be null or Empty'                                     | 'First Name '   | 'Null'
        ['', 'LastName', '80000', '9.0', '5/2017']           | 'Employee\'s First Name cannot be null or Empty'                                     | 'First Name '   | 'Empty'
        ['FirstName', null, '80000', '9.0', '5/2017']        | 'Employee\'s Last Name cannot be null or Empty'                                      | 'Last Name'     | 'Null'
        ['FirstName', '', '80000', '9.0', '5/2017']          | 'Employee\'s Last Name cannot be null or Empty'                                      | 'Last Name'     | 'Empty'
        ['FirstName', 'LastName', '80000', '9.0', null]      | 'Employee\'s Pay period cannot be null or Empty and should follow month/year format' | 'Pay Period'    | 'Null'
        ['FirstName', 'LastName', '80000', '9.0', '']        | 'Employee\'s Pay period cannot be null or Empty and should follow month/year format' | 'Pay Period'    | 'Empty'
        ['FirstName', 'LastName', '80000', '9.0', '14/2015'] | 'Employee\'s Pay Period should be in month/year format'                              | 'Pay Period'    | 'in Invalid Format'
        ['FirstName', 'LastName', '-8000', '9.0', '12/2015'] | 'Employee\'s Annual Salary should be a positive value'                               | 'Annual Salary' | 'Not Positive Interger'
        ['FirstName', 'LastName', '8000', '-9.0', '12/2015'] | 'Employee\'s Super Rate should be a positive value'                                  | 'Super %'       | 'Not Positive Interger'
    }

    def "processPay, should return a valid Payslip, when valid Employee Details is passed"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']

        and :
        payrollCalculatorService.payCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return new PaySlip(firstName: payDetail.getFirstName(), lastName: payDetail.getLastName(), grossIncome: 1000, netIncome: 1000, incomeTax: 100, superAnnuation: 100)
            }
        }

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = payrollCalculatorService.processPay(payDetail)

        then :
        paySlip
        paySlip.firstName == payDetail.firstName
        paySlip.lastName == payDetail.lastName
    }

}
