package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Gestionnaire permettant de rendre un plateau hexagonal déplaçable par glisser-déposer avec la souris.
 * Implémente les interfaces {@code MouseListener} et {@code MouseMotionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class PlateauDraggableHandler implements MouseListener, MouseMotionListener {

    /**
     * Référence au plateau à déplacer.
     * Le plateau hexagonal qui sera déplacé par l'utilisateur.
     */
    private final PlateauHexagonal plateau; 

    /**
     * Position initiale de la souris au moment du clic.
     * Utilisée pour calculer la différence de mouvement de la souris lors du glisser-déposer.
     */
    private Point initialMouseLocation; 

    /**
     * Position initiale du plateau au moment du clic.
     * Permet de calculer le déplacement du plateau en fonction du mouvement de la souris.
     */
    private Point initialPanelLocation; 

    /**
     * Constructeur de la classe {@code PlateauDraggableHandler}.
     * Ajoute les écouteurs d'événements nécessaires pour permettre le déplacement du plateau.
     * 
     * @param plateau Le plateau hexagonal à rendre déplaçable.
     */
    public PlateauDraggableHandler(PlateauHexagonal plateau) {
        this.plateau = plateau;
        // Ajouter les listeners nécessaires
        plateau.addMouseListener(this);
        plateau.addMouseMotionListener(this);
    }

    /**
     * Capture la position initiale de la souris et du plateau lorsque l'utilisateur commence à cliquer.
     * 
     * @param e L'événement de clic de souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        initialMouseLocation = e.getPoint(); // Position de la souris
        initialPanelLocation = plateau.getLocation(); // Position actuelle du plateau
    }

    /**
     * Réinitialise les positions après que l'utilisateur relâche le bouton de la souris.
     * 
     * @param e L'événement de relâchement de souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        initialMouseLocation = null;
        initialPanelLocation = null;
    }

    /**
     * Permet de déplacer le plateau en fonction du mouvement de la souris.
     * 
     * @param e L'événement de glisser-déposer de la souris.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (initialMouseLocation != null && initialPanelLocation != null) {
            // Calculer le déplacement
            int deltaX = e.getX() - initialMouseLocation.x;
            int deltaY = e.getY() - initialMouseLocation.y;

            // Appliquer le déplacement au plateau
            plateau.setLocation(initialPanelLocation.x + deltaX, initialPanelLocation.y + deltaY);
        }
    }

    /**
     * Méthode obligatoire pour {@code MouseMotionListener}, inutilisée ici.
     * 
     * @param e L'événement de déplacement de la souris.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // Ne rien faire ici
    }

    /**
     * Méthode obligatoire pour {@code MouseListener}, inutilisée ici.
     * 
     * @param e L'événement de clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) { }

    /**
     * Méthode obligatoire pour {@code MouseListener}, inutilisée ici.
     * 
     * @param e L'événement lorsque la souris entre dans le composant.
     */
    @Override
    public void mouseEntered(MouseEvent e) { }

    /**
     * Méthode obligatoire pour {@code MouseListener}, inutilisée ici.
     * 
     * @param e L'événement lorsque la souris sort du composant.
     */
    @Override
    public void mouseExited(MouseEvent e) { }
}
