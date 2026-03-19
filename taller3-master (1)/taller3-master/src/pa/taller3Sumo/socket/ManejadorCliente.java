package pa.taller3Sumo.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pa.taller3Sumo.modelo.Luchador;

public class ManejadorCliente extends Thread {

    private Socket socket;
    private ServidorSocket servidor;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Luchador luchador;

    public ManejadorCliente(Socket socket, ServidorSocket servidor) {
        this.socket = socket;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try {
            // SIEMPRE primero Output, luego flush, luego Input
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();

            in = new ObjectInputStream(socket.getInputStream());

            // Recibir luchador
            luchador = (Luchador) in.readObject();

            // Enviar al servidor principal
            servidor.agregarLuchador(luchador, this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Luchador getLuchador() {
        return luchador;
    }

    public void enviarResultado(String mensaje) {
        try {
            if (out != null) {
                out.writeObject(mensaje);
                out.flush();
            }

            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}