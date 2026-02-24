import java.sql.*;

/**
 * MySQL datu-baserako konexioa kudeatzen duen klasea.
 * Datu-basearen URL-a, erabiltzailea eta pasahitza gordetzen ditu.
 */
public class Konexioa {
    private static final String URL = "jdbc:mysql://localhost:3306/IndiUsurbil?serverTimezone=UTC";
    private static final String ERABILTZAILEA = "root";
    private static final String PASAHITZA = "MarkelOst05";

    /**
     * Datu-basearekiko konexio aktibo bat lortzen du JDBC bidez.
     * * @return Datu-basearekin sortutako {@link Connection} objektua.
     * @throws SQLException Konexioa ezartzean arazoren bat gertatzen bada.
     */
    public static Connection getKonexioa() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Errorea: MySQL Driverra ez da aurkitu.");
            e.printStackTrace();
        }
        
        return DriverManager.getConnection(URL, ERABILTZAILEA, PASAHITZA);
    }
}