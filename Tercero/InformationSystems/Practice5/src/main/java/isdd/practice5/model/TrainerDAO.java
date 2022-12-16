package isdd.practice5.model;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pepe
 */
public class TrainerDAO {
    
    private Session session;
    
    public TrainerDAO(Session sess) {
        this.session = sess;
    }
    
    public ArrayList<Object[]> listAllTrainers() {
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM TRAINER");
        
        ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();

        tr.commit();
        
        return list;
    }
    
    public void insertTrainer(Trainer a) {
        
    }
    
    public void updateTrainer(Trainer a) {
        
    }
    
    public void deleteTrainer(String id) {
        
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Phone Number", "E-Mail", "Date", "Nickname"};
    }
}
