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
        Query query = session.createNativeQuery("SELECT * FROM TRAINER ORDER BY T_COD");
        
        ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();

        tr.commit();
        
        return list;
    }
    
    public void insertTrainer(Trainer t) {
        Transaction tr = session.beginTransaction();
        session.save(t);
        tr.commit();
    }
    
    public void updateTrainer(Trainer t) {
        Transaction tr = session.beginTransaction();
        session.save(t);
        tr.commit();
    }
    
    public void deleteTrainer(String id) {
        Transaction tr = session.beginTransaction();
        Trainer t = session.get(Trainer.class, id);
        session.delete(t);
        tr.commit();
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Phone Number", "E-Mail", "Date", "Nickname"};
    }
}
