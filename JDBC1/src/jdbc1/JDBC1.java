/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc1;

/**
 *
 * @author Mimi Opkins with some tweaking from Dave Brown
 * @modified by Kunal and Dennis
 */
public class JDBC1 
{
//    //  Database credentials
//    static String USER;
//    static String PASS;
//    static String DBNAME;
//    //This is the specification for the printout that I'm doing:
//    //each % denotes the start of a new field.
//    //The - denotes left justification.
//    //The number indicates how wide to make the field.
//    //The "s" denotes that it's a string.  All of our output in this test are
//    //strings, but that won't always be the case.
//    static final String displayFormat="%-5s%-15s%-15s%-15s\n";
//    // JDBC driver name and database URL
//    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
//    static String DB_URL = "jdbc:derby://localhost:1527/";    
//    //            + "testdb;user=";
//    
    /**
     * Takes the input string and outputs "N/A" if the string is empty or null.
     * @param input The string to be mapped.
     * @return  Either the input string or "N/A" as appropriate.
     */
    public static void main(String[] args) 
    {
        Database db = new Database();
        db.start();

    }//end main
}//end FirstExample}

