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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    String  bookTitle = "booktitle",
            groupName = "groupname",
            publisherName = "publishername",
            yearPublished = "yearpublished",
            numberPages = "numberpages",
            publisherAddress = "publisheraddress",
            publisherPhone = "publisherphone",
            publisherEmail = "publisheremail",
            headWriter = "headwriter",
            yearFormed = "yearformed",
            subject = "subject";
            
            
//    int numberPages, yearFormed;
//    String publisherAddress, publisherPhone, publisherEmail;
    int choice;
    String userInput;
    
    //This is the specification for the printout that I'm doing:
    //each % denotes the start of a new field.
    //The - denotes left justification.
    //The number indicates how wide to make the field.
    //The "s" denotes that it's a string.  All of our output in this test are
    //strings, but that won't always be the case.
    String  dfListWG = "%-25s%-25s%-25s%-25s\n",
            dfListPub = "%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n",
            dfListAllPub = "%-25s%-25s%-25s%-25s\n",
            dfListAllBook = "%-25s%-25s%-25s%-25s%-25s\n",
            dfListBook = "%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s%-25s\n";
    
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
        
        //prepared statement strings
        
        String  sqlListWritingGroup = "SELECT groupname, headwriter, yearformed, subject FROM writinggroups", 
                sqlListPub = "SELECT * FROM books NATURAL JOIN publishers NATURAL JOIN writinggroups WHERE groupname = ?",
                sqlListAllPub = "SELECT publishername, publisheraddress, publisherphone, publisheremail FROM publishers",
                sqlListAllBook = "SELECT booktitle, groupname, publishername, yearpublished, numberpages FROM books",
                sqlListBook = "SELECT * FROM books NATURAL JOIN publishers NATURAL JOIN writinggroups WHERE booktitle = ? AND groupname = ?",
                sqlRemoveBook = "DELETE FROM books WHERE booktitle = ?",
                sqlInsertBook = "INSERT INTO books(booktitle, groupname, publishername, yearpublished, numberpages) VALUES(?,?,?,?,?)",
                sqlInsertPub = "INSERT INTO publishers(publishername, publisheraddress, publisherphone, publisheremail) VALUES(?,?,?,?)", 
                sqlUpdatePub = "UPDATE publishers SET publishername = ?, publisheraddress = ?, publisherphone = ?, publisheremail = ? WHERE publishername = ?",
                sqlUpdateBookPub = "UPDATE books SET publishername = ? WHERE publishername = ?",
                sqlInsertWG = "INSERT INTO writinggroups(groupname, headwriter, yearformed, subject) VALUES(?,?,?,?)";
        

            //end prepared statement strings
        PreparedStatement pstmtListWG = null;
        PreparedStatement pstmtListPub = null;
        PreparedStatement pstmtListAllPub = null;
        PreparedStatement pstmtListAllBook = null;
        PreparedStatement pstmtListBook = null;
        PreparedStatement pstmtRemoveBook = null;
        PreparedStatement pstmtInsertBook = null;
        PreparedStatement pstmtInsertPub = null;
        PreparedStatement pstmtUpdatePub = null;
        PreparedStatement pstmtUpdateBookPub = null;
        PreparedStatement pstmtInsertWG = null;

        
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
//            pstmt = conn.prepareCall(sql);
            
            try 
            {
                //prepared statement objects
                
                pstmtListWG = conn.prepareStatement(sqlListWritingGroup);
                pstmtListPub = conn.prepareStatement(sqlListPub);
                pstmtListAllPub = conn.prepareStatement(sqlListAllPub);
                pstmtListAllBook = conn.prepareStatement(sqlListAllBook);
                pstmtListBook = conn.prepareStatement(sqlListBook);
                pstmtRemoveBook = conn.prepareStatement(sqlRemoveBook);
                pstmtInsertBook = conn.prepareStatement(sqlInsertBook);
                pstmtInsertPub = conn.prepareStatement(sqlInsertPub);
                pstmtUpdatePub = conn.prepareStatement(sqlUpdatePub);
                pstmtUpdateBookPub = conn.prepareStatement(sqlUpdateBookPub);
                pstmtInsertWG = conn.prepareStatement(sqlInsertWG);

                 //end prepared statement objects
                 
                do
                {
                    System.out.print("Enter choice:"
                            + "\n1. Look up"
                            + "\n2. Add book to database"
                            + "\n3. Add and Update Publisher"
                            + "\n4. Remove book from database"
                            + "\n9. Quit"
                            + "\nEnter number: ");
                    choice = scan.nextInt();
                    scan.skip("\n"); //prevents following input skipping
                    System.out.println();
                    switch(choice)
                    {
                        case 1:
                            int listChoice;

                            do
                            {
                                System.out.print("Enter look up choice:"
                                        + "\n1. All writing groups"
                                        + "\n2. Group info"
                                        + "\n3. All publishers"
                                        + "\n4. All titles"
                                        + "\n5. Book info"
                                        + "\n9. Go back"
                                        + "\nEnter choice: ");
                                listChoice = scan.nextInt();
                                scan.skip("\n"); //prevents following input skipping
                                System.out.println();
                                switch(listChoice)
                                {
                                    case 1:
                                        rs = pstmtListWG.executeQuery();
                                        System.out.printf(dfListWG, 
                                                "Writing Groups",
                                                "Head Writer",
                                                "Year Formed",
                                                "Subject"
                                        );
                                        while (rs.next())
                                        {
                                            System.out.printf(dfListWG, 
                                                    dispNull(rs.getString(groupName)),
                                                    dispNull(rs.getString(headWriter)),
                                                    dispNull(rs.getString(yearFormed)),
                                                    dispNull(rs.getString(subject)));
                                        }
                                        rs.close();
                                        System.out.println();
                                        break;
                                    case 2:
                                        System.out.print("Enter name of group: ");
                                        pstmtListPub.setString(1, scan.nextLine());
                                        rs = pstmtListPub.executeQuery();
                                        System.out.printf(dfListPub, 
                                                "Name", 
                                                "Address", 
                                                "Phone", 
                                                "Email",
                                                "Group Name",
                                                "Head Writer", 
                                                "Year Formed",
                                                "Subject",
                                                "Book Title", 
                                                "Year Published", 
                                                "Number of Pages");

                                        while (rs.next())
                                        {
                                            System.out.printf(dfListPub, 
                                                    dispNull(rs.getString(publisherName)),
                                                    dispNull(rs.getString(publisherAddress)),
                                                    dispNull(rs.getString(publisherPhone)),
                                                    dispNull(rs.getString(publisherEmail)),
                                                    dispNull(rs.getString(groupName)),
                                                    dispNull(rs.getString(headWriter)),
                                                    dispNull(rs.getString(yearFormed)),
                                                    dispNull(rs.getString(subject)),
                                                    dispNull(rs.getString(bookTitle)),
                                                    dispNull(rs.getString(yearPublished)),
                                                    rs.getString(numberPages)
                                            );
                                        }
                                        rs.close();
                                        System.out.println();
                                        break;
                                    case 3:
                                        rs = pstmtListAllPub.executeQuery();
                                        System.out.printf(dfListAllPub,
                                                "Publisher",
                                                "Address",
                                                "Phone",
                                                "Email"
                                        );
                                        while (rs.next())
                                        {
                                            System.out.printf(dfListAllPub, 
                                                    dispNull(rs.getString(publisherName)), 
                                                    dispNull(rs.getString(publisherAddress)), 
                                                    dispNull(rs.getString(publisherPhone)), 
                                                    dispNull(rs.getString(publisherEmail))
                                            );
                                        }
                                        rs.close();
                                        System.out.println();
                                        break;
                                    case 4:
                                        rs = pstmtListAllBook.executeQuery();
                                        System.out.printf(dfListAllBook, 
                                                "List of Book Titles", 
                                                "Writing Group",
                                                "Publisher",
                                                "Year Published",
                                                "Number of Pages"
                                        );

                                        while (rs.next())
                                        {
                                            System.out.printf(dfListAllBook, 
                                                    dispNull(rs.getString(bookTitle)), 
                                                    dispNull(rs.getString(groupName)),
                                                    dispNull(rs.getString(publisherName)),
                                                    dispNull(rs.getString(yearPublished)),
                                                    rs.getString(numberPages)                                                
                                            );
                                        }
                                        rs.close();
                                        System.out.println();
                                        break;
                                    case 5:
                                        System.out.print("Enter book title: ");
                                        if(!isValueStored("books", bookTitle, userInput = scan.nextLine()))
                                        {
                                            System.out.println("Error: Book title not found in database.");
                                            break;
                                        }
                                        
                                        pstmtListBook.setString(1, userInput);
                                        
                                        System.out.print("Enter writing group: ");
                                        if(!isValueStored("books", groupName, userInput = scan.nextLine()))
                                        {
                                            System.out.println("Error: Book title by this group not found in database.");
                                            break;
                                        }
                                        
                                        pstmtListBook.setString(2, userInput);

                                        System.out.printf(dfListBook,
                                                "Book Title", 
                                                "Year Published", 
                                                "Number of Pages",
                                                "Writing Group",
                                                "Head Writer",
                                                "Year Formed",
                                                "Subject",
                                                "Publisher Name",
                                                "Publisher Address",
                                                "Publisher Phone",
                                                "Publisher Email"
                                        );
                                        rs = pstmtListBook.executeQuery();
                                        while(rs.next())
                                        {
                                            System.out.printf(dfListBook, 
                                                    dispNull(rs.getString(bookTitle)),
                                                    dispNull(rs.getString(yearPublished)),
                                                    rs.getInt(numberPages),
                                                    dispNull(rs.getString(groupName)),
                                                    dispNull(rs.getString(headWriter)),
                                                    dispNull(rs.getString(yearFormed)),
                                                    dispNull(rs.getString(subject)),
                                                    dispNull(rs.getString(publisherName)),
                                                    dispNull(rs.getString(publisherAddress)),
                                                    dispNull(rs.getString(publisherPhone)),
                                                    dispNull(rs.getString(publisherEmail))
                                            );        
                                        }
                                        rs.close();
                                        System.out.println();
                                }

                            } while(listChoice != 9);
                            break;
                        case 2: //insert methods 
                            String PK = "bookTitle = '";
                            System.out.print("Enter Title: ");
                            pstmtInsertBook.setString(1, userInput = scan.nextLine());
                            PK += userInput + "' AND groupName = '";
                            System.out.print("Writing Group: ");
                            pstmtInsertBook.setString(2, userInput = scan.nextLine());
                            PK += userInput + "'";

                            if (isPK("books", PK))
                            {
                                System.out.println("Book already in database!");
                                break;
                            }

                            if (!isValueStored("writinggroups", groupName, userInput))
                            {
                                pstmtInsertWG.setString(1, userInput);
                                System.out.print("Group: Head Writer: ");
                                pstmtInsertWG.setString(2, scan.nextLine());
                                System.out.print("Group: Year Formed: ");
                                pstmtInsertWG.setString(3, scan.nextLine());
                                System.out.print("Group: Subject: ");
                                pstmtInsertWG.setString(4, scan.nextLine());
                            }

                            System.out.print("Publisher Name: ");
                            userInput = scan.nextLine();
                            pstmtInsertBook.setString(3, userInput);

                            if (!isValueStored("publishers", publisherName, userInput))
                            {
                                pstmtInsertPub.setString(1, userInput);
                                System.out.print("Publisher: Address: ");
                                pstmtInsertPub.setString(2, scan.nextLine());
                                System.out.print("Publisher: Phone: ");
                                pstmtInsertPub.setString(3, scan.nextLine());
                                System.out.print("Publisher: Email: ");
                                pstmtInsertPub.setString(4, scan.nextLine());
                            }
                            System.out.print("Enter Year Published: ");
                            pstmtInsertBook.setString(4, scan.nextLine());
                            System.out.print("Enter Number of Pages: ");
                            pstmtInsertBook.setInt(5, scan.nextInt());
                            System.out.println();

                            pstmtInsertWG.executeUpdate();
                            pstmtInsertPub.executeUpdate();
                            pstmtInsertBook.executeUpdate();                      

                            break;
                        case 3:                                        
                            System.out.print("Name of publisher to be replaced: ");

                            if (!isValueStored("publishers", publisherName, userInput = scan.nextLine()))
                            {
                                System.out.println(userInput);
                                System.out.println("Publisher not found!");
                                break;
                            }

                            pstmtUpdateBookPub.setString(2, userInput);

                            System.out.print("Enter updated publisher name: ");
                            userInput = scan.nextLine();
                            pstmtInsertBook.setString(3, userInput);

                            if (!isValueStored("publishers", publisherName, userInput))
                            {
                                pstmtInsertPub.setString(1, userInput);
                                System.out.print("Address: ");
                                pstmtInsertPub.setString(2, scan.nextLine());
                                System.out.print("Phone: ");
                                pstmtInsertPub.setString(3, scan.nextLine());
                                System.out.print("Email: ");
                                pstmtInsertPub.setString(4, scan.nextLine());
                            }
                            else
                            {
                                System.out.println("Publisher already in Database!");
                                break;
                            }
                            
                            System.out.println();

                            pstmtUpdateBookPub.setString(1, userInput);
                            pstmtInsertPub.executeUpdate();
                            pstmtUpdateBookPub.executeUpdate();
                            break;
                        case 4:
                            System.out.print("Enter to be removed book title: ");
                            userInput = scan.nextLine();
                            System.out.print("Enter writing group: ");
                            String tempInput = "booktitle = '" + userInput + "' AND groupname = '" + scan.nextLine() + "'";
                            if (!isPK("books", tempInput))
                            {
                                System.out.println("Title by this writing group not found!");
                                break;
                            }

                            if(0 < pstmtRemoveBook.executeUpdate())
                            {
                                System.out.println("Title successfully removed from database.");
                            }
                            else 
                            {
                                System.out.println("Error: Something went wrong with title removal.");
                            }                  
                            System.out.println();
                    }            
                } while(choice != 9);
                
                //clean up
                if(!stmt.isClosed())
                    stmt.close();
                if(!conn.isClosed())
                    conn.close();
                if(!pstmtListWG.isClosed())
                    pstmtListWG.close();
                if(!pstmtListPub.isClosed())
                    pstmtListPub.close();
                if(!pstmtListAllPub.isClosed())
                    pstmtListAllPub.close();
                if(!pstmtListAllBook.isClosed())
                    pstmtListAllBook.close();
                if(!pstmtListBook.isClosed())
                    pstmtListBook.close();
                if(!pstmtRemoveBook.isClosed())
                    pstmtRemoveBook.close();
                if(!pstmtInsertBook.isClosed())
                    pstmtInsertBook.close();
                if(!pstmtInsertPub.isClosed())
                    pstmtInsertPub.close();
                if(!pstmtUpdatePub.isClosed())
                    pstmtUpdatePub.close();
                if(!pstmtUpdateBookPub.isClosed())
                    pstmtUpdateBookPub.close();
                if(!pstmtInsertWG.isClosed())
                    pstmtInsertWG.close();
                
            } catch (SQLException ex){
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }          
                     
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
    }
    
    public boolean isValueStored(String tableName, String colName, String valueIn) throws SQLException
    {
        sql = "SELECT " + colName + " FROM " + tableName + " WHERE " 
                + colName + " = '" + valueIn + "'";
        
//        System.out.println(sql);
        
        rs = stmt.executeQuery(sql);
        
        return rs.next();
    }
    
    public boolean isPK(String tableName, String PKstr) throws SQLException
    {
        sql = "SELECT * FROM " + tableName + " WHERE " + PKstr;
        
//        System.out.println(sql);
        
        rs = stmt.executeQuery(sql);
        
        return rs.next();
    }
    
}

