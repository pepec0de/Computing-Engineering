package isdd.practice4.model;

import java.sql.*;

/**
 *
 * @author Pepe
 */
public class ConnectionDB {
    
    private Connection connection;
    private String dbms;
    
    public ConnectionDB(String dbms, String ip, String db, String user, String password) throws SQLException {
        this.dbms = dbms;
        String url = null;
        if (dbms.equals("oracle")) {
            url = "jdbc:oracle:thin:@" + ip + ":" + db;
        } else if (dbms.equals("mariadb")) {
            url = "jdbc:mariadb://" + ip + "/" + db;
        }
        connection = DriverManager.getConnection(url, user, password);
        if (connection == null)
            throw new SQLException();
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void disconnect() throws SQLException {
        connection.close();
    }
    
    public DatabaseMetaData informationDB() throws SQLException {
        return connection.getMetaData();
    }

    public String getDbms() {
        return dbms;
    }
}
