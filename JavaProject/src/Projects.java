
public class Projects 
{

	public void showAll() 
	{
		System.out.println("Here is a list of all projects:\n");
		this.getAllProjects();
	}

	private void getAllProjects() 
	{
		//sql get * from projects
	}

	public void showPast() 
	{
		//sql get* from projects where date != today
	}

	public void showRange() 
	{
		//sql get * from Projects where x < date < y 
	}

	public void assignTeam() 
	{
		//sql update team set project () 
	}

	public void modifyProject() 
	{
		//sql update project where proj name = ?
	}

	public void createProject() 
	{
		//sql insert () into projects
	}
}
