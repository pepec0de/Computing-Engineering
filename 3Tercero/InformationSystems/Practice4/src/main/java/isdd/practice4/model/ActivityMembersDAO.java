package isdd.practice4.model;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Pepe
 */
public class ActivityMembersDAO {

    private ConnectionDB conn = null;
    private CallableStatement cs = null;
    private ResultSet rs = null;
    private int ROW_LENGTH = 3;

    public ActivityMembersDAO(ConnectionDB conn) {
        this.conn = conn;
    }

    public ArrayList<String[]> listMembersInActivity(String a_id) throws SQLException {
        switch (conn.getDbms()) {
            case "oracle":
                cs = conn.getConnection().prepareCall("{ CALL FINDACTIVITYMEMBERS(?, ?)}");
                cs.setString(1, a_id);
                cs.registerOutParameter(2, OracleTypes.REF_CURSOR);

                cs.executeUpdate();

                rs = (ResultSet) cs.getObject(2);
                break;
                
            case "mariadb":
                cs = conn.getConnection().prepareCall("{ CALL FINDACTIVITYMEMBERS(?) }");
                cs.setString(1, a_id);
                
                cs.execute();
                
                rs = cs.getResultSet();
                break;

            default:
                throw new SQLException("Not supported database");
        }
        
        ArrayList<String[]> r = new ArrayList<>();

        String[] row;
        while (rs.next()) {
            row = new String[ROW_LENGTH];
            for (int i = 1; i <= ROW_LENGTH; i++) {
                row[i - 1] = rs.getString(i);
            }
            r.add(row);
        }
        rs.close();
        cs.close();
        return r;
    }

    public String[] columnNames() {
        return new String[]{"Number", "Name", "E-Mail"};
    }
}
