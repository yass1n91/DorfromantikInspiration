package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gestionnaire d'événements pour le bouton "J'ai compris".
 * Implémente l'interface {@code ActionListener}.
 * Permet de fermer une fenêtre lorsqu'on clique sur le bouton.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerUnderstandBouton implements ActionListener {
    /**
     * Fenêtre à fermer lorsque le bouton est cliqué
     */
    private JFrame f; 

    /**
     * Constructeur de la classe {@code HandlerUnderstandBouton}.
     * Ce constructeur associe un bouton au gestionnaire d'événements et configure la fenêtre à fermer.
     * 
     * @param b Le bouton qui déclenche l'action de fermeture.
     * @param f La fenêtre à fermer lorsque le bouton est cliqué.
     */
    public HandlerUnderstandBouton(JButton b, JFrame f) {
        b.addActionListener(this);  // Ajoute l'écouteur d'événements au bouton
        this.f = f;  // Associe la fenêtre à gérer
    }

    /**
     * Méthode appelée lorsque le bouton "J'ai compris" est cliqué.
     * Cette méthode ferme la fenêtre associée en appelant {@code dispose()}.
     * 
     * @param e L'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        f.dispose(); // Ferme la fenêtre
    }
}
