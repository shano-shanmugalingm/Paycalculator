package au.com.payroll.ui.handler;

import org.apache.log4j.Logger;

/**
 * This class is responsible for Translating the Exception to User Readable format.
 * @author Senthur Shanmugalingm
 */
public class ExceptionHandlerImpl implements ExceptionHandler {

    final static Logger logger = Logger.getLogger(ExceptionHandlerImpl.class);

    @Override
    public String translate(Exception e) {
        String message;
        if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
        } else {
            logger.error("Something went wrong!", e);
            message = "Something went wrong.  Please contact Administrator";
        }
        return message;
    }

}
