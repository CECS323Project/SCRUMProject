
public class Members 
{

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
	
	private void getEmployees() 
	{
		//sql all
	}

	private void getStakeholders() 
	{
		//sql all
	}

	public void showAssignedEmployees() 
	{
		//sql all emps where proj = ?
	}

	public void showScrumTeam() 
	{
		//sql all emps where team = ?
	}

	public void addEmployee() 
	{
		//sql insert emp 
	}

	public void modifyEmployee() 
	{
		//sql update emp where name = ?
	}

	public void addStakeholder() 
	{
		//sql insert stakeholder
	}

	public void modifyStakeholder() 
	{
		//sql update stakeholder where name = ?
	}

}
