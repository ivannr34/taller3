package pa.taller3Sumo.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pa.taller3Sumo.modelo.Luchador;

public class ClienteSocket {

    public String enviarLuchador(Luchador luchador) {
        String resultado = "Sin respuesta del servidor";

        try (
            Socket socket = new Socket("localhost", 5000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ) {
            // IMPORTANTE: mandar el header del ObjectOutputStream
            out.flush();

            // Enviar luchador
            out.writeObject(luchador);
            out.flush();

            // Esperar respuesta del servidor
            Object respuesta = in.readObject();

            if (respuesta instanceof String) {
                resultado = (String) respuesta;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultado = "Error de conexión: " + e.getMessage();
        }

        return resultado;
    }
}