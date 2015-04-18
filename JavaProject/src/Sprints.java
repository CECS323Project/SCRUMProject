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
			int k = 0;
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; k <= cols; i++)
			{
				System.out.println(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				k++;
				String sprintID = results.getString("SprintID");
				String backlogID = results.getString("BackLogID");
				String date = results.getString("Date").toString();
				String teamID = results.getString("TeamID");
				
				System.out.format("%8s,%8s,%8s,%8s",sprintID,backlogID,date,teamID);
			}
			
			System.out.println("\n");
			results.close();
			rsmd = null;
			jdbcConn.getStatment().close();
			
			
		}
		catch(SQLException e)
		{
			
		}
		//sql select * from sprints
		
	}

	public void showPast(String inDate) 
	{
		//sql select * from sprints where date < today
		try
		{
			int k = 0;
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints where Date < '"+inDate+"'");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; k <= cols; i++)
			{
				System.out.println(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				k++;
				String sprintID = results.getString("SprintID");
				String backlogID = results.getString("BackLogID");
				String date = results.getString("Date").toString();
				String teamID = results.getString("TeamID");
				
				System.out.format("%8s,%8s,%8s,%8s",sprintID,backlogID,date,teamID);
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
			int k = 0;
			jdbcConn.setStatment(jdbcConn.getConnection());
			ResultSet results = jdbcConn.getStatment().executeQuery("select * from Sprints where Date > '"+x+"' and Date < '"+y+"'");
			ResultSetMetaData rsmd = results.getMetaData();
			int cols = rsmd.getColumnCount();
			
			for(int i = 1; k <= cols; i++)
			{
				System.out.println(rsmd.getColumnLabel(i)+"\t");
			}
			
			System.out.println("\n-----------------------------------------------------------");
			
			while(results.next())
			{
				k++;
				String sprintID = results.getString("SprintID");
				String backlogID = results.getString("BackLogID");
				String date = results.getString("Date").toString();
				String teamID = results.getString("TeamID");
				
				System.out.format("%8s,%8s,%8s,%8s",sprintID,backlogID,date,teamID);
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

	public void createStory() 
	{
		//Need to be able to select a particular sprint from the menu
		//sql insert into Stories where sprintid = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeQuery("insert into Backlogs () values ()");
			jdbcConn.getStatment().close();
			System.out.println("Insert Successful");			
		}
		catch(SQLException e)
		{
			
		}
	}

	public void modifyStory() 
	{
		//Need to select a particular sprint and a particular story
		//sql update stories where storyid = ? AND sprint id = ?
		try
		{
			jdbcConn.setStatment(jdbcConn.getConnection());
			jdbcConn.getStatment().executeQuery("update Backlogs set() values()");
			jdbcConn.getStatment().close();
			System.out.println("Update Successful");			
		}
		catch(SQLException e)
		{
			
		}
	}
}
