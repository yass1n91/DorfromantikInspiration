package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;

/**
 * Gestionnaire d'événements pour gérer l'orientation des hexagones via un clic de souris.
 * Implémente l'interface {@code MouseListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerOrientationHexagone implements MouseListener {

    /**
     * Gestionnaire des choix d'hexagones
     */
    private ChoixHexagone ch; 

    /**
     * Indique si une rotation est active
     */
    private boolean rotation; 

    /**
     * Constructeur de la classe {@code HandlerOrientationHexagone}.
     * Initialise le gestionnaire pour un hexagone donné et attache un écouteur d'événements.
     * 
     * @param ch Le gestionnaire des choix d'hexagones.
     */
    public HandlerOrientationHexagone(ChoixHexagone ch) {
        this.ch = ch;
        this.rotation = false;
        this.ch.addMouseListener(this);
    }

    /**
     * Méthode appelée lorsqu'un clic de souris est effectué sur l'hexagone.
     * Active la rotation si le choix d'hexagone n'est pas terminé.
     * 
     * @param ae L'événement de clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent ae) {
        if (!this.ch.terminer()) {
            this.rotation = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Retourne l'état de la rotation.
     * 
     * @return {@code true} si une rotation est active, {@code false} sinon.
     */
    public boolean getRotation() {
        return this.rotation;
    }

    /**
     * Réinitialise l'état de la rotation à {@code false}.
     */
    public void resetRotation() {
        this.rotation = false;
    }
}
