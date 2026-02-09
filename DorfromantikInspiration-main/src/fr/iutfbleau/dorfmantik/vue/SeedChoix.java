package fr.iutfbleau.dorfmantik.vue;

import javax.swing.*;
import java.awt.*;

/**
 * Classe permettant à l'utilisateur de choisir un "seed" (paramètre de génération) via une boîte de dialogue.
 *
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class SeedChoix {

    /**
     * Constructeur de la classe {@code SeedChoix}.
     * Affiche une boîte de dialogue permettant à l'utilisateur de sélectionner un "seed" à partir d'une liste déroulante.
     * Si l'utilisateur confirme sa sélection, le "seed" est transmis au menu.
     *
     * @param comboBox La liste déroulante contenant les options de "seed".
     * @param menu     Le menu principal où le "seed" choisi sera enregistré.
     */
    public SeedChoix(JComboBox<Integer> comboBox, Menu menu) {

        /**
         * Ceci est une variable de classe.
         */
        int option = JOptionPane.showConfirmDialog(
            null, 
            comboBox, 
            "Choisissez une partie", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );

        // Si l'utilisateur appuie sur OK
        if (option == JOptionPane.OK_OPTION) {
            menu.setSeedPris((Integer) comboBox.getSelectedItem());
        }
    }
}
