package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import java.awt.*;
import java.util.*;

/**
 * Classe représentant une poche d'hexagones.
 * Une poche regroupe une liste d'hexagones ayant la même couleur et étant adjacents.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Poche {

    /**
     * La couleur de la poche.
     * Chaque poche regroupe des hexagones de cette couleur.
     */
    private final Color couleur; 

    /**
     * La liste des hexagones appartenant à cette poche.
     * Elle contient tous les hexagones de la même couleur et adjacents.
     */
    private final java.util.List<Hexagone> hexagones; 

    /**
     * Constructeur de la classe {@code Poche}.
     * Initialise une poche avec une couleur et ajoute une tuile initiale.
     * 
     * @param couleur La couleur de la poche (par exemple : bleu, vert, etc.).
     * @param tuile   L'hexagone initial à ajouter dans la poche.
     */
    public Poche(Color couleur, Hexagone tuile) {
        this.couleur = couleur;
        this.hexagones = new ArrayList<>();
        this.hexagones.add(tuile);
    }

    /**
     * Retourne la liste des hexagones appartenant à cette poche.
     * 
     * @return Une liste contenant les hexagones de la poche.
     */
    public java.util.List<Hexagone> getTuiles() {
        return hexagones;
    }

    /**
     * Ajoute un hexagone à la poche.
     * 
     * @param tuile L'hexagone à ajouter.
     */
    public void addTuile(Hexagone tuile) {
        this.hexagones.add(tuile);
    }

    /**
     * Retourne la couleur de la poche.
     * 
     * @return La couleur de la poche.
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Enlève un hexagone de la poche.
     * 
     * @param tuile L'hexagone à enlever de la poche.
     */
    public void removeTuile(Hexagone tuile) {
        this.hexagones.remove(tuile);
    }

    /**
     * Calcule le score de la poche.
     * Le score correspond au nombre d'hexagones dans la poche.
     * 
     * @return Le score de la poche.
     */
    public int calculerScore() {
        return hexagones.size();
    }
}
