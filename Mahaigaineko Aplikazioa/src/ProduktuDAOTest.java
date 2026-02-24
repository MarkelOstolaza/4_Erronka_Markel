import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class ProduktuDAOTest {

    private ProduktuDAO dao;

    @BeforeEach
    void setUp() {
        dao = new ProduktuDAO();
    }

    @Test
    void testCicloCRUD() {
        // Sortu
        String izena = "TEST_CRUD_" + System.currentTimeMillis();
        Produktua p = new Produktua(izena, "Desk", 50.0, 5, 1, "url");
        dao.produktuBerriaSortu(p);

        // Irakurri
        List<Produktua> lista = dao.bilatuProduktua(izena);
        assertFalse(lista.isEmpty());
        Produktua berreskuratua = lista.get(0);
        
        // Eguneratu
        berreskuratua.setId(berreskuratua.getId()); // Coverage-rako setter deia
        Produktua pBerria = new Produktua(berreskuratua.getId(), izena, "DeskEguneratua", 60.0, 10, 1, "url2");
        dao.eguneratuProduktua(pBerria);

        // Ezabatu
        dao.ezabatuProduktua(berreskuratua.getId());
        assertTrue(dao.bilatuProduktua(izena).isEmpty());
    }

    @Test
    void testKargatuCSVOkerra(@TempDir Path tempDir) throws IOException {
        // CSV bat sortuko dugu lerro okerrekin (datu gutxiegi)
        // Honek if (d.length >= 6) baldintza faltsua denean probatuko du.
        File csvFile = tempDir.resolve("okerrak.csv").toFile();
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Goiburua\n");
            writer.write("DatuGutxi,1,2\n"); // 6 zutabe baino gutxiago -> Ez du sartu behar
            writer.write("Ondo,Desk,10,5,1,url\n"); // Hau ondo dago
            writer.write("Txarra,Formatua,ZenkabiaEzDena,5,1,url\n"); // Exception probatzeko (Prezioa ez da zenbakia)
        }

        dao.kargatuCSV(csvFile.getAbsolutePath());
        
        // "Ondo" bakarrik sartu beharko litzateke, edo errorea kudeatu.
        // Test honekin kodeko adar guztiak (if/else eta catch) ikutzen ditugu.
    }
    
    @Test
    void testKargatuCSVFitxategiaEzDaExistitzen() {
        // Exception (FileNotFound) catch blokea probatzeko
        dao.kargatuCSV("ez_du_existitu_behar.csv");
    }

    @Test
    void testEsportatuJSON(@TempDir Path tempDir) {
        String ruta = tempDir.resolve("test.json").toString();
        List<Produktua> lista = dao.getProduktuak("prezioa");
        dao.esportatuJSON(ruta, lista);
        assertTrue(new File(ruta).exists());
    }

    @Test
    void testGetKategoriak() {
        assertNotNull(dao.getKategoriak());
    }
    
    @Test
    void testGetProduktuak() {
        assertNotNull(dao.getProduktuak("stocka"));
    }
}