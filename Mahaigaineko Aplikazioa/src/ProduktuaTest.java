
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Hau beharrezkoa da assertEquals, assertAll, etab. erabiltzeko.

/**
 * Produktua klasearen unitate-testak. Ereduaren (Model) funtzionamendua
 * egiaztatzen du.
 */
class ProduktuaTest {

    @Test
    void testEraikitzaileaEtaGetterrak() {
        // Datuak
        int id = 1;
        String izena = "Ordenagailua";
        String deskribapena = "Gaming PC";
        double prezioa = 1200.50;
        int stocka = 5;
        int kategoriaId = 10;
        String irudiaUrl = "pc.jpg";

        // Ekintza
        Produktua p = new Produktua(id, izena, deskribapena, prezioa, stocka, kategoriaId, irudiaUrl);

        // Egiaztapena (Asserts)
        assertAll("Produktuaren propietateak",
                () -> assertEquals(id, p.getId()),
                () -> assertEquals(izena, p.getIzena()),
                () -> assertEquals(deskribapena, p.getDeskribapena()),
                () -> assertEquals(prezioa, p.getPrezioa()),
                () -> assertEquals(stocka, p.getStocka()),
                () -> assertEquals(kategoriaId, p.getKategoriaId()),
                () -> assertEquals(irudiaUrl, p.getIrudiaUrl())
        );
    }

    @Test
    void testEraikitzaileaIdGabe() {
        // IDrik gabeko eraikitzailea
        Produktua p = new Produktua("Sagua", "Haririk gabea", 25.0, 10, 2, "sagua.jpg");

        // int motakoa denez 0 da lehenetsita
        assertEquals(0, p.getId());
        assertEquals("Sagua", p.getIzena());
    }

    @Test
    void testSetters() {
        Produktua p = new Produktua();

        // Zure Produktua klaseak BAKARRIK setId dauka. 
        // setIzena eta besteak ez daudenez, ezin dira testatu gehitu gabe.
        p.setId(99);

        assertEquals(99, p.getId());
    }

    @Test
    void testToString() {
        Produktua p = new Produktua(5, "Pantaila", "4K", 300.0, 10, 1, "img.png");
        String emaitza = p.toString();

        assertTrue(emaitza.contains("ID: 5"));
        assertTrue(emaitza.contains("Pantaila"));
    }
}
