package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Classe représentant la fenêtre principale du jeu Dorfmantik.
 * Elle inclut le plateau de jeu, l'affichage des informations comme le score et le contrôle du volume.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Jeu extends JFrame {

    /**
     * Ceci est une variable de classe
     */
    private Score s;

    /**
     * Ceci est une variable de classe
     */
    private int seedPris;

    /**
     * Ceci est une variable de classe
     */
    private Tutoriel t;

    /**
     * Constructeur de la classe {@code Jeu}.
     * Initialise l'interface graphique, le plateau hexagonal, les hexagones et le système de score.
     *
     * @param seedPris Le seed pour générer les hexagones.
     * @param t        L'instance du tutoriel.
     */
    public Jeu(int seedPris, Tutoriel t) {
        super("Dorfmantik - Jeu");
        this.setResizable(false);
        this.seedPris = seedPris;
        this.t = t;

        // Ajout d'une fenêtre modale pour confirmer la fermeture
        this.addWindowListener(new ModalFermer());

        // Définir la taille de la fenêtre en plein écran
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.setUndecorated(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        // Charger l'image de fond
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream imageStream = loader.getResourceAsStream("images/background.jpg");
            if (imageStream == null) {
                throw new FileNotFoundException("Image de fond non trouvée !");
            }
            this.setIconImage(ImageIO.read(imageStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();

        // Génération des hexagones
        GenerateurHexagones generateur = new GenerateurHexagones(seedPris, 50);
        Hexagone[] htab = generateur.getHexagones();

        // Gestion des poches
        PocheManager pm = new PocheManager();

        // Création du panneau de sélection des hexagones
        ChoixHexagone ch = new ChoixHexagone(htab, this, this.t);
        new HandlerOrientationHexagone(ch);

        // Panneau d'informations (score, volume, etc.)
        InformationsChoix ic = new InformationsChoix(ch);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(ic, gbc);

        // Initialisation du système de score
        this.s = new Score(pm, ic);

        // Création du plateau hexagonal
        PlateauHexagonal plateau = new PlateauHexagonal(101, 101, 50, ch, this.getScore(), pm);

        // Taille personnalisée du plateau
        int plateauLargeur = (int) (screenSize.width * 0.8);
        int plateauHauteur = (int) (screenSize.height * 0.6);

        // Décalage pour centrer le plateau
        int plateauWidth = 101 * 50;
        int plateauHeight = 101 * 50;
        int xOffset = (plateauLargeur - plateauWidth) / 2;
        int yOffset = (plateauHauteur - plateauHeight) / 2;
        plateau.setBounds((xOffset - 1300), (yOffset - 1820), plateau.getPreferredSize().width, plateau.getPreferredSize().height);

        // Conteneur pour le plateau
        JPanel plateauContainer = new JPanel();
        plateauContainer.setLayout(new BorderLayout());
        plateauContainer.setPreferredSize(new Dimension(plateauLargeur, plateauHauteur));
        plateauContainer.setLayout(null);
        plateauContainer.add(plateau);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(plateauContainer, gbc);

        // Rendre la fenêtre visible
        this.setVisible(true);
    }

    /**
     * Retourne l'instance de la classe {@code Score}.
     *
     * @return L'instance de {@code Score}.
     */
    public Score getScore() {
        return this.s;
    }

    /**
     * Retourne le seed utilisé pour générer les hexagones.
     *
     * @return Le seed utilisé.
     */
    public int getSeed() {
        return this.seedPris;
    }
}
