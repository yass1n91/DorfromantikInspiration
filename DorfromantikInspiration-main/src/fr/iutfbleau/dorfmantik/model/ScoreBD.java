package fr.iutfbleau.dorfmantik.model;

import java.sql.*;
import java.util.*;

/**
 * Classe responsable de la gestion des scores dans la base de données.
 * Permet d'ajouter un score, de récupérer les meilleurs scores, et de gérer la connexion à la base de données.
 * 
 * @author Rafael Ghouar-Toussaint
 * @author Yassin Benbrahim
 * @author Gaël Oddos-Marcel
 */
public class ScoreBD {

    /**
     * Instance de connexion à la base de données.
     */
    private ConnexionBD cbd;

    /**
     * Constructeur de la classe {@code ScoreBD}.
     * Initialise une connexion à la base de données.
     */
    public ScoreBD() {
        this.cbd = new ConnexionBD();
    }

    /**
     * Ajoute un score dans la base de données pour une série donnée.
     * 
     * @param score  Le score à ajouter.
     * @param nSerie L'identifiant de la série associée au score.
     * @return {@code true} si l'ajout a réussi, {@code false} sinon.
     */
    public boolean ajouterScore(int score, int nSerie) {
        try {
            Connection conn = this.cbd.getBdd();
            String query = "INSERT INTO Scores (serie_id, score) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, nSerie);
            pstmt.setInt(2, score);
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du score : " + e.getMessage());
            return false;
        }
    }

    /**
     * Récupère les 10 meilleurs scores pour une série donnée.
     * 
     * @param numSerie L'identifiant de la série pour laquelle les scores doivent être récupérés.
     * @return Un tableau contenant les 10 meilleurs scores, triés par ordre décroissant.
     */
    public int[] recupererTop10Scores(int numSerie) {
        List<Integer> topScores = new ArrayList<>();
        try {
            Connection conn = cbd.getBdd();
            String query = "SELECT score FROM Scores WHERE serie_id = ? ORDER BY score DESC LIMIT 10";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, numSerie);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                topScores.add(rs.getInt("score"));
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des scores : " + e.getMessage());
        }

        // Convertir la liste en tableau
        int[] top10Scores = new int[topScores.size()];
        for (int i = 0; i < topScores.size(); i++) {
            top10Scores[i] = topScores.get(i);
        }
        return top10Scores;
    }

    /**
     * Ferme la connexion à la base de données en libérant les ressources associées.
     */
    public void close() {
        this.cbd.deconnexion();
    }
}
