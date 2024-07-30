package fr.diginamic.jdbc;

import fr.diginamic.jdbc.entites.Fournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static fr.diginamic.TestConnexionJdbc.*;

public class TestSelect {

    private static final String SELECT_QUERY = "SELECT * FROM fournisseur";
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Fournisseur> fournisseurs = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_QUERY);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }

            for (Fournisseur fournisseur : fournisseurs) {
                System.out.println(fournisseur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}