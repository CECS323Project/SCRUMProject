/**
 * Main menu structure
 * @author Russell Tan
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SCRUMMenus {
  private Scanner userIn = new Scanner(System.in);
  private Sprints sprints = new Sprints();
  private Members members = new Members();
  private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

  SCRUMMenus() {}

  public void mainMenuLoop() {
    int selection;
    boolean again = true;

    while (again) {
      this.displayMain();
      System.out.print("\n\tWhat would you like to do? ");
      selection = userIn.nextInt();

      switch (selection) {
        case 1:
          try {
            this.sprintMenuLoop();
          } catch (ParseException e) {
            System.out.println("Not a valid date");
          }
          break;
        case 2:
          this.membersMenuLoop();
          break;
        case 3:
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

  public void quit() {
    System.out.println("Have a SCRUMMY day!");
  }

  public void sprintMenuLoop() throws ParseException {
    int selection;
    boolean again = true;

    while (again) {
      this.displaySprints();
      System.out.println("\n\tWhat would you like to do?");
      selection = userIn.nextInt();

      switch (selection) {
        case 1:
          int backlogShow = 0;
          sprints.showAll();
          System.out.println("Do you want to show the story backlog?\n\t1.)Yes\n\t2.)No");
          backlogShow = userIn.nextInt();
          if (backlogShow == 1) {
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
          System.out.println("Do you want to show the story backlog?\n\t1.)Yes\n\t2.)No");
          backlogShow = userIn.nextInt();
          if (backlogShow == 1) {
            int selectedSprint;
            System.out.println("Please select a sprint by ID: ");
            selectedSprint = userIn.nextInt();
            sprints.getAllBacklogs(selectedSprint);
          }
          break;
        case 3:
          String lower,
          upper;
          lower = JOptionPane.showInputDialog("What is the lower limit? (YYYY-MM-DD)");
          after = df.format(df.parse(lower));
          upper = JOptionPane.showInputDialog("What is the upper limit? (YYYY-MM-DD)");
          after = df.format(df.parse(upper));

          sprints.showRange(lower, upper);
          System.out.println("Do you want to show the story backlog?\n\t1.)Yes\n\t2.)No");
          backlogShow = userIn.nextInt();
          if (backlogShow == 1) {
            int selectedSprint;
            System.out.println("Please select a sprint by ID: ");
            selectedSprint = userIn.nextInt();
            sprints.getAllBacklogs(selectedSprint);
          }
          break;
        case 4:
          int Sprint,
          Employee;
          String Goal,
          Benefit;
          sprints.showAll();
          members.getEmployees();
          Sprint =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which sprint would you like to add to? (SprintID)"));
          Employee =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which employee is this story for? (EmployeeID)"));
          Goal = JOptionPane.showInputDialog("What is the goal?");
          Benefit = JOptionPane.showInputDialog("What will the benefit be?");
          String Status = JOptionPane.showInputDialog("What is the new status?");
          sprints.createStory(Sprint, Employee, Goal, Benefit, Status);
          break;
        case 5:
          int sprintID,
          backlogID,
          employee;
          String goal,
          benefit,
          status;
          sprints.showAll(1);
          sprintID =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which sprint do you want to modify?(SprintID"));
          sprints.getAllBacklogs(sprintID);
          backlogID =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which backlog do you want to modify?(BacklogID)"));
          members.getEmployees();
          employee =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which employee will be taking this story?(EmployeeID)"));
          goal = JOptionPane.showInputDialog("What is the new goal?");
          benefit = JOptionPane.showInputDialog("What is the new benefit?");
          status = JOptionPane.showInputDialog("What is the new status?");
          sprints.modifyStory(sprintID, backlogID, employee, goal, benefit, status);
          break;
        case 6:// create
          int TeamID;
          String date,
          projectName;
          date = JOptionPane.showInputDialog("Enter the date planned for the sprint: (YYYY-MM-DD)");
          String newDate = df.format(df.parse(date));
          sprints.showTeams();
          TeamID =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which team will be assigned to this sprint?"));
          projectName = sprints.getProjectName(TeamID);
          sprints.createSprint(newDate, TeamID, projectName);
          break;
        case 7:// modify
          String SprintStatus;
          sprints.showAll();
          sprintID =
              Integer.parseInt(JOptionPane
                  .showInputDialog("Which sprint do you want to modify?(SprintID"));
          SprintStatus =
              JOptionPane
                  .showInputDialog("What is the new status?\n\tCompleted\n\tPlanned\n\tIn Progress");
          sprints.modifySprint(sprintID, SprintStatus);
          break;
        case 8:
          again = false;
          break;
        default:
          again = false;
          break;
      }
    }
  }

  public void membersMenuLoop() {
    int selection;
    boolean again = true;
    
    while(again)
    {
        this.displayMembers();
        System.out.print("\n\tWhat would you like to do? ");
        selection = userIn.nextInt();
        
        switch(selection)
        {
        case 1:
            members.showAll("employees");
            break;
        case 2:
            members.showAll("stakeholders");
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
            members.modifyMembers();
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

  public void displayMain() {
    System.out.println("Welcome to SCRUMMY\n");
    System.out.println("1. Open sprint menu");
    System.out.println("2. Open staff menu");
    System.out.println("3. Exit program");
  }

  public void displaySprints() {
    System.out.println("Sprints\n");
    System.out.println("1. Show all current sprints");
    System.out.println("2. Show past sprints");
    System.out.println("3. Show sprints within a range");
    System.out.println("4. Select a sprint to create a story");
    System.out.println("5. Select a sprint to modify an existing story");
    System.out.println("6. Create a sprint");
    System.out.println("7. Modify a sprints status");
    System.out.println("8. Return to main menu");
  }

  public void displayProjects() {
    System.out.println("Projects\n");
    System.out.println("1. Show all current projects");
    System.out.println("2. Show past projects");
    System.out.println("3. Show projects within a range");
    System.out.println("4. Select a project to assign a team");
    System.out.println("5. Select a project to modify");
    System.out.println("6. Create new project");
    System.out.println("7. Return to main menu");
  }

  public void displayMembers() {
    System.out.println("Staff\n");
    System.out.println("1. Show all Employees");
    System.out.println("2. Show all Stakeholders");
    System.out.println("3. Show all employees assigned to a project");
    System.out.println("4. Show all employees in a certain scrum team");
    System.out.println("5. Add a new member");
    System.out.println("6. Modify an existing member");
    System.out.println("7. Return to main menu");
  }
}
