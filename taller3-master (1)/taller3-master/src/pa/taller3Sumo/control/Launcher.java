package pa.taller3Sumo.control;

import javax.swing.SwingUtilities;
import pa.taller3Sumo.vista.VentanaPrincipal;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}