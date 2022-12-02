package isdd.practice3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pepe
 */
public class MemberDAO {
    
    private ConnectionDB conn;
    private PreparedStatement ps;

    public MemberDAO(ConnectionDB conn) {
        this.conn = conn;
    }
    
    public ArrayList<Member> listAllMembers() throws SQLException {
        ArrayList<Member> list = new ArrayList<>();
        
        ps = conn.getConnection().prepareStatement("SELECT * FROM MEMBER");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Member(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8)));
        }
        return list;
    }
    
    public boolean insertMember(Member member) throws SQLException {
        boolean ok = false;
        try {
            ps = conn.getConnection().prepareStatement("INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, member.getM_num());
            ps.setString(2, member.getM_name());
            ps.setString(3, member.getM_id());
            ps.setString(4, member.getM_birthdate());
            ps.setString(5, member.getM_phone());
            ps.setString(6, member.getM_emailMember());
            ps.setString(7, member.getM_startingDateMember());
            ps.setString(8, member.getM_cathegoryMember());
            
            ps.executeUpdate();
            
            ps.close();
            ok = true;
        } catch (SQLException e) {
            throw e;
        }
        return ok;
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Birthday", "Phone", "E-Mail", "Starting Date", "Category"};
    }
}
