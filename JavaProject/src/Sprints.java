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
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints;");
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
				int backlogID = results.getInt(2);
				String date = results.getString(3);
				int teamID = results.getInt(4);
				
				System.out.format("%8d %16d %13s %4d",sprintID,backlogID,date,teamID);
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

	public void showPast(String inDate) 
	{
		//sql select * from sprints where date < today
		try
		{
			
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints where Date < '"+java.sql.Date.valueOf(inDate)+"'");
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
				int backlogID = results.getInt(2);
				String date = results.getString(3);
				int teamID = results.getInt(4);
				
				System.out.format("%8d %16d %13s %4d",sprintID,backlogID,date,teamID);
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
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints where Date > '"+java.sql.Date.valueOf(x)+"' and Date < '"+java.sql.Date.valueOf(y)+"';");
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
				int backlogID = results.getInt(2);
				String date = results.getString(3);
				int teamID = results.getInt(4);
				
				System.out.format("%8d %16d %13s %4d",sprintID,backlogID,date,teamID);
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

	public void createStory(int Sprint,int Employee,String Goal, String Benefit) 
	{
		//Need to be able to select a particular sprint from the menu
		//sql insert into Stories where sprintid = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("insert into Backlogs (`EmployeeID`,`Goal`,`Benefit`,`SprintID`) values ("+Employee+",'"+Goal+"','"+Benefit+"',"+Sprint+");");
			jdbcConn.getStatment().close();
			System.out.println("Insert Successful");			
		}
		catch(SQLException e)
		{
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void modifyStory(int sprintID, int backlogID,int employee,String goal, String benefit) 
	{
		//Need to select a particular sprint and a particular story
		//sql update stories where storyid = ? AND sprint id = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeUpdate("update Backlogs set EmployeeID = "+employee+", Goal = '"+goal+"', Benefit = '"+benefit+"' where SprintID = "+sprintID+" and BacklogID = "+backlogID+";");
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
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Backlogs where sprintID = "+Sprint+";");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
			{
				System.out.print(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				
				int backlogID = results.getInt(1);
				int employeeID = results.getInt(2);
				String Goal = results.getString(3);
				String Benefit = results.getString(4);
				int sprintID = results.getInt(5);
				
				System.out.format("%d %13d %5s %4s %8d\n",backlogID,employeeID,Goal,Benefit,sprintID);
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
	
}
