package pa.taller3Sumo.control;

import javax.swing.SwingUtilities;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControlPrincipal controlPrincipal = new ControlPrincipal();
            controlPrincipal.iniciar();
        });
    }
}