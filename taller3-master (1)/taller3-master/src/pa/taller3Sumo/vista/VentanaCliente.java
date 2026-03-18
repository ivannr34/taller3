package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import pa.taller3Sumo.control.ControlCliente;
import pa.taller3Sumo.modelo.Luchador;

public class VentanaCliente extends JFrame {

    private VentanaLuchador panelLuchador1;
    private VentanaLuchador panelLuchador2;

    private JButton botonEnviar;
    private JButton botonVolver;

    private VentanaPrincipal ventanaPrincipal;
    private ControlCliente controlCliente;

    public VentanaCliente(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlCliente = new ControlCliente();

        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Registro de 2 Luchadores");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        panelLuchador1 = new VentanaLuchador("Luchador 1");
        panelLuchador2 = new VentanaLuchador("Luchador 2");

        botonEnviar = new JButton("Enviar Luchadores");
        botonVolver = new JButton("Volver");

        botonEnviar.setFont(new Font("Arial", Font.BOLD, 15));
        botonVolver.setFont(new Font("Arial", Font.BOLD, 15));
    }

    private void construirInterfaz() {
        JLabel titulo = new JLabel("REGISTRO DE LUCHADORES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 15, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCentral.add(panelLuchador1);
        panelCentral.add(panelLuchador2);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonEnviar);
        panelBotones.add(botonVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        botonEnviar.addActionListener(e -> enviarLuchadores());
        botonVolver.addActionListener(e -> volverAVentanaPrincipal());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });
    }

    private void enviarLuchadores() {
        try {
            Luchador luchador1 = construirLuchadorDesdePanel(panelLuchador1, 1);
            if (luchador1 == null) return;

            Luchador luchador2 = construirLuchadorDesdePanel(panelLuchador2, 2);
            if (luchador2 == null) return;

            controlCliente.enviarLuchadores(luchador1, luchador2);

            JOptionPane.showMessageDialog(this, "Los 2 luchadores fueron enviados correctamente.");
            limpiarCampos();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al enviar los luchadores: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Luchador construirLuchadorDesdePanel(VentanaLuchador panel, int numero) {
        String nombre = panel.getNombreLuchador();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del Luchador " + numero + " no puede estar vacío.");
            return null;
        }

        double peso;
        try {
            peso = Double.parseDouble(panel.getPesoLuchador());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El peso del Luchador " + numero + " debe ser un número válido.");
            return null;
        }

        return controlCliente.crearLuchador(nombre, peso, panel.getTunicaSeleccionada());
    }

    private void limpiarCampos() {
        panelLuchador1.limpiarCampos();
        panelLuchador2.limpiarCampos();
    }

    private void volverAVentanaPrincipal() {
        dispose();
        ventanaPrincipal.setVisible(true);
    }
}