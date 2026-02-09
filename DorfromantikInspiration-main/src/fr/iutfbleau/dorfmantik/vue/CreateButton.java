package fr.iutfbleau.dorfmantik.vue;

import javax.swing.*;
import fr.iutfbleau.dorfmantik.controleur.Fonts;
import java.awt.*;

/**
 * Classe représentant un bouton personnalisé pour l'interface graphique du jeu.
 * Étend la classe {@code JButton} et applique des styles spécifiques.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class CreateButton extends JButton {

    /**
     * Constructeur de la classe {@code CreateButton}.
     * Initialise un bouton avec un texte, une police personnalisée, et un style graphique.
     * 
     * @param text Le texte à afficher sur le bouton.
     */
    public CreateButton(String text) {
        super(text);

        // Charger la police personnalisée en utilisant la classe Fonts
        Font policePersonnalisee = Fonts.chargerPolice("/fonts/arial.ttf", Font.PLAIN, 24);

        // Appliquer la police au bouton
        this.setFont(policePersonnalisee);

        // Définir les propriétés graphiques du bouton
        this.setBackground(new Color(100, 150, 255)); // Couleur de fond
        this.setForeground(Color.WHITE);              // Couleur du texte
        this.setFocusPainted(false);                  // Supprime l'effet de focus
        this.setBorderPainted(false);                 // Supprime les bordures
        this.setPreferredSize(new Dimension(200, 50)); // Taille par défaut du bouton
        this.setOpaque(true);                         // Rend le bouton opaque
    }
}
