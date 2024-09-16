package isdd.practice5.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pepe
 */
public class MemberDAO {
    
    private Session session;
    
    public MemberDAO(Session sess) {
        this.session = sess;
    }
    
    public ArrayList<Object[]> listAllMembers() {
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM MEMBER ORDER BY M_NUM");
        
        ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();

        tr.commit();
        
        return list;
    }
    
    public void insertMember(Member1 m) {
        Transaction tr = session.beginTransaction();
        session.save(m);
        tr.commit();
    }
    
    public void updateMember(Member1 m) {
        Transaction tr = session.beginTransaction();
        session.save(m);
        tr.commit();
    }
    
    public void deleteMember(String id) {
        Transaction tr = session.beginTransaction();
        Member1 m = session.get(Member1.class, id);
        session.delete(m);
        tr.commit();
    }
    
    /// DEFENSE CODE

    public ArrayList<Object> activitiesFeeFromMember(String id) {
        Transaction tr = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT a.A_PRICE FROM PERFORMS p INNER JOIN ACTIVITY a ON p.P_ID = a.A_ID WHERE p.P_NUM = \'" + id + "\'");
        
        ArrayList<Object> list = (ArrayList<Object>) query.list();
        
        tr.commit();

        return list;
    }
    /// ---------------------------------------------
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Birthday", "Phone", "E-Mail", "Starting Date", "Category"};
    }
}
