
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class IndiUsurbilKudeaketaTest {

    @Test
    void testFluxuOsoa() {
        // Simulazio honetan, erabiltzaileak programa ireki eta hainbat gauza egiten ditu segidan.
        // String erraldoi honek teklatuko sarrera guztiak ditu, lerroz lerro (\n).

        StringBuilder inputs = new StringBuilder();

        // --- 1. KASUA: Produktu berria sortu ---
        inputs.append("1\n");           // 1. aukera: Gehitu
        inputs.append("TestJunit\n");   // Izena
        inputs.append("99.99\n");       // Prezioa
        inputs.append("10\n");          // Stocka
        inputs.append("1\n");           // Kategoria ID (1 existitzen dela suposatuz)

        // --- ERRORE KASUA: Zenbakia ez den sarrera menuan ---
        inputs.append("aukeratxarra\n"); // "Mesedez, sartu zenbaki bat" mezua ateratzeko

        // --- ERRORE KASUA: Aukera baliogabea (switch default) ---
        inputs.append("99\n");           // "Aukera okerra" mezua ateratzeko

        // --- 2. KASUA: CSV Kargatu ---
        // Fitxategia existitzen ez bada ere, errorea kudeatzen duen catch blokea probatuko du
        inputs.append("2\n");
        inputs.append("ez_du_existitu_behar.csv\n");

        // --- 4. KASUA: Bilatu ---
        inputs.append("4\n");
        inputs.append("TestJunit\n");   // Sortu berri duguna bilatu

        // --- 3. KASUA: Zerrendatu eta Eguneratu (Stocka) ---
        inputs.append("3\n");           // 3. aukera
        inputs.append("1\n");           // Ordenatu prezioz
        // Orain ID bat eskatu behar dugu. Ez dakigu ziur zein ID duen 'TestJunit'-ek,
        // baina 0 sakatzen badugu, "Utzi" adarra probatzen dugu.
        inputs.append("0\n");           // Utzi (ID 0)

        // --- 5. KASUA: JSON Esportatu ---
        inputs.append("5\n");

        // --- 6. KASUA: Irten ---
        inputs.append("6\n");           // Begiztatik atera

        // Sarrera guztiak InputStream bihurtu
        InputStream in = new ByteArrayInputStream(inputs.toString().getBytes());
        System.setIn(in);

        // Programa exekutatu
        IndiUsurbilKudeaketa.main(new String[]{});
    }

    @Test
    void testEzabatuFluxua() {
        // Test honek bereziki ezabatzeko eta eguneratzeko fluxua probatzen du.
        // Kontuz: Honek benetako DBan IDak asmatzea zaila denez, fluxua (inputs) 
        // prestatzen dugu kodearen zati horietatik pasatzeko saiakeran.

        // Estrategia: 3 -> Ordenatu -> ID bat sartu -> 2 (Ezabatu)
        // Erroreak saihesteko, existitzen ez den ID bat probatuko dugu lehenbizi
        // kodearen "Ez da ID hori duen produkturik aurkitu" adarra ukitzeko.
        String input = "3\n1\n999999\n1\n10\n6\n"; // 3 (Ikusi) -> 1 (Ordena) -> ID 999999 -> 1 (Eguneratu) -> Stock 10 -> 6 (Irten)
        // Oharra: IDa existitzen ez bada, kodeak mezua emango du eta menura itzuliko da edo jarraituko du.

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        IndiUsurbilKudeaketa.main(new String[]{});
    }
}
