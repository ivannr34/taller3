package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import pa.taller3Sumo.control.ControlCliente;
import pa.taller3Sumo.modelo.Luchador;

public class VentanaCliente extends JFrame {

    private VentanaLuchador panelLuchador;

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
        setTitle("Registro de Luchador");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        List<String> tecnicasDisponibles;

        try {
            tecnicasDisponibles = controlCliente.obtenerTecnicasDisponibles();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error cargando técnicas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            tecnicasDisponibles = java.util.Collections.emptyList();
        }

        panelLuchador = new VentanaLuchador("Luchador", tecnicasDisponibles);

        botonEnviar = new JButton("Enviar Luchador");
        botonVolver = new JButton("Volver");

        botonEnviar.setFont(new Font("Arial", Font.BOLD, 15));
        botonVolver.setFont(new Font("Arial", Font.BOLD, 15));
    }

    private void construirInterfaz() {
        JLabel titulo = new JLabel("REGISTRO DEL LUCHADOR", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelCentral.add(panelLuchador, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonEnviar);
        panelBotones.add(botonVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        botonEnviar.addActionListener(e -> enviarLuchador());
        botonVolver.addActionListener(e -> volverAVentanaPrincipal());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });
    }

    private void enviarLuchador() {
        try {
            String nombre = panelLuchador.getNombreLuchador();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del luchador no puede estar vacío.");
                return;
            }

            double peso;
            try {
                peso = Double.parseDouble(panelLuchador.getPesoLuchador());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El peso debe ser un número válido.");
                return;
            }

            if (panelLuchador.getTecnicasSeleccionadas().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar al menos una técnica.");
                return;
            }

            Luchador luchador = controlCliente.crearLuchador(
                    nombre,
                    peso,
                    panelLuchador.getTunicaSeleccionada(),
                    panelLuchador.getTecnicasSeleccionadas()
            );

            botonEnviar.setEnabled(false);
            registrarEstado("Luchador enviado. Esperando resultado del combate...");

            // ⚠️ Esto puede tardar porque espera al segundo cliente
            new Thread(() -> {
                String resultado = controlCliente.enviarLuchador(luchador);

                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Resultado: " + resultado);
                    registrarEstado("Resultado recibido: " + resultado);
                    botonEnviar.setEnabled(true);
                    panelLuchador.limpiarCampos();
                });
            }).start();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error al enviar el luchador: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            botonEnviar.setEnabled(true);
        }
    }

    private void registrarEstado(String mensaje) {
        setTitle("Registro de Luchador - " + mensaje);
    }

    private void volverAVentanaPrincipal() {
        dispose();
        ventanaPrincipal.setVisible(true);
    }
}