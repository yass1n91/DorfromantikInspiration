package fr.iutfbleau.dorfmantik.controleur;

import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * Gestionnaire d'événements pour afficher une boîte de dialogue de confirmation
 * lorsque l'utilisateur tente de fermer une fenêtre.
 * Étend la classe {@code WindowAdapter} pour ne surcharger que les méthodes nécessaires.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class ModalFermer extends WindowAdapter {

    /**
     * Méthode appelée lorsque l'utilisateur tente de fermer une fenêtre.
     * Affiche une boîte de dialogue demandant une confirmation avant de quitter l'application.
     * Si l'utilisateur choisit "Oui", l'application se ferme, sinon rien ne se passe.
     * 
     * @param e L'événement de fermeture de fenêtre.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        // Affiche une boîte de dialogue de confirmation pour quitter
        int confirmation = JOptionPane.showConfirmDialog(
            e.getWindow(),              // La fenêtre sur laquelle l'événement se produit
            "Voulez-vous vraiment quitter?",  // Message à afficher
            "Confirmation",             // Titre de la boîte de dialogue
            JOptionPane.YES_NO_OPTION  // Options "Oui" et "Non"
        );
        
        // Si l'utilisateur clique sur "Oui", ferme l'application
        if (confirmation == JOptionPane.YES_OPTION) {
            System.exit(0);  // Termine l'application
        }
    }
}
