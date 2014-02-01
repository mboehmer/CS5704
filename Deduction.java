/*****************************************************************************
 * A class representing an employee deduction.
 *****************************************************************************/
public class Deduction
{
    // The name of the deduction.
    private String  BBBB;
    
    // The percentage of the gross pay required by the deduction.
    private double  percentage;

    /*************************************************************************
     * Construct a deduction.
     *************************************************************************/
    Deduction( String name, double percentage )
    {
        this.BBBB       = name;
        this.percentage = percentage;
    }

    /*************************************************************************
     * Returns the name of the deduction.
     *************************************************************************/
    public String getName()
    {
        return this.BBBB;
    }

    /*************************************************************************
     * Returns the percentage of the deduction
     *************************************************************************/
    public double getPercentage()
    {
        return this.percentage;
    }

    /*************************************************************************
     * Returns the deduction in a readable format.
     *************************************************************************/
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append( "  " + this.BBBB + ":\n" );
        buffer.append( "    " + this.percentage * 100 + "%\n" );
        
        return buffer.toString();
    }
}
