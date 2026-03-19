package pa.taller3Sumo.control;

import pa.taller3Sumo.vista.VentanaCliente;
import pa.taller3Sumo.vista.VentanaPrincipal;
import pa.taller3Sumo.vista.VentanaServidor;

public class ControlPrincipal {

    private VentanaPrincipal vistaPrincipal;
    private VentanaServidor vistaServidor;
    private VentanaCliente vistaCliente;

    public void iniciar() {
        vistaPrincipal = new VentanaPrincipal();

        vistaPrincipal.getBtnServidor().addActionListener(e -> abrirVentanaServidor());
        vistaPrincipal.getBtnCliente().addActionListener(e -> abrirVentanaCliente());
        vistaPrincipal.getBtnSalir().addActionListener(e -> salirAplicacion());

        vistaPrincipal.setVisible(true);
    }

    private void abrirVentanaServidor() {
        vistaServidor = new VentanaServidor(vistaPrincipal);
        vistaServidor.setVisible(true);
        vistaPrincipal.setVisible(false);
    }

    private void abrirVentanaCliente() {
        vistaCliente = new VentanaCliente(vistaPrincipal);
        vistaCliente.setVisible(true);
        vistaPrincipal.setVisible(false);
    }

    private void salirAplicacion() {
        if (vistaPrincipal != null) {
            vistaPrincipal.dispose();
        }
        System.exit(0);
    }
}