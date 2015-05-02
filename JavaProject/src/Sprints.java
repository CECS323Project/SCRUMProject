import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Sprints
{
	private JDBCConnections jdbcConn = new JDBCConnections();
	public void showAll()
	{
		System.out.println("Here is a list of all sprints:\n");
		this.getAllSprints();
	}

	public void getAllSprints() 
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams;");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				int sprintID = results.getInt(1);
				String date = results.getString(2);
				String teamName = results.getString(3);
				String projectName = results.getString(4);
				String SprintStatus = results.getString(5);
				
				System.out.format("%8d %13s %4s %4s %4s\n",sprintID,date,teamName,projectName,SprintStatus);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			jdbcConn.getConnection().close();
			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		//sql select * from sprints
		
	}
	
	public void showAll(int i) 
	{
		System.out.println("Here is a list of all sprints:\n");
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where SprintStatus != 'Completed';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int k = 1; k <= cols; k++)
			{
				System.out.print(rsmd.getColumnLabel(k)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				int sprintID = results.getInt(1);
				String date = results.getString(2);
				String teamName = results.getString(3);
				String projectName = results.getString(4);
				String SprintStatus = results.getString(5);
				
				System.out.format("%8d %13s %4s %4s %4s\n",sprintID,date,teamName,projectName,SprintStatus);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			jdbcConn.getConnection().close();
			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		//sql select * from sprints
	}

	public void insertSprint()
	{
		//Need to be able to select a particular sprint from the menu
		//sql insert into Stories where sprintid = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			//jdbcConn.getStatment().executeUpdate("insert into Sprints (,`Date`,`TeamID`) values ("+Date+",'"+TeamID+"');");
			jdbcConn.getStatment().close();
			System.out.println("Insert Successful");			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void showPast(String inDate) 
	{
		//sql select * from sprints where date < today
		try
		{
			
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where Date < '"+java.sql.Date.valueOf(inDate)+"';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				int sprintID = results.getInt(1);
				String date = results.getString(2);
				String teamName = results.getString(3);
				String projectName = results.getString(4);
				String SprintStatus = results.getString(5);
				
				System.out.format("%8d %13s %4s %4s %4s\n",sprintID,date,teamName,projectName,SprintStatus);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
			
		}
		catch(SQLException e)
		{
			
		}
	}

	public void showRange(String x, String y) 
	{
		//sql select * from sprints where x < date < y
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where Date > '"+java.sql.Date.valueOf(x)+"' and Date < '"+java.sql.Date.valueOf(y)+"';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				int sprintID = results.getInt(1);
				String date = results.getString(2);
				String teamName = results.getString(3);
				String projectName = results.getString(4);
				String SprintStatus = results.getString(5);
				
				System.out.format("%8d %13s %4s %4s %4s\n",sprintID,date,teamName,projectName,SprintStatus);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
			
		}
		catch(SQLException e)
		{
			
		}
	}

	public void createStory(int Sprint,int Employee,String Goal, String Benefit,String status) 
	{
		//Need to be able to select a particular sprint from the menu
		//sql insert into Stories where sprintid = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("insert into Backlogs (`EmployeeID`,`Goal`,`Benefit`,`SprintID`,`StoryStatus`) values ("+Employee+",'"+Goal+"','"+Benefit+"',"+Sprint+",'"+status+"');");
			jdbcConn.getStatment().close();
			System.out.println("Insert Successful");			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void modifyStory(int sprintID, int backlogID,int employee,String goal, String benefit, String status) 
	{
		//Need to select a particular sprint and a particular story
		//sql update stories where storyid = ? AND sprint id = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("update Backlogs set EmployeeID = "+employee+", Goal = '"+goal+"', Benefit = '"+benefit+"', StoryStatus = '"+status+"' where SprintID = "+sprintID+" and BacklogID = "+backlogID+";");
			jdbcConn.getStatment().close();
			System.out.println("Update Successful");			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void getAllBacklogs(int Sprint)
	{
		try
		{
			
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select BacklogID,concat(lName,', ',fName) AS 'Last, First',Role,Goal,Benefit,StoryStatus from Backlogs natural join Roles natural join Members where sprintID = "+Sprint+";");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			//for(int i = 1; i <= cols; i++)
			//{
			//	System.out.print(rsmd.getColumnLabel(i)+"\t");
			//}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				
				int backlogID = results.getInt(1);
				String employeeName = results.getString(2);
				String Role = results.getString(3);
				String Goal = results.getString(4);
				String Benefit = results.getString(5);
				String Status = results.getString(6);
				
				System.out.format("ID:%2d Employee: %10s\tStatus: %s \n\tAs a(n) %s \n\tI want to %s \n\tSo that %s\n\n",backlogID,employeeName,Status,Role,Goal,Benefit);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
			
		}
		catch(SQLException e)
		{
			
		}
	}

	public void showTeams() 
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select TeamID,TeamName from Teams;");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				int teamID = results.getInt(1);
				String teamName = results.getString(2);
				
				System.out.format("%8d %13s\n",teamID,teamName);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			jdbcConn.getConnection().close();
			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void createSprint(String date, int teamID,String ProjectName) 
	{
		//Need to be able to select a particular sprint from the menu
		//sql insert into Stories where sprintid = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("insert into Sprints (`Date`,`TeamID`,`SprintStatus`,`ProjectName`) values ('"+java.sql.Date.valueOf(date)+"','"+teamID+"','Planned','"+ProjectName+"');");
			jdbcConn.getStatment().close();
			System.out.println("Insert Successful");			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public String getProjectName(int teamID) {
		String ProjectName = "NULL";
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select ProjectName from Teams where TeamID = '"+teamID+"';");
			results.next();
			ProjectName = results.getString(1);
			return ProjectName;
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return ProjectName;
	}

	public void modifySprint(int sprintID,String sprintStatus) 
	{
		//Need to select a particular sprint and a particular story
		//sql update stories where storyid = ? AND sprint id = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("update Sprints set SprintStatus = '"+sprintStatus+"' where SprintID = "+sprintID+";");
			jdbcConn.getStatment().close();
			System.out.println("Update Successful");			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
