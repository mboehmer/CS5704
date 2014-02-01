/*****************************************************************************
 * A class representing an hourly employee
 *****************************************************************************/
public class HourlyEmployee
    extends Employee
{
    // Hours worked this pay period.
    private double hoursWorked;

    /*************************************************************************
     * Constructs a hourly employee.
     *************************************************************************/
    public HourlyEmployee( String  name,
                           String  employeeNumber,
                           double  rateOfPay )
    {
        super( name, employeeNumber, rateOfPay );
        this.hoursWorked = 0;
    }

    /*************************************************************************
     * Returns hours worked this pay period.
     *************************************************************************/
    public double getHoursWorked()
    {
        return this.hoursWorked;
    }

    /*************************************************************************
     * Sets hours worked this pay period.
     *************************************************************************/
    public void setHoursWorked( double hoursWorked )
    {
        this.hoursWorked = hoursWorked;
    }

    /*************************************************************************
     * Calculates the employee's gross pay.
     *************************************************************************/
    public double getGrossPay()
    {
        this.grossPay = this.rateOfPay * this.hoursWorked;
        return this.grossPay;
    }

    /*************************************************************************
     * Returns true.
     *************************************************************************/
    public boolean isHourly()
    {
        return true;
    }
}
