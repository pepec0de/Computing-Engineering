package isdd.practice4.model;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pepe
 */
public class ActivityMembersDAO {
    
    private ConnectionDB conn = null;
    private CallableStatement cs = null;

    public ActivityMembersDAO(ConnectionDB conn) {
        this.conn = conn;
    }
    
    public ArrayList<Member> listMembersInActivity(String a_id) throws SQLException {
        ArrayList<Member> r = new ArrayList<>();
        cs = conn.getConnection().prepareCall("{ call }");

        cs.executeUpdate();
        return r;
    }
}
