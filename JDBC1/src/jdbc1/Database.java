/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc1;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Kunal and Dennis
 */
public class Database 
{
    //  Database credentials
    String USER;
    String PASS;
    String DBNAME;
    Connection conn = null; //initialize the connection
    Statement stmt = null;  //initialize the statement that we're using
    ResultSet rs = null;
    Scanner scan = new Scanner(System.in);
    
    String sql = null;
    
    String groupName, headWriter, subject;
    String bookTitle, publisherName, yearPublished;
    int numberPages, yearFormed;
    String publisherAddress, publisherPhone, publisherEmail;
    int choice;
    
    //This is the specification for the printout that I'm doing:
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are
    //strings, but that won't always be the case.
    final String displayFormat="%-20s%-20s%-10s%-10s\n";
    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    String DB_URL = "jdbc:derby://localhost:1527/";    
    //            + "testdb;user=";
    
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    
    public void start()
    {
        //Prompt the user for the database name, and the credentials.
        //If your database has no credentials, you can update this code to
        //remove that from the connection string.
//        System.out.print("Name of the database (not the user account): ");
//        DBNAME = scan.nextLine();
//        System.out.print("Database user name: ");
//        USER = scan.nextLine();
//        System.out.print("Database password: ");
//        PASS = scan.nextLine();
        //Constructing the database URL connection string
        
        USER = "user1";
        PASS = "password";
        DBNAME = "JDBC";
        
        DB_URL = DB_URL + DBNAME + ";user=" + USER + ";password=" + PASS;
        
        try 
        {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            System.out.println("DB_URL = " + DB_URL);
            
            conn = DriverManager.getConnection(DB_URL);
            //STEP 4: Executing a query example
            System.out.println("Creating statement...");
            stmt = conn.createStatement(); /////creates statement object
            
            
            //what do you want to do now?
            //switch case menu goes here
            
            
            do
            {
                System.out.print("Enter choice:"
                        + "\n1. Look up"
                        + "\n2. Add book to database"
                        + "\n3. Remove book from database"
                        + "\n ..."
                        + "\n10.Quit"
                        + "\nEnter number: ");
                choice = scan.nextInt();
                scan.skip("\n"); //prevents following input skipping
                switch(choice)
                {
                    case 1:
                        int listChoice;
                        List list = new List();
                        
                        do
                        {
                            System.out.print("Enter look up choice:"
                                    + "\n1. All writing groups"
                                    + "\n2. Group info"
                                    + "\n3. All publishers"
                                    + "\n4. All titles"
                                    + "\n5. Book info"
                                    + "\n10.Go back"
                                    + "\nEnter choice: ");
                            listChoice = scan.nextInt();
                            System.out.println();
                            scan.skip("\n");

                            switch(listChoice)
                            {
                                case 1:
                                    list.allWritingGroups(this);
                                    break;
                                case 2:
                                    list.selectGroup(this);
                                    break;
                                case 3:
                                    list.allPublishers(this);
                                    break;
                                case 4:
                                    list.allTitles(this);
                                    break;
                                case 5:
                                    System.out.println("Not ready yet!");
                            }
                        } while(listChoice != 10);
                        break;
                    case 2: 
                        Insert ins = new Insert();
                        ins.book(this);
                        break;
                    case 3:
                        Remove rem = new Remove();
                        rem.selectBook(this);
                        break;
                }
            
            } while(choice != 10);
            
            
            
            
            String sql = "SELECT * FROM Books";
            
            rs = stmt.executeQuery(sql);
            
            //Display values
            System.out.printf(displayFormat,
                    "Group",
                    "Title",
                    "Year", 
                    "Pages");
            
            while (rs.next())
            {
                groupName = rs.getString("groupName");
                bookTitle = rs.getString("bookTitle");
                publisherName = rs.getString("publisherName");
                yearPublished = rs.getString("yearPublished");
                numberPages = rs.getInt("numberPages");
                
                System.out.printf(displayFormat, 
                        dispNull(groupName), 
                        dispNull(bookTitle), 
                        dispNull(yearPublished),
                        numberPages);
//                        dispNull(numberPages));
            }
            
//            //STEP 4: Executing a query example
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement(); /////creates statement object
//            String sql;
//            sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
//            ResultSet rs = stmt.executeQuery(sql); //////executing sql, sends results to rs

            
            
            
            
            
//            //STEP 5: Extracting data from result set example
//            System.out.printf(displayFormat, "ID", "First Name", "Last Name", "Phone #");
//            while (rs.next()) 
//            {
//                //Retrieve by column name
//                String id = rs.getString("au_id");
//                String phone = rs.getString("phone");
//                String first = rs.getString("au_fname");
//                String last = rs.getString("au_lname");
//
//                //Display values
//                System.out.printf(displayFormat,
//                        dispNull(id), dispNull(first), dispNull(last), dispNull(phone));
//            }
            
            
            
            
            
            
            
            
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
    
    public boolean isValueStored(Database dbin, String tableName, 
            String colName, String valueIn) throws SQLException
    {
        sql = "SELECT " + colName + " FROM " + tableName + " WHERE " 
                + colName + " = '" + valueIn + "'";
        
        //System.out.println(sql);
        
        rs = stmt.executeQuery(sql);
        
        return rs.next();
    }
    
    public boolean isPK(Database dbin, String tableName, String PKstr) throws SQLException
    {
        sql = "SELECT * FROM " + tableName + " WHERE " + PKstr;
        
        rs = stmt.executeQuery(sql);
        
        return rs.next();
    }
}

