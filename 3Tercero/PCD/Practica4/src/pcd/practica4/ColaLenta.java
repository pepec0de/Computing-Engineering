package pcd.practica4;

/**
 * Clase ColaLenta
 *
 * @author pepe
 */
public class ColaLenta implements ICola {

    private int head, tail, numelementos, capacidad;
    private Object datos[];
    private CanvasCola canvas;

    public ColaLenta(int c, CanvasCola canvas) {
        head = tail = numelementos = 0;
        capacidad = c;
        datos = new Object[c];
        this.canvas = canvas;
    }

    @Override
    public int GetNum() {
        return numelementos;
    }

    @Override
    public synchronized void Acola(Object el) throws Exception {
        Thread.sleep(100);

        int intentos = 0;

        while (colallena()) {
            System.out.println("Cola llena Productor " + Thread.currentThread().getId() + " esperando");
            canvas.avisa("Cola llena");

            if (intentos == 3)
                throw new Exception("Cola llena");
            
            intentos++;
            wait();
        }

        datos[tail] = el;

        Thread.sleep(100);
        tail = (tail + 1) % capacidad;

        Thread.sleep(100);
        numelementos++;

        canvas.representa(datos, head, tail, numelementos);
        notifyAll();
    }

    @Override
    public synchronized Object Desacola() throws Exception {
        Thread.sleep(100);

        int intentos = 0;
        while (colavacia()) {
            System.out.println("Cola vacia Consumidor " + Thread.currentThread().getId() + " esperando");
            canvas.avisa("Cola vacía");
            
            if (intentos == 3)
                throw new Exception("Cola llena");
            
            intentos++;
            wait();
        }

        Object el = Primero();

        Thread.sleep(100);
        head = (head + 1) % capacidad;

        Thread.sleep(100);
        numelementos--;

        canvas.representa(datos, head, tail, numelementos);

        notifyAll();
        return el;
    }

    @Override
    public Object Primero() throws Exception {
        if (colavacia()) {
            throw new Exception("Cola vacia");
        }
        return datos[head];
    }

    private boolean colavacia() {
        return numelementos == 0;
    }

    private boolean colallena() {
        return numelementos == capacidad;
    }

}
