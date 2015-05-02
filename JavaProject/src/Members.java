import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;

/**
 * @author Jose Terrones
 * @author Napoleon Fulinara
 *
 */
public class Members {
  private Scanner scan = new Scanner(System.in);
  private JDBCConnections jdbcConn = new JDBCConnections();

  /**
   * Selects employee ID, first name, last name, role and team name from the members table. First
   * and last names are concatenated and the output is ordered by employee ID.
   */
  public void getEmployees() {

    System.out.println("\n----------------------------------------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn
              .getStatment()
              .executeQuery(
                  "select EmployeeID,concat(lName,', ',fName) AS 'Last, First',Role,TeamName from Members natural join Teams natural join Roles order by EmployeeID;");

      ResultSetMetaData rsmd = results.getMetaData();

      // print column names
      int cols = rsmd.getColumnCount();
      for (int i = 1; i <= cols; i++) {
        if (i != 1)
          System.out.print(rsmd.getColumnLabel(i) + "\t\t");
        else
          System.out.print(rsmd.getColumnLabel(i) + "\t");
      }
      System.out.println("\n----------------------------------------------------------------");

      // print meta data
      while (results.next()) {
        int employeeID = results.getInt(1);
        String employeeName = results.getString(2);
        String Role = results.getString(3);
        String TeamName = results.getString(4);

        if (employeeID < 10)
          System.out.format("%2d %24s %16s %19s\n", employeeID, employeeName, Role, TeamName);
        else
          System.out.format("%1d %24s %16s %19s\n", employeeID, employeeName, Role, TeamName);
      }

      System.out.println("\n");
      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();
      jdbcConn.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  } // end of method: getEmployees()

  /**
   * Selects a particular project by ID. Joining the members and teams tables, first name, last name
   * and project name data are gathered when team IDs match.
   */
  public void showAssignedEmployees() {

    System.out.println("\n--------------------------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn
              .getStatment()
              .executeQuery(
                  "select FName, LName, ProjectName from Members JOIN Teams ON Members.TeamID = Teams.TeamID;");

      ResultSetMetaData rsmd = results.getMetaData();

      // print column names
      int numberCols = rsmd.getColumnCount();
      for (int i = 1; i <= numberCols; i++) {
        if (i == 1)
          System.out.print(rsmd.getColumnLabel(i + 1) + "\t\t");
        else if (i == 2)
          System.out.print(rsmd.getColumnLabel(i - 1) + "\t\t");
        else
          System.out.print(rsmd.getColumnLabel(i) + "\t\t");
      }
      System.out.println("\n--------------------------------------------------");

      // print meta data
      while (results.next()) {
        String FName = results.getString(1).trim();
        String LName = results.getString(2).trim();
        String ProjectName = results.getString(3).trim();

        // format
        if (LName.length() >= 8 && FName.length() >= 8)
          System.out.println(LName.trim() + "\t" + FName.trim() + "\t" + ProjectName.trim());
        else if (LName.length() < 8 && FName.length() < 8)
          System.out.println(LName.trim() + "\t\t" + FName.trim() + "\t\t" + ProjectName.trim());
        else if (LName.length() >= 8 && FName.length() < 8)
          System.out.println(LName.trim() + "\t" + FName.trim() + "\t\t" + ProjectName.trim());
        else
          System.out.println(LName.trim() + "\t\t" + FName.trim() + "\t" + ProjectName.trim());
      }

      System.out.println("\n");
      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();

    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  } // end of method: showAssignedEmployees()

  /**
   * Selects a particular team by ID. Joining the members and teams tables where team IDs match,
   * last name, first name and team name data is gathered.
   */
  public void showScrumTeam() {

    System.out.println("\n------------------------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn
              .getStatment()
              .executeQuery(
                  "select LName, FName, TeamName from Members JOIN Teams ON Members.TeamID = Teams.TeamID;");

      ResultSetMetaData rsmd = results.getMetaData();

      // print column names
      int numberCols = rsmd.getColumnCount();
      for (int i = 1; i <= numberCols; i++) {
        System.out.print(rsmd.getColumnLabel(i) + "\t\t");
      }
      System.out.println("\n------------------------------------------------");

      // print meta data
      while (results.next()) {
        String LName = results.getString(1).trim();
        String FName = results.getString(2).trim();
        String ProjectName = results.getString(3).trim();

        // format
        if (LName.length() >= 8 && FName.length() >= 8)
          System.out.println(LName.trim() + "\t" + FName.trim() + "\t" + ProjectName.trim());
        else if (LName.length() < 8 && FName.length() < 8)
          System.out.println(LName.trim() + "\t\t" + FName.trim() + "\t\t" + ProjectName.trim());
        else if (LName.length() >= 8 && FName.length() < 8)
          System.out.println(LName.trim() + "\t" + FName.trim() + "\t\t" + ProjectName.trim());
        else
          System.out.println(LName.trim() + "\t\t" + FName.trim() + "\t" + ProjectName.trim());
      }

      System.out.println("\n");
      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();

    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  } // end of method: showScrumTeam()

  /**
   * Adds a new member to the database. Team name and team ID are selected from the teams table. The
   * user is prompted with a simple menu in which to add new member data.
   */
  public void addEmployee() {
    
    System.out.println("\n-------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
      ResultSetMetaData rsmd = results.getMetaData();

      // print column names
      int cols = rsmd.getColumnCount();
      for (int i = 1; i <= cols; i++) {
        System.out.print(rsmd.getColumnLabel(i) + "\t\t");
      }
      System.out.println("\n-------------------------------");

      // print meta data
      while (results.next()) {
        String TeamName = results.getString(1).trim();
        int TeamID = results.getInt(2);
        
        // format
        if (TeamName.length() >= 8)
          System.out.println(TeamName.trim() + "\t\t" + "  " + TeamID);
        else
          System.out.println(TeamName.trim() + "\t\t\t" + "  " + TeamID);
      }
      
      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();
      jdbcConn.getConnection().close();
      System.out.println("-------------------------------");
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    System.out.print("Enter members first name: ");
    String fName = scan.nextLine();

    System.out.print("Enter members last name: ");
    String lName = scan.nextLine();

    int RoleID = 1;
    System.out.print("Enter members Team id: ");
    int teamID = scan.nextInt();

    try {
      // adds a new member to the database
      jdbcConn.getStatment().executeUpdate(
          "INSERT INTO Members (" + "FName,LName,RoleID,TeamID) VALUES ('" + fName + "','" + lName
              + "'" + ",'" + RoleID + "','" + teamID + "')");
      
      System.out.println("Member added");
      jdbcConn.getStatment().close();
      jdbcConn.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  } // end of method: addEmployee()

  /**
   * Sets new member data and updates the database. The user is presented with simple menus of
   * modifiable options. The user may then update the first name, last name, role and team.
   */
  public void modifyMembers() {
    Scanner in = new Scanner(System.in);

    // gets employee list
    getEmployees();
    System.out.print("Please choose an Employee ID: ");
    int employeeID = in.nextInt();

    while (true) {

      // modifications menu
      getModMenu();
      System.out.print("What would you like to modify? ");
      int modOption = in.nextInt();

      /**
       * case 1: update first and last name case 2: update role case 3: update team
       */
      switch (modOption) {
        case 1:
          System.out.println("\nPlease enter new information:");

          System.out.print("First name: ");
          String newFirstName = in.next();

          System.out.print("Last name: ");
          String newLastName = in.next();

          try {
            // updates database with new first and last name data
            jdbcConn.setStatment(jdbcConn.getConnection());
            jdbcConn.getStatment().executeUpdate(
                "update Members set FName = '" + newFirstName + "', LName = '" + newLastName
                    + "' where Members.EmployeeID = " + employeeID + ";");

            jdbcConn.getStatment().close();
            System.out.println("\nUpdate Successful!");
          } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
          }
          break;
        case 2:

          // role menu
          getRoleMenu();
          System.out.print("Please choose a new role: ");
          int newRoleID = in.nextInt();

          try {
            // updates database with new rold ID data
            jdbcConn.setStatment(jdbcConn.getConnection());
            jdbcConn.getStatment().executeUpdate(
                "update Members set RoleID = " + newRoleID + " where Members.EmployeeID = "
                    + employeeID + ";");

            jdbcConn.getStatment().close();
            System.out.println("\nUpdate Successful");
          } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
          }
          break;
        case 3:
          
          System.out.println("\n------------------------");
          
          try {
            // gets team name meta data
            jdbcConn.setStatment(jdbcConn.getConnection());
            ResultSet results =
                jdbcConn.getStatment().executeQuery("select TeamID, TeamName from Teams;");
            ResultSetMetaData rsmd = results.getMetaData();

            // print column names
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
              System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }
            System.out.println("\n------------------------");

            // print meta data
            while (results.next()) {
              String teamID = results.getString(1);
              String teamName = results.getString(2);
              System.out.format("%1s %22s\n", teamID, teamName);
            }
            
            System.out.println("\n-------------------------------");
            System.out.print("Please choose a new team by ID: ");
            int teamID = in.nextInt();

            // updates database with new team name data
            jdbcConn.getStatment().executeUpdate(
                "update Teams inner join Members on Teams.TeamID = Members.TeamID set Members.TeamID = "
                    + teamID + " where Members.EmployeeID = " + employeeID + ";");
            
            System.out.println("\nUpdate Successful");
            results.close();
            rsmd = null;
            jdbcConn.getStatment().close();
            jdbcConn.getConnection().close();
          } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
          }
      } // end switch statement

      // simple menu to modify more member data
      System.out.println("\n1. Yes");
      System.out.println("2. No");
      System.out.print("More modifications? ");
      int decision = in.nextInt();

      if (decision == 2)
        break;
    }
  } // end of method: modifyMembers()

  /**
   * simple menu for modifiable options
   */
  public void getModMenu() {
    System.out.println("");
    System.out.println("1. Name");
    System.out.println("2. Role");
    System.out.println("3. Team");
  } // end of method: getModMenu()

  /**
   * simple menu of specific modifiable attributes
   */
  public void getRoleMenu() {
    System.out.println("");
    System.out.println("1. Owner");
    System.out.println("2. Employee");
    System.out.println("3. Stakeholder");
  } // end of method: getRoleMenu()
} // end of class: Members.java
