package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe responsable de la gestion des interactions entre les hexagones et les événements de la souris.
 * Elle implémente les interfaces {@code MouseListener} et {@code MouseMotionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerHexagone implements MouseListener, MouseMotionListener {

    /**
     * Hexagone lié à ce gestionnaire
     */
    private Hexagone hex; 
    
    /**
     * Gestionnaire des choix d'hexagones
     */
    private ChoixHexagone ch; 
    
    /**
     * Gestionnaire des scores
     */
    private Score s; 
    
    /**
     * Gestionnaire pour les actions de glisser-déposer
     */
    private PlateauDraggableHandler phex; 
    
    /**
     * Plateau contenant les hexagones
     */
    private Hexagone[][] plateau;

    /**
     * Constructeur de la classe {@code HandlerHexagone}.
     * Initialise les interactions pour un hexagone spécifique.
     * 
     * @param hex     L'hexagone à gérer.
     * @param ch      Le gestionnaire des choix d'hexagones.
     * @param s       Le gestionnaire de score.
     * @param phex    Le gestionnaire des interactions de glisser-déposer.
     * @param plateau Le plateau contenant tous les hexagones.
     */
    public HandlerHexagone(Hexagone hex, ChoixHexagone ch, Score s, PlateauDraggableHandler phex, Hexagone[][] plateau) {
        this.hex = hex;
        this.ch = ch;
        this.s = s;
        this.phex = phex;
        this.plateau = plateau;
        this.hex.addMouseListener(this);
        this.hex.addMouseMotionListener(this);
        this.hex.setFocusable(false);
        this.hex.setHandlerHexagone(this);
    }

    /**
     * Gère l'événement de clic sur un hexagone.
     * Si l'hexagone est valide et qu'il est connecté à un autre hexagone rempli,
     * il est rempli avec les couleurs du choix en cours, et le score est mis à jour.
     * 
     * @param ae L'événement de clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent ae) {
        if (!hex.estRempli() && !this.ch.terminer() && this.hexagonePlein()) {
            Hexagone hexChoisi = this.ch.getChoixHexagone();
            Color[] couleursChoisies = hexChoisi.getCouleur();
            this.hex.remplirAvecCouleurs(couleursChoisies);
            this.s.changerScore(this.getHexagone());
            this.ch.changerHexagone();
        }
    }

    /**
     * Vérifie si un hexagone est adjacent à au moins un hexagone rempli.
     * 
     * @return {@code true} si l'hexagone a un voisin rempli, {@code false} sinon.
     */
    public boolean hexagonePlein() {
        int ligne = hex.getLigne();
        int colonne = hex.getColonne();

        int[][] directionsPair = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}
        };
        int[][] directionsImpair = {
            {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}
        };

        int[][] directions = (colonne % 2 == 0) ? directionsPair : directionsImpair;

        for (int[] direction : directions) {
            int nouvelleLigne = ligne + direction[0];
            int nouvelleColonne = colonne + direction[1];

            if (nouvelleLigne >= 0 && nouvelleLigne < plateau.length &&
                nouvelleColonne >= 0 && nouvelleColonne < plateau[0].length) {

                Hexagone voisin = plateau[nouvelleLigne][nouvelleColonne];

                if (voisin != null && voisin.estRempli()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Met à jour les bordures des hexagones voisins.
     * Ajoute un effet visuel aux hexagones adjacents lorsqu'un hexagone est rempli.
     */
    public void changementHexagoneVoisin() {
        int ligne = hex.getLigne();
        int colonne = hex.getColonne();

        int[][] directionsPair = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}
        };
        int[][] directionsImpair = {
            {-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}
        };

        int[][] directions = (colonne % 2 == 0) ? directionsPair : directionsImpair;

        for (int[] direction : directions) {
            int nouvelleLigne = ligne + direction[0];
            int nouvelleColonne = colonne + direction[1];

            if (nouvelleLigne >= 0 && nouvelleLigne < plateau.length &&
                nouvelleColonne >= 0 && nouvelleColonne < plateau[0].length) {

                Hexagone voisin = this.plateau[nouvelleLigne][nouvelleColonne];

                if (voisin != null) {
                    voisin.setAvoisin(true);
                    voisin.repaint();
                }
            }
        }
    }

    /**
     * Retourne l'hexagone associé à ce gestionnaire.
     * 
     * @return L'hexagone géré.
     */
    public Hexagone getHexagone() {
        return this.hex;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.phex != null) {
            this.phex.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.phex != null) {
            this.phex.mouseReleased(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.phex != null) {
            this.phex.mouseDragged(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
