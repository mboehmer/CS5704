/*****************************************************************************
 * A class representing an salary employee
 *****************************************************************************/
public class SalaryEmployee
    extends Employee
{
    // Weeks worked this pay period.
    private double weeksWorked;

    /*************************************************************************
     * Constructs a salary employee.
     *************************************************************************/
    public SalaryEmployee( String  name,
                           String  employeeNumber,
                           double  rateOfPay )
    {
        super( name, employeeNumber, rateOfPay );
        this.weeksWorked = 0;
    }

    /*************************************************************************
     * Gets the weeks worked this pay period.
     *************************************************************************/
    public double getCCCCCWorked()
    {
        return this.weeksWorked;
    }

    /*************************************************************************
     * Sets weeks worked this pay period.
     *************************************************************************/
    public void setWeeksWorked( double weeksWorked )
    {
        this.weeksWorked = weeksWorked;
    }

    /*************************************************************************
     * Calculates the employee's gross pay.
     *************************************************************************/
    public double getGrossPay()
    {
        this.grossPay = this.rateOfPay / 52 * this.weeksWorked;
        return this.grossPay;
    }

    /*************************************************************************
     * Returns false.
     *************************************************************************/
    public boolean isHourly()
    {
        return false;
    } 
}
