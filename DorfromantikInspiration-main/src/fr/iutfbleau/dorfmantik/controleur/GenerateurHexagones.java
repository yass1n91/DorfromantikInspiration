package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.Color;
import java.util.Random;

/**
 * Classe responsable de la génération de tuiles hexagonales pseudo-aléatoires
 * avec des couleurs, basée sur un seed.
 * Cette classe permet de créer un tableau d'hexagones qui peuvent être utilisés dans le jeu.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class GenerateurHexagones {

    /**
     * Tableau d'hexagones générés
     */
    private Hexagone[] hexagones;

    /**
     * Taille du tableau d'hexagones
     */
    private int tailleTableau;

    // Définition des couleurs fixes pour les hexagones
    private static Color bleu = new Color(46, 176, 255);
    private static Color jaune = new Color(255, 249, 46);
    private static Color vert_c = new Color(145, 255, 145);
    private static Color vert_f = new Color(0, 113, 0);
    private static Color gris = new Color(124, 139, 124);

    // Tableau des couleurs disponibles
    private static Color[] couleursDisponibles = {bleu, jaune, vert_c, vert_f, gris};

    /**
     * Constructeur de la classe GenerateurHexagones.
     * Initialise le tableau d'hexagones et génère leur contenu en fonction du seed.
     * 
     * @param seed Le seed utilisé pour générer les hexagones de manière déterministe.
     * @param tailleTableau La taille du tableau d'hexagones à générer.
     */
    public GenerateurHexagones(int seed, int tailleTableau) {
        this.tailleTableau = tailleTableau;
        this.hexagones = new Hexagone[tailleTableau];
        genererHexagones(seed);
    }

    /**
     * Génère un tableau d'hexagones avec des couleurs déterminées par le seed.
     * 
     * @param seed Le seed utilisé pour générer les couleurs des hexagones.
     */
    private void genererHexagones(int seed) {
        for (int i = 0; i < tailleTableau; i++) {
            Hexagone hex = new Hexagone(50); // Crée un hexagone avec un rayon fixe de 50
            Color[] couleurs = genererCouleursParSeed(seed + i); // Génère des couleurs pour chaque hexagone
            hex.remplirAvecCouleurs(couleurs); // Applique les couleurs aux triangles de l'hexagone
            hexagones[i] = hex; // Ajoute l'hexagone au tableau
        }
    }

    /**
     * Génère un tableau de couleurs pseudo-aléatoires basé sur un seed donné.
     * Les couleurs sont assignées aux six triangles d'un hexagone, en respectant une logique
     * de répartition aléatoire mais déterministe.
     * 
     * @param seed Le seed utilisé pour générer les couleurs.
     * @return Un tableau contenant les couleurs des six triangles de l'hexagone.
     */
    private Color[] genererCouleursParSeed(int seed) {
        Random rand = new Random(seed);
        Color[] couleurs = new Color[6]; // Tableau pour les 6 triangles de l'hexagone

        int nbrFacesCouleur1 = rand.nextInt(6); // Nombre de triangles pour la première couleur
        int indicePremierTriangle = rand.nextInt(6); // Indice de départ pour la première couleur

        Color couleur1 = couleursDisponibles[rand.nextInt(couleursDisponibles.length)]; // Première couleur

        // Assigne la première couleur
        for (int i = 0; i < nbrFacesCouleur1; i++) {
            couleurs[(indicePremierTriangle + i) % 6] = couleur1;
        }

        // Sélectionne une deuxième couleur différente de la première
        Color couleur2 = couleursDisponibles[rand.nextInt(couleursDisponibles.length)];
        while (couleur2.equals(couleur1)) {
            couleur2 = couleursDisponibles[rand.nextInt(couleursDisponibles.length)];
        }

        // Assigne la deuxième couleur aux triangles restants
        for (int i = 0; i < 6; i++) {
            if (couleurs[i] == null) {
                couleurs[i] = couleur2;
            }
        }

        return couleurs;
    }

    /**
     * Retourne le tableau des hexagones générés.
     * 
     * @return Un tableau contenant les hexagones générés.
     */
    public Hexagone[] getHexagones() {
        return hexagones;
    }
}
