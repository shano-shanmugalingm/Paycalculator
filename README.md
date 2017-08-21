# Payroll Application

The Application uses Gradle 3.1 as the Build System.  However to run the application or to run any of the below commands we do not need to install Gradle.
Java 8 is used as the main Development Language.
All Unit and Integration Tests are written using Groovy and Spock Framework.

## Application Commands  
                                                 
    1. Unit Tests: ./gradlew clean test                                      
    2. Integration Tests: ./gradlew clean integrationTest                    
    3. Code Coverage: ./gradlew clean test integrationTest jacocoTestReport  
    4. Run Application: ./gradlew clean run                                  

The './gradlew' command will only run in Unix Systems.  In order to use in Windows please replace the './gradlew' with 'gradlew'.
However, please note that this application has not been tested on a Windows platform.

Assumption, when building this application I took is that, this will be a console based application, where user will input the Employee Annual Pay Details and the System will Calculate the PaySlip.
Further, this application will calculate the Pay Slip on a Month basis (No daily rate based calculation is supported).


Once the application is up and running, the User can input employee details in the following format:

    - First Name,Last Name,Annual Salary,Super Percentage,Pay Period

An Example of a valid format is as follows:

    - David,Rudd,60050,9.00,3/2017

Based on the above example:

    1. The input should be comma delimited.
    2. No spaces should exist between the commas.  If spaces are found the application will return 'Invalid Input! Example of Valid Input: David,Rud,60050,9,3/2017'.
    3. Super Percentage should be a Double value with two decimal places. Example: 9.00.  If a double value is not provided the application will return  'Invalid Input! Example of Valid Input: David,Rud,60050,9,3/2017'.
    4. Pay period should be month/year. Example: 3/2017.  If not provided in this format the application will return  'Invalid Input! Example of Valid Input: David,Rud,60050,9,3/2017'.

The calculated Pay Details will be displayed as follows:

    PaySlip for the Period of : 01 March 2017 - 31 March 2017
    Name of Employee : David Rudd
    Gross Income : 5000
    Income Tax : 921
    Net Income : 4079
    Super Contribution : 450

## A bit about the design

The main class is the PayrollBootStrap.  This will start up the the application.  Do necessary configurations (including adding seed data) and Call the PayrollConsole.
The PayrollConsole (PayrollConsoleImpl implementation) class will listen for User Input.  Once the user inputs the Pay Details, the PayrollConsole will construct the EmployeePayDetail Object and
pass it to the PayrollCalculationService.

PayrollCalculationService (PayrollCalculationServiceImpl implementation) will validate the EmployeePayDetail. If the validation is successful (no exception is thrown), this will call the PayrollCalculationHandler.

PayrollCalculationHandler is based on Chain of Responsibility Pattern.  This will traverse through the chain using different implementations of PayrollCalculationHandler, to calculate the Monthly Pay Slip.
The output of this chain is a PaySlip object containing the calculated pay details.

The PaySlip object is passed back to the PayrollConsole, which will transform the PaySlip Object to a String and display it to the User.