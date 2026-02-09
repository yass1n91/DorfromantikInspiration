package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant un hexagone graphique dans le jeu.
 * Cette classe permet de dessiner, manipuler et remplir un hexagone avec des couleurs spécifiques.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Hexagone extends JPanel {

    /**
     * Rayon de l'hexagone (distance du centre à un sommet).
     */
    private int rayon;

    /**
     * Couleurs des 6 côtés de l'hexagone : [Nord, Nord-Est, ..., Nord-Ouest].
     */
    private Color[] couleurs;

    /**
     * Indique si l'hexagone est complètement rempli.
     */
    private boolean complet;

    /**
     * Épaisseur des bordures de l'hexagone.
     */
    private int epaisseurBordure;

    /**
     * Position de l'hexagone sur le plateau (ligne).
     */
    private int ligne;

    /**
     * Position de l'hexagone sur le plateau (colonne).
     */
    private int colonne;

    /**
     * Gestionnaire associé à cet hexagone.
     */
    private HandlerHexagone hh;

    /**
     * Indique si cet hexagone a un voisin adjacent.
     */
    private boolean aVoisin;

    /**
     * Constructeur de la classe {@code Hexagone}.
     * Initialise un hexagone avec un rayon donné.
     * 
     * @param rayon Le rayon de l'hexagone (distance du centre à un sommet).
     */
    public Hexagone(int rayon) {
        super();
        this.rayon = rayon;
        this.epaisseurBordure = 3;
        this.complet = false;

        // Configuration du panneau graphique
        setPreferredSize(new Dimension(this.rayon * 2, this.rayon * 2));
        setOpaque(false);
        setFocusable(false);
        setBackground(null);
    }

    /**
     * Méthode appelée pour dessiner l'hexagone.
     * Remplit les triangles avec les couleurs définies et dessine les bordures.
     * 
     * @param g L'objet {@code Graphics} utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (this.aVoisin || this.complet) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

            int centreX = getWidth() / 2;
            int centreY = getHeight() / 2;

            int adjustedRayon = rayon - epaisseurBordure;
            int[] xPoints = new int[6];
            int[] yPoints = new int[6];

            // Calculer les points des sommets de l'hexagone
            for (int i = 0; i < 6; i++) {
                int angle = 60 * i;
                xPoints[i] = centreX + (int) (adjustedRayon * Math.cos(Math.toRadians(angle)));
                yPoints[i] = centreY + (int) (adjustedRayon * Math.sin(Math.toRadians(angle)));
            }

            // Dessiner les bordures
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(epaisseurBordure));
            g2d.drawPolygon(xPoints, yPoints, 6);

            // Remplir les triangles avec les couleurs
            if (this.complet) {
                for (int i = 0; i < 6; i++) {
                    int[] triangleX = new int[3];
                    int[] triangleY = new int[3];

                    triangleX[0] = centreX;
                    triangleY[0] = centreY;

                    int angle = 60 * i + 30;
                    triangleX[1] = centreX + (int) (adjustedRayon * Math.sin(Math.toRadians(angle)));
                    triangleY[1] = centreY - (int) (adjustedRayon * Math.cos(Math.toRadians(angle)));

                    int nextAngle = 60 * i - 30;
                    triangleX[2] = centreX + (int) (adjustedRayon * Math.sin(Math.toRadians(nextAngle)));
                    triangleY[2] = centreY - (int) (adjustedRayon * Math.cos(Math.toRadians(nextAngle)));

                    g2d.setColor(couleurs[i] != null ? couleurs[i] : Color.WHITE);
                    g2d.fillPolygon(triangleX, triangleY, 3);
                }
            }
        }
    }

    /**
     * Définit si cet hexagone a un voisin adjacent.
     * 
     * @param aVoisin {@code true} si l'hexagone a un voisin, {@code false} sinon.
     */
    public void setAvoisin(boolean aVoisin) {
        this.aVoisin = aVoisin;
    }

    /**
     * Associe un gestionnaire à cet hexagone.
     * 
     * @param hh L'instance de {@code HandlerHexagone} à associer.
     */
    public void setHandlerHexagone(HandlerHexagone hh) {
        this.hh = hh;
    }

    /**
     * Définit la position de l'hexagone dans la grille.
     * 
     * @param ligne   La ligne de l'hexagone.
     * @param colonne La colonne de l'hexagone.
     */
    public void setLigneColonne(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Retourne la ligne de l'hexagone dans la grille.
     * 
     * @return La ligne de l'hexagone.
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * Retourne la colonne de l'hexagone dans la grille.
     * 
     * @return La colonne de l'hexagone.
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Remplit l'hexagone avec les couleurs spécifiées.
     * 
     * @param nouvellesCouleurs Un tableau de 6 couleurs à appliquer aux côtés de l'hexagone.
     */
    public void remplirAvecCouleurs(Color[] nouvellesCouleurs) {
        if (!this.complet) {
            this.couleurs = nouvellesCouleurs;
            this.complet = true;
            if (this.hh != null) {
                this.hh.changementHexagoneVoisin();
            }
            this.repaint();
        }
    }

    /**
     * Met à jour les couleurs de l'hexagone sans modifier son état de remplissage.
     * 
     * @param nouvellesCouleurs Un tableau de 6 couleurs à appliquer.
     */
    public void mettreAJourCouleurs(Color[] nouvellesCouleurs) {
        this.couleurs = nouvellesCouleurs;
        this.repaint();
    }

    /**
     * Vérifie si l'hexagone est rempli.
     * 
     * @return {@code true} si l'hexagone est rempli, {@code false} sinon.
     */
    public boolean estRempli() {
        return this.complet;
    }

    /**
     * Retourne les couleurs des côtés de l'hexagone.
     * 
     * @return Un tableau de 6 couleurs représentant les côtés de l'hexagone.
     */
    public Color[] getCouleur() {
        return this.couleurs;
    }

    /**
     * Définit le rayon de l'hexagone et met à jour la taille du panneau.
     * 
     * @param rayon Le nouveau rayon de l'hexagone.
     */
    public void setRayon(int rayon) {
        this.rayon = rayon;
        setPreferredSize(new Dimension(this.rayon * 2, this.rayon * 2));
        this.repaint();
    }
}
