package pa.taller3Sumo.modelo;

import java.util.Random;
import pa.taller3Sumo.vista.VentanaServidor;

public class Dohyo {

    private Luchador l1;
    private Luchador l2;
    private Luchador ganador;
    private boolean turnoL1 = true;
    private Random random = new Random();
    private VentanaServidor vista;

    public Dohyo(Luchador l1, Luchador l2, VentanaServidor vista) {
        this.l1 = l1;
        this.l2 = l2;
        this.vista = vista;
    }

    public void iniciarCombate() {
        turnoL1 = true;
        ganador = null;

        l1.setRival(l2);
        l2.setRival(l1);

        LuchadorThread t1 = new LuchadorThread(l1, this);
        LuchadorThread t2 = new LuchadorThread(l2, this);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void turno(Luchador l) {
        try {
            if (ganador != null) {
                notifyAll();
                return;
            }

            while ((l == l1 && !turnoL1) || (l == l2 && turnoL1)) {
                wait();
                if (ganador != null) {
                    notifyAll();
                    return;
                }
            }

            if (ganador != null) {
                notifyAll();
                return;
            }

            // Usa una técnica aleatoria SOLO de las seleccionadas del luchador
            String tecnica = l.usarTecnica();

            if (vista != null) {
                vista.registrarMovimiento(l.getNombre() + " usa la técnica: " + tecnica);
            }

            int prob = random.nextInt(100);

            // 15% de ganar con ese movimiento
            if (prob < 15) {
                l.getRival().setDentroDohyo(false);
                l.sumarVictoria();
                ganador = l;

                if (vista != null) {
                    vista.registrarMovimiento("¡¡" + l.getNombre() + " saca a su rival del dohyo!!");
                    vista.registrarMovimiento("GANADOR: " + l.getNombre());
                }

                notifyAll();
                return;
            }

            // Si no ganó, sigue el combate
            if (vista != null) {
                vista.registrarMovimiento("El ataque no fue decisivo...");
            }

            Thread.sleep(random.nextInt(500) + 200);

            turnoL1 = !turnoL1;
            notifyAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Luchador getGanador() {
        return ganador;
    }
}