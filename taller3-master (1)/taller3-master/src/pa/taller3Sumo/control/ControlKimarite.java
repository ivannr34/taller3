package pa.taller3Sumo.control;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControlKimarite {

    public static List<String> cargarTecnicas() {
        List<String> tecnicas = new ArrayList<>();

        try {
            Properties props = new Properties();

            InputStream is = ControlKimarite.class
                    .getResourceAsStream("/pa/taller3Sumo/libreria/kimarites.properties");

            if (is == null) {
                throw new RuntimeException("No se encontró el archivo kimarites.properties en la ruta /pa/taller3Sumo/libreria/");
            }

            props.load(is);

            for (Object key : props.keySet()) {
                tecnicas.add(props.getProperty(key.toString()));
            }

            is.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tecnicas;
    }
}