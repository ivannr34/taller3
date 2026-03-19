package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import pa.taller3Sumo.control.ControlServidor;

public class VentanaServidor extends JFrame {

    private JTextArea areaCombate;
    private JButton btnIniciarServidor;
    private JButton btnVolver;
    private VentanaPrincipal ventanaPrincipal;
    private ControlServidor controlServidor;

    public VentanaServidor(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.controlServidor = new ControlServidor(this);

        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
        agregarEventos();
    }

    private void configurarVentana() {
        setTitle("Servidor Combate");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        areaCombate = new JTextArea();
        areaCombate.setEditable(false);
        areaCombate.setFont(new Font("Monospaced", Font.PLAIN, 13));

        btnIniciarServidor = new JButton("Iniciar Servidor");
        btnIniciarServidor.setFont(new Font("Arial", Font.BOLD, 16));

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void construirInterfaz() {
        JLabel titulo = new JLabel("DOHYO - COMBATE DE SUMO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());

        JLabel lblImagen = new JLabel("", JLabel.CENTER);
        java.net.URL rutaImagen = getClass().getResource("/pa/taller3Sumo/Recursos/ring.jpg");

        if (rutaImagen != null) {
            lblImagen.setIcon(new ImageIcon(rutaImagen));
        } else {
            lblImagen.setText("Imagen del ring no disponible");
        }

        panelCentro.add(lblImagen, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnIniciarServidor);
        panelBoton.add(btnVolver);

        panelCentro.add(panelBoton, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(areaCombate);
        scroll.setPreferredSize(new Dimension(650, 200));
        add(scroll, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        btnIniciarServidor.addActionListener(e -> iniciarServidor());

        btnVolver.addActionListener(e -> {
            dispose();
            ventanaPrincipal.setVisible(true);
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });
    }

    private void iniciarServidor() {
        try {
            controlServidor.iniciarServidor();

            btnIniciarServidor.setEnabled(false);
            registrarMovimiento("Servidor iniciado en puerto 5000...");
            registrarMovimiento("Esperando luchadores...");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al iniciar el servidor: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrarMovimiento(String texto) {
        SwingUtilities.invokeLater(() -> areaCombate.append(texto + "\n"));
    }

    public void mostrarGanador(String nombreGanador, String resumenCombate) {
        SwingUtilities.invokeLater(() -> {
            VentanaGanador ventanaGanador = new VentanaGanador(nombreGanador, resumenCombate);
            ventanaGanador.setVisible(true);
        });
    }
}