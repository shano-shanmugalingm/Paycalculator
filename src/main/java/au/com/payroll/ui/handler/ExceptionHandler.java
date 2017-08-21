package au.com.payroll.ui.handler;

/**
 * Implement this to handle any exceptions.
 *
 * @author Senthur Shanmugalingm
 */
public interface ExceptionHandler {

    /**
     * This method will traslate the exception to a User Readable String message
     * @param e {@link Exception}
     * @return {@link String}
     * */
    String translate(Exception e);

}
