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
			results = jdbcConn.getStatment().executeQuery("select * from Employees;");
		
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
			String fName = results.getString(2);
			String lName = results.getString(3);
			int roleID = results.getInt(4);
			int teamID = results.getInt(5);
			
			System.out.format("%8d %16s %13s %4d %4d",employeeID,fName,lName,roleID,teamID);
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
