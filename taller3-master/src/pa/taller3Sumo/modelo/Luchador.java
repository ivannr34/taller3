package pa.taller3Sumo.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Luchador implements Serializable {

    private String nombre;
    private double peso;
    private String tunica;
    private boolean dentroDohyo = true;
    private int victorias = 0;
    private Luchador rival;

    private List<String> tecnicas;

    private Random random = new Random();

    public Luchador(String nombre, double peso, String tunica, List<String> tecnicas) {

        this.nombre = nombre;
        this.peso = peso;
        this.tunica = tunica;
        this.tecnicas = tecnicas;

    }

    public String usarTecnica() {
        if (tecnicas == null || tecnicas.isEmpty()) {
            return "No seleccionó técnica";
        }
        int index = random.nextInt(tecnicas.size());
        return tecnicas.get(index);
    }

    public String getNombre() {

        return nombre;

    }
    
    public double getPeso() {
        return peso;
    }

    public String getTunica() {
        return tunica;
    }

public List<String> getTecnicas() {
    return tecnicas;
}

    public boolean estaDentroDohyo() {
        return dentroDohyo;
    }

    public void setDentroDohyo(boolean dentroDohyo) {
        this.dentroDohyo = dentroDohyo;
    }

    public void setRival(Luchador rival) {
        this.rival = rival;
    }

    public Luchador getRival() {
        return rival;
    }

    public void sumarVictoria() {
        victorias++;
    }

    public int getVictorias() {
        return victorias;
    }
}
