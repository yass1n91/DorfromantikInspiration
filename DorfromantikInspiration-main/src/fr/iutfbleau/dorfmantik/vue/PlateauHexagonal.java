package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant le plateau hexagonal du jeu Dorfmantik.
 * Le plateau est composé d'une grille d'hexagones positionnés de manière à former un motif hexagonal.
 *
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class PlateauHexagonal extends JPanel {

    /**
     * Nombre de lignes sur le plateau.
     */
    private int lignes;

    /**
     * Nombre de colonnes sur le plateau.
     */
    private int colonnes;

    /**
     * Rayon des hexagones (en pixels).
     */
    private int rayonHexagone;

    /**
     * Instance pour gérer le choix des hexagones.
     */
    private ChoixHexagone ch;

    /**
     * Tableau bidimensionnel représentant la grille d'hexagones.
     */
    private Hexagone[][] plateau;

    /**
     * Gestionnaire du score.
     */
    private Score s;

    /**
     * Gestionnaire des poches.
     */
    private PocheManager pm;

    /**
     * Gestionnaire du déplacement du plateau.
     */
    private PlateauDraggableHandler pdh;

    /**
     * Constructeur de la classe {@code PlateauHexagonal}.
     * Initialise les dimensions du plateau, génère les hexagones, et configure les gestionnaires nécessaires.
     *
     * @param lignes        Nombre de lignes sur le plateau.
     * @param colonnes      Nombre de colonnes sur le plateau.
     * @param rayonHexagone Rayon des hexagones.
     * @param ch            Instance de {@code ChoixHexagone} pour gérer les hexagones sélectionnés.
     * @param s             Instance de {@code Score} pour gérer le score.
     * @param pm            Instance de {@code PocheManager} pour gérer les poches.
     */
    public PlateauHexagonal(int lignes, int colonnes, int rayonHexagone, ChoixHexagone ch, Score s, PocheManager pm) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.rayonHexagone = rayonHexagone;
        this.ch = ch;
        this.s = s;
        this.pm = pm;

        // Gestion du déplacement du plateau
        this.pdh = new PlateauDraggableHandler(this);

        // Calcul des dimensions préférées du plateau
        int largeur = (int) (colonnes * rayonHexagone * 1.5) + 3 * this.rayonHexagone;
        int hauteur = (int) ((lignes + 0.5) * rayonHexagone * Math.sqrt(3)) + 50;

        setPreferredSize(new Dimension(largeur, hauteur));
        setLayout(null); // Positionnement manuel des composants
        setFocusable(true);

        this.plateau = new Hexagone[lignes][colonnes];

        // Générer les hexagones et les placer sur le plateau
        genererHexagones();
    }

    /**
     * Génère les hexagones et les place sur le plateau.
     * Chaque hexagone est positionné de manière à former une grille hexagonale.
     */
    private void genererHexagones() {
        // Calcul de la hauteur effective d'un hexagone
        double hauteurHexagone = Math.sqrt(3) * this.rayonHexagone;

        // Positions initiales en pixels
        int x = 0;
        int y = 0;

        // Préparer un hexagone spécial pour le cinquantième
        Hexagone cinquantieme = new Hexagone(50);

        for (int row = 0; row < this.lignes; row++) {
            boolean allerVersSudEst = true; // Alternance entre Sud-Est et Nord-Est
            for (int col = 0; col < this.colonnes; col++) {
                Hexagone hexagone = new Hexagone(this.rayonHexagone);

                // Associer un gestionnaire à chaque hexagone
                new HandlerHexagone(hexagone, this.getChoixHexagone(), this.getScore(), this.pdh, this.plateau);

                // Identifier le cinquantième hexagone
                if (col == 50 && row == 50) {
                    cinquantieme = hexagone;
                }

                // Positionner l'hexagone
                hexagone.setBounds(x, y, this.rayonHexagone * 2, this.rayonHexagone * 2);
                hexagone.setLigneColonne(row, col);
                plateau[row][col] = hexagone;

                // Ajouter l'hexagone au panneau
                this.add(hexagone);

                // Calculer la position suivante
                if (allerVersSudEst) {
                    x += (int) (1.5 * this.rayonHexagone); // Déplacement horizontal (Sud-Est)
                    y += (int) (hauteurHexagone / 2);      // Déplacement vertical (Sud-Est)
                } else {
                    x += (int) (1.5 * this.rayonHexagone); // Déplacement horizontal (Nord-Est)
                    y -= (int) (hauteurHexagone / 2);      // Déplacement vertical (Nord-Est)
                }

                // Alterner la direction
                allerVersSudEst = !allerVersSudEst;
            }

            // Réinitialiser pour la ligne suivante
            x = 0;
            y += this.rayonHexagone - 7;
        }

        // Remplir le cinquantième hexagone avec des couleurs choisies
        Hexagone hexChoisi = this.ch.getChoixHexagone();
        Color[] couleursChoisies = hexChoisi.getCouleur();
        cinquantieme.remplirAvecCouleurs(couleursChoisies);
        this.ch.changerHexagone();

        // Enregistrer le plateau dans le gestionnaire de poches
        this.pm.setPlateauPocheManager(this.getPlateau());

        // Calcul du score initial pour le premier hexagone
        this.s.changerScore(this.plateau[50][50]);
    }

    /**
     * Retourne la grille d'hexagones du plateau.
     *
     * @return Un tableau bidimensionnel représentant le plateau.
     */
    public Hexagone[][] getPlateau() {
        return this.plateau;
    }

    /**
     * Retourne l'instance de {@code ChoixHexagone}.
     *
     * @return L'instance de {@code ChoixHexagone}.
     */
    public ChoixHexagone getChoixHexagone() {
        return this.ch;
    }

    /**
     * Retourne l'instance de {@code Score}.
     *
     * @return L'instance de {@code Score}.
     */
    public Score getScore() {
        return this.s;
    }
}
