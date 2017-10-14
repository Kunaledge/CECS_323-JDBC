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
        System.out.println("Enter book title: ");
        dbin.bookTitle = dbin.scan.nextLine();
        System.out.println("Enter name of writing group: ");
        dbin.groupName = dbin.scan.nextLine();
        //if group name not found solution here
        System.out.println("Enter name of publisher: ");
        dbin.publisherName = dbin.scan.nextLine();
        System.out.println("Enter year published: ");
        dbin.yearPublished = dbin.scan.nextLine();
        System.out.println("Enter number of pages: ");
        dbin.numberPages = dbin.scan.nextInt();
        //if publisher not found solution here
        
        
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "INSERT Books VALUES('" + dbin.bookTitle + "', '" 
                + dbin.groupName + "', '" + dbin.publisherName + "', '"
                + dbin.yearPublished + "', " + dbin.numberPages + ");";
        System.out.println(dbin.sql);
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs

    }
    
    public void replacePub(Database dbin) throws SQLException
    {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
    }
}
