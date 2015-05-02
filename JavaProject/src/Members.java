import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Members 
{
	private Scanner scan = new Scanner(System.in);
	private final JDialog diag = new JDialog();
	private JDBCConnections jdbcConn = new JDBCConnections();
	
	Members()
	{
		diag.setAlwaysOnTop(true);
	}
	public void showAll(String memberType) 
	{
		if(memberType.toLowerCase() == "employees") {
			System.out.println("Here is a list of all of the employees:\n");
			this.getEmployees();
		}
		else if(memberType.toLowerCase() == "stakeholders") {
			System.out.println("Here is a list of all the stakeholders:\n");
			this.getStakeholders();
		}
	}
	
	public void getEmployees() 
	{
		//SQL: select * from Employees
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results;
			try {
				results = jdbcConn.getStatment().executeQuery("select EmployeeID AS 'Member ID',concat(lName,', ',fName) AS 'Last, First',Role,TeamName from Members natural join Teams natural join Roles;");
			
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
		//SQL: select * from StakeHolders
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Members where RoleID = 3 ;");
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			//print column names
			for (int i = 1; i <= numberCols; i++) {
				if (i == 2)
					System.out.print(rsmd.getColumnLabel(3) + "\t");
				else if (i == 3)
					System.out.print(rsmd.getColumnLabel(2) + "\t");
				else
					System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n--------------------------------------");
			
			while (results.next()) {
				String StakeHolderID = results.getString(1);
				String FName = results.getString(2);
				String LName = results.getString(3);
				String RoleID = results.getString(4);

				System.out.format("%8s %8s %8s %8s\n",StakeHolderID,FName,LName,RoleID);
			}
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void showAssignedEmployees() 
	{
		//Need to select a particular project by id
		//sql select * from employees where proj id = ?
		
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select LName, FName, ProjectName from Members JOIN Teams ON Members.TeamID = Teams.TeamID;");		
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			//print column names
			for (int i = 1; i <= numberCols; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n--------------------------------------");
			
			while (results.next()) {
				String FName = results.getString(1);
				String LName = results.getString(2);
				String ProjectName = results.getString(3);

				System.out.format("%8s %8s %8s\n",FName,LName,ProjectName);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void showScrumTeam() 
	{
		//Need to be able to select a particular team by id
		//sql select * from employees where team id= ?
		
		try {
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select LName, FName, TeamName from Members JOIN Teams ON Members.TeamID = Teams.TeamID;");		
			ResultSetMetaData rsmd = results.getMetaData();
			
			int numberCols = rsmd.getColumnCount();
			
			//print column names
			for (int i = 1; i <= numberCols; i++) {
				System.out.print(rsmd.getColumnLabel(i) + "\t");
			}
			System.out.println("\n--------------------------------------");
			
			while (results.next()) {
				String FName = results.getString(1);
				String LName = results.getString(2);
				String ProjectName = results.getString(3);

				System.out.format("%8s %8s %8s\n",FName,LName,ProjectName);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
		}catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public void addEmployee() 
	{
		jdbcConn.setStatment(jdbcConn.getConnection());
		ResultSet results;
		//System.out.println("Enter members first name:");
		String fName = JOptionPane.showInputDialog(diag,"Enter members first name:");
		String lName = JOptionPane.showInputDialog(diag,"Enter members last name:");
		int role = Integer.parseInt(JOptionPane.showInputDialog(diag,"Enter role option:\n"
				+ "\t1. Employee"
				+ "\n\t2. Owner"
				+ "\n\t3. Stakeholder"));
		if(role == 1){
			try {
				results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
				ResultSetMetaData rsmd = results.getMetaData();
				int cols = rsmd.getColumnCount();
				
				for(int i = 1; i <= cols; i++)
				{
					System.out.print(rsmd.getColumnLabel(i)+"\t");
				}
				System.out.println("\n");
				while(results.next())
				{
					String TeamName = results.getString(1);
					int TeamID = results.getInt(2);
					System.out.format("%8s %13d\n",TeamName, TeamID);
				}
				System.out.println("\n");
				results.close();
				rsmd = null;
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
				System.out.println("\n-----------------------------------------------------------");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jdbcConn.setStatment(jdbcConn.getConnection());
			int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Enter team ID"));
			try {
				jdbcConn.getStatment().executeUpdate("INSERT INTO Members ("
						+ "FName,LName,RoleID,TeamID) VALUES ('"+fName+"','"+lName+"'"
								+ ",'"+role+"','"+teamID+"')");
				System.out.println("Employee added");
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		if(role == 2){
			try {
				results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
				ResultSetMetaData rsmd = results.getMetaData();
				int cols = rsmd.getColumnCount();
				
				for(int i = 1; i <= cols; i++)
				{
					System.out.print(rsmd.getColumnLabel(i)+"\t");
				}
				System.out.println("\n");
				while(results.next())
				{
					String TeamName = results.getString(1);
					int TeamID = results.getInt(2);
					System.out.format("%8s %13d\n",TeamName, TeamID);
				}
				System.out.println("\n");
				results.close();
				rsmd = null;
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
				System.out.println("\n-----------------------------------------------------------");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jdbcConn.setStatment(jdbcConn.getConnection());
			int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Enter team ID"));
			try {
				jdbcConn.getStatment().executeUpdate("INSERT INTO Members ("
						+ "FName,LName,RoleID,TeamID) VALUES ('"+fName+"','"+lName+"'"
								+ ",'"+role+"','"+teamID+"')");
				System.out.println("Employee added");
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(role == 3){
			try {
				results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
				ResultSetMetaData rsmd = results.getMetaData();
				int cols = rsmd.getColumnCount();
				
				for(int i = 1; i <= cols; i++)
				{
					System.out.print(rsmd.getColumnLabel(i)+"\t");
				}
				System.out.println("\n");
				while(results.next())
				{
					String TeamName = results.getString(1);
					int TeamID = results.getInt(2);
					System.out.format("%8s %13d\n",TeamName, TeamID);
				}
				System.out.println("\n");
				results.close();
				rsmd = null;
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
				System.out.println("\n-----------------------------------------------------------");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			jdbcConn.setStatment(jdbcConn.getConnection());
			int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Enter team ID"));
			try {
				jdbcConn.getStatment().executeUpdate("INSERT INTO Members ("
						+ "FName,LName,RoleID,TeamID) VALUES ('"+fName+"','"+lName+"'"
								+ ",'"+role+"','"+teamID+"')");
				System.out.println("Employee added");
				jdbcConn.getStatment().close();
				jdbcConn.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void modifyMembers() 
	{
		//Need to be able to select a particular stakeholder by id
		//sql update stakeholders set () values() where stakeholder id = ?
		Scanner in = new Scanner(System.in);
		
		getEmployees();
		int employeeID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Please choose a Memebers ID:"));
		
		while(true) {
			int modOption = Integer.parseInt(JOptionPane.showInputDialog(diag,"What would you like to modify?"
					+ "\n\t1. Name"
					+ "\n\t2. Role"
					+ "\n\t3.Team"));
			
			switch(modOption) {
			case 1:
				String newFirstName = JOptionPane.showInputDialog(diag,"First Name");
				String newLastName = JOptionPane.showInputDialog(diag,"Last Name");
				try {
					jdbcConn.setStatment(jdbcConn.getConnection());
					jdbcConn.getStatment().executeUpdate("update Members set FName = '" + newFirstName + "', LName = '" + newLastName + "' where Members.EmployeeID = " + employeeID + ";");
					jdbcConn.getStatment().close();
					System.out.println("Update Successful");
				}catch (SQLException sqlExcept) {
					sqlExcept.printStackTrace();
				}
				break;
			case 2:
				int newRoleID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Please choose a new role"
						+ "\n\t1. Employee"
						+ "\n\t2. Owner"
						+ "\n\t3. Stakeholder"));
				try {
					jdbcConn.setStatment(jdbcConn.getConnection());
					jdbcConn.getStatment().executeUpdate("update Members set RoleID = " + newRoleID + " where Members.EmployeeID = " + employeeID + ";");
					jdbcConn.getStatment().close();
					System.out.println("Update Successful");
				}catch (SQLException sqlExcept) {
					sqlExcept.printStackTrace();
				}
				break;
			case 3:
				try {
					jdbcConn.setStatment(jdbcConn.getConnection());
					ResultSet results = jdbcConn.getStatment().executeQuery("select TeamID, TeamName from Teams;");		
					ResultSetMetaData rsmd = results.getMetaData();
					
					int numberCols = rsmd.getColumnCount();
					
					//print column names
					for (int i = 1; i <= numberCols; i++) {
						System.out.print(rsmd.getColumnLabel(i) + "\t");
					}
					System.out.println("\n--------------------------------------");
					
					while (results.next()) {
						String teamID = results.getString(1);
						String teamName = results.getString(2);

						System.out.format("%8s %8s\n",teamID,teamName);
					}
					int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag,"Please choose new team:"));
					
					jdbcConn.getStatment().executeUpdate("update Teams inner join Members on Teams.TeamID = Members.TeamID set Members.TeamID = " + teamID + " where Members.EmployeeID = " + employeeID + ";");
					
					System.out.println("\n");
					results.close();
					rsmd = null;
					jdbcConn.getStatment().close();
					jdbcConn.getConnection().close();
					System.out.println("Update Successful");
				}catch (SQLException sqlExcept) {
					sqlExcept.printStackTrace();
				}
			}
			int decision = Integer.parseInt(JOptionPane.showInputDialog(diag,"More modifications?\n\t1. No\n\t2. Yes"));
			if (decision == 1)
				break;
		}
	}
}
