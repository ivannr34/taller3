package pa.taller3Sumo.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pa.taller3Sumo.modelo.Luchador;

public class ManejadorCliente extends Thread {

    private Socket socket;
    private ServidorSocket servidor;
    private ObjectOutputStream out;
    private Luchador luchador;

    public ManejadorCliente(Socket socket, ServidorSocket servidor) {

        this.socket = socket;
        this.servidor = servidor;

    }

    public void run() {
        try {

            ObjectInputStream in
                    = new ObjectInputStream(socket.getInputStream());

            out = new ObjectOutputStream(socket.getOutputStream());

            luchador = (Luchador) in.readObject();

            servidor.agregarLuchador(luchador);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Luchador getLuchador() {
        return luchador;
    }

    public void enviarResultado(String mensaje) {
        try {
            out.writeObject(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
