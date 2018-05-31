
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Connectivity //create class Connectivity
{ 
		// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		
	    static final String DB_URL = "jdbc:mysql://localhost/database001";
 
	    //  Database credentials
	    static final String USER = "root" ;
	    static final String PASS = "";
	   
	    public static void main(String[] args) //main method
	    {
	    	Connection conn = null;//create object of Connection and define it null
	    	try //try block
	    	{

	    		Class.forName("com.mysql.jdbc.Driver");

	    		System.out.println("Connecting to a selected database...");
	    		conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        //print on console
	    		System.out.println("Connected database successfully...");	    	
                }
	    	catch(SQLException se) //catch block
	    	{
	    		//Handle errors for JDBC
	    		se.printStackTrace();
	    	}
	    	catch(Exception e) //catch block
	    	{

	    		e.printStackTrace();
	    	}
	    	finally  //finally block
	    	{

	    		try  //try block
	    		{
	    			if(conn!=null)
	    			conn.close(); 
	    		}
	    		catch(SQLException se)
	    		{
	    			se.printStackTrace();
	    		}//end finally try
	    	}//end try
	    	System.out.println("Goodbye!"); 
	    }//end main
}


