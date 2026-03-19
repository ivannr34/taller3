package pa.taller3Sumo.control;

import java.util.List;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.socket.ClienteSocket;

public class ControlCliente {

    public List<String> obtenerTecnicasDisponibles() {
        return ControlKimarite.cargarTecnicas();
    }

    public Luchador crearLuchador(String nombre, double peso, String tunica, List<String> tecnicas) {
        return new Luchador(nombre, peso, tunica, tecnicas);
    }

    public String enviarLuchador(Luchador luchador) {
        ClienteSocket clienteSocket = new ClienteSocket();
        return clienteSocket.enviarLuchador(luchador);
    }
}