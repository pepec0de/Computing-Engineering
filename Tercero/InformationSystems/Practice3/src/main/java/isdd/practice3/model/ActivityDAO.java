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
    
    public void insertActivity(Activity a) throws SQLException {
        ps = conn.getConnection().prepareStatement("INSERT INTO ACTIVITY VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, a.getA_id());
        ps.setString(2, a.getA_name());
        ps.setString(3, a.getA_description());
        ps.setString(4, a.getA_price());
        ps.setString(5, a.getA_trainerInCharge());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void updateActivity(Activity a) throws SQLException {
        ps = conn.getConnection().prepareStatement("UPDATE ACTIVITY SET A_NAME = ?, A_DESCRIPTION = ?, A_PRICE = ?, A_TRAINERINCHARGE = ? WHERE A_ID = ?");
        ps.setString(1, a.getA_name());
        ps.setString(2, a.getA_description());
        ps.setString(3, a.getA_price());
        ps.setString(4, a.getA_trainerInCharge());
        ps.setString(5, a.getA_id());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void deleteActivity(String id) throws SQLException {
        ps = conn.getConnection().prepareStatement("DELETE FROM ACTIVITY WHERE A_ID = ?");
        ps.setString(1, id);

        ps.executeUpdate();

        ps.close();
    }
    
    
    
    public String[] columnNames() {
        return new String[] {"ID", "Name", "Description", "Price", "Trainer in Charge"};
    }
}
