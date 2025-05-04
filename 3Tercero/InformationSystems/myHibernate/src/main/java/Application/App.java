/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;

import Model.Member1;
import Model.Trainer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pepe
 */
public class App {
    
    public static void main(String[] args) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        
        
        Query query = s.getNamedNativeQuery("Trainer.findAll");
        query.list();
        
        Transaction tr = s.beginTransaction();
        Trainer t = s.get(Trainer.class, "M001");
        System.out.println(t.getTName());
        
        Member1 mNew = new Member1("X888", "Jack Sparrow", "mId", "10/10/10", 'A');
        
        try {
            s.save(mNew);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tr.rollback();
        }
        
        
    }
}
