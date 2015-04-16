import java.sql.*;
import java.util.Scanner;
public class JDBCConnections 
{
	private String dbUrl = "";
	private Connection conn = null;
	private Statement stmnt = null;
	private String uName = "";
	private String pass = "";
	private Scanner userIn = new Scanner(System.in);
	
	public Connection getConnection()
	{
		try
		{
			conn = DriverManager.getConnection(dbUrl,uName,pass);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		 return conn;
	}
	
	public Statement getStatment()
	{
		return stmnt;
	}

	public void setStatment(Connection stmntConn) 
	{
		try 
		{
			stmnt = stmntConn.createStatement();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
