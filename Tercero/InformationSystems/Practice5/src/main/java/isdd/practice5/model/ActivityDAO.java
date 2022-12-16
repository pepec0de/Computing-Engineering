package isdd.practice5.model;

import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pepe
 */
public class ActivityDAO {

    private Session session;
    
    public ActivityDAO(Session sess) {
        this.session = sess;
    }
    
    public ArrayList<Object[]> listAllActivities() {
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM ACTIVITY");
        
        ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();

        tr.commit();
        
        return list;
    }
    
    public void insertActivity(Activity a) {
        Transaction tr = session.beginTransaction();
        session.save(a);
        tr.commit();
    }
    
    public void updateActivity(Activity a) {
        Transaction tr = session.beginTransaction();
        session.save(a);
        tr.commit();
    }
    
    public void deleteActivity(String id) {
        Transaction tr = session.beginTransaction();
        Activity a = session.get(Activity.class, id);
        session.delete(a);
        tr.commit();
    }

    public String[] columnNames() {
        return new String[] {"ID", "Name", "Description", "Price", "Trainer in Charge"};
    }
}
