package fr.iutfbleau.dorfmantik.vue;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Classe représentant un panneau avec une image de fond personnalisée.
 * Ce panneau redimensionne automatiquement l'image de fond pour occuper tout l'espace disponible.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class PanelFond extends JPanel {

    /**
     * Ceci est une variable de classe
     */
    private BufferedImage backgroundImage; 

    /**
     * Constructeur de la classe {@code PanelFond}.
     * Charge une image de fond depuis les ressources.
     */
    public PanelFond() {
        try {
            // Charger l'image de fond depuis les ressources
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream imageStream = loader.getResourceAsStream("images/background.jpg");
            this.backgroundImage = ImageIO.read(imageStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de l'image de fond.");
        }

        // Définir une couleur de fond par défaut (au cas où l'image ne se charge pas)
        this.setBackground(Color.BLACK);
    }

    /**
     * Surcharge de la méthode {@code paintComponent} pour dessiner l'image de fond.
     *
     * @param g L'objet {@code Graphics} utilisé pour dessiner les composants.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Redessine les composants enfants

        if (this.backgroundImage != null) {
            // Dessiner l'image de fond à la taille du panneau
            g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
