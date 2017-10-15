/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc1;

import java.sql.ResultSet;
import java.sql.SQLException;
import static jdbc1.Database.dispNull;

/**
 *
 * @author Kunal and Dennis
 */
public class List
{
    final String dfSelectGroup = "%-50s%-50s%-10s%-10s\n";
    final String dfAllTitle = "%-50s%-50s\n";
    final String dfSelectTitle = "%-50s%-8s%-8s\n";///////////fix for ALL data for all scenarios...
    
    public void allWritingGroups(Database dbin) throws SQLException
    {
        dbin.sql = "SELECT DISTINCT groupName FROM WritingGroups";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
        System.out.println("Groups");
        while (dbin.rs.next())
        {
            System.out.println(dbin.rs.getString("groupName"));
        }
        
        System.out.println();
    }
    
    public void selectGroup(Database dbin) throws SQLException
    {
        System.out.print("Enter group name: ");
        
        dbin.groupName = dbin.scan.nextLine();
        
        if (!dbin.isValueStored(dbin, "WritingGroups", "groupName", dbin.groupName))
        {
            System.out.println("Group was not found\n");
            return;
        }
        
        dbin.sql = "SELECT * FROM WritingGroups WHERE groupName = '" 
                + dbin.groupName + '\'';
            
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
        
        System.out.println(dbin.sql);
        
        System.out.printf(dfSelectGroup, "Group", "Writer", "Year", "Subject");
        
        dbin.rs.next();
        
        dbin.groupName = dbin.rs.getString("groupName");
        dbin.headWriter = dbin.rs.getString("headWriter");
        dbin.yearFormed = dbin.rs.getInt("yearFormed");
        dbin.subject = dbin.rs.getString("subject");
        
        System.out.printf(dfSelectGroup, 
                dbin.dispNull(dbin.rs.getString("groupName")), 
                dbin.dispNull(dbin.rs.getString("headWriter")), 
                dbin.rs.getInt("yearFormed"),
                dbin.dispNull(dbin.rs.getString("subject")));
    }
    
    public void allPublishers(Database dbin) throws SQLException
    {
        dbin.sql = "SELECT DISTINCT publisherName FROM Publishers";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
        System.out.println("Publishers");
        while (dbin.rs.next())
        {
            System.out.println(dbin.rs.getString("publisherName"));
        }
        
        System.out.println();
    }
    
    public void allTitles(Database dbin) throws SQLException
    {
        dbin.sql = "SELECT DISTINCT bookTitle, groupName FROM Books NATURAL "
                + "JOIN WritingGroups";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
        System.out.printf(dfAllTitle, "Titles", "Groups");
        while (dbin.rs.next())
        {
            System.out.printf(dfAllTitle, dbin.rs.getString("bookTitle"),
                    dbin.rs.getString("groupName"));
        }
        
        System.out.println();
    }
    
    public void selectBook(Database dbin)
    {
        try
        {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
        } catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
    
    public void output(Database dbin) throws SQLException
    {
        System.out.printf(dbin.displayFormat, "ID", "First Name", "Last Name", "Phone #");
        while (dbin.rs.next()) 
        {
            //Retrieve by column name
            String id = dbin.rs.getString("au_id");
            String phone = dbin.rs.getString("phone");
            String first = dbin.rs.getString("au_fname");
            String last = dbin.rs.getString("au_lname");

            //Display values
            System.out.printf(dbin.displayFormat,
                    dispNull(id), dispNull(first), dispNull(last), dispNull(phone));
        }
    }
}
