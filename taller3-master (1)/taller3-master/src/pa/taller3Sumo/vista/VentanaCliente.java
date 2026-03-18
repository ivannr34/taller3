package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import pa.taller3Sumo.control.ControlKimarite;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.socket.ClienteSocket;

public class VentanaCliente extends JFrame {

    // LUCHADOR 1
    private JTextField campoNombre1;
    private JTextField campoPeso1;

    // LUCHADOR 2
    private JTextField campoNombre2;
    private JTextField campoPeso2;

    // TÚNICAS SELECCIONADAS
    private String tunicaSeleccionada1 = "Roja";
    private String tunicaSeleccionada2 = "Roja";

    // BOTONES TÚNICA LUCHADOR 1
    private JButton btnRoja1;
    private JButton btnAzul1;
    private JButton btnNegra1;

    // BOTONES TÚNICA LUCHADOR 2
    private JButton btnRoja2;
    private JButton btnAzul2;
    private JButton btnNegra2;

    // BOTONES GENERALES
    private JButton botonEnviar;
    private JButton botonVolver;

    private VentanaPrincipal ventanaPrincipal;

    public VentanaCliente(VentanaPrincipal ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;

        setTitle("Registro de 2 Luchadores");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("REGISTRO DE LUCHADORES", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        // Panel central con dos columnas
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 15, 15));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // =========================
        // PANEL LUCHADOR 1
        // =========================
        JPanel panelLuchador1 = new JPanel(new BorderLayout(10, 10));
        panelLuchador1.setBorder(BorderFactory.createTitledBorder("Luchador 1"));

        JPanel datos1 = new JPanel(new GridLayout(4, 2, 5, 5));

        datos1.add(new JLabel("Nombre:"));
        campoNombre1 = new JTextField();
        datos1.add(campoNombre1);

        datos1.add(new JLabel("Peso:"));
        campoPeso1 = new JTextField();
        datos1.add(campoPeso1);

        datos1.add(new JLabel("Túnica:"));

        JPanel panelTunicas1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnRoja1 = new JButton(" ");
        btnRoja1.setBackground(Color.RED);
        btnRoja1.setOpaque(true);
        btnRoja1.setPreferredSize(new Dimension(40, 25));

        btnAzul1 = new JButton(" ");
        btnAzul1.setBackground(Color.BLUE);
        btnAzul1.setOpaque(true);
        btnAzul1.setPreferredSize(new Dimension(40, 25));

        btnNegra1 = new JButton(" ");
        btnNegra1.setBackground(Color.BLACK);
        btnNegra1.setForeground(Color.WHITE);
        btnNegra1.setOpaque(true);
        btnNegra1.setPreferredSize(new Dimension(40, 25));

        panelTunicas1.add(btnRoja1);
        panelTunicas1.add(btnAzul1);
        panelTunicas1.add(btnNegra1);

        datos1.add(panelTunicas1);

        panelLuchador1.add(datos1, BorderLayout.NORTH);

        // =========================
        // PANEL LUCHADOR 2
        // =========================
        JPanel panelLuchador2 = new JPanel(new BorderLayout(10, 10));
        panelLuchador2.setBorder(BorderFactory.createTitledBorder("Luchador 2"));

        JPanel datos2 = new JPanel(new GridLayout(4, 2, 5, 5));

        datos2.add(new JLabel("Nombre:"));
        campoNombre2 = new JTextField();
        datos2.add(campoNombre2);

        datos2.add(new JLabel("Peso:"));
        campoPeso2 = new JTextField();
        datos2.add(campoPeso2);

        datos2.add(new JLabel("Túnica:"));

        JPanel panelTunicas2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnRoja2 = new JButton(" ");
        btnRoja2.setBackground(Color.RED);
        btnRoja2.setOpaque(true);
        btnRoja2.setPreferredSize(new Dimension(40, 25));

        btnAzul2 = new JButton(" ");
        btnAzul2.setBackground(Color.BLUE);
        btnAzul2.setOpaque(true);
        btnAzul2.setPreferredSize(new Dimension(40, 25));

        btnNegra2 = new JButton(" ");
        btnNegra2.setBackground(Color.BLACK);
        btnNegra2.setForeground(Color.WHITE);
        btnNegra2.setOpaque(true);
        btnNegra2.setPreferredSize(new Dimension(40, 25));

        panelTunicas2.add(btnRoja2);
        panelTunicas2.add(btnAzul2);
        panelTunicas2.add(btnNegra2);

        datos2.add(panelTunicas2);

        panelLuchador2.add(datos2, BorderLayout.NORTH);

