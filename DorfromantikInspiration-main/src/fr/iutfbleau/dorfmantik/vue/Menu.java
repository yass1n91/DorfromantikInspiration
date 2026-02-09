package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Classe représentant le menu principal du jeu Dorfmantik.
 * Le menu inclut plusieurs options :
 * - Jouer.
 * - Accéder au tutoriel.
 * - Sélectionner une partie (choix de seed).
 * - Quitter.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Menu extends JFrame {

    /**
     * Ceci est une variable de classe
     */
    public ClassLoader loader = Thread.currentThread().getContextClassLoader();
    
    /**
     * Ceci est une variable de classe
     */
    private BufferedImage backgroundImage; 

    /**
     * Ceci est une variable de classe
     */
    private JComboBox<Integer> listeDeroulante; 

    /**
     * Ceci est une variable de classe
     */
    private int seedPris; 

    /**
     * Ceci est une variable de classe
     */
    private Tutoriel tutorielPanel; 

    /**
     * Constructeur de la classe {@code Menu}.
     * Initialise l'interface graphique du menu principal, configure les boutons et leurs actions.
     */
    public Menu() {
        super("Dorfmantik");

        // Configuration de la taille de la fenêtre
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.6);
        int height = (int) (screenSize.getHeight() * 0.6);
        this.setSize(width, height);

        // Empêcher le redimensionnement de la fenêtre
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer la fenêtre
        this.setMinimumSize(new Dimension(600, 400));

        // Chargement de l'image de fond
        try {
            InputStream imageStream = loader.getResourceAsStream("images/background.jpg");
            if (imageStream == null) {
                throw new FileNotFoundException("Image de fond non trouvée !");
            }
            this.setIconImage(ImageIO.read(imageStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Définir l'image de fond
        setContentPane(new PanelFond());
        setLayout(null); // Utilisation d'un positionnement absolu

        // Panneau principal (boutons) aligné à gauche
        JPanel panel = new JPanel();
        panel.setOpaque(false); // Permet de voir l'image de fond
        panel.setLayout(new GridBagLayout());
        panel.setBounds(0, 0, (int) (width * 0.4), height);
        this.add(panel);

        // Panneau du tutoriel en haut à droite
        tutorielPanel = new Tutoriel();
        tutorielPanel.setPreferredSize(new Dimension(250, 150));
        tutorielPanel.setOpaque(false);
        tutorielPanel.setBounds(width - 450, 10, 400, 120);
        if (!tutorielPanel.getIsTutorial()) {
            this.remove(tutorielPanel);
        } else {
            this.add(tutorielPanel);
        }

        // Configurer les marges et alignements dans le panneau
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 35, 10, 0);

        // Bouton "Jouer"
        CreateButton jouerButton = new CreateButton("Jouer");
        new HandlerAnimationBoutons(jouerButton);
        new HandlerJeuBoutons(jouerButton, this, tutorielPanel);
        jouerButton.setBounds(35, 15, 150, 40);
        panel.add(jouerButton, gbc);

        // Bouton "Tutoriel"
        gbc.gridy++;
        CreateButton tutorielButton = new CreateButton("Tutoriel");
        new HandlerAnimationBoutons(tutorielButton);
        new HandlerTutorielBoutons(tutorielButton, tutorielPanel);
        tutorielButton.setBounds(35, 70, 150, 40);
        panel.add(tutorielButton, gbc);

        // Bouton "Choix de Seed"
        gbc.gridy++;
        CreateButton seedButton = new CreateButton("Choix de partie");
        new HandlerAnimationBoutons(seedButton);
        Integer[] seeds = {1, 2, 3, 4}; // Liste des seeds disponibles
        this.listeDeroulante = new JComboBox<>(seeds);
        this.seedPris = 1;
        new HandlerSeedChoix(seedButton, this.listeDeroulante, this, tutorielPanel);
        seedButton.setBounds(35, 125, 150, 40);
        panel.add(seedButton, gbc);

        // Bouton "Quitter"
        gbc.gridy++;
        CreateButton quitterButton = new CreateButton("Quitter");
        new HandlerAnimationBoutons(quitterButton);
        new HandlerQuitterBoutons(quitterButton);
        quitterButton.setBounds(35, 180, 150, 40);
        panel.add(quitterButton, gbc);
    }

    /**
     * Retourne le seed actuellement sélectionné.
     *
     * @return Le seed sélectionné.
     */
    public int getSeedPris() {
        return this.seedPris;
    }

    /**
     * Définit le seed sélectionné.
     *
     * @param n Le nouveau seed à utiliser.
     */
    public void setSeedPris(int n) {
        this.seedPris = n;
    }
}
