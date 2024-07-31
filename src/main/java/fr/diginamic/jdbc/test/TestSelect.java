package fr.diginamic.jdbc.test;

import fr.diginamic.jdbc.entites.Fournisseur;
import static fr.diginamic.jdbc.test.TestConnexionJdbc.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {

    private static final String SELECT_QUERY = "SELECT * FROM fournisseur";
    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            Statement st = connection.createStatement();
            ResultSet curseur = st.executeQuery(SELECT_QUERY)) {

            List<Fournisseur> fournisseurs = new ArrayList<>();
            while(curseur.next()) {
                int id = curseur.getInt("ID");
                String raisonSociale = curseur.getString("NOM");
                fournisseurs.add(new Fournisseur(id, raisonSociale));
            }

            //fournisseurs.forEach(System.out::println);
            for(Fournisseur item : fournisseurs) {
                System.out.println(item);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }
}