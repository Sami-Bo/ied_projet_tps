package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnection {

    private static String JDBC_URL = "jdbc:mysql://localhost:3306/ied";
    private static String USERNAME = "root";
    private static String PASSWORD = "";

    public static Film getFilmByTitre(String titre) {
        Film film = new Film();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        
            String sql = "SELECT * FROM films WHERE titre = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, titre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                film = new Film();
                film.setTitre(titre);
                film.setDateSortie(resultSet.getString("date_sortie"));
                film.setGenre(resultSet.getString("genre"));
                film.setDistributeur(resultSet.getString("distributeur"));
                film.setBudget(resultSet.getDouble("budget"));
                film.setRevenusUs(resultSet.getDouble("revenus_usa"));
                film.setRevenusMonde(resultSet.getDouble("revenus_mondiaux"));
                
            }
            else {
                System.out.println("Les informations concernant ce film ne sont pas disponibles dans la base de donnée locale");
                film = new Film();
                film.setTitre("");
                film.setDateSortie("");
                film.setGenre("");
                film.setDistributeur("");
                film.setBudget(0);
                film.setRevenusUs(0);
                film.setRevenusMonde(0);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return film;
    }
}

