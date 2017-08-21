package au.com.payroll.config;

import au.com.payroll.service.PayrollCalculatorService;
import au.com.payroll.service.PayrollCalculatorServiceImpl;
import au.com.payroll.service.handler.*;
import au.com.payroll.ui.PayrollConsole;
import au.com.payroll.ui.PayrollConsoleImpl;
import au.com.payroll.ui.handler.ExceptionHandler;
import au.com.payroll.ui.handler.ExceptionHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
@Configuration
@Import(PersistenceConfig.class)
@EnableJpaRepositories("au.com.payroll.repository")
public class AppConfig {

    @Bean
    public PayrollConsole payrollConsole() {
        return new PayrollConsoleImpl();
    }

    @Bean
    public PayrollCalculatorService payrollCalculatorService() {
        return new PayrollCalculatorServiceImpl();
    }

    @Bean
    public PayCalculationHandler payCalculationHandler() {
        return new PayCalculationHandlerImpl();
    }

    @Bean
    public PayCalculationHandler payPeriodCalculationHandler() {
        return new PayPeriodCalculationHandler();
    }

    @Bean
    public  PayCalculationHandler grossIncomeCalculationHandler() {
        return new GrossIncomeCalculationHandler();
    }

    @Bean
    public PayCalculationHandler incomeTaxCalculationHandler() {
        return new IncomeTaxCalculationHandler();
    }

    @Bean
    public PayCalculationHandler netIncomeCalculationHandler() {
        return new NetIncomeCalculationHandler();
    }

    @Bean
    public PayCalculationHandler superCalculationHandler() {
        return new SuperCalculationHandler();
    }

    @Bean
    public ExceptionHandler exceptionHandler() {
        return new ExceptionHandlerImpl();
    }
}
