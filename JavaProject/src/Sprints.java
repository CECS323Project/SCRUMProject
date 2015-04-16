
public class Sprints 
{
	public void showAll()
	{
		System.out.println("Here is a list of all sprints:\n");
		this.getAllSprints();
	}

	private void getAllSprints() 
	{
		//sql select * from sprints
		
	}

	public void showPast() 
	{
		//sql select * from sprints where date != today
	}

	public void showRange() 
	{
		//sql select * from sprints where x < date < y
	}

	public void createStory() 
	{
		//sql insert into Stories where sprintid = ?
	}

	public void modifyStory() 
	{
		//sql update stories where storyid = ? AND sprint id = ?
	}
}
