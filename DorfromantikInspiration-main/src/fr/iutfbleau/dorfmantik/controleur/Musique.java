package fr.iutfbleau.dorfmantik.controleur;

import javax.sound.sampled.*;

/**
 * Classe responsable de la gestion de la lecture musicale.
 * Permet de lire, arrêter et ajuster le volume d'un fichier audio.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class Musique {

    /**
     * Volume actuel de la musique, entre 0.0f (silence) et 1.0f (volume maximal).
     * Cette variable est utilisée pour régler le volume de la musique en cours de lecture.
     */
    private float volume;

    /**
     * Clip audio pour jouer la musique.
     * Il contient le fichier audio chargé et permet la lecture et le contrôle du son.
     */
    private Clip clip;

    /**
     * Constructeur de la classe {@code Musique}.
     * Initialise le volume par défaut à 0.3 et prépare le clip.
     * 
     * Le clip sera initialisé lors de la première lecture de la musique.
     */
    public Musique() {
        this.volume = 0.3f; // Valeur initiale du volume (30%)
        this.clip = null;   // Clip est initialisé à null, il sera chargé lors de la première lecture
    }

    /**
     * Définit le volume de la musique.
     * Le volume est contraint entre 0.0 (muet) et 1.0 (volume maximum).
     * Le changement de volume est appliqué en ajustant les décibels du clip.
     * 
     * @param volume La valeur du volume à définir (entre 0.0 et 1.0).
     */
    public void setVolume(float volume) {
        this.volume = Math.max(0.0f, Math.min(1.0f, volume)); // Contraint le volume entre 0.0 et 1.0
        if (clip != null) {
            try {
                // Récupère le contrôle de volume et applique la conversion en décibels
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volumeControl.setValue(20f * (float) Math.log10(this.volume)); // Conversion du volume en décibels
            } catch (IllegalArgumentException e) {
                System.err.println("Le contrôle du volume n'est pas disponible pour ce clip.");
            }
        }
    }

    /**
     * Joue la musique en boucle. Si la musique n'est pas encore chargée,
     * elle est initialisée à partir du fichier audio spécifié.
     * 
     * La musique est lue en continu (en boucle infinie) dès qu'elle est lancée.
     */
    public void play() {
        try {
            if (clip == null) {
                // Charge le fichier audio à partir du chemin spécifié
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/musique/musique.wav"));
                clip = AudioSystem.getClip(); // Crée un objet Clip pour gérer la musique
                clip.open(audioStream); // Ouvre le fichier audio
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Boucle la musique en continu
            }
            clip.start(); // Démarre la lecture de la musique
        } catch (Exception e) {
            e.printStackTrace(); // Affiche les erreurs éventuelles
        }
    }

    /**
     * Arrête la lecture de la musique et libère les ressources associées.
     * Si la musique est en cours de lecture, elle est stoppée et les ressources sont libérées.
     */
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Arrête la lecture du clip
            clip.close(); // Libère les ressources du clip
            clip = null;  // Réinitialise l'objet clip
        }
    }
}
