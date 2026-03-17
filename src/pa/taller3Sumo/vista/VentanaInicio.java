package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import pa.taller3Sumo.socket.ServidorSocket;

public class VentanaInicio extends JFrame {

    private JButton botonServidor;
    private JButton botonCliente;

    public VentanaInicio() {

        setTitle("Combate de Sumo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("SIMULADOR DE SUMO", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 1, 20, 20));

        botonServidor = new JButton("Iniciar Servidor");
        botonCliente = new JButton("Iniciar Cliente");

        panelBotones.add(botonServidor);
        panelBotones.add(botonCliente);

        add(panelBotones, BorderLayout.CENTER);

        botonServidor.addActionListener(e -> {

            VentanaServidor vista = new VentanaServidor();

            ServidorSocket servidor = new ServidorSocket(vista);

            new Thread(() -> servidor.iniciar()).start();

            dispose();

        });

        botonCliente.addActionListener(e -> {

            new VentanaCliente();
            dispose();

        });

        setVisible(true);

    }
}
