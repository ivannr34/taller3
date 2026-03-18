package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import pa.taller3Sumo.socket.ServidorSocket;

public class VentanaServidor extends JFrame {

    private JTextArea areaCombate;
    private JButton btnIniciarServidor;
    private JButton btnVolver;
    private ServidorSocket servidor;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaServidor(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Servidor Combate");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("DOHYO - COMBATE DE SUMO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());

        JLabel lblImagen = new JLabel("", JLabel.CENTER);

        java.net.URL rutaImagen = getClass().getResource("/pa/taller3Sumo/Recursos/ring.jpg");
        if (rutaImagen != null) {
            ImageIcon icon = new ImageIcon(rutaImagen);
            lblImagen.setIcon(icon);
        } else {
            lblImagen.setText("Imagen del ring no disponible");
        }

        panelCentro.add(lblImagen, BorderLayout.CENTER);

        btnIniciarServidor = new JButton("Iniciar Servidor");
        btnIniciarServidor.setFont(new Font("Arial", Font.BOLD, 16));

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnIniciarServidor);
        panelBoton.add(btnVolver);

        panelCentro.add(panelBoton, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);

        areaCombate = new JTextArea();
        areaCombate.setEditable(false);
        areaCombate.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(areaCombate);
        scroll.setPreferredSize(new Dimension(650, 200));
        add(scroll, BorderLayout.SOUTH);

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
            servidor = new ServidorSocket(this);

            Thread hiloServidor = new Thread(() -> {
                servidor.iniciar();
            });

            hiloServidor.start();

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
        areaCombate.append(texto + "\n");
    }
}