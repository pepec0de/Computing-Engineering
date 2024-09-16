/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcd.examen19;

/**
 *
 * @author Pepe
 */
public class Piscina {

    private int cupo = 0;
    private int nAdultosEspera = 0;
    private int adultos = 0;

    public Piscina() {

    }

    public synchronized void entraAdulto() throws InterruptedException {
        nAdultosEspera++;

        while (cupo == 5) {
            wait();
        }

        adultos++;
        nAdultosEspera--;
        cupo++;
    }

    public synchronized void SaleAdulto() {
        adultos--;
        cupo--;
        notifyAll();
    }

    public synchronized void EntraNinyo() throws InterruptedException, Exception {

        if (adultos == 0)
            throw new Exception("No hay adultos en la piscina");

        while (nAdultosEspera > 0 || cupo == 4) { // 4 ya que cada niño ocupa dos plazas
            wait();
        }

        cupo += 2;
    }

    public synchronized void SaleNinyo() {
        cupo -= 2;
        notifyAll();
    }
}
