package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gestionnaire d'événements pour le bouton qui lance la fenêtre du jeu.
 * Implémente l'interface {@code ActionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerJeuBoutons implements ActionListener {

    /**
     * Référence au menu principal
     */
    private Menu m; 
    
    /**
     * Référence au tutoriel
     */
    private Tutoriel t;

    /**
     * Constructeur de la classe {@code HandlerJeuBoutons}.
     * Initialise le gestionnaire pour un bouton spécifique et configure les dépendances nécessaires.
     * 
     * @param cb Le bouton à écouter pour l'événement d'action.
     * @param m  Le menu principal.
     * @param t  Le tutoriel associé.
     */
    public HandlerJeuBoutons(CreateButton cb, Menu m, Tutoriel t) {
        this.m = m;
        cb.addActionListener(this);
        this.t = t;
    }

    /**
     * Méthode appelée lorsqu'une action est effectuée sur le bouton.
     * Si les conditions du tutoriel sont remplies ou s'il n'y a pas de tutoriel actif,
     * une nouvelle fenêtre de jeu est créée et le menu est fermé.
     * 
     * @param e L'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérifie si le tutoriel est actif et à l'étape correcte, ou s'il n'y a pas de tutoriel
        if ((this.t.getIsTutorial() && this.t.getStep() == 2) || (!this.t.getIsTutorial())) {
            Jeu jeu = new Jeu(this.m.getSeedPris(), this.t); // Crée une nouvelle instance du jeu
            this.m.dispose(); // Ferme le menu principal
            if (this.t.getIsTutorial()) {
                this.t.setMessageText("Te voici sur la fenêtre de jeu," + 
                " tu peux cliquer sur les cases blanches pour ajouter un hexagone");
                this.t.fenetreTuto(); // Affiche une fenêtre de tutoriel
            }
        }
    }
}
