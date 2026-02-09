package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Gestionnaire d'événements permettant de tourner les couleurs d'un hexagone
 * lorsque l'utilisateur clique dessus. Implémente l'interface {@code MouseListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerTournerHexagone implements MouseListener {

    /**
     * Instance associée au choix d'hexagone, utilisée pour gérer l'hexagone sélectionné.
     */
    private ChoixHexagone ch; 

    /**
     * Constructeur de la classe {@code HandlerTournerHexagone}.
     * Attache un écouteur d'événements à l'hexagone sélectionné pour détecter les clics.
     * 
     * @param ch Le gestionnaire du choix d'hexagone.
     */
    public HandlerTournerHexagone(ChoixHexagone ch) {
        this.ch = ch;
        this.ch.addMouseListener(this); // Ajoute le gestionnaire d'événements à l'hexagone
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur l'hexagone.
     * Décale les couleurs d'une position dans le sens des aiguilles d'une montre.
     * 
     * @param e L'événement de clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.ch.getChoixHexagone().estRempli()) {
            Color[] couleurs = this.ch.getChoixHexagone().getCouleur();
            // Tourner les couleurs : décaler toutes les couleurs d'une position
            Color temp = couleurs[5];
            for (int i = 5; i > 0; i--) {
                couleurs[i] = couleurs[i - 1];
            }
            couleurs[0] = temp;

            // Mettre à jour les couleurs de l'hexagone
            this.ch.getChoixHexagone().mettreAJourCouleurs(couleurs);
        }
    }

    /**
     * Méthode inutile mais obligatoire pour l'interface {@code MouseListener}.
     * 
     * @param e L'événement lorsque la souris quitte l'hexagone.
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Méthode inutile mais obligatoire pour l'interface {@code MouseListener}.
     * 
     * @param e L'événement lorsque l'utilisateur appuie sur la souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Méthode inutile mais obligatoire pour l'interface {@code MouseListener}.
     * 
     * @param e L'événement lorsque l'utilisateur relâche le bouton de la souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Méthode inutile mais obligatoire pour l'interface {@code MouseListener}.
     * 
     * @param e L'événement lorsque la souris entre dans la zone de l'hexagone.
     */
    @Override
    public void mouseEntered(MouseEvent e) {}
}
