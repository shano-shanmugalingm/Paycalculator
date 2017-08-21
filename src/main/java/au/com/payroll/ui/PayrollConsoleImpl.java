package au.com.payroll.ui;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import au.com.payroll.service.PayrollCalculatorService;
import au.com.payroll.ui.handler.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * This Class is responsible for Starting the Console Application, listening to User Input and respond based on it.
 *
 * @author Senthur Shanmugalingm
 */
public class PayrollConsoleImpl implements PayrollConsole {

    private static Pattern expectedInputPattern = Pattern.compile("[A-Za-z]*,[A-Za-z]*,[0-9]*,[0-9]{1,99}.[0-9]{2},([1-9]|10|11|12)/20[0-9]{2}");
    private BufferedReader reader;

    @Autowired
    private PayrollCalculatorService payrollCalculatorService;

    @Autowired
    private ExceptionHandler exceptionHandler;

    public void startConsole() {
        printBanner();

        while (true) {
            System.out.println("Please Type in Pay Details in comma delimited form(FirstName,LastName,AnnualSalary,SuperPercentage,salaryMonthAndYear) to get Pay Slip or Type EXIT to close application.");
            String consoleInput = readLine();

            if (consoleInput.equalsIgnoreCase("EXIT")) {
                System.out.println("Have a Nice Day !!!");
                break;
            }

            if (expectedInputPattern.matcher(consoleInput).matches()) {
                System.out.println(generatePaySlip(consoleInput));
            } else {
                System.out.println("Invalid Input! Example of Valid Input: David,Rud,60050,9.00,3/2017");
            }
        }
    }

    private void printBanner() {
        System.out.println("|--------------------------------------------------|");
        System.out.println("|-----------  PAYROLL APPLICATION  ----------------|");
        System.out.println("|--------------------------------------------------|");
    }

    private String readLine() {
        String consoleInput = "";
        try {
            consoleInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consoleInput;
    }

    private String generatePaySlip(String consoleInput) {
        String result;
        try {
            PaySlip generatedSlip = payrollCalculatorService.processPay(new EmployeePayDetail(consoleInput.split(",")));
            result = generatedSlip.toString();
        } catch (Exception e) {
            result = exceptionHandler.translate(e);
        }
        return result;
    }

    @PostConstruct
    private void initReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @PreDestroy
    private void destroyReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
