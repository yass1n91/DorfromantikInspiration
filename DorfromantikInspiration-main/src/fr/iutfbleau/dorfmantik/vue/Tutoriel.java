package fr.iutfbleau.dorfmantik.vue;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.*;
import fr.iutfbleau.dorfmantik.controleur.*;

/**
 * Classe représentant un panneau de tutoriel interactif pour guider les joueurs.
 * Cette classe affiche des messages explicatifs et une image d'assistance (robot).
 *
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Tutoriel extends JPanel {

    /**
     * Ceci est une variable de classe.
     */
    private boolean isTutorial;

    /**
     * Ceci est une variable de classe.
     */
    private JTextArea message;

    /**
     * Ceci est une variable de classe.
     */
    private int step;

    /**
     * Constructeur de la classe {@code Tutoriel}.
     * Initialise le panneau de tutoriel avec un message et une image.
     */
    public Tutoriel() {
        super();
        this.step = 0;
        this.isTutorial = false;

        Font policePersonnalisee = Fonts.chargerPolice("/fonts/arial.ttf", Font.BOLD, 20);

        this.message = new JTextArea("");
        JLabel robotImage = new JLabel();

        // Charger l'image du robot
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream imageStream = loader.getResourceAsStream("images/robot.png");
            if (imageStream == null) {
                throw new FileNotFoundException("Image 'robot.png' non trouvée !");
            }
            Image image = ImageIO.read(imageStream);
            ImageIcon robotIcon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            robotImage.setIcon(robotIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Configurer la zone de texte
        message.setFont(policePersonnalisee);
        message.setWrapStyleWord(true);
        message.setEditable(false);
        message.setLineWrap(true);
        message.setBackground(new Color(0, 0, 0, 0)); // Fond transparent
        message.setBorder(null);
        message.setPreferredSize(new Dimension(300, 150));

        // Mise en page avec GridBagLayout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Ajouter le message
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 5);
        this.add(message, gbc);

        // Ajouter l'image
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 0);
        this.add(robotImage, gbc);

        // Définir la taille préférée du panneau
        this.setPreferredSize(new Dimension(400, 200));
    }

    /**
     * Met à jour le texte affiché dans la zone de message.
     *
     * @param newText Le nouveau texte à afficher.
     */
    public void setMessageText(String newText) {
        message.setText(newText);
        this.step++;
    }

    /**
     * Retourne l'étape actuelle du tutoriel.
     *
     * @return L'étape actuelle.
     */
    public int getStep() {
        return this.step;
    }

    /**
     * Retourne l'état du tutoriel (activé ou non).
     *
     * @return {@code true} si le tutoriel est activé, {@code false} sinon.
     */
    public boolean getIsTutorial() {
        return this.isTutorial;
    }

    /**
     * Active ou désactive le tutoriel.
     */
    public void setIsTutorial() {
        this.isTutorial = !this.isTutorial;
    }

    /**
     * Affiche une nouvelle fenêtre pour le tutoriel.
     */
    public void fenetreTuto() {
        JFrame tutorielFrame = new JFrame("Tutoriel");
        tutorielFrame.setSize(500, 300);
        tutorielFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tutorielFrame.setLocationRelativeTo(null);
        tutorielFrame.setAlwaysOnTop(true);

        // Panneau principal
        JPanel panel = new JPanel(new BorderLayout());

        // Ajouter le panneau Tutoriel
        panel.add(this, BorderLayout.CENTER);

        // Ajouter un bouton "J'ai compris"
        JButton fermerButton = new JButton("J'ai compris");
        new HandlerUnderstandBouton(fermerButton, tutorielFrame);
        panel.add(fermerButton, BorderLayout.SOUTH);

        // Ajouter le panneau à la fenêtre et afficher
        tutorielFrame.add(panel);
        tutorielFrame.setVisible(true);
    }
}
