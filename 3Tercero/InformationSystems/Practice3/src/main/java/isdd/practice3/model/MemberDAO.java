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
    
    public void insertMember(Member member) throws SQLException {
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
    }
    
    public void updateMember(Member member) throws SQLException {
        ps = conn.getConnection().prepareStatement("UPDATE MEMBER SET M_NAME = ?, M_ID = ?, M_BIRHTDATE = ?, M_PHONE = ?, M_EMAILMEMBER = ?, M_STARTINGDATEMEMBER = ?, M_CATHEGORYMEMBER = ? WHERE M_NUM = ?");
        ps.setString(1, member.getM_name());
        ps.setString(2, member.getM_id());
        ps.setString(3, member.getM_birthdate());
        ps.setString(4, member.getM_phone());
        ps.setString(5, member.getM_emailMember());
        ps.setString(6, member.getM_startingDateMember());
        ps.setString(7, member.getM_cathegoryMember());
        ps.setString(8, member.getM_num());
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    public void deleteMember(String num) throws SQLException {
        ps = conn.getConnection().prepareStatement("DELETE FROM MEMBER WHERE M_NUM = ?");
        ps.setString(1, num);

        ps.executeUpdate();

        ps.close();
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Birthday", "Phone", "E-Mail", "Starting Date", "Category"};
    }
}
