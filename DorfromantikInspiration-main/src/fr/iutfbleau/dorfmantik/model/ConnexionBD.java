package fr.iutfbleau.dorfmantik.model;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Classe responsable de la connexion à la base de données.
 * Charge les informations de connexion depuis un fichier de propriétés et permet
 * d'établir une connexion à la base de données.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class ConnexionBD {

    /**
     * Instance de la connexion à la base de données.
     */
    private Connection bdd;

    /**
     * Constructeur de la classe {@code ConnexionBD}.
     * Initialise la connexion à la base de données en chargeant les propriétés
     * depuis le fichier {@code res/accountBDD.properties}.
     * En cas d'erreur de connexion ou de chargement des propriétés, le programme est arrêté.
     */
    public ConnexionBD() {
        try {
            // Charger les propriétés depuis le fichier
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("res/accountBDD.properties");
            properties.load(fis);

            // Récupérer les informations de connexion
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            // Établir la connexion
            this.bdd = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.err.println("Problème lors du chargement des propriétés : " + e.getMessage());
            System.exit(1); // Arrêt en cas de problème de fichier
        } catch (SQLException e) {
            System.err.println("Problème de connexion à la base de données : " + e.getMessage());
            System.exit(1); // Arrêt en cas de problème de connexion
        }
    }

    /**
     * Ferme la connexion à la base de données si elle est ouverte.
     */
    public void deconnexion() {
        try {
            if (this.bdd != null && !this.bdd.isClosed()) {
                this.bdd.close();
            }
        } catch (SQLException e) {
            System.err.println("Erreur de déconnexion de la base de données : " + e.getMessage());
        }
    }

    /**
     * Retourne l'instance de la connexion à la base de données.
     * 
     * @return L'objet {@code Connection} représentant la connexion à la base de données.
     */
    public Connection getBdd() {
        return this.bdd;
    }
}
