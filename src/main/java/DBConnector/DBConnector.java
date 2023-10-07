package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cardiac_patient_management?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql123";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the MySQL driver in the classpath!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        //   String url = "jdbc:mysql://localhost:3306/loandb?serverTimezone=UTC&useSSL=false";
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect to the database!", e);
        }
    }

}
