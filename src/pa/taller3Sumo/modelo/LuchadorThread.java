package pa.taller3Sumo.modelo;

public class LuchadorThread extends Thread {

    private Luchador luchador;
    private Dohyo dohyo;

    public LuchadorThread(Luchador luchador, Dohyo dohyo) {
        this.luchador = luchador;
        this.dohyo = dohyo;
    }

    @Override
    public void run() {
        while (luchador.estaDentroDohyo() && luchador.getRival().estaDentroDohyo()) {
            dohyo.turno(luchador);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}