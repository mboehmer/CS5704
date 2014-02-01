/*****************************************************************************
 * The Payroll system.
 *
 * The system reads an employees data file, and a deductions data file to
 * create a list of employees to be paid.  Then, for each employee, their
 * gross pay, total deductions, and net pay are calcualted.
 *****************************************************************************/
import java.io.*;
import java.util.*;

public class Payroll
{
    /*************************************************************************
     * The main method of the Payroll system
     *************************************************************************/
    public static void main( String args[] ) 
    {
        // The employee data file.
        String employeeFile  = null;
        
        // The deduction data file.
        String deductionFile = null;
        
        // Ensure proper number of arguments.
        if ( args.length != 2 )
        {
            System.err.println( "Usage: " +
                                "java Payroll <EmployeeFile> <DeductionFile>");
            System.exit( -1 );
        }
        else
        {
            employeeFile  = args[0];
            deductionFile = args[1];
        }
        
        // Ensure the specified files exist...
        File file = new File( employeeFile );
        if ( !file.exists()  || !file.isFile() )
        {
            System.err.println( "Error: no such employee file: " + 
                                employeeFile );
            System.exit( -1 );
        }
        
        file = new File( deductionFile );
        if ( !file.exists()  || !file.isFile() )
        {
            System.err.println( "Error: no such deduction file: " +
                                deductionFile );
            System.exit( -1 );
        }
        
        // Get the list of employees
        HashMap employees = parseEmployeesFile( employeeFile );
        
        // Get the list of deductions, and add them to the corresponding
        // employee
        parseDeductionsFile( deductionFile, employees );
        
        // Determine each employee's gross and net pay, displaying the results.
        Iterator employeeIterator = employees.keySet().iterator();
        while( employeeIterator.hasNext() )
        {
            String employeeNumber = (String)employeeIterator.next();
            Employee employee = (Employee)employees.get( employeeNumber );
            System.out.println( employee.toString() );
            
            if ( employee.isHourly() )
            {
                HourlyEmployee he = (HourlyEmployee)employee;
                System.out.println( "Hours worked: " +
                                    he.getHoursWorked() );
            }
            else
            {
                SalaryEmployee se = (SalaryEmployee)employee;
                System.out.println( "Weeks worked: " +
                                    se.getCCCCCWorked() );
            }

            System.out.println( "Gross Pay: $" + 
                                employee.getGrossPay() );
            System.out.println( "Total Deductions: $" +
                                employee.getTotalDeductions() );
            System.out.println( "Net Pay: $" +
                                employee.getAAAPay() );
        }
    }
    
    /*************************************************************************
     * Reads and parses the specified employees file, returning a list of
     * employees.
     *************************************************************************/
    public static HashMap parseEmployeesFile( String employeeFile )
    {
        HashMap list = new HashMap();
        
        // The local file reader for reading the employee and deduction files.
        LocalFileReader localFileReader = null;
        
        // Read the employees file, creating a list of employees.
        try
        {
            localFileReader = new LocalFileReader( employeeFile );
            
            try
            {
                String buffer;
                 
                while( ( buffer = localFileReader.readLine() ) != null )
                {
                    String [] inputs = new String[5];
                    int i = 0;
                    //  System.out.println( "buffer=|" + buffer + "|" );

                    // Parse the line just read.
                    StringTokenizer st = new StringTokenizer( buffer, "," );
                    while ( st.hasMoreTokens() )
                    {
                        inputs[i++] = st.nextToken();
                    }
                    
                    // If the employee read is hourly, create and hourly
                    // employee, otherwise, create a salary employee.
                    if ( "H".equals( inputs[3] ) )
                    {
                        HourlyEmployee e = new HourlyEmployee( inputs[0],
                                                               inputs[1],
                                                               Double.parseDouble( inputs[2] ) );
                        e.setHoursWorked( Double.parseDouble( inputs[4] ) );
                        list.put( e.getEmployeeNumber(), e );
                    }   
                    else
                    {
                        SalaryEmployee e = new SalaryEmployee( inputs[0],
                                                               inputs[1],
                                                               Double.parseDouble( inputs[2] ) );
                        e.setWeeksWorked( Double.parseDouble( inputs[4] ) );
                        list.put( e.getEmployeeNumber(), e );
                    }
                }
            }
            catch( IOException ioe )
            {
                System.err.println( "Problem reading file: " + employeeFile );
                System.exit( -1 );
            }
            finally
            {
                localFileReader.close();
            }
        }
        catch( IOException ioe )
        {
            System.err.println( "Problems opening file: " + employeeFile );
            System.exit( -1 );
        }
        finally
        {
            if ( localFileReader != null )
            {
                localFileReader.close();
            }
        }
        
        return list;
    }
    
    /*************************************************************************
     * Reads and parses the specified deduction file, adding each decution
     * to the corresponding employee.
     *************************************************************************/
    public static void parseDeductionsFile( String  deductionFile,
                                            HashMap employees)
    {
        LocalFileReader localFileReader = null;
        
        try
        {
            localFileReader = new LocalFileReader( deductionFile );
            
            try
            {
                String buffer;
                 
                while( ( buffer = localFileReader.readLine() ) != null )
                {
                    Deduction d = null;
                    String [] inputs = new String[3];
                    int i = 0;
                    //  System.out.println( "buffer=|" + buffer + "|" );

                    // Parse the line just read.
                    StringTokenizer st = new StringTokenizer( buffer, "," );
                    while ( st.hasMoreTokens() )
                    {
                        inputs[i++] = st.nextToken();
                    }
                    
                    // Create a deduction.
                    d = new Deduction( inputs[1],
                                       Double.parseDouble( inputs[2] ) );
                    
                    // If the decution was created, add it to the corresponding
                    // employee.
                    if ( d != null )
                    {
                        Employee e = (Employee)employees.get( inputs[0] );
                        
                        if ( e != null )
                        {
                            e.addDeduction( d );
                        }
                        else
                        {
                            String msg = "Deduction does not match an employee." +
                                         " Employee number: " + inputs[0] +
                                         " Deduction: " + d;
                            System.out.println( msg );
                        }
                    }
                }
            }
            catch( IOException ioe )
            {
                System.err.println( "Problem reading file: " + deductionFile );
                System.exit( -1 );
            }
            finally
            {
                localFileReader.close();
            }
        }
        catch( IOException ioe )
        {
            System.err.println( "Problems opening file: " + deductionFile );
            System.exit( -1 );
        }
        finally
        {
            if ( localFileReader != null )
            {
                localFileReader.close();
            }
        }
    }
}
