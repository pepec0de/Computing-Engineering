package pcd.examen21.e1;


/**
 *
 * @author Pepe
 */
public class Coche implements Runnable {

    private int id;
    private Tunel tunel;

    public Coche(int id, Tunel tunel) {
        this.id = id;
        this.tunel = tunel;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Coche " + id + " esperando");
            tunel.entraCoche();
            
            // Prelavado
            System.out.println("Coche " + id + " Pre");
            Thread.sleep(2000);
            
            // Lavado
            System.out.println("Coche " + id + " Lavando");
            tunel.lavar();
            Thread.sleep(2000);
            
            // Secado
            System.out.println("Coche " + id + " Secando");
            tunel.secar(false);
            Thread.sleep(2000);
            
            System.out.println("Coche " + id + " sale");
            tunel.saleCoche();
        } catch (InterruptedException ex) {
            System.out.println("ERROR: Coche " + id + ex.getMessage());
        }
        
    }
    
}
