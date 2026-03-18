package pa.taller3Sumo.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {

    private JButton btnServidor;
    private JButton btnCliente;
    private JButton btnSalir;
    private JLabel lblTitulo;
    private JLabel lblImagen;

    public VentanaPrincipal() {
        initComponents();
        configurarVentana();
        agregarEventos();
    }

    private void initComponents() {

        lblTitulo = new JLabel("COMBATE DE SUMO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(139, 69, 19));

        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

        java.net.URL rutaImagen = getClass().getResource("/pa/taller3Sumo/Recursos/ring.jpg");
        if (rutaImagen != null) {
            lblImagen.setIcon(new ImageIcon(rutaImagen));
        } else {
            lblImagen.setText("Imagen del ring no disponible");
            lblImagen.setFont(new Font("Arial", Font.ITALIC, 14));
        }

        btnServidor = new JButton("Abrir Ventana Servidor");
        btnCliente = new JButton("Abrir Ventana Cliente");
        btnSalir = new JButton("Salir");

        btnServidor.setFont(new Font("Arial", Font.BOLD, 16));
        btnCliente.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));

        btnServidor.setBackground(new Color(205, 133, 63));
        btnCliente.setBackground(new Color(222, 184, 135));
        btnSalir.setBackground(new Color(178, 34, 34));

        btnServidor.setForeground(Color.WHITE);
        btnCliente.setForeground(Color.BLACK);
        btnSalir.setForeground(Color.WHITE);
    }

    private void configurarVentana() {
        setTitle("Ventana Principal");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblImagen.setPreferredSize(new Dimension(400, 220));
        panelCentro.add(lblImagen, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));
        panelBotones.add(btnServidor);
        panelBotones.add(btnCliente);
        panelBotones.add(btnSalir);

        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void agregarEventos() {
        btnServidor.addActionListener(e -> {
            VentanaServidor ventanaServidor = new VentanaServidor(this);
            ventanaServidor.setVisible(true);
            this.setVisible(false);
        });

        btnCliente.addActionListener(e -> {
            VentanaCliente ventanaCliente = new VentanaCliente(this);
            ventanaCliente.setVisible(true);
            this.setVisible(false);
        });

        btnSalir.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
    }
}