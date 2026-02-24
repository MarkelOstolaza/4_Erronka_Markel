
import java.util.*;

/**
 * Aplikazioaren klase nagusia. Kontsola bidezko erabiltzaile-interfaze (CLI)
 * bat eskaintzen du produktuak kudeatzeko, besteak beste: berriak sortu,
 * ezabatu, bilatu, eta datuak esportatu/inportatu.
 */
public class IndiUsurbilKudeaketa {

    /**
     * Aplikazioaren sarrera-puntua eta exekuzio-fluxuaren kontrolatzaile
     * nagusia. Menu bat bistaratzen du eta erabiltzailearen sarreraren arabera
     * {@link ProduktuDAO} klaseko metodo egokiak deitzen ditu.
     *
     * * @param args Komando-lerroko argumentuak (ez dira programan zehar
     * erabiltzen).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProduktuDAO dao = new ProduktuDAO();

        while (true) {
            System.out.println("\n--- INDI USURBIL KUDEAKETA ---");
            System.out.println("1. Produktu berria gehitu");
            System.out.println("2. CSV fitxategitik kargatu");
            System.out.println("3. Produktuak ikusi / Eguneratu / Ezabatu");
            System.out.println("4. Bilatu produktuak (Izenez)");
            System.out.println("5. Esportatu JSON (Guztiak)");
            System.out.println("6. Irten");
            System.out.print("Aukeratu: ");

            int aukera = -1;
            try {
                aukera = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Mesedez, sartu zenbaki bat.");
                continue;
            }

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

                    System.out.println("--- KATEGORIAK ---");
                    Map<Integer, String> kats = dao.getKategoriak();
                    for (Map.Entry<Integer, String> entry : kats.entrySet()) {
                        System.out.println(entry.getKey() + ". " + entry.getValue());
                    }

                    System.out.print("Aukeratu Kategoria ID: ");
                    int kat = Integer.parseInt(sc.nextLine());

                    dao.produktuBerriaSortu(new Produktua(iz, "", pr, st, kat, ""));
                    break;
                case 2:
                    System.out.print("CSV fitxategiaren izena: ");
                    dao.kargatuCSV(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Ordenatu (1: Prezioa, 2: Stock): ");
                    String ordIn = sc.nextLine();
                    String ord = ordIn.equals("1") ? "prezioa" : "stocka";
                    List<Produktua> lista = dao.getProduktuak(ord);

                    for (Produktua p : lista) {
                        System.out.println(p);
                    }

                    System.out.print("\nEkintza (0: Utzi, ID: Aukeratu ezabatzeko/eguneratzeko): ");
                    int idSel = Integer.parseInt(sc.nextLine());

                    if (idSel != 0) {
                        System.out.print("1: Eguneratu stock-a, 2: Ezabatu: ");
                        int op = Integer.parseInt(sc.nextLine());

                        if (op == 1) {
                            System.out.print("Sartu stock berria: ");
                            int stockBerria = Integer.parseInt(sc.nextLine());
                            Produktua eguneratzeko = null;
                            for (Produktua p : lista) {
                                if (p.getId() == idSel) {
                                    eguneratzeko = p;
                                    break;
                                }
                            }
                            if (eguneratzeko != null) {
                                Produktua pBerria = new Produktua(
                                        eguneratzeko.getId(),
                                        eguneratzeko.getIzena(),
                                        eguneratzeko.getDeskribapena(),
                                        eguneratzeko.getPrezioa(),
                                        stockBerria,
                                        eguneratzeko.getKategoriaId(),
                                        eguneratzeko.getIrudiaUrl()
                                );
                                dao.eguneratuProduktua(pBerria);
                                System.out.println("Stock-a ondo eguneratu da.");
                            } else {
                                System.out.println("Errorea: Ez da ID hori duen produkturik aurkitu.");
                            }
                        } else if (op == 2) {
                            dao.ezabatuProduktua(idSel);
                            System.out.println("Produktua ezabatu da.");
                        }
                    }
                    break;
                case 4:
                    System.out.print("Sartu produktuaren izena: ");
                    dao.bilatuProduktua(sc.nextLine()).forEach(System.out::println);
                    break;
                case 5:
                    dao.esportatuJSON("produktuak.json", dao.getProduktuak("prezioa"));
                    System.out.println("Esportatuta!");
                    break;
                default:
                    System.out.println("Aukera okerra.");
            }
        }
        sc.close();
    }
}
