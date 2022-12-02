package isdd.practice3.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pepe
 */
public class TrainerDAO {
    
    private ConnectionDB conn;
    private PreparedStatement ps;
    
    public TrainerDAO(ConnectionDB conn) {
        this.conn = conn;
    } 
    
    public ArrayList<Trainer> listAllMembers() throws SQLException {
        ArrayList<Trainer> list = new ArrayList<>();
        
        ps = conn.getConnection().prepareStatement("SELECT * FROM TRAINER");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Trainer(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)));
        }
        return list;
    }
    
    public boolean insertTrainer(Trainer trainer) throws SQLException {
        boolean ok = false;
        try {
            ps = conn.getConnection().prepareStatement("INSERT INTO TRAINER VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, trainer.getT_cod());
            ps.setString(2, trainer.getT_name());
            ps.setString(3, trainer.getT_idNumber());
            ps.setString(4, trainer.getT_phoneNumber());
            ps.setString(5, trainer.getT_email());
            ps.setString(6, trainer.getT_date());
            ps.setString(7, trainer.getT_nick());
            
            ps.executeUpdate();
            
            ps.close();
            ok = true;
        } catch (SQLException e) {
            throw e;
        }
        return ok;
    }
}
