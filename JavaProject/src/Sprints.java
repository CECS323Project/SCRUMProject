/**
 * This class handles all of the sprint database options
 *  -Show all
 *  -Show previous sprints from a certain date
 *  -Create a new sprint
 *  -Create a new story
 *  -Show sprints within a range
 *  -Modify a sprints status
 *  -Modify a story as well as its status
 * @author Russell Tan
 */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Sprints
{
    //Intantiate the connection class
	private JDBCConnections jdbcConn = new JDBCConnections();
	public void showAll()
	{
		System.out.println("Here is a list of all sprints:\n");
		this.getAllSprints();
	}

	/**
	 * Sql query that returns and displays all of the contents stored in the sprints table joined with the teams table
	 */
	public void getAllSprints() 
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams;");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n----------------------------------------------------------------");
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
				
				System.out.format("%4d %18s %14s %15s %13s\n",sprintID,date,teamName,projectName,SprintStatus);
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
	
	/**
	 * Shows all of the sprints that are either in progress or planned as completed sprints are not allowed to be added to
	 * @param i This is a dummy variable to indicate that you want to display only planned and in progress sprints
	 */
	public void showAll(int i) 
	{
		System.out.println("Here is a list of all sprints:\n");
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where SprintStatus != 'Completed';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n----------------------------------------------------------------");
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
				
				System.out.format("%4d %18s %14s %15s %13s\n",sprintID,date,teamName,projectName,SprintStatus);
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
	
	/**
	 * Sql query to select sprints that are prior to the entered date
	 * @param inDate Date to act as the upper limit of the select statement
	 */
	public void showPast(String inDate) 
	{
		try
		{		
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where Date < '"+java.sql.Date.valueOf(inDate)+"';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n----------------------------------------------------------------");
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
				
				System.out.format("%4d %18s %14s %15s %13s\n",sprintID,date,teamName,projectName,SprintStatus);
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

	/**
	 * Sql query to select sprints that are prior to the second entered date and proceeding the first entered date 
	 * @param x
	 * @param y
	 */
	public void showRange(String x, String y) 
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select SprintID,Date,TeamName,ProjectName,SprintStatus from Sprints natural join Teams where Date > '"+java.sql.Date.valueOf(x)+"' and Date < '"+java.sql.Date.valueOf(y)+"';");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n----------------------------------------------------------------");
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
				
				System.out.format("%4d %18s %14s %15s %13s\n",sprintID,date,teamName,projectName,SprintStatus);
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
	
	/**
	 * Inserts a story based on the user input gathered from joptionpane
	 * @param Sprint The sprint id to add the story to
	 * @param Employee The employee id to assign the story to
	 * @param Goal The employee's planned goal
	 * @param Benefit The benefit if the goal is achieved
	 * @param status The current status of the story
	 */
	public void createStory(int Sprint,int Employee,String Goal, String Benefit,String status) 
	{
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

	/**
	 * Modifies a story based on the user input gathered by joptionpane
	 * @param sprintID The sprint to reference
	 * @param backlogID The story to reference
	 * @param employee The new employee id to reference
	 * @param goal The new goal
	 * @param benefit The new benefit
	 * @param status The updated status of the story
	 */
	public void modifyStory(int sprintID, int backlogID,int employee,String goal, String benefit, String status) 
	{
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
	
	/**
	 * Sql query to select and display all the stories pertaining to a sprint
	 * @param Sprint The sprint id to reference
	 */
	public void getAllBacklogs(int Sprint)
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select BacklogID,concat(lName,', ',fName) AS 'Last, First',Role,Goal,Benefit,StoryStatus from Backlogs natural join Roles natural join Members where sprintID = "+Sprint+";");
			
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
			jdbcConn.getStatment().close();
		}
		catch(SQLException e)
		{
			
		}
	}

	/**
	 * Sql query to select and display all the teams to help user decide which team to modify or apply a story or sprint to
	 */
	public void showTeams() 
	{
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select TeamID,TeamName from Teams;");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			System.out.println("\n----------------------------------------------------------------");
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

	/**
     * Inserts a sprints based on the input gathered from joptionpane
     */
	public void createSprint(String date, int teamID,String ProjectName) 
	{
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

	/**
	 * Method to get a project name based on a particular team
	 * @param teamID team to reference
	 * @return The project assigned to that team
	 */
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
	
	/**
	 * Modify a sprint
	 * @param sprintID Sprint to reference
	 * @param sprintStatus Updated status
	 */
	public void modifySprint(int sprintID,String sprintStatus) 
	{
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
