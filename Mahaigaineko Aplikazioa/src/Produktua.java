/**
 * Produktu baten informazioa gordetzeko eta kudeatzeko klasea.
 * Datu-baseko PRODUKTUAK taulako erregistro bat errepresentatzen du.
 */
public class Produktua {

    private int id;
    private String izena;
    private String deskribapena;
    private double prezioa;
    private int stocka;
    private int kategoriaId;
    private String irudiaUrl;

    /**
     * Produktuaren eraikitzaile hutsa.
     */
    public Produktua() {
    }

    /**
     * Eraikitzailea datu guztiekin. Normalean datu-basetik irakurritako
     * informazioarekin objektuak sortzeko erabiltzen da.
     * * @param id Produktuaren identifikatzailea.
     * @param izena Produktuaren izena.
     * @param deskribapena Produktuaren azalpena.
     * @param prezioa Produktuaren salmenta prezioa.
     * @param stocka Biltegian dagoen kantitatea.
     * @param kategoriaId Lotutako kategoriaren identifikatzailea.
     * @param irudiaUrl Produktuaren irudiaren helbidea.
     */
    public Produktua(int id, String izena, String deskribapena, double prezioa, int stocka, int kategoriaId, String irudiaUrl) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.kategoriaId = kategoriaId;
        this.irudiaUrl = irudiaUrl;
    }

    /**
     * Eraikitzailea ID gabe. Produktu berriak sortzeko eta datu-basean
     * txertatu aurretik erabiltzen da.
     * * @param izena Produktuaren izena.
     * @param deskribapena Produktuaren azalpena.
     * @param prezioa Produktuaren salmenta prezioa.
     * @param stocka Biltegian dagoen kantitatea.
     * @param kategoriaId Lotutako kategoriaren identifikatzailea.
     * @param irudiaUrl Produktuaren irudiaren helbidea.
     */
    public Produktua(String izena, String deskribapena, double prezioa, int stocka, int kategoriaId, String irudiaUrl) {
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.kategoriaId = kategoriaId;
        this.irudiaUrl = irudiaUrl;
    }

    /**
     * @return Produktuaren identifikatzailea.
     */
    public int getId() {
        return id;
    }

    /**
     * Produktuaren identifikatzailea ezartzen du.
     * @param id Identifikatzaile berria.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Produktuaren izena.
     */
    public String getIzena() {
        return izena;
    }

    /**
     * @return Produktuaren deskribapena.
     */
    public String getDeskribapena() {
        return deskribapena;
    }

    /**
     * @return Produktuaren prezioa.
     */
    public double getPrezioa() {
        return prezioa;
    }

    /**
     * @return Produktuaren stocka edo eskuragarri dagoen kopurua.
     */
    public int getStocka() {
        return stocka;
    }

    /**
     * @return Produktuari dagokion kategoriaren ID-a.
     */
    public int getKategoriaId() {
        return kategoriaId;
    }

    /**
     * @return Produktuaren irudiaren URL-a.
     */
    public String getIrudiaUrl() {
        return irudiaUrl;
    }

    /**
     * Produktuaren informazioa testu formatuan itzultzen du.
     * * @return Produktuaren datuak dauzkan String formatuduna.
     */
    @Override
    public String toString() {
        return String.format("ID: %d | %-20s | %8.2f€ | Stock: %d | Kat: %d",
                id, izena, prezioa, stocka, kategoriaId);
    }
}