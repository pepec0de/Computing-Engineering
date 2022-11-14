package isdd.practice3.model;

import java.sql.Connection;

/**
 *
 * @author Pepe
 */
public class Query {
    
    private Connection conn;
    
    public Query(Connection c) {
        conn = c;
    }
    
    public Object[][] getTableFromQuery(String cmd) throws Exception {
        
        return null;
    }
}
