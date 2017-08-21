package au.com.payroll.ui.handler

import spock.lang.Specification


/**
 * Created by senthurshanmugalingm on 29/05/2017.
 */
class ExceptionHandlerImplSpec extends Specification {

    ExceptionHandler exceptionHandler

    def setup() {
        exceptionHandler = new ExceptionHandlerImpl()
    }

    def "translate, should return error Message, when the Exception is IllegalArgumentException"() {
        expect :
        exceptionHandler.translate(new IllegalArgumentException('Test Exception')) == 'Test Exception'
    }

    def "translate, should return generic error Message, when the Exception is Unknown"() {
        expect :
        exceptionHandler.translate(new Exception('Unhandled Exception')) == 'Something went wrong.  Please contact Administrator'
    }


}