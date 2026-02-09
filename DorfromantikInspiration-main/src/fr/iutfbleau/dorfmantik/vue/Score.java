package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import fr.iutfbleau.dorfmantik.model.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe représentant la gestion du score dans le jeu Dorfmantik.
 * Le score est calculé en fonction des hexagones ajoutés et des poches formées.
 *
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Score {

    /**
     * Ceci est une variable de classe.
     */
    private int points;

    /**
     * Ceci est une variable de classe.
     */
    private PocheManager pm;

    /**
     * Ceci est une variable de classe.
     */
    private InformationsChoix ic;

    /**
     * Constructeur de la classe {@code Score}.
     *
     * @param pm Gestionnaire des poches permettant de calculer le score.
     * @param ic Interface permettant d'afficher le score.
     */
    public Score(PocheManager pm, InformationsChoix ic) {
        this.points = 0;
        this.pm = pm;
        this.ic = ic;
    }

    /**
     * Met à jour le score en ajoutant un hexagone.
     *
     * @param hex L'hexagone ajouté.
     */
    public void changerScore(Hexagone hex) {
        this.pm.ajouterHexagone(hex); // Ajoute l'hexagone dans le gestionnaire de poches
        this.points = this.pm.calculerScore(); // Recalcule le score total
        this.ic.updateScore(this.getPoints()); // Met à jour l'affichage du score
    }

    /**
     * Retourne le score actuel.
     *
     * @return Le score du joueur.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Affiche une fenêtre contenant le score final du joueur et les 10 meilleurs scores.
     *
     * @param score Score obtenu par le joueur.
     * @param jeu   Instance du jeu pour accéder aux informations nécessaires.
     * @param mt    Instance de {@code MusicTask} pour gérer la musique.
     */
    public static void afficherScore(int score, Jeu jeu, MusicTask mt) {

        /**
         * Ceci est une variable locale.
         */
        JFrame frame = new JFrame("Score du joueur");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.setAlwaysOnTop(true);

        /**
         * Ceci est une variable locale.
         */
        Font policePersonnalisee = Fonts.chargerPolice("/fonts/arial.ttf", Font.PLAIN, 24);

        /**
         * Ceci est une variable locale.
         */
        JLabel titre = new JLabel("Résultat de la partie", SwingConstants.CENTER);
        titre.setFont(policePersonnalisee.deriveFont(Font.BOLD));
        frame.add(titre, BorderLayout.NORTH);

        /**
         * Ceci est une variable locale.
         */
        int[] top10Scores;

        /**
         * Ceci est une variable locale.
         */
        ScoreBD scoreBD = new ScoreBD();
        try {
            scoreBD.ajouterScore(score, jeu.getSeed());
            top10Scores = scoreBD.recupererTop10Scores(jeu.getSeed());
        } finally {
            scoreBD.close();
        }

        JPanel scoresPanel = new JPanel();
        scoresPanel.setLayout(new BoxLayout(scoresPanel, BoxLayout.Y_AXIS));
        scoresPanel.add(Box.createVerticalStrut(20));

        JLabel joueurScoreLabel = new JLabel("Votre score : " + score, SwingConstants.CENTER);
        joueurScoreLabel.setFont(policePersonnalisee.deriveFont(Font.BOLD));
        joueurScoreLabel.setForeground(Color.RED);
        joueurScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresPanel.add(joueurScoreLabel);
        scoresPanel.add(Box.createVerticalStrut(20));

        Font policeSecondaire = Fonts.chargerPolice("/fonts/arial.ttf", Font.PLAIN, 20);
        for (int i = 0; i < top10Scores.length; i++) {
            JLabel scoreLabel = new JLabel((i + 1) + ". " + top10Scores[i], SwingConstants.CENTER);
            scoreLabel.setFont(policeSecondaire);
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            scoresPanel.add(scoreLabel);
            scoresPanel.add(Box.createVerticalStrut(5));
        }

        JScrollPane scrollPane = new JScrollPane(scoresPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        CreateButton boutonRejouer = new CreateButton("Rejouer");
        new HandlerRejouerBoutons(boutonRejouer, jeu, frame, mt);
        panneauBoutons.add(boutonRejouer);

        CreateButton boutonFermer = new CreateButton("Quitter");
        new HandlerQuitterBoutons(boutonFermer);
        panneauBoutons.add(boutonFermer);

        frame.add(panneauBoutons, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
