package au.com.payroll

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import au.com.payroll.service.PayrollCalculatorService
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Unroll

/**
 * Created by senthurshanmugalingm on 29/05/2017.
 */
class PayrollIntegrationSpec extends BaseIntegrationSpec {

    @Autowired
    PayrollCalculatorService payrollCalculatorService


    @Unroll
    def "input of (#firstName, #lastName, #annualSal, #superPerc, #payPeriod) to processPay, should process pay and return Monthly Pay (Net Salary: #netIncome)"() {
        given:
        String [] employeeParams = [firstName, lastName, annualSal, superPerc, payPeriod]

        when:
        PaySlip paySlip = payrollCalculatorService.processPay(new EmployeePayDetail(employeeParams))

        then :
        paySlip.toString() == getValidatableResult(displayPayPeriod, firstName, lastName, grossIncome, incomeTax, netIncome, superCont)

        where :
        firstName | lastName | annualSal | superPerc | payPeriod | displayPayPeriod                      | grossIncome | incomeTax | netIncome | superCont
        'David'   | 'Rudd'   | '60050'   | '9.00'    | '3/2017'  | '01 March 2017 - 31 March 2017'       | '5004'      | '922'     | '4082'    | '450'
        'Rayan'   | 'Chen'   | '120000'  | '10.00'   | '5/2017'  | '01 May 2017 - 31 May 2017'           | '10000'     | '2696'    | '7304'    | '1000'
        'George'  | 'Eliot'  | '12000'   | '10.00'   | '12/2016' | '01 December 2016 - 31 December 2016' | '1000'      | '0'       | '1000'    | '100'

    }

    String getValidatableResult(String payPeriod, String firstName, String lastName, String grossIncome, String incomeTax, String netIncome, String superCont) {
        return "PaySlip for the Period of : " + payPeriod + "\n" +
                "Name of Employee : " + firstName + ' ' + lastName + "\n" +
                "Gross Income : " + grossIncome + "\n" +
                "Income Tax : " + incomeTax + "\n" +
                "Net Income : " + netIncome + "\n" +
                "Super Contribution : " + superCont + "\n" ;
    }

}