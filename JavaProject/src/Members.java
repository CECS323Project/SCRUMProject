import java.sql.*;

/**
 * 
 * @author anon
 * lName, fName TeamName, Role
 */

public class Members 
{
	private JDBCConnections jdbcConn = new JDBCConnections();
	
	public void showAll(String memberType) 
	{
		if(memberType.toLowerCase() == "employees")
		{
			System.out.println("Here is a list of all of the employees:\n");
			this.getEmployees();
		}
		else if(memberType.toLowerCase() == "stakeholders")
		{
			System.out.println("Here is a list of all the stakeholders:\n");
			this.getStakeholders();
		}
	}
	
	public void getEmployees() 
	{
		//sql select * from employees
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Employees;");
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			for (int i = 1; i <= numberCols; i++) {
				// print Column Names
				System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n----------------------------------------------");
			
			while (results.next()) {
				String EmployeeID = results.getString(1);
				String FName = results.getString(2);
				String LName = results.getString(3);
				String RoleID = results.getString(4);
				String TeamID = results.getString(5);

				System.out.format("%6s %16s %3s %8s %6s\n",EmployeeID,FName,LName,RoleID, TeamID);
			}
			
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void getStakeholders() 
	{
		//sql select * from stakeholders
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from StakeHolders;");
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			for (int i = 1; i <= numberCols; i++) {
				// print Column Names
				System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n----------------------------------------------");
			
			while (results.next()) {
				String StakeHolderID = results.getString(1);
				String FName = results.getString(2);
				String LName = results.getString(3);
				String RoleID = results.getString(4);

				System.out.format("%6s %16s %3s %8s %6s\n",StakeHolderID,FName,LName,RoleID);
			}
			
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void showAssignedEmployees() 
	{
		//Need to select a particular project by id
		//sql select * from employees where proj id = ?
	}

	public void showScrumTeam() 
	{
		//Need to be able to select a particular team by id
		//sql select * from employees where team id= ?
	}

	public void addEmployee() 
	{
		//sql insert into employees
	}

	public void modifyEmployee() 
	{
		//Need to be able to select a particular employee by id
		//sql update employee set () values() where employee id = ?
	}

	public void addStakeholder() 
	{
		//sql insert into stakeholders
	}

	public void modifyStakeholder() 
	{
		//Need to be able to select a particular stakeholder by id
		//sql update stakeholders set () values() where stakeholder id = ?
	}

}
