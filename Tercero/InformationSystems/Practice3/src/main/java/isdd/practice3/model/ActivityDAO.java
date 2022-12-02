package isdd.practice3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pepe
 */
public class ActivityDAO {
    
    private ConnectionDB conn = null;
    private PreparedStatement ps = null;

    public ActivityDAO(ConnectionDB conn) {
        this.conn = conn;
    }
    
    public ArrayList<Activity> listAllActivities() throws SQLException {
        ArrayList<Activity> list = new ArrayList<>();
        
        ps = conn.getConnection().prepareStatement("SELECT * FROM ACTIVITY");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Activity(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        return list;
    }
    
    
}
