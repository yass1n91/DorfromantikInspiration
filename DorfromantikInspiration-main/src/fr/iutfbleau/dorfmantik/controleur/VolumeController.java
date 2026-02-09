package fr.iutfbleau.dorfmantik.controleur;

import javax.swing.event.*;
import javax.swing.JSlider;

/**
 * Contrôleur pour ajuster le volume de la musique via un {@code JSlider}.
 * Implémente l'interface {@code ChangeListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class VolumeController implements ChangeListener {

    /**
     * Référence à l'instance de Musique pour ajuster le volume.
     */
    private Musique musique; 
    
    /**
     * Slider utilisé pour contrôler le volume.
     */
    private JSlider slider;  

    /**
     * Constructeur de la classe {@code VolumeController}.
     * Initialise le contrôleur avec un slider et une instance de musique.
     * Ajoute le contrôleur en tant que ChangeListener au slider.
     * 
     * @param slider  Le slider permettant de régler le volume (valeurs entre 0 et 100).
     * @param musique L'instance de musique à contrôler.
     */
    public VolumeController(JSlider slider, Musique musique) {
        this.musique = musique;
        this.slider = slider;
        
        // Ajout du ChangeListener directement au slider
        this.slider.addChangeListener(this);
    }

    /**
     * Méthode appelée lorsque l'état du slider change.
     * Ajuste le volume de la musique en fonction de la position du slider.
     * 
     * @param e L'événement déclenché par le changement de valeur du slider.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        // Récupérer la valeur actuelle du slider (entre 0 et 100)
        int volumeValue = this.slider.getValue();
        
        // Ajuster le volume de la musique (convertir en une valeur entre 0.0 et 1.0)
        if (musique != null) {
            musique.setVolume(volumeValue / 100f);
        }
    }
}
