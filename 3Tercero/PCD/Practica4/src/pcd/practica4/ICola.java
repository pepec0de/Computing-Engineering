/*
 * 
 * 
 */
package pcd.practica4;

/**
 *
 * @author Pepe
 */
public interface ICola {
    int GetNum();
    void Acola(Object el) throws Exception;
    Object Desacola() throws Exception;
    Object Primero() throws Exception;
}
