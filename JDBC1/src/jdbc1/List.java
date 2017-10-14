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
    public void allWritingGroups(Database dbin) throws SQLException
    {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
    }
    
    public void selectGroup(Database dbin) throws SQLException
    {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
    }
    
    public void allPublishers(Database dbin) throws SQLException
    {
        //STEP 4: Executing a query example
        System.out.println("Creating statement...");
        dbin.stmt = dbin.conn.createStatement(); /////creates statement object
        dbin.sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
        dbin.rs = dbin.stmt.executeQuery(dbin.sql); //////executing sql, sends results to rs
    }
    
    public void allTitles(Database dbin)
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
