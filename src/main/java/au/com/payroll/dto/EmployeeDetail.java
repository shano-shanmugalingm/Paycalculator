package au.com.payroll.dto;

/**
 * Represents an Employees basic details
 *
 * @author Senthur Shanmugalingm
 */
public abstract class EmployeeDetail {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
