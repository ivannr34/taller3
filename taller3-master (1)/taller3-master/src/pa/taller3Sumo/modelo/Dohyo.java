package pa.taller3Sumo.modelo;

import java.util.Random;

public class Dohyo {

    private Luchador l1;
    private Luchador l2;
    private Luchador ganador;
    private Random random = new Random();

    public Dohyo(Luchador l1, Luchador l2) {

        this.l1 = l1;
        this.l2 = l2;

    }

    public void iniciarCombate() {
        
        turnoL1 = true;
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
    private boolean turnoL1 = true;

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

            String tecnica = l.usarTecnica();

            System.out.println(l.getNombre() + " usa " + tecnica);
//se tiene que sacar este system out y que funcione con la vista
            int prob = random.nextInt(100);

            if (prob < 15) {
                l.getRival().setDentroDohyo(false);
                l.sumarVictoria();
                ganador = l;
                System.out.println("GANADOR: " + l.getNombre());
                notifyAll();
                return;
            }

            Thread.sleep(random.nextInt(500));

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
