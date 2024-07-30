package fr.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Statement;

public class TestConnexionJdbc {
    public static final String DB_URL ;
    public static final String DB_USER ;
    public static final String DB_PWD ;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        DB_URL  = bundle.getString("db.url");
        DB_USER = bundle.getString("db.user");
        DB_PWD  = bundle.getString("db.pwd");
    }

    public static void main(String[] args) {
        try (Connection cnx = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
             Statement statement = cnx.createStatement()) {
            cnx.setAutoCommit(false);//Désactivation de l'auto-commit => on passe en mode transactionnel

            statement.executeUpdate("UPDATE fournisseur SET NOM = 'FDM SA' WHERE NOM = 'FDM'");
            cnx.commit();//On valide la transaction
            //cnx.rollback();//On annule la transaction
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }
}