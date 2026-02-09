package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe responsable de la gestion des animations sur les boutons lorsqu'ils sont survolés par la souris.
 * Cette classe implémente l'interface {@code MouseListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerAnimationBoutons implements MouseListener {

    /**
     * Bouton sur lequel les animations sont appliquées
     */
    private CreateButton cb;

    /**
     * Constructeur de la classe HandlerAnimationBoutons.
     * Ajoute un écouteur d'événements de souris au bouton spécifié.
     * 
     * @param cb Le bouton pour lequel les animations seront gérées.
     */
    public HandlerAnimationBoutons(CreateButton cb) {
        this.cb = cb;
        this.cb.addMouseListener(this);
    }

    /**
     * Change la couleur de fond du bouton lorsque la souris entre dans celui-ci.
     * 
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource(); 
        button.setBackground(new Color(50, 100, 255)); // Couleur sur survol
    }

    /**
     * Change la couleur de fond du bouton lorsque la souris sort de celui-ci.
     * 
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource(); 
        button.setBackground(new Color(100, 150, 255)); // Couleur par défaut
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
