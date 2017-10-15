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
    public void selectBook(Database dbin) throws SQLException
    {
        System.out.print("Enter book title: ");
        dbin.bookTitle = dbin.scan.nextLine();
        
        System.out.print("Enter writing group: ");
        dbin.groupName = dbin.scan.nextLine();
        
        String PK = "bookTitle = '" + dbin.bookTitle + "' AND groupName = '"
                + dbin.groupName + '\'';
        
        if (!dbin.isPK(dbin, "Books", PK))
        {
            System.out.println("Book not found in database. "
                    + "Check writing group and book title.");
            
            return;
        }
        
        dbin.sql = "DELETE FROM Books "
                + "WHERE bookTitle = '" + dbin.bookTitle + "' AND "
                + "groupName = '" + dbin.groupName + "'";
        
        dbin.stmt.executeUpdate(dbin.sql); //////executing sql, sends results to rs
    }
}
