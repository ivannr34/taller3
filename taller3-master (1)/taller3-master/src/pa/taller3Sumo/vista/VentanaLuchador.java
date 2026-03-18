package pa.taller3Sumo.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaLuchador extends JPanel {

    private static final String ROJA = "Roja";
    private static final String AZUL = "Azul";
    private static final String NEGRA = "Negra";

    private JTextField campoNombre;
    private JTextField campoPeso;

    private JButton btnRoja;
    private JButton btnAzul;
    private JButton btnNegra;

    private String tunicaSeleccionada = ROJA;

    public VentanaLuchador(String titulo) {
        inicializarComponentes();
        construirPanel(titulo);
        agregarEventos();
        actualizarSeleccionTunica();
    }

    private void inicializarComponentes() {
        campoNombre = new JTextField();
        campoPeso = new JTextField();

        btnRoja = crearBotonTunica(Color.RED, Color.BLACK);
        btnAzul = crearBotonTunica(Color.BLUE, Color.BLACK);
        btnNegra = crearBotonTunica(Color.BLACK, Color.WHITE);
    }

    private void construirPanel(String titulo) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder(titulo));

        JPanel datos = new JPanel(new GridLayout(4, 2, 5, 5));

        datos.add(new JLabel("Nombre:"));
        datos.add(campoNombre);

        datos.add(new JLabel("Peso:"));
        datos.add(campoPeso);

        datos.add(new JLabel("Túnica:"));

        JPanel panelTunicas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTunicas.add(btnRoja);
        panelTunicas.add(btnAzul);
        panelTunicas.add(btnNegra);

        datos.add(panelTunicas);

        add(datos, BorderLayout.NORTH);
    }

    private JButton crearBotonTunica(Color fondo, Color texto) {
        JButton boton = new JButton(" ");
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setOpaque(true);
        boton.setPreferredSize(new Dimension(40, 25));
        return boton;
    }

    private void agregarEventos() {
        btnRoja.addActionListener(e -> seleccionarTunica(ROJA));
        btnAzul.addActionListener(e -> seleccionarTunica(AZUL));
        btnNegra.addActionListener(e -> seleccionarTunica(NEGRA));
    }

    private void seleccionarTunica(String tunica) {
        tunicaSeleccionada = tunica;
        actualizarSeleccionTunica();
    }

    private void actualizarSeleccionTunica() {
        btnRoja.setBorder(BorderFactory.createLineBorder(ROJA.equals(tunicaSeleccionada) ? Color.GREEN : Color.GRAY, 3));
        btnAzul.setBorder(BorderFactory.createLineBorder(AZUL.equals(tunicaSeleccionada) ? Color.GREEN : Color.GRAY, 3));
        btnNegra.setBorder(BorderFactory.createLineBorder(NEGRA.equals(tunicaSeleccionada) ? Color.GREEN : Color.GRAY, 3));
    }

    public String getNombreLuchador() {
        return campoNombre.getText().trim();
    }

    public String getPesoLuchador() {
        return campoPeso.getText().trim();
    }

    public String getTunicaSeleccionada() {
        return tunicaSeleccionada;
    }

    public void limpiarCampos() {
        campoNombre.setText("");
        campoPeso.setText("");
        tunicaSeleccionada = ROJA;
        actualizarSeleccionTunica();
    }
}