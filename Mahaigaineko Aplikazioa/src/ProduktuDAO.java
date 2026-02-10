import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; // Hau gehitu behar da
import java.util.Map;     // Hau gehitu behar da

public class ProduktuDAO {

    // 1. Produktua Sortu
    public void produktuBerriaSortu(Produktua p) {
        String sql = "INSERT INTO PRODUKTUAK (izena, deskribapena, prezioa, stocka, kategoria_id, irudia_url, sorkuntza_data) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Konexioa.getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getIzena());
            pstmt.setString(2, p.getDeskribapena());
            pstmt.setDouble(3, p.getPrezioa());
            pstmt.setInt(4, p.getStocka());
            pstmt.setInt(5, p.getKategoriaId());
            pstmt.setString(6, p.getIrudiaUrl());
            pstmt.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();
            System.out.println("Produktua ondo gorde da.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Produktua Eguneratu
    public void eguneratuProduktua(Produktua p) {
        String sql = "UPDATE PRODUKTUAK SET izena=?, deskribapena=?, prezioa=?, stocka=?, kategoria_id=?, irudia_url=? WHERE id=?";
        try (Connection conn = Konexioa.getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getIzena());
            pstmt.setString(2, p.getDeskribapena());
            pstmt.setDouble(3, p.getPrezioa());
            pstmt.setInt(4, p.getStocka());
            pstmt.setInt(5, p.getKategoriaId());
            pstmt.setString(6, p.getIrudiaUrl());
            pstmt.setInt(7, p.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Produktua Ezabatu
    public void ezabatuProduktua(int id) {
        String sql = "DELETE FROM PRODUKTUAK WHERE id=?";
        try (Connection conn = Konexioa.getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Produktuak Zerrendatu
    public List<Produktua> getProduktuak(String ordenazioa) {
        List<Produktua> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUKTUAK ORDER BY " + (ordenazioa.equals("prezioa") ? "prezioa" : "stocka");
        try (Connection conn = Konexioa.getKonexioa(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapResultSetToProduktua(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 5. Bilaketa (Izena BAKARRIK)
    public List<Produktua> bilatuProduktua(String izena) {
        List<Produktua> lista = new ArrayList<>();
        String sql = "SELECT * FROM PRODUKTUAK WHERE izena LIKE ?"; 
        try (Connection conn = Konexioa.getKonexioa(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + izena + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSetToProduktua(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 6. Kategoriak lortu (METODO BERRIA)
    public Map<Integer, String> getKategoriak() {
        Map<Integer, String> kategoriak = new HashMap<>();
        String sql = "SELECT id, izena FROM KATEGORIAK";
        try (Connection conn = Konexioa.getKonexioa(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                kategoriak.put(rs.getInt("id"), rs.getString("izena"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kategoriak;
    }

    // 7. CSV-tik kargatu
    public void kargatuCSV(String fitxPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(fitxPath))) {
            String lerroa;
            br.readLine(); 
            while ((lerroa = br.readLine()) != null) {
                String[] d = lerroa.split(",");
                if(d.length >= 6) {
                    Produktua p = new Produktua(d[0], d[1], Double.parseDouble(d[2]), Integer.parseInt(d[3]), Integer.parseInt(d[4]), d[5]);
                    produktuBerriaSortu(p);
                }
            }
        } catch (Exception e) {
            System.out.println("Errorea CSV kargatzean: " + e.getMessage());
        }
    }

    // 8. JSON Esportatu
    public void esportatuJSON(String fitxIzena, List<Produktua> produktuak) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fitxIzena))) {
            out.println("[");
            for (int i = 0; i < produktuak.size(); i++) {
                Produktua p = produktuak.get(i);
                out.printf("  {\"id\": %d, \"izena\": \"%s\", \"prezioa\": %.2f, \"stock\": %d}%s\n",
                        p.getId(), p.getIzena(), p.getPrezioa(), p.getStocka(), (i == produktuak.size() - 1) ? "" : ",");
            }
            out.println("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Produktua mapResultSetToProduktua(ResultSet rs) throws SQLException {
        return new Produktua(rs.getInt("id"), rs.getString("izena"), rs.getString("deskribapena"),
                rs.getDouble("prezioa"), rs.getInt("stocka"), rs.getInt("kategoria_id"), rs.getString("irudia_url"));
    }
}