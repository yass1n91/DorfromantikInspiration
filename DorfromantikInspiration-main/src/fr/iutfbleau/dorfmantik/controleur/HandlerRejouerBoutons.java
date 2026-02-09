package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gestionnaire d'événements pour le bouton permettant de rejouer le jeu.
 * Implémente l'interface {@code ActionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerRejouerBoutons implements ActionListener {

    /**
     * Bouton de rejouer.
     */
    private CreateButton boutonRejouer; 
    
    /**
     * Instance actuelle du jeu.
     */
    private Jeu jeu; 
    
    /**
     * Fenêtre associée au jeu.
     */
    private JFrame s; 
    
    /**
     * Gestionnaire de musique pour arrêter la musique en cours.
     */
    private MusicTask mt; 

    /**
     * Constructeur de la classe {@code HandlerRejouerBoutons}.
     * Ajoute un écouteur d'événements au bouton de rejouer et initialise les dépendances.
     * 
     * @param boutonRejouer Le bouton permettant de relancer le jeu.
     * @param jeu           L'instance du jeu actuel.
     * @param s             La fenêtre associée au jeu.
     * @param mt            Le gestionnaire de musique pour arrêter la musique en cours.
     */
    public HandlerRejouerBoutons(CreateButton boutonRejouer, Jeu jeu, JFrame s, MusicTask mt) {
        this.boutonRejouer = boutonRejouer;
        this.jeu = jeu;
        this.s = s;
        this.mt = mt;
        this.boutonRejouer.addActionListener(this);
    }

    /**
     * Méthode appelée lorsque le bouton "Rejouer" est activé.
     * Réinitialise l'état en ouvrant un nouveau menu, arrêtant la musique en cours,
     * et fermant les fenêtres du jeu actuel.
     * 
     * @param ae L'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Menu m = new Menu(); // Crée une nouvelle instance du menu principal
        m.setVisible(true); // Affiche le menu
        this.mt.getMusique().stop(); // Arrête la musique actuelle
        this.jeu.dispose(); // Ferme la fenêtre du jeu actuel
        this.s.dispose(); // Ferme la fenêtre associée
    }
}
