package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gestionnaire d'événements pour le bouton lançant le tutoriel.
 * Implémente l'interface {@code ActionListener} pour répondre aux actions sur un bouton.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerTutorielBoutons implements ActionListener {
    
    /**
     * Instance du tutoriel à gérer, permet de contrôler l'état et le déroulement du tutoriel.
     */
    private Tutoriel t;

    /**
     * Constructeur de la classe {@code HandlerTutorielBoutons}.
     * Ce constructeur ajoute un écouteur d'événements au bouton associé et initialise l'instance du tutoriel.
     * 
     * @param cb Le bouton qui déclenche le lancement du tutoriel.
     * @param t  L'instance du tutoriel à gérer.
     */
    public HandlerTutorielBoutons(CreateButton cb, Tutoriel t) {
        cb.addActionListener(this); // Ajoute le gestionnaire d'événements au bouton
        this.t = t; // Associe l'instance du tutoriel
    }

    /**
     * Méthode appelée lorsque le bouton de tutoriel est activé (cliqué).
     * Lance le tutoriel en affichant un message de bienvenue et la première étape.
     * 
     * @param e L'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérifie si le tutoriel n'est pas déjà en cours et s'il est à l'étape initiale
        if (!this.t.getIsTutorial() && this.t.getStep() == 0) {
            this.t.setIsTutorial(); // Active le mode tutoriel
            // Message d'introduction qui est affiché au début du tutoriel
            this.t.setMessageText("Bienvenue sur Dorfmantik!\n" +
            "Commence par choisir une partie en cliquant sur Choix de Partie.");
            this.t.fenetreTuto(); // Affiche la fenêtre tutorielle
        }
    }
}
