/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc1;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kunal and Dennis
 */
public class Insert 
{
    public void book(Database dbin) throws SQLException
    {
        //*****crashes when apostrophe is entered
        //*****search for apostrophe in input to add backslash before apostrophe
        
        System.out.print("Enter book title: ");
        dbin.bookTitle = dbin.scan.nextLine();
        
        System.out.print("Enter name of writing group: ");
        dbin.groupName = dbin.scan.nextLine();
        
        String PKstr = "bookTitle = '" + dbin.bookTitle + "' AND "
                + "groupName = '" + dbin.groupName + '\''; 
        
        //Checks for already existing entry using PK
        if (dbin.isPK(dbin, "Books", PKstr))
        {
                System.out.println("Book already in DB");
                return;
        }
        
        //Checks if Writing Group is already in DB
        if (!dbin.isValueStored(dbin, "WritingGroups", "groupName", dbin.groupName))
        {
            //Adds Writing Group
            addWritingGroup(dbin);
        }
        

        System.out.print("Enter name of publisher: ");
        dbin.publisherName = dbin.scan.nextLine();
        
        //Checks if publisher already exists on DB
        if (!dbin.isValueStored(dbin, "Publishers", "publisherName", dbin.publisherName))
        {
            //Adds Publisher
            this.addPublisher(dbin);
        }
        
        System.out.print("Enter year published: ");
        dbin.yearPublished = dbin.scan.nextLine();
        System.out.print("Enter number of pages: ");
        dbin.numberPages = dbin.scan.nextInt();       
        dbin.sql = "INSERT INTO Books VALUES('" + dbin.bookTitle + "', '" 
                + dbin.groupName + "', '" + dbin.publisherName + "', '"
                + dbin.yearPublished + "', " + dbin.numberPages + ")";
        
        //System.out.println(dbin.sql);
        
        dbin.stmt.executeUpdate(dbin.sql); //////executing sql, sends results to rs

    }
    
    public void replacePub(Database dbin) throws SQLException
    {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
    }
    
    public void addWritingGroup(Database dbin) throws SQLException
    {
        System.out.print("Enter last name of head writer: ");
        dbin.headWriter = dbin.scan.nextLine();
        System.out.print("Enter year " + dbin.groupName + " formed: ");
        dbin.yearFormed = dbin.scan.nextInt();
        System.out.print("Enter subject: ");
        dbin.subject = dbin.scan.nextLine();
        dbin.sql = "INSERT INTO WritingGroups VALUES('"
                + dbin.groupName    + "', '"
                + dbin.headWriter   + "', "
                + dbin.yearFormed   + ", '"
                + dbin.subject      + "')";
        
        //System.out.println(dbin.sql);
        
        dbin.stmt.executeUpdate(dbin.sql);
    }
    
    public void addPublisher(Database dbin) throws SQLException
    {
        System.out.print("Enter publisher's address: ");
        dbin.publisherAddress = dbin.scan.nextLine();
        System.out.print("Enter publisher's phone: ");
        dbin.publisherPhone = dbin.scan.nextLine();
        System.out.print("Enter publisher's email: ");
        dbin.publisherEmail = dbin.scan.nextLine();
        
        dbin.sql = "INSERT INTO Publishers VALUES('"
                + dbin.publisherName    + "', '"
                + dbin.publisherAddress + "', '"
                + dbin.publisherPhone   + "', '"
                + dbin.publisherEmail   + "')";
        
        //System.out.println(dbin.sql);
        
        dbin.stmt.executeUpdate(dbin.sql);
    }
}