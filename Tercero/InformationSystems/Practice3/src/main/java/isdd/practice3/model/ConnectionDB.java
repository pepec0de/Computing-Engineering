package isdd.practice3.model;

import java.sql.*;

/**
 *
 * @author Pepe
 */
public class ConnectionDB {
    
    private Connection connection;
    
    public ConnectionDB(String dbms, String ip, String db, String user, String password) throws SQLException {
        String url = null;
        if (dbms.equals("oracle")) {
            url = "jdbc:oracle:thin:@" + ip + ":" + db;
        } else if (dbms.equals("mariadb")) {
            url = "jdbc:mariadb://" + ip + "/";
        }
        connection = DriverManager.getConnection(url, user, password);
        if (connection == null)
            throw new SQLException();
    }
    
    public void disconnect() throws SQLException {
        connection.close();
    }
    
    public DatabaseMetaData informationDB() throws SQLException {
        return connection.getMetaData();
    }
    
    public Object[][] getTableFromQuery(String cmd) throws Exception {
        Object[][] table;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(cmd);
        rs.getMetaData().
        
        return null;
    }
}
