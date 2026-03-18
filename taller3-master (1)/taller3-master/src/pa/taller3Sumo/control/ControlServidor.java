package pa.taller3Sumo.control;

import pa.taller3Sumo.socket.ServidorSocket;
import pa.taller3Sumo.vista.VentanaServidor;

public class ControlServidor {

    private ServidorSocket servidor;
    private VentanaServidor vista;

    public ControlServidor(VentanaServidor vista) {
        this.vista = vista;
    }

    public void iniciarServidor() {
        servidor = new ServidorSocket(vista);

        Thread hiloServidor = new Thread(() -> {
            servidor.iniciar();
        });

        hiloServidor.start();
    }
}