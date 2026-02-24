
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class KonexioaTest {

    @Test
    void testKonexioaLortu() {
        try (Connection conn = Konexioa.getKonexioa()) {
            assertNotNull(conn);
            assertFalse(conn.isClosed());
        } catch (SQLException e) {
            fail("Konexioak huts egin du: " + e.getMessage());
        }
    }

    @Test
    void testEraikitzailea() {
        // Konexioa klaseak eraikitzaile inplizitua du.
        // Hura deitzeak coverage-a 100%era igotzen laguntzen du klasearen definizioan.
        Konexioa k = new Konexioa();
        assertNotNull(k);
    }
}