        // Agregar ambos paneles al centro
        panelCentral.add(panelLuchador1);
        panelCentral.add(panelLuchador2);

        add(panelCentral, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel();

        botonEnviar = new JButton("Enviar Luchadores");
        botonVolver = new JButton("Volver");

        botonEnviar.setFont(new Font("Arial", Font.BOLD, 15));
        botonVolver.setFont(new Font("Arial", Font.BOLD, 15));

        panelBotones.add(botonEnviar);
        panelBotones.add(botonVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // ===== EVENTOS BOTONES DE TÚNICA LUCHADOR 1 =====
        btnRoja1.addActionListener(e -> {
            tunicaSeleccionada1 = "Roja";
            actualizarSeleccionTunica();
        });

        btnAzul1.addActionListener(e -> {
            tunicaSeleccionada1 = "Azul";
            actualizarSeleccionTunica();
        });

        btnNegra1.addActionListener(e -> {
            tunicaSeleccionada1 = "Negra";
            actualizarSeleccionTunica();
        });

        // ===== EVENTOS BOTONES DE TÚNICA LUCHADOR 2 =====
        btnRoja2.addActionListener(e -> {
            tunicaSeleccionada2 = "Roja";
            actualizarSeleccionTunica();
        });

        btnAzul2.addActionListener(e -> {
            tunicaSeleccionada2 = "Azul";
            actualizarSeleccionTunica();
        });

        btnNegra2.addActionListener(e -> {
            tunicaSeleccionada2 = "Negra";
            actualizarSeleccionTunica();
        });

        // Acción botón enviar
        botonEnviar.addActionListener(e -> enviarLuchadores());

        // Acción botón volver
        botonVolver.addActionListener(e -> {
            dispose();
            ventanaPrincipal.setVisible(true);
        });

        // Si cierra con la X
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaPrincipal.setVisible(true);
            }
        });

        // Mostrar selección inicial
        actualizarSeleccionTunica();
    }

    private void actualizarSeleccionTunica() {
        btnRoja1.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada1.equals("Roja") ? Color.GREEN : Color.GRAY, 3));
        btnAzul1.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada1.equals("Azul") ? Color.GREEN : Color.GRAY, 3));
        btnNegra1.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada1.equals("Negra") ? Color.GREEN : Color.GRAY, 3));

        btnRoja2.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada2.equals("Roja") ? Color.GREEN : Color.GRAY, 3));
        btnAzul2.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada2.equals("Azul") ? Color.GREEN : Color.GRAY, 3));
        btnNegra2.setBorder(BorderFactory.createLineBorder(
                tunicaSeleccionada2.equals("Negra") ? Color.GREEN : Color.GRAY, 3));
    }

    private void enviarLuchadores() {
        try {
            // ===== VALIDAR LUCHADOR 1 =====
            String nombre1 = campoNombre1.getText().trim();
            if (nombre1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del Luchador 1 no puede estar vacío.");
                return;
            }

            double peso1;
            try {
                peso1 = Double.parseDouble(campoPeso1.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El peso del Luchador 1 debe ser un número válido (double).");
                return;
            }

            String tunica1 = tunicaSeleccionada1;

            // ===== VALIDAR LUCHADOR 2 =====
            String nombre2 = campoNombre2.getText().trim();
            if (nombre2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del Luchador 2 no puede estar vacío.");
                return;
            }

            double peso2;
            try {
                peso2 = Double.parseDouble(campoPeso2.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El peso del Luchador 2 debe ser un número válido (double).");
                return;
            }

            String tunica2 = tunicaSeleccionada2;

            // ===== CARGAR TODAS LAS TÉCNICAS AUTOMÁTICAMENTE =====
            List<String> tecnicas = ControlKimarite.cargarTecnicas();

            // ===== CREAR LUCHADORES =====
            Luchador luchador1 = new Luchador(nombre1, peso1, tunica1, tecnicas);
            Luchador luchador2 = new Luchador(nombre2, peso2, tunica2, tecnicas);

            // ===== ENVIAR LOS DOS AL SERVIDOR =====
            ClienteSocket cliente1 = new ClienteSocket();
            cliente1.enviarLuchador(luchador1);

            ClienteSocket cliente2 = new ClienteSocket();
            cliente2.enviarLuchador(luchador2);

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

    private void limpiarCampos() {
        campoNombre1.setText("");
        campoPeso1.setText("");
        tunicaSeleccionada1 = "Roja";

        campoNombre2.setText("");
        campoPeso2.setText("");
        tunicaSeleccionada2 = "Roja";

        actualizarSeleccionTunica();
    }
}