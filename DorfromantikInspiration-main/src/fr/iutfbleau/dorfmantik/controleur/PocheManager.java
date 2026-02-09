package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.Color;
import java.util.*;

/**
 * Classe responsable de la gestion des poches d'hexagones sur le plateau.
 * Elle permet d'ajouter des hexagones aux poches, de fusionner des poches,
 * et de calculer le score total du plateau.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class PocheManager {

    /**
     * Liste des poches existantes sur le plateau.
     */
    private List<Poche> pochesExistantes; 

    /**
     * Le plateau contenant les hexagones.
     */
    private Hexagone[][] plateau; 

    /**
     * Constructeur de la classe {@code PocheManager}.
     * Initialise une nouvelle instance sans poches existantes.
     */
    public PocheManager() {
        pochesExistantes = new ArrayList<>();
    }

    /**
     * Définit le plateau pour le gestionnaire de poches.
     * 
     * @param plateau Le plateau hexagonal.
     */
    public void setPlateauPocheManager(Hexagone[][] plateau) {
        this.plateau = plateau;
    }

    /**
     * Ajoute un hexagone à une ou plusieurs poches en fonction de ses couleurs
     * et de ses voisins.
     * 
     * @param tuile L'hexagone à ajouter.
     */
    public void ajouterHexagone(Hexagone tuile) {
        if (tuile == null || tuile.getCouleur() == null) {
            return; // Hexagone ou ses couleurs non initialisés
        }

        Color[] couleurs = tuile.getCouleur();
        List<Poche> pochesPourCouleur = new ArrayList<>();
        HashSet<Color> couleurSansLiaison = new HashSet<>();
        HashSet<Poche> pochesDejaAjoutees = new HashSet<>();

        for (int direction = 0; direction < 6; direction++) {
            if (couleurs[direction] != null) {
                Poche pocheAdjacent = trouverPocheVoisin(tuile, couleurs[direction], direction);

                if (pocheAdjacent == null) {
                    couleurSansLiaison.add(couleurs[direction]);
                } else if (!pochesDejaAjoutees.contains(pocheAdjacent)) {
                    pocheAdjacent.getTuiles().add(tuile);
                    pochesPourCouleur.add(pocheAdjacent);
                    pochesDejaAjoutees.add(pocheAdjacent);
                }
            }
        }

        for (Color c : couleurSansLiaison) {
            boolean couleurTrouvee = false;
            for (Poche poche : pochesPourCouleur) {
                if (poche.getCouleur().equals(c)) {
                    couleurTrouvee = true;
                    break;
                }
            }

            if (!couleurTrouvee) {
                Poche p = new Poche(c, tuile);
                this.pochesExistantes.add(p);
            }
        }

        lierPochesIdentiques(pochesPourCouleur);
    }

    /**
     * Fusionne des poches ayant la même couleur.
     * 
     * @param pochesPourCouleur La liste des poches à fusionner.
     */
    private void lierPochesIdentiques(List<Poche> pochesPourCouleur) {
        for (int i = 0; i < pochesPourCouleur.size(); i++) {
            Poche p1 = pochesPourCouleur.get(i);
            if (p1 == null) continue;

            for (int j = i + 1; j < pochesPourCouleur.size(); j++) {
                Poche p2 = pochesPourCouleur.get(j);
                if (p2 == null) continue;

                if (p1.getCouleur().equals(p2.getCouleur())) {
                    fusionnerPoches(p1, p2);
                    pochesPourCouleur.set(i, null);
                    pochesPourCouleur.set(j, null);
                    break;
                }
            }
        }

        pochesPourCouleur.removeIf(Objects::isNull);
    }

    /**
     * Fusionne deux poches en une seule et met à jour la liste des poches existantes.
     * 
     * @param poche1 La première poche à fusionner.
     * @param poche2 La deuxième poche à fusionner.
     */
    private void fusionnerPoches(Poche poche1, Poche poche2) {
        Poche nouvellePoche = new Poche(poche1.getCouleur(), poche1.getTuiles().get(0));
        nouvellePoche.getTuiles().addAll(poche1.getTuiles());
        nouvellePoche.getTuiles().addAll(poche2.getTuiles());

        this.pochesExistantes.add(nouvellePoche);
        this.pochesExistantes.remove(poche1);
        this.pochesExistantes.remove(poche2);
    }

    /**
     * Trouve une poche voisine pour un hexagone en fonction de sa couleur et d'une direction.
     * 
     * @param tuile     L'hexagone à vérifier.
     * @param couleur   La couleur à rechercher.
     * @param direction La direction à explorer.
     * @return La poche voisine correspondante, ou {@code null} si aucune n'est trouvée.
     */
    private Poche trouverPocheVoisin(Hexagone tuile, Color couleur, int direction) {
        Hexagone voisin = this.getVoisinDansDirection(tuile, direction);
        if (voisin != null && voisin.getCouleur() != null) {
            Color couleurVoisin = this.getCouleurDansDirectionInverse(voisin, direction);
            if (couleurVoisin != null && couleurVoisin.equals(couleur)) {
                return trouverPoche(voisin, couleur);
            }
        }
        return null;
    }

    /**
     * Trouve la poche contenant un hexagone pour une couleur spécifique.
     * 
     * @param tuile   L'hexagone à rechercher.
     * @param couleur La couleur à vérifier.
     * @return La poche correspondante, ou {@code null} si aucune n'est trouvée.
     */
    private Poche trouverPoche(Hexagone tuile, Color couleur) {
        for (Poche poche : pochesExistantes) {
            if (poche.getCouleur().equals(couleur) && poche.getTuiles().contains(tuile)) {
                return poche;
            }
        }
        return null;
    }

    /**
     * Récupère un voisin dans une direction donnée.
     * 
     * @param tuile     L'hexagone de départ.
     * @param direction La direction à explorer.
     * @return L'hexagone voisin, ou {@code null} s'il n'existe pas.
     */
    private Hexagone getVoisinDansDirection(Hexagone tuile, int direction) {
        int ligne = tuile.getLigne();
        int colonne = tuile.getColonne();
        int[][] directions = {{-1, 0}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        int[][] directionsImpair = {{-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}};
        int[] directionChoisie = (colonne % 2 == 0) ? directions[direction] : directionsImpair[direction];

        int nouvelleLigne = ligne + directionChoisie[0];
        int nouvelleColonne = colonne + directionChoisie[1];

        if (estDansGrille(nouvelleLigne, nouvelleColonne)) {
            return plateau[nouvelleLigne][nouvelleColonne];
        }

        return null;
    }

    /**
     * Récupère la couleur opposée dans la direction inverse.
     * 
     * @param voisin    L'hexagone voisin.
     * @param direction La direction actuelle.
     * @return La couleur opposée ou {@code null} si non définie.
     */
    private Color getCouleurDansDirectionInverse(Hexagone voisin, int direction) {
        if (voisin == null || voisin.getCouleur() == null) {
            return null;
        }
        int directionInverse = (direction + 3) % 6;
        return voisin.getCouleur()[directionInverse];
    }

    /**
     * Vérifie si une position est dans les limites du plateau.
     * 
     * @param ligne   La ligne à vérifier.
     * @param colonne La colonne à vérifier.
     * @return {@code true} si la position est valide, {@code false} sinon.
     */
    private boolean estDansGrille(int ligne, int colonne) {
        return ligne >= 0 && ligne < plateau.length && colonne >= 0 && colonne < plateau[0].length;
    }

    /**
     * Calcule le score total du plateau en fonction des poches.
     * Le score de chaque poche est égal au carré de sa taille.
     * 
     * @return Le score total.
     */
    public int calculerScore() {
        int scoreTotal = 0;
        for (Poche poche : pochesExistantes) {
            scoreTotal += poche.calculerScore() * poche.calculerScore();
        }
        return scoreTotal;
    }
}
