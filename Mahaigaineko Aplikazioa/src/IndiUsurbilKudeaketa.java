
import java.util.*;

public class IndiUsurbilKudeaketa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProduktuDAO dao = new ProduktuDAO();

        while (true) {
            System.out.println("\n--- INDI USURBIL KUDEAKETA ---");
            System.out.println("1. Produktu berria gehitu");
            System.out.println("2. CSV fitxategitik kargatu");
            System.out.println("3. Produktuak ikusi / Eguneratu / Ezabatu");
            System.out.println("4. Bilatu produktuak");
            System.out.println("5. Esportatu JSON (Guztiak)");
            System.out.println("6. Irten");
            System.out.print("Aukeratu: ");

            int aukera = Integer.parseInt(sc.nextLine());
            if (aukera == 6) {
                break;
            }

            switch (aukera) {
                case 1:
                    System.out.print("Izena: ");
                    String iz = sc.nextLine();
                    System.out.print("Prezioa: ");
                    double pr = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock: ");
                    int st = Integer.parseInt(sc.nextLine());
                    System.out.print("Kategoria ID (1-4): ");
                    int kat = Integer.parseInt(sc.nextLine());
                    dao.produktuBerriaSortu(new Produktua(iz, "", pr, st, kat, ""));
                    break;
                case 2:
                    System.out.print("CSV fitxategiaren izena: ");
                    dao.kargatuCSV(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Ordenatu (1: Prezioa, 2: Stock): ");
                    String ord = sc.nextLine().equals("1") ? "prezioa" : "stocka";
                    List<Produktua> lista = dao.getProduktuak(ord);
                    lista.forEach(System.out::println);
                    System.out.print("\nEkintza (0: Utzi, ID: Aukeratu ezabatzeko/eguneratzeko): ");
                    int idSel = Integer.parseInt(sc.nextLine());
                    if (idSel != 0) {
                        System.out.print("1: Eguneratu stock-a, 2: Ezabatu: ");
                        int op = Integer.parseInt(sc.nextLine());
                        if (op == 2) {
                            dao.ezabatuProduktua(idSel);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Bilaketa terminoa: ");
                    dao.bilatuProduktua(sc.nextLine()).forEach(System.out::println);
                    break;
                case 5:
                    dao.esportatuJSON("produktuak.json", dao.getProduktuak("prezioa"));
                    System.out.println("Esportatuta!");
                    break;
            }
        }
        sc.close();
    }
}
