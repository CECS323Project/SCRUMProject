import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


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
		jdbcConn.setStatment(jdbcConn.getConnection());
		ResultSet results;
		try {
			results = jdbcConn.getStatment().executeQuery("select EmployeeID,concat(lName,', ',fName) AS 'Last, First',Role,TeamName from Employees natural join Teams natural join Roles;");
		
		ResultSetMetaData rsmd = results.getMetaData();
		int cols = rsmd.getColumnCount();
		
		for(int i = 1; i <= cols; i++)
		{
			System.out.print(rsmd.getColumnLabel(i)+"\t");
		}
		
		System.out.println("\n-----------------------------------------------------------");
		
		while(results.next())
		{
			int employeeID = results.getInt(1);
			String employeeName = results.getString(2);
			String Role = results.getString(3);
			String TeamName = results.getString(4);
	
			
			System.out.format("%8d %16s %13s %4s\n",employeeID,employeeName,Role,TeamName);
		}
		
		System.out.println("\n");
		results.close();
		rsmd = null;
		jdbcConn.getStatment().close();
		jdbcConn.getConnection().close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getStakeholders() 
	{
		//sql select * from stakeholders 
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
