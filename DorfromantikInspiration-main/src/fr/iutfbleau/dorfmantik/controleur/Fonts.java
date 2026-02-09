package fr.iutfbleau.dorfmantik.controleur;

import java.awt.Font;  
import java.awt.FontFormatException;
import java.io.InputStream;
import java.io.IOException;

/**
 * Classe utilitaire pour charger des polices personnalisées depuis les ressources du projet.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Fonts {

    /**
     * Charge une police personnalisée à partir d'une ressource, avec un style et une taille spécifiés.
     * Si la police ne peut pas être chargée, une police par défaut est retournée.
     * 
     * @param cheminRessource Le chemin de la ressource contenant la police (par exemple, "/polices/maPolice.ttf").
     * @param style           Le style de la police (par exemple, {@code Font.PLAIN}, {@code Font.BOLD}).
     * @param taille          La taille de la police en points.
     * @return La police chargée avec le style et la taille spécifiés, ou une police par défaut si le chargement échoue.
     */
    public static Font chargerPolice(String cheminRessource, int style, int taille) {
        try (InputStream is = Fonts.class.getResourceAsStream(cheminRessource)) {
            if (is == null) {
                throw new IOException("Ressource non trouvée : " + cheminRessource);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(style, taille); // Applique style et taille
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Police par défaut
            return new Font("SansSerif", style, taille);
        }
    }
}
