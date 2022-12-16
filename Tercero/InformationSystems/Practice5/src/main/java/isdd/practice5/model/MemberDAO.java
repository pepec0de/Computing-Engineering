package isdd.practice5.model;

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
        Query query = session.createNativeQuery("SELECT * FROM MEMBER");
        
        ArrayList<Object[]> list = (ArrayList<Object[]>) query.list();

        tr.commit();
        
        return list;
    }
    
    public void insertMember(Member1 a) {
        
    }
    
    public void updateMember(Member1 a) {
        
    }
    
    public void deleteMember(String id) {
        
    }
    
    public String[] columnNames() {
        return new String[] {"Code", "Name", "ID", "Birthday", "Phone", "E-Mail", "Starting Date", "Category"};
    }
}
