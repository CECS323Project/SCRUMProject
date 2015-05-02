import java.sql.*;

import javax.swing.JOptionPane;
//import java.util.Scanner;
public class JDBCConnections 
{
	private String dbUrl = "jdbc:mysql://cecs-db01.coe.csulb.edu:3306/cecs323h18";
	private Connection conn = null;
	private Statement stmnt = null;
	private String uName = "cecs323h18";
	private String pass = "aTubih";
	//private Scanner userIn = new Scanner(System.in);
	
	public JDBCConnections()
	{
		//uName = JOptionPane.showInputDialog("Please enter the uname");
		//pass = JOptionPane.showInputDialog("Please enter the password");
	}
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
