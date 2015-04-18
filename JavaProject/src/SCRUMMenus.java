import java.util.Scanner;

public class SCRUMMenus 
{
	private Scanner userIn = new Scanner(System.in);
	private Sprints sprints = new Sprints();
	private Projects projects = new Projects();
	private Members members = new Members();
	SCRUMMenus(){}
	
	public void mainMenuLoop()
	{
		int selection;
		boolean again = true;
		
		while(again)
		{
			this.displayMain();
			System.out.println("\n\tWhat would you like to do?");
			selection = userIn.nextInt();
			
			switch(selection)
			{
			case 1:
				this.sprintMenuLoop();
				break;
			case 2:
				this.projectsMenuLoop();
				break;
			case 3:
				this.membersMenuLoop();
				break;
			case 4:
				again = false;
				this.quit();
				break;
			default:
				again = false;
				this.quit();
				break;
			}
			
		}
	}
	
	public void quit() 
	{
		System.out.println("Have a SCRUMMY day!");		
	}

	public void sprintMenuLoop()
	{
		int selection;
		boolean again = true;
		
		while(again)
		{
			this.displaySprints();
			System.out.println("\n\tWhat would you like to do?");
			selection = userIn.nextInt();
			
			switch(selection)
			{
			case 1:
				sprints.showAll();
				break;
			case 2:
				sprints.showPast();
				break;
			case 3:
				sprints.showRange("04-04-1999","04-04-2015");
				break;
			case 4:
				sprints.createStory();
				break;
			case 5:
				sprints.modifyStory();
				break;
			case 6:
				again = false;
				break;
			default:
				again = false;
				break;
			}
		}
	}
	
	public void projectsMenuLoop()
	{
		int selection;
		boolean again = true;
		
		while(again)
		{
			this.displayProjects();
			System.out.println("\n\tWhat would you like to do?");
			selection = userIn.nextInt();
			
			switch(selection)
			{
			case 1:
				projects.showAll();
				break;
			case 2:
				projects.showPast();
				break;
			case 3:
				projects.showRange("04-04-1999","04-04-2015");
				break;
			case 4:
				projects.assignTeam();
				break;
			case 5:
				projects.modifyProject();
				break;
			case 6:
				projects.createProject();
				break;
			case 7:
				again = false;
				break;
			default:
				again = false;
				break;
			}
		}
	}
	
	public void membersMenuLoop()
	{
		int selection;
		boolean again = true;
		
		while(again)
		{
			this.displayMembers();
			System.out.println("\n\tWhat would you like to do?");
			selection = userIn.nextInt();
			
			switch(selection)
			{
			case 1:
				members.showAll("Employees");
				break;
			case 2:
				members.showAll("Stakeholders");
				break;
			case 3:
				members.showAssignedEmployees();
				break;
			case 4:
				members.showScrumTeam();
				break;
			case 5:
				members.addEmployee();
				break;
			case 6:
				members.modifyEmployee();
				break;
			case 7:
				members.addStakeholder();
				break;
			case 8:
				members.modifyStakeholder();
				break;
			case 9:
				again = false;
				break;
			default:
				again = false;
				break;
			}
			
		}
	}
	
	public void displayMain()
	{
		System.out.println("Welcome to SCRUMMY\n");
		System.out.println("1. Open sprint menu");
		System.out.println("2. Open Projects menu");
		System.out.println("3. Open staff menu");
		System.out.println("4. Exit program");
		/*
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		System.out.println("9. ");
		*/		
	}
	
	public void displaySprints()
	{
		System.out.println("Sprints\n");
		System.out.println("1. Show all current sprints");
		System.out.println("2. Show past sprints");
		System.out.println("3. Show sprints within a range");
		System.out.println("4. Select a sprint to create a story");
		System.out.println("5. Select a sprint to modify an existing story");
		System.out.println("6. Return to main menu");
	}

	public void displayProjects()
	{
		System.out.println("Projects\n");
		System.out.println("1. Show all current projects");
		System.out.println("2. Show past projects");
		System.out.println("3. Show projects within a range");
		System.out.println("4. Select a project to assign a team");
		System.out.println("5. Select a project to modify");
		System.out.println("6. Create new project");
		System.out.println("7. Return to main menu");
	}
	
	public void displayMembers()
	{
		System.out.println("Staff\n");
		System.out.println("1. Show all Employees");
		System.out.println("2. Show all stakeholders");
		System.out.println("3. Show all employees assigned to a project");
		System.out.println("4. Show all employees in a certain scrum team");
		System.out.println("5. Add a new employee");
		System.out.println("6. Modify an existing employee");
		System.out.println("7. Add a stakeholder");
		System.out.println("8. Modify a stakeholder");
		System.out.println("9. Return to main menu");
	}

}
