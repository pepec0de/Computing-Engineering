package isdd.practice4.model;

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
    
    public ArrayList<Trainer> listAllTrainers() throws SQLException {
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
    
    public void insertTrainer(Trainer trainer) throws SQLException {
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
    }
    
    public void updateTrainer(Trainer trainer) throws SQLException {
        ps = conn.getConnection().prepareStatement("UPDATE TRAINER SET T_NAME = ?, T_IDNUMBER = ?, T_PHONENUMBER = ?, T_EMAIL = ?, T_DATE = ?, T_NICK = ? WHERE T_COD = ?");
        ps.setString(1, trainer.getT_name());
        ps.setString(2, trainer.getT_idNumber());
        ps.setString(3, trainer.getT_phoneNumber());
        ps.setString(4, trainer.getT_email());
        ps.setString(5, trainer.getT_date());
        ps.setString(6, trainer.getT_nick());
        ps.setString(7, trainer.getT_cod());

        ps.executeUpdate();

        ps.close();
    }
    
    public void deleteTrainer(String code) throws SQLException {
        ps = conn.getConnection().prepareStatement("DELETE FROM TRAINER WHERE T_COD = ?");
        ps.setString(1, code);

        ps.executeUpdate();

        ps.close();
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Phone Number", "E-Mail", "Date", "Nickname"};
    }
}
