package fr.iutfbleau.dorfmantik;

import fr.iutfbleau.dorfmantik.vue.*;

/**
 * Classe principale permettant de lancer le jeu Dorfmantik.
 * Cette classe initialise et affiche le menu principal du jeu.
 *
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Lancement {

    /**
     * Point d'entrée du programme.
     * Crée une instance du menu principal et l'affiche.
     *
     * @param arg Les arguments de la ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] arg) {
        /**
         * Ceci est une variable de classe
         */
        Menu m = new Menu();
        m.setVisible(true); // Rendre le menu visible
    }
}
