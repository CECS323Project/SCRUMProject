
public class Projects 
{

	public void showAll() 
	{
		System.out.println("Here is a list of all projects:\n");
		this.getAllProjects();
	}

	public void getAllProjects() 
	{
		//sql select * from projects
	}

	public void showPast() 
	{
		//sql get* from projects where date < today
	}

	public void showRange(String x, String y) 
	{
		//sql select * from Projects where x < date < y 
	}

	public void assignTeam() 
	{
		//Need to select a particular team by id
		//sql update team set project () where team id = ? 
	}

	public void modifyProject() 
	{
		//Need to select a particular project by id
		//sql update project set () where proj id = ?
	}

	public void createProject() 
	{
		//sql insert () into projects
	}
}
