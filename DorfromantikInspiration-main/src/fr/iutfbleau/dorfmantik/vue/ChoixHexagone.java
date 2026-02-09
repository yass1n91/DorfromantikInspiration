package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant le panneau de sélection et d'affichage des hexagones dans le jeu.
 * Gère l'interface pour choisir, afficher et tourner les hexagones.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class ChoixHexagone extends JPanel {

    /**
     * Tableau contenant les hexagones disponibles.
     */
    private Hexagone[] hexagonalTableau;

    /**
     * Index de l'hexagone actuellement sélectionné.
     */
    private int occurenceHexagone;

    /**
     * Indique si le jeu est terminé.
     */
    private boolean jeuTerminer;

    /**
     * Instance du jeu associée.
     */
    private Jeu jeu;

    /**
     * Panneau pour afficher l'hexagone actuel.
     */
    private JPanel hexagonePanel;

    /**
     * Gestionnaire de musique.
     */
    private MusicTask mt;

    /**
     * Instance du tutoriel pour afficher les messages d'aide.
     */
    private Tutoriel t;

    /**
     * Constructeur de la classe {@code ChoixHexagone}.
     * Initialise les composants nécessaires pour afficher et manipuler les hexagones.
     *
     * @param hexTab Le tableau d'hexagones à afficher.
     * @param jeu    L'instance du jeu associée.
     * @param t      L'instance du tutoriel pour afficher les messages d'aide.
     */
    public ChoixHexagone(Hexagone[] hexTab, Jeu jeu, Tutoriel t) {
        this.hexagonalTableau = hexTab;
        this.occurenceHexagone = 0;
        this.jeu = jeu;
        this.t = t;
        this.jeuTerminer = false;

        this.setLayout(new BorderLayout());

        // Ajouter un gestionnaire pour tourner les hexagones
        new HandlerTournerHexagone(this);

        // Créer un panneau pour afficher l'hexagone
        this.hexagonePanel = new JPanel();
        this.hexagonePanel.setOpaque(false);
        Hexagone hex = this.getChoixHexagone();
        hex.setRayon(75); // Définir le rayon de l'hexagone
        this.hexagonePanel.add(hex);

        // Ajouter le panneau à l'interface
        this.add(this.hexagonePanel, BorderLayout.CENTER);
    }

    /**
     * Retourne l'hexagone actuellement sélectionné.
     *
     * @return L'hexagone sélectionné.
     */
    public Hexagone getChoixHexagone() {
        return this.hexagonalTableau[this.occurenceHexagone];
    }

    /**
     * Affiche l'hexagone sélectionné sur le panneau.
     * Met également à jour les messages du tutoriel si nécessaire.
     */
    public void afficherHexagone() {
        this.hexagonePanel.removeAll(); // Nettoyer le panneau

        Hexagone hex = this.getChoixHexagone();
        hex.setRayon(75); // Ajuster la taille de l'hexagone
        this.hexagonePanel.add(hex);

        // Rafraîchir l'affichage
        this.hexagonePanel.revalidate();
        this.hexagonePanel.repaint();

        // Messages tutoriels en fonction de l'état du jeu
        if (this.occurenceHexagone == 5 && this.t.getIsTutorial()) {
            this.t.setMessageText("Tu peux appuyer sur le prochain hexagone pour le tourner afin de le faire correspondre au mieux avec les autres.");
            this.t.fenetreTuto();
        }
        if (this.occurenceHexagone == 7 && this.t.getIsTutorial()) {
            this.t.setMessageText("Plus tu poses des couleurs similaires côte-à-côte, plus tu gagnes de points ! Tu as un total de 49 hexagones à placer, bon courage !");
            this.t.fenetreTuto();
        }
        if (this.occurenceHexagone == 47 && this.t.getIsTutorial()) {
            this.t.setMessageText("Attention, il ne te reste plus que 3 hexagones à placer. Tu pourras ensuite voir si tu fais partie des meilleurs joueurs et retenter ta chance.");
            this.t.fenetreTuto();
            this.t.setIsTutorial();
        }
    }

    /**
     * Passe à l'hexagone suivant et met à jour l'affichage.
     * Si tous les hexagones ont été placés, termine le jeu et affiche le score.
     */
    public void changerHexagone() {
        if (this.occurenceHexagone < hexagonalTableau.length - 1) {
            this.occurenceHexagone++;
            afficherHexagone(); // Met à jour l'affichage
        } else {
            this.jeuTerminer = true;
            Score.afficherScore(this.jeu.getScore().getPoints(), this.getJeu(), this.getMusic());
        }
    }

    /**
     * Définit le gestionnaire de musique.
     *
     * @param mt L'instance de {@code MusicTask} à utiliser.
     */
    public void setMusic(MusicTask mt) {
        this.mt = mt;
    }

    /**
     * Retourne le gestionnaire de musique.
     *
     * @return L'instance de {@code MusicTask}.
     */
    public MusicTask getMusic() {
        return this.mt;
    }

    /**
     * Vérifie si le jeu est terminé.
     *
     * @return {@code true} si le jeu est terminé, {@code false} sinon.
     */
    public boolean terminer() {
        return this.jeuTerminer;
    }

    /**
     * Retourne l'instance du jeu associée.
     *
     * @return L'instance de {@code Jeu}.
     */
    public Jeu getJeu() {
        return this.jeu;
    }
}
