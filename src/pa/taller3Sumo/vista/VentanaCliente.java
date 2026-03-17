package pa.taller3Sumo.vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import pa.taller3Sumo.control.ControlKimarite;
import pa.taller3Sumo.modelo.Luchador;
import pa.taller3Sumo.socket.ClienteSocket;

public class VentanaCliente extends JFrame {

    private JTextField campoNombre;
    private JTextField campoPeso;

    private JComboBox<String> comboTunica;

    private JList<String> listaTecnicas;

    private JButton botonEnviar;

    public VentanaCliente() {

        setTitle("Registro Luchador");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(3, 2));

        panelDatos.add(new JLabel("Nombre"));
        campoNombre = new JTextField();
        panelDatos.add(campoNombre);

        panelDatos.add(new JLabel("Peso"));
        campoPeso = new JTextField();
        panelDatos.add(campoPeso);

        panelDatos.add(new JLabel("Tunica"));
        comboTunica = new JComboBox<>(new String[]{"Roja", "Azul", "Negra"});
        panelDatos.add(comboTunica);

        add(panelDatos, BorderLayout.NORTH);

        List<String> tecnicas = ControlKimarite.cargarTecnicas();
        listaTecnicas = new JList<>(tecnicas.toArray(new String[0]));
        add(new JScrollPane(listaTecnicas), BorderLayout.CENTER);

        botonEnviar = new JButton("Enviar luchador");
        add(botonEnviar, BorderLayout.SOUTH);

        botonEnviar.addActionListener(e -> {
            try {

                String nombre = campoNombre.getText();
                double peso = Double.parseDouble(campoPeso.getText());
                String tunica = (String) comboTunica.getSelectedItem();

                List<String> tecnicasSeleccionadas = listaTecnicas.getSelectedValuesList();

                Luchador luchador = new Luchador(nombre, peso, tunica, tecnicasSeleccionadas);

                ClienteSocket cliente = new ClienteSocket();
                cliente.enviarLuchador(luchador);

                JOptionPane.showMessageDialog(this, "Luchador enviado");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
