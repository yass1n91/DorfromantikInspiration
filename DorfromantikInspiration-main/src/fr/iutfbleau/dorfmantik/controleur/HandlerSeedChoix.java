package fr.iutfbleau.dorfmantik.controleur;

import fr.iutfbleau.dorfmantik.vue.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestionnaire d'événements pour la sélection d'un seed à partir d'une liste déroulante.
 * Implémente l'interface {@code ActionListener}.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class HandlerSeedChoix implements ActionListener {

    /**
     * Liste déroulante pour choisir un seed.
     */
    private JComboBox<Integer> listeDeroulante; 
    
    /**
     * Instance du menu principal.
     */
    private Menu menu; 
    
    /**
     * Instance du tutoriel associé.
     */
    private Tutoriel t; 

    /**
     * Constructeur de la classe {@code HandlerSeedChoix}.
     * Ajoute un écouteur d'événements au bouton associé et initialise les dépendances.
     * 
     * @param seedButton     Le bouton permettant d'ouvrir la fenêtre de sélection de seed.
     * @param listeDeroulante La liste déroulante contenant les seeds disponibles.
     * @param menu           Le menu principal.
     * @param t              Le tutoriel associé.
     */
    public HandlerSeedChoix(CreateButton seedButton, JComboBox<Integer> listeDeroulante, Menu menu, Tutoriel t) {
        seedButton.addActionListener(this); 
        this.listeDeroulante = listeDeroulante;
        this.menu = menu;
        this.t = t;
    }

    /**
     * Retourne la liste déroulante utilisée pour la sélection des seeds.
     * 
     * @return La liste déroulante contenant les seeds disponibles.
     */
    public JComboBox<Integer> getListeDeroulante() {
        return this.listeDeroulante;
    }

    /**
     * Méthode appelée lorsque le bouton de sélection de seed est activé.
     * Affiche une fenêtre pour choisir un seed et met à jour le tutoriel si nécessaire.
     * 
     * @param ae L'événement d'action déclenché par le bouton.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Affiche la fenêtre de sélection de seed
        new SeedChoix(this.getListeDeroulante(), this.menu);
        
        // Mise à jour du tutoriel si en cours
        if (this.t.getIsTutorial() && this.t.getStep() == 1) {
            this.t.setMessageText("Appuie sur le bouton pour jouer ou choisis une autre partie");
            this.t.fenetreTuto();
        }
    }
}
