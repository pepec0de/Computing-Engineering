/*
 * 
 * 
 */
package isdd.practice3.view;

import java.sql.*;

/**
 *
 * @author Pepe
 */
public class ConsoleView {
    
    public ConsoleView() {}
    
    public void consoleViewLogin(String text) {
        System.out.println("***********************************************");
        System.out.println(text);
        //System.out.println("***********************************************");
    }
    
    public void consoleViewLogin(String text, String error) {
        consoleViewLogin(text + "\nERROR: " + error);
    }
    
    public void MetadataView(DatabaseMetaData dbmd) {
        String text = "Connection Metadata:\n";
        try {
            text += "Database version: " + dbmd.getDatabaseProductVersion() + "\n"
                  + "URL: " + dbmd.getURL() + "\n"
                  + "Driver: " + dbmd.getDriverName() + "\n"
                  + "User: " + dbmd.getUserName() + "\n"
                  + "Max username lenght: " + dbmd.getMaxUserNameLength() + "\n"
                  + "Max columns in table: " + dbmd.getMaxColumnsInTable();
            consoleViewLogin(text);
        } catch (SQLException ex) {
            consoleViewLogin("An error has ocurred showing metadata", ex.getMessage());
        }
    }
}
