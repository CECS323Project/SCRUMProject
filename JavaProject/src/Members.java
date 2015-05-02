import java.util.Scanner;
import java.sql.ResultSet;
import javax.swing.JDialog;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSetMetaData;

/**
 * @author Jose Terrones
 * @author Napoleon Fulinara
 *
 */
public class Members {
  private Scanner scan = new Scanner(System.in);
  private final JDialog diag = new JDialog();
  private JDBCConnections jdbcConn = new JDBCConnections();

  Members() {
    diag.setAlwaysOnTop(true);
  }

  public void showAll(String memberType) {
    if (memberType.toLowerCase() == "employees") {
      System.out.println("\nHere is a list of all of the employees:\n");
      this.getEmployees();
    } else if (memberType.toLowerCase() == "stakeholders") {
      System.out.println("\nHere is a list of all the stakeholders:\n");
      this.getStakeholders();
    }
  }

  /**
   * Selects employee ID, first name, last name, role and team name from the members table. First
   * and last names are concatenated and the output is ordered by employee ID.
   */
  public void getEmployees() {

    System.out.println("----------------------------------------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn
              .getStatment()
              .executeQuery(
                  "select EmployeeID AS 'Member ID',concat(lName,', ',fName) AS 'Last, First',Role,TeamName from Members natural join Teams natural join Roles order by EmployeeID;");

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
   * Selects stakeholders by its role ID from the members table. Employee ID, first name, last name
   * and role ID order by last name.
   */
  public void getStakeholders() {

    System.out.println("------------------------------------------------------");

    try {
      jdbcConn.setStatment(jdbcConn.getConnection());
      ResultSet results =
          jdbcConn.getStatment().executeQuery(
              "select * from Members where RoleID = 3 order by EmployeeID;");
      ResultSetMetaData rsmd = results.getMetaData();

      // print column names
      int numberCols = rsmd.getColumnCount();
      for (int i = 1; i <= numberCols; i++) {
        if (i == 2)
          System.out.print(rsmd.getColumnLabel(3) + "\t\t");
        else if (i == 3)
          System.out.print(rsmd.getColumnLabel(2) + "\t");
        else
          System.out.print(rsmd.getColumnLabel(i) + "\t");
      }
      System.out.println("\n------------------------------------------------------");

      // print meta data
      while (results.next()) {
        String StakeHolderID = results.getString(1);
        String FName = results.getString(2);
        String LName = results.getString(3);
        String RoleID = results.getString(4);
        String TeamID = results.getString(5);

        // format
        if (StakeHolderID.length() < 2)
          System.out.format("%2s %18s %15s %5s %8s\n", StakeHolderID, FName, LName, RoleID, TeamID);
        else
          System.out.format("%1s %18s %15s %5s %8s\n", StakeHolderID, FName, LName, RoleID, TeamID);
      }

      System.out.println("\n");
      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();

    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  } // end of method: getStakeholders()

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
    jdbcConn.setStatment(jdbcConn.getConnection());
    ResultSet results;

    String fName = JOptionPane.showInputDialog(diag, "Enter members first name:");
    String lName = JOptionPane.showInputDialog(diag, "Enter members last name:");

    int role =
        Integer.parseInt(JOptionPane.showInputDialog(diag, "Enter role option:\n" + "\t1. Employee"
            + "\n\t2. Owner" + "\n\t3. Stakeholder"));

    // 1. employee
    if (role == 1) {
      try {
        System.out.println("\n------------------------------");
        results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
        ResultSetMetaData rsmd = results.getMetaData();
        
        // print column names
        int numberCols = rsmd.getColumnCount();
        for (int i = 1; i <= numberCols; i++) {
          System.out.print(rsmd.getColumnLabel(i) + "\t");
        }
        System.out.println("\n------------------------------");

        // print meta data
        while (results.next()) {
          String TeamName = results.getString(1);
          int TeamID = results.getInt(2);
          System.out.println(TeamID + "\t\t" + TeamName.trim());
        }

        results.close();
        rsmd = null;
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      
      // update database with new member
      jdbcConn.setStatment(jdbcConn.getConnection());
      int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag, "Enter team ID"));
      try {
        jdbcConn.getStatment().executeUpdate(
            "INSERT INTO Members (" + "FName,LName,RoleID,TeamID) VALUES ('" + fName + "','"
                + lName + "'" + ",'" + role + "','" + teamID + "')");
        
        System.out.println("Employee added");
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
    // 2. owner
    if (role == 2) {
      try {
        System.out.println("\n------------------------------");
        results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
        ResultSetMetaData rsmd = results.getMetaData();

        // print column names
        int numberCols = rsmd.getColumnCount();
        for (int i = 1; i <= numberCols; i++) {
          System.out.print(rsmd.getColumnLabel(i) + "\t");
        }
        System.out.println("\n------------------------------");

        // print meta data
        while (results.next()) {
          String TeamName = results.getString(1);
          int TeamID = results.getInt(2);
          System.out.println(TeamID + "\t\t" + TeamName.trim());
        }

        results.close();
        rsmd = null;
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      
      // add database with new member
      jdbcConn.setStatment(jdbcConn.getConnection());
      int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag, "Enter team ID"));
      try {
        jdbcConn.getStatment().executeUpdate(
            "INSERT INTO Members (" + "FName,LName,RoleID,TeamID) VALUES ('" + fName + "','"
                + lName + "'" + ",'" + role + "','" + teamID + "')");
        
        System.out.println("Employee added");
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    
    // 3. stakeholder
    if (role == 3) {
      try {
        System.out.println("\n------------------------------");
        results = jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
        ResultSetMetaData rsmd = results.getMetaData();
        
        // print column names
        int numberCols = rsmd.getColumnCount();
        for (int i = 1; i <= numberCols; i++) {
          System.out.print(rsmd.getColumnLabel(i) + "\t");
        }
        System.out.println("\n------------------------------");

        // print meta data
        while (results.next()) {
          String TeamName = results.getString(1);
          int TeamID = results.getInt(2);
          System.out.println(TeamID + "\t\t" + TeamName.trim());
        }

        results.close();
        rsmd = null;
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
      
      // update database with new member
      jdbcConn.setStatment(jdbcConn.getConnection());
      int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag, "Enter team ID"));
      try {
        jdbcConn.getStatment().executeUpdate(
            "INSERT INTO Members (" + "FName,LName,RoleID,TeamID) VALUES ('" + fName + "','"
                + lName + "'" + ",'" + role + "','" + teamID + "')");
        
        System.out.println("Employee added");
        jdbcConn.getStatment().close();
        jdbcConn.getConnection().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
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
    int employeeID =
        Integer.parseInt(JOptionPane.showInputDialog(diag, "Please choose an member by ID: "));

    while (true) {

      // modifications menu
      int modOption =
          Integer.parseInt(JOptionPane.showInputDialog(diag, "What would you like to modify?"
              + "\n\t1. Name" + "\n\t2. Role" + "\n\t3.Team"));

      /**
       * case 1: update first and last name case 2: update role case 3: update team
       */
      switch (modOption) {
        case 1:

          String newFirstName = JOptionPane.showInputDialog(diag, "First Name");
          String newLastName = JOptionPane.showInputDialog(diag, "Last Name");

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

          int newRoleID =
              Integer.parseInt(JOptionPane.showInputDialog(diag, "Please choose a new role"
                  + "\n\t1. Employee" + "\n\t2. Owner" + "\n\t3. Stakeholder"));

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
            int teamID =
                Integer.parseInt(JOptionPane.showInputDialog(diag, "Please choose new team:"));

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
      int decision =
          Integer.parseInt(JOptionPane.showInputDialog(diag,
              "More modifications?\n\t1. Yes\n\t2. No"));
      if (decision == 2)
        break;
    }
  } // end of method: modifyMembers()

  public void getTeamData(int role, String fName, String lName) {
    jdbcConn.setStatment(jdbcConn.getConnection());

    try {
      System.out.println("\n------------------------------");
      ResultSet results =
          jdbcConn.getStatment().executeQuery("select TeamName, TeamID from Teams ;");
      ResultSetMetaData rsmd = results.getMetaData();

      int numberCols = rsmd.getColumnCount();
      for (int i = 1; i <= numberCols; i++) {
        System.out.print(rsmd.getColumnLabel(i) + "\t");
      }
      System.out.println("\n------------------------------");

      while (results.next()) {
        String TeamName = results.getString(1);
        int TeamID = results.getInt(2);
        System.out.println(TeamID + "\t\t" + TeamName.trim());
      }

      results.close();
      rsmd = null;
      jdbcConn.getStatment().close();
      jdbcConn.getConnection().close();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    int teamID = Integer.parseInt(JOptionPane.showInputDialog(diag, "Enter team ID"));

    try {
      jdbcConn.getStatment().executeUpdate(
          "INSERT INTO Members (" + "FName,LName,RoleID,TeamID) VALUES ('" + fName + "','" + lName
              + "'" + ",'" + role + "','" + teamID + "')");
      System.out.println("Member added!");
      jdbcConn.getStatment().close();
      jdbcConn.getConnection().close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
} // end of class: Members.java
