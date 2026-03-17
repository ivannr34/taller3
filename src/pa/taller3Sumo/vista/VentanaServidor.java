package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaServidor extends JFrame {

    private JTextArea areaCombate;

    public VentanaServidor() {

        setTitle("Servidor Combate");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("DOHYO - COMBATE DE SUMO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);


        System.out.println(getClass().getResource("/pa/taller3Sumo/Recursos/ring.jpg"));
        JLabel imagenRing;

        java.net.URL url = getClass().getResource("/pa/taller3Sumo/Recursos/ring.jpg");

        if (url != null) {
            imagenRing = new JLabel(new ImageIcon(url));
        } else {
            System.out.println("ERROR: No se encontró la imagen");
            imagenRing = new JLabel("Imagen no disponible");
        }

        imagenRing.setHorizontalAlignment(JLabel.CENTER);
        add(imagenRing, BorderLayout.CENTER);
        imagenRing.setHorizontalAlignment(JLabel.CENTER);

        add(imagenRing, BorderLayout.CENTER);

        // Area combate
        areaCombate = new JTextArea();
        areaCombate.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaCombate);

        scroll.setPreferredSize(new Dimension(650, 200));

        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void registrarMovimiento(String texto) {

        areaCombate.append(texto + "\n");

    }

}
