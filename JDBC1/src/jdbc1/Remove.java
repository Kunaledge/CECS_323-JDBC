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
public class Remove 
{
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
}
