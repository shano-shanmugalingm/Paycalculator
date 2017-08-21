package au.com.payroll.commons;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
public class PayrollConstants {

    public static final int MONTHS_OF_YEAR = 12;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("dd MMMM yyyy");

}
