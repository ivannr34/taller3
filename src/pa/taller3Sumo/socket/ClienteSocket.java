package pa.taller3Sumo.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import pa.taller3Sumo.modelo.Luchador;

public class ClienteSocket {

    public void enviarLuchador(Luchador luchador) {

        try {

            Socket socket = new Socket("localhost", 5000);

            ObjectOutputStream out
                    = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(luchador);

            socket.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
