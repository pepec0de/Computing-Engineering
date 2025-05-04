package isdd.practice5.controller;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Pepe
 */
public class HibernateUtil {

    private final SessionFactory sessionFactory;

    public HibernateUtil(String configFile) {
        this.sessionFactory = buildSessionFactory(configFile);
    }

    private SessionFactory buildSessionFactory(String configFile) {
        try {
            ServiceRegistry serviceRegistry = 
                    (ServiceRegistry) new StandardServiceRegistryBuilder().configure(configFile).build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }
}
