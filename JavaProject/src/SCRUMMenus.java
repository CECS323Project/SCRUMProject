import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SCRUMMenus 
{
	private Scanner userIn = new Scanner(System.in);
	private Sprints sprints = new Sprints();
	private Projects projects = new Projects();
	private Members members = new Members();
	private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
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
				try {
					this.sprintMenuLoop();
				} catch (ParseException e) {
					System.out.println("Not a valid date");
					
					
				}
				
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

	public void sprintMenuLoop() throws ParseException
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
				int backlogShow = 0;
				sprints.showAll();
				System.out.println("Do you want to show the story backlog?\n\t1.)Yes\n\t2.)No");
				backlogShow = userIn.nextInt();
				if(backlogShow == 1)
				{
					int selectedSprint;
					System.out.println("Please select a sprint by ID: ");
					selectedSprint = userIn.nextInt();
					sprints.getAllBacklogs(selectedSprint);
				}				
				break;
			case 2:

				
				String past;
				past = JOptionPane.showInputDialog("What date will be the upper limit? (YYYY-MM-DD)");
				String after = df.format(df.parse(past));
				sprints.showPast(past);
				break;
			case 3:
				String lower,upper;
				lower = JOptionPane.showInputDialog("What is the lower limit? (YYYY-MM-DD)");
				after = df.format(df.parse(lower));
				upper= JOptionPane.showInputDialog("What is the upper limit? (YYYY-MM-DD)");
				after = df.format(df.parse(upper));
				
				sprints.showRange(lower,upper);
				break;
			case 4:
				int Sprint,Employee;
				String Goal,Benefit;
				sprints.showAll();
				members.showAll("employees");
				Sprint = Integer.parseInt(JOptionPane.showInputDialog("Which sprint would you like to add to? (SprintID)"));
				Employee = Integer.parseInt(JOptionPane.showInputDialog("Which employee is this story for? (EmployeeID)"));
				Goal = JOptionPane.showInputDialog("What is the goal?");
				Benefit = JOptionPane.showInputDialog("What will the benefit be?");				
				
				sprints.createStory(Sprint, Employee, Goal, Benefit);
				break;
			case 5:
				int sprintID,backlogID,employee;
				String goal,benefit;
				sprints.showAll();
				sprintID = Integer.parseInt(JOptionPane.showInputDialog("Which sprint do you want to modify?(SprintID"));
				sprints.getAllBacklogs(sprintID);
				backlogID = Integer.parseInt(JOptionPane.showInputDialog("Which backlog do you want to modify?(BacklogID)"));
				employee = Integer.parseInt(JOptionPane.showInputDialog("Which employee will be taking this story?(EmployeeID)"));
				goal = JOptionPane.showInputDialog("What is the new goal?");
				benefit = JOptionPane.showInputDialog("What is the new benefit?");				
				sprints.modifyStory(sprintID,backlogID,employee,goal,benefit);
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
