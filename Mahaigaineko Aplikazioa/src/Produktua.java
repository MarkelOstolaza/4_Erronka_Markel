
public class Produktua {

    private int id;
    private String izena;
    private String deskribapena;
    private double prezioa;
    private int stocka;
    private int kategoriaId;
    private String irudiaUrl;

    public Produktua() {
    }

    // Eraikitzailea datu guztiekin (Datu-basetik irakurtzean erabiltzeko)
    public Produktua(int id, String izena, String deskribapena, double prezioa, int stocka, int kategoriaId, String irudiaUrl) {
        this.id = id;
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.kategoriaId = kategoriaId;
        this.irudiaUrl = irudiaUrl;
    }

    // Eraikitzailea ID gabe (Berria sortzeko)
    public Produktua(String izena, String deskribapena, double prezioa, int stocka, int kategoriaId, String irudiaUrl) {
        this.izena = izena;
        this.deskribapena = deskribapena;
        this.prezioa = prezioa;
        this.stocka = stocka;
        this.kategoriaId = kategoriaId;
        this.irudiaUrl = irudiaUrl;
    }

    // Getter-ak eta Setter-ak
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public String getDeskribapena() {
        return deskribapena;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public int getStocka() {
        return stocka;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }

    public String getIrudiaUrl() {
        return irudiaUrl;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %-20s | %8.2f€ | Stock: %d | Kat: %d",
                id, izena, prezioa, stocka, kategoriaId);
    }
}
