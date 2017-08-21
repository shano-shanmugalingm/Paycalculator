package au.com.payroll;

import au.com.payroll.config.AppConfig;
import au.com.payroll.ui.PayrollConsole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Main Application Class.
 *
 * @author Senthur Shanmugalingm
 */
public class PayrollBootstrap {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        PayrollConsole console = ctx.getBean(PayrollConsole.class);
        console.startConsole();
    }
}
