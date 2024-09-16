package pcd.examen21.e1;

/**
 *
 * @author Pepe
 */
public class Furgoneta implements Runnable {

    private int id;
    private Tunel tunel;

    public Furgoneta(int id, Tunel tunel) {
        this.id = id;
        this.tunel = tunel;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Furgo " + id + " esperando");
            tunel.entraFurgo();
            
            // Prelavado
            System.out.println("Furgo " + id + " Pre");
            Thread.sleep(2000);
            
            // Lavado
            tunel.lavar();
            System.out.println("Furgo " + id + " Lavando");
            Thread.sleep(2000);
            
            // Secado
            tunel.secar(true);
            System.out.println("Furgo " + id + " Secando");
            Thread.sleep(2000);
            
            System.out.println("Furgo " + id + " sale");
            tunel.saleFurgo();
        } catch (InterruptedException ex) {
            System.out.println("ERROR: Furgo " + id + ex.getMessage());
        }
    }
    
}
