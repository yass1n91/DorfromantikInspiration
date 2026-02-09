package fr.iutfbleau.dorfmantik.controleur;

/**
 * Classe représentant une tâche musicale exécutée dans un thread séparé.
 * Elle utilise la classe {@code Musique} pour jouer de la musique en arrière-plan.
 * Implémente l'interface {@code Runnable} afin de pouvoir être exécutée dans un thread.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class MusicTask implements Runnable {

    /**
     * Instance de la musique jouée.
     * Cette variable est utilisée pour gérer la musique en arrière-plan.
     */
    private Musique musique;

    /**
     * Constructeur de la classe {@code MusicTask}.
     * Initialise une nouvelle instance de la musique à jouer.
     * La musique sera lue en arrière-plan dans un thread séparé.
     */
    public MusicTask() {
        this.musique = new Musique();  // Crée une nouvelle instance de Musique
    }

    /**
     * Méthode appelée lorsqu'un thread démarre la tâche.
     * Cette méthode lance la lecture de la musique en arrière-plan.
     * Elle est exécutée dans le contexte d'un thread séparé.
     */
    @Override
    public void run() {
        this.musique.play();  // Démarre la lecture de la musique
    }

    /**
     * Retourne l'instance de musique associée à cette tâche.
     * Cela permet d'accéder à l'objet {@code Musique} pour contrôler la musique (ex: changer le volume).
     * 
     * @return L'instance de {@code Musique} utilisée pour jouer la musique.
     */
    public Musique getMusique() {
        return this.musique;  // Retourne l'objet musique associé à cette tâche
    }
}
