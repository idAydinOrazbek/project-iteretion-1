import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/shop_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "aydyn2007"; // см. ниже

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection error");
            e.printStackTrace();
            return null;
        }
    }
}