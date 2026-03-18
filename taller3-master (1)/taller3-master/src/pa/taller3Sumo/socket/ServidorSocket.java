package pa.taller3Sumo.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.modelo.Dohyo;
import pa.taller3Sumo.vista.VentanaServidor;

public class ServidorSocket {

    private List<Luchador> luchadores = new ArrayList<>();
    private List<ManejadorCliente> clientes = new ArrayList<>();
    private VentanaServidor vista;

    public ServidorSocket(VentanaServidor vista) {

        this.vista = vista;

    }

    public void iniciar() {

        try {

            ServerSocket server = new ServerSocket(5000);

            vista.registrarMovimiento("Servidor iniciado");

            while (true) {

                Socket socket = server.accept();

                ManejadorCliente handler = new ManejadorCliente(socket, this);
                clientes.add(handler);
                handler.start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized void agregarLuchador(Luchador l) {

        luchadores.add(l);

        vista.registrarMovimiento("Luchador recibido: " + l.getNombre());

        if (luchadores.size() == 2) {

            iniciarCombate();

        }

    }

    private void iniciarCombate() {

        Dohyo dohyo = new Dohyo(luchadores.get(0), luchadores.get(1));

        vista.registrarMovimiento("Iniciando combate...");

        dohyo.iniciarCombate();

        Luchador ganador = dohyo.getGanador();

        vista.registrarMovimiento("GANADOR: " + ganador.getNombre());

        luchadores.clear();
        
        for (ManejadorCliente c : clientes) {

            if (c.getLuchador().getNombre().equals(ganador.getNombre())) {
                c.enviarResultado("GANASTE");
            } else {
                c.enviarResultado("PERDISTE");
            }
        }

    }
}
