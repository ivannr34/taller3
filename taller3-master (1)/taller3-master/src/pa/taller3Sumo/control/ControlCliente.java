package pa.taller3Sumo.control;

import java.util.List;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.socket.ClienteSocket;

public class ControlCliente {

    public Luchador crearLuchador(String nombre, double peso, String tunica) {
        List<String> tecnicas = ControlKimarite.cargarTecnicas();
        return new Luchador(nombre, peso, tunica, tecnicas);
    }

    public void enviarLuchadores(Luchador luchador1, Luchador luchador2) {
        ClienteSocket cliente1 = new ClienteSocket();
        cliente1.enviarLuchador(luchador1);

        ClienteSocket cliente2 = new ClienteSocket();
        cliente2.enviarLuchador(luchador2);
    }
}