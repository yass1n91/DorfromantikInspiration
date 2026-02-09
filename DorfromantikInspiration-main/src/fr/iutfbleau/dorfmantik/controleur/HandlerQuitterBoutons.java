package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;

/**
 * Gestionnaire d'événements pour un bouton permettant de quitter l'application.
 * Implémente l'interface {@code ActionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerQuitterBoutons implements ActionListener {

    /**
     * Constructeur de la classe {@code HandlerQuitterBoutons}.
     * Ajoute un écouteur d'événements au bouton spécifié.
     * 
     * @param cb Le bouton pour lequel l'action de quitter est associée.
     */
    public HandlerQuitterBoutons(CreateButton cb) {
        cb.addActionListener(this); 
    }

    /**
     * Méthode appelée lorsque le bouton est activé.
     * Quitte immédiatement l'application en appelant {@code System.exit(0)}.
     * 
     * @param e L'événement d'action déclenché.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0); 
    }
}
