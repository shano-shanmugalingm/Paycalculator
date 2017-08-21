package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification
import spock.lang.Unroll


/**
 * Created by senthurshanmugalingm on 29/05/2017.
 */
class SuperCalculationHandlerSpec extends Specification {

    PayCalculationHandler superCalculationHandler

    def setup() {
        superCalculationHandler = new SuperCalculationHandler()
    }

    def "calculate, should calculate Super and return a valid Pay Slip Object"() {
        given:
        String[] employeeParams = ['FirstName', 'LastName', '72000', '9.0', '12/2015']
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)

        when :
        PaySlip paySlip = superCalculationHandler.calculate(payDetail, new PaySlip(grossIncome: 6000))

        then :
        paySlip.superAnnuation == 540
    }

    @Unroll
    def "calculate, should calculate Super and #testFor to whole number(#superAmount), when Gross Income of #grossIncome and Super Percentage (#superPercentage) are provided "() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '72', superPercentage, '12/2015']

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = superCalculationHandler.calculate(payDetail, new PaySlip(grossIncome: grossIncome))

        then :
        paySlip
        paySlip.superAnnuation == superAmount

        where :
        superPercentage | grossIncome | superAmount | testFor
        '9.6'           | 6200        | 595         |'Round Down'
        '9.6'           | 6464        | 621         | 'Round Up'
    }

}