package pa.taller3Sumo.socket;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import pa.taller3Sumo.modelo.Dohyo;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.vista.VentanaServidor;

public class ServidorSocket {

    private static final int PUERTO = 5000;

    private final List<Luchador> luchadores = new ArrayList<>();
    private final List<ManejadorCliente> clientesActivos = new ArrayList<>();
    private final VentanaServidor vista;

    public ServidorSocket(VentanaServidor vista) {
        this.vista = vista;
    }

    public void iniciar() {
        try (ServerSocket server = new ServerSocket(PUERTO)) {

            vista.registrarMovimiento("Servidor iniciado en puerto " + PUERTO);
            vista.registrarMovimiento("Esperando 2 clientes...");

            while (true) {
                Socket socket = server.accept();

                vista.registrarMovimiento("Cliente conectado: " + socket.getInetAddress());

                ManejadorCliente handler = new ManejadorCliente(socket, this);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
            vista.registrarMovimiento("Error en el servidor: " + e.getMessage());
        }
    }

    public synchronized void agregarLuchador(Luchador luchador, ManejadorCliente cliente) {
        luchadores.add(luchador);

        if (!clientesActivos.contains(cliente)) {
            clientesActivos.add(cliente);
        }

        vista.registrarMovimiento("Luchador recibido: " + luchador.getNombre());

        if (luchadores.size() == 2) {
            iniciarCombate();
        }
    }

    private synchronized void iniciarCombate() {
        vista.registrarMovimiento("Iniciando combate...");

        Dohyo dohyo = new Dohyo(luchadores.get(0), luchadores.get(1), vista);
        dohyo.iniciarCombate();

        Luchador ganador = dohyo.getGanador();

        if (ganador == null) {
            vista.registrarMovimiento("No se pudo determinar el ganador.");
            limpiarRonda();
            return;
        }

        String resumenCombate = construirResumenCombate(ganador);

        vista.registrarMovimiento("GANADOR: " + ganador.getNombre());

        // Mostrar ventana del ganador
        vista.mostrarGanador(ganador.getNombre(), resumenCombate);

        // Enviar resultado a los 2 clientes de esta ronda
        enviarResultadosClientes(ganador);

        // Limpiar para la siguiente pelea
        limpiarRonda();
        vista.registrarMovimiento("Servidor listo para una nueva pelea. Esperando 2 clientes...");
    }

    private String construirResumenCombate(Luchador ganador) {
        StringBuilder sb = new StringBuilder();

        sb.append("RESUMEN DEL COMBATE\n\n");

        sb.append("Luchador 1: ").append(luchadores.get(0).getNombre())
          .append(" | Peso: ").append(luchadores.get(0).getPeso())
          .append(" | Túnica: ").append(luchadores.get(0).getTunica()).append("\n");

        sb.append("Luchador 2: ").append(luchadores.get(1).getNombre())
          .append(" | Peso: ").append(luchadores.get(1).getPeso())
          .append(" | Túnica: ").append(luchadores.get(1).getTunica()).append("\n\n");

        sb.append("Ganador final: ").append(ganador.getNombre()).append("\n");

        return sb.toString();
    }

    private void enviarResultadosClientes(Luchador ganador) {
        for (ManejadorCliente cliente : clientesActivos) {
            if (cliente.getLuchador() != null) {
                if (cliente.getLuchador().getNombre().equals(ganador.getNombre())) {
                    cliente.enviarResultado("GANASTE");
                } else {
                    cliente.enviarResultado("PERDISTE");
                }
            }
        }
    }

    private void limpiarRonda() {
        luchadores.clear();
        clientesActivos.clear();
    }
}