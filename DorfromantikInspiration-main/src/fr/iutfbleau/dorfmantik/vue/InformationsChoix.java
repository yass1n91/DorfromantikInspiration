package fr.iutfbleau.dorfmantik.vue;

import fr.iutfbleau.dorfmantik.controleur.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Classe représentant le panneau d'informations du jeu, comprenant :
 * - L'affichage du score.
 * - Un slider pour contrôler le volume.
 * - L'affichage d'un hexagone sélectionné au centre.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class InformationsChoix extends JPanel {

    /**
     * Ceci est une variable de classe
     */
    private JLabel scoreLabel;

    /**
     * Ceci est une variable de classe
     */
    private JSlider volumeSlider;

    /**
     * Ceci est une variable de classe
     */
    private MusicTask musicTask;

    /**
     * Constructeur de la classe {@code InformationsChoix}.
     * Configure l'interface utilisateur pour afficher le score, contrôler le volume, 
     * et afficher les hexagones sélectionnés.
     *
     * @param choixHexagone Le panneau des hexagones sélectionnés.
     */
    public InformationsChoix(ChoixHexagone choixHexagone) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Lancer la musique dans un thread séparé
        this.startMusic();

        // Panneau d'affichage du score
        JPanel scorePanel = new JPanel(new GridBagLayout());
        GridBagConstraints scoreGbc = new GridBagConstraints();

        JLabel scoreTitle = new JLabel("Score :");
        scoreTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        this.scoreLabel = new JLabel(String.valueOf(0)); // Score initial
        this.scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Ajouter le titre et le score au panneau
        scoreGbc.gridx = 0;
        scoreGbc.gridy = 0;
        scoreGbc.anchor = GridBagConstraints.CENTER;
        scorePanel.add(scoreTitle, scoreGbc);

        scoreGbc.gridy = 1;
        scorePanel.add(this.scoreLabel, scoreGbc);

        // Ajouter le panneau de score à la disposition principale
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(scorePanel, gbc);

        // Panneau pour la barre de volume
        JPanel volumePanel = new JPanel(new GridBagLayout());
        GridBagConstraints volumeGbc = new GridBagConstraints();

        // Ajouter une icône pour le contrôle du son
        JLabel sonImageLabel = new JLabel();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream imageStream = loader.getResourceAsStream("images/son.png");
            if (imageStream == null) {
                throw new IllegalArgumentException("Image 'son.png' non trouvée.");
            }
            Image image = ImageIO.read(imageStream).getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            sonImageLabel.setIcon(new ImageIcon(image));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image 'son.png': " + e.getMessage());
        }

        volumeGbc.gridx = 0;
        volumeGbc.gridy = 0;
        volumeGbc.anchor = GridBagConstraints.CENTER;
        volumeGbc.insets = new Insets(0, 0, 0, 10);
        volumePanel.add(sonImageLabel, volumeGbc);

        // Ajouter un slider pour contrôler le volume
        this.volumeSlider = new JSlider(0, 100, 30);
        this.volumeSlider.setPreferredSize(new Dimension(150, 32));
        this.volumeSlider.setPaintTicks(true);
        this.volumeSlider.setPaintLabels(true);
        this.volumeSlider.setUI(new CustomSliderUI());

        // Lier le slider au VolumeController
        if (this.musicTask != null && this.musicTask.getMusique() != null) {
            new VolumeController(this.volumeSlider, musicTask.getMusique());
        }

        volumeGbc.gridx = 1;
        volumeGbc.gridy = 0;
        volumePanel.add(this.volumeSlider, volumeGbc);

        // Ajouter le panneau de volume à la disposition principale
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(volumePanel, gbc);

        // Ajouter le panneau des hexagones au centre
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(choixHexagone, gbc);

        choixHexagone.setMusic(this.musicTask);
    }

    /**
     * Initialise et démarre la musique en arrière-plan.
     */
    private void startMusic() {
        this.musicTask = new MusicTask();
        Thread musicThread = new Thread(musicTask);
        musicThread.start();
    }

    /**
     * Met à jour l'affichage du score.
     *
     * @param newscore Le nouveau score à afficher.
     */
    public void updateScore(int newscore) {
        this.scoreLabel.setText(String.valueOf(newscore));
    }

    /**
     * Retourne le slider de volume.
     *
     * @return Le {@code JSlider} pour contrôler le volume.
     */
    public JSlider getVolumeSlider() {
        return this.volumeSlider;
    }
}
