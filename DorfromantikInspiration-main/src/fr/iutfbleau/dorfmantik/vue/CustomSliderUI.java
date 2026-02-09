package fr.iutfbleau.dorfmantik.vue;

import javax.swing.plaf.metal.MetalSliderUI;
import javax.swing.*;
import java.awt.*;

/**
 * Classe personnalisée pour le style d'un {@code JSlider}.
 * Étend la classe {@code MetalSliderUI} pour modifier l'apparence du curseur (thumb)
 * et du canal (track) du slider.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class CustomSliderUI extends MetalSliderUI {

    /**
     * Ceci est une méthode de classe
     * Redessine le curseur (thumb) du slider en utilisant un cercle noir.
     * 
     * @param g L'objet {@code Graphics} utilisé pour dessiner le curseur.
     */
    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Activer l'anti-aliasing pour des courbes plus lisses
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Définir la couleur et dessiner un cercle pour le curseur
        g2d.setColor(Color.BLACK);
        int thumbX = thumbRect.x + thumbRect.width / 2 - 6;
        int thumbY = thumbRect.y + thumbRect.height / 2 - 6;
        g2d.fillOval(thumbX, thumbY, 12, 12); // Dessine un cercle de diamètre 12 pixels
    }

    /**
     * Ceci est une méthode de classe
     * Redessine le canal (track) du slider.
     * Inclut une ligne grise pour le fond du canal et une ligne noire pour représenter la progression.
     * 
     * @param g L'objet {@code Graphics} utilisé pour dessiner le canal.
     */
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Activer l'anti-aliasing pour des lignes plus lisses
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dessiner le fond du canal
        int trackY = trackRect.y + trackRect.height / 2 - 2;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(trackRect.x, trackY, trackRect.width, 4); // Ligne grise de 4 pixels de hauteur

        // Dessiner la partie remplie (progression)
        g2d.setColor(Color.BLACK);
        int filledWidth = thumbRect.x + thumbRect.width / 2 - trackRect.x;
        g2d.fillRect(trackRect.x, trackY, filledWidth, 4); // Ligne noire correspondant à la progression
    }
}
