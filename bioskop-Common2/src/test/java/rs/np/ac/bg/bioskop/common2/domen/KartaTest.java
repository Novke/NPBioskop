package rs.np.ac.bg.bioskop.common2.domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;



import java.sql.*;
import java.util.Date;
import rs.np.ac.bg.bioskop.common2.Util.DateParser;
import rs.np.ac.bg.bioskop.common2.domen.Film;
import rs.np.ac.bg.bioskop.common2.domen.Karta;
import rs.np.ac.bg.bioskop.common2.domen.Korisnik;
import rs.np.ac.bg.bioskop.common2.domen.Projekcija;
import rs.np.ac.bg.bioskop.common2.domen.Sala;

public class KartaTest {

    private Karta karta;

    @BeforeEach
    void setUp() {
    	Korisnik korisnik = new Korisnik(1L, "ime", new Date());
    	Film film = new Film(1L, "Memento", 9, 120, "Opis1", new Date());
    	Projekcija projekcija = new Projekcija(1L, "vrsta", new Date(), new Sala(1,1), film);
        karta = new Karta(1, "VIP", 2, 5, projekcija, korisnik);

    }

    @AfterEach
    void tearDown() {
        karta = null;
    }

    @Test
    @DisplayName("Getters and Setters test")
    void gettersAndSettersTest() {
        assertEquals(1, karta.getKartaID());
        assertEquals("VIP", karta.getTipKarte());
        assertEquals(2, karta.getRed());
        assertEquals(5, karta.getMesto());
        assertNotNull(karta.getProjekcija());
        assertNotNull(karta.getKorisnik());

        Karta testKarta = new Karta();
        testKarta.setKartaID(2);
        testKarta.setTipKarte("Regular");
        testKarta.setRed(3);
        testKarta.setMesto(10);
        Projekcija projekcija = new Projekcija();
        Korisnik korisnik = new Korisnik();
        testKarta.setProjekcija(projekcija);
        testKarta.setKorisnik(korisnik);

        assertEquals(2, testKarta.getKartaID());
        assertEquals("Regular", testKarta.getTipKarte());
        assertEquals(3, testKarta.getRed());
        assertEquals(10, testKarta.getMesto());
        assertEquals(projekcija, testKarta.getProjekcija());
        assertEquals(korisnik, testKarta.getKorisnik());
    }

    @Test
    @DisplayName("ToString test")
    void toStringTest() {
        String expectedString = "Karta{" +
                "kartaID=1" +
                ", tipKarte='VIP'" +
                ", red=2" +
                ", mesto=5" +
                ", projekcija=" + karta.getProjekcija() +
                ", korisnik=" + karta.getKorisnik() +
                "}";
        assertEquals(expectedString, karta.toString());
    }

    @Test
    @DisplayName("Table name test")
    void getTableNameTest() {
        assertEquals("karta", karta.getTableName());
    }

    @Test
    @DisplayName("Column names test")
    void getColumnNamesTest() {
        String expectedColumnNames = "(kartaid, tipkarte, red, mesto, korisnikid, projekcijaid)";
        assertEquals(expectedColumnNames, karta.getColumnNames());
    }

    @Test
    @DisplayName("Column names without ID test")
    void getColumnNamesWithoutIdTest() {
        String expectedColumnNamesWithoutId = "(tipkarte, red, mesto, korisnikid, projekcijaid)";
        assertEquals(expectedColumnNamesWithoutId, karta.getColumnNamesWithoutId());
    }

    @Test
    @DisplayName("Get values test")
    void getValuesTest() {
        String expectedValues = "kartaid = 1, tipkarte = 'VIP', red = 2, mesto = 5, korisnikid = " + karta.getKorisnik().getId() + ", projekcijaid = " + karta.getProjekcija().getId();
        assertEquals(expectedValues, karta.getValues());
    }

    @Test
    @DisplayName("Get insert values test")
    void getInsertValuesTest() {
        String expectedInsertValues = "('VIP', 2, 5, " + karta.getKorisnik().getKorisnikID() + ", " + karta.getProjekcija().getProjekcijaID() + ")";
        assertEquals(expectedInsertValues, karta.getInsertValues());
    }

    @Test
    @DisplayName("Set ID test")
    void setIdTest() {
        karta.setId(2);
        assertEquals(2, karta.getKartaID());
    }

    @Test
    @DisplayName("Get ID test")
    void getIdTest() {
        assertEquals(1L, karta.getId());
    }

    @Test
    @DisplayName("Extract from ResultSet test")
    void extractFromResultSetTest() throws SQLException {
        ResultSet resultSet = createMockResultSet();
        Karta extractedKarta = (Karta) karta.extractFromResultSet(resultSet);

        assertNotNull(extractedKarta);
        assertEquals(1, extractedKarta.getKartaID());
        assertEquals(2, extractedKarta.getRed());
        assertEquals(5, extractedKarta.getMesto());
        assertNotNull(extractedKarta.getProjekcija());
        assertNotNull(extractedKarta.getKorisnik());
    }

    @Test
    @DisplayName("Get where condition test")
    void getWhereConditionTest() {
        String expectedWhereCondition = "(kartaid = 1)";
        assertEquals(expectedWhereCondition, karta.getWhereCondition());
    }

    @Test
    @DisplayName("All details test")
    void allDetailsTest() {
        String expectedAllDetails = "KARTA:\n" +
                "Korisnik: " + karta.getKorisnik().getIme() + "\n" +
                "Film: " + karta.getProjekcija().getFilm().getImeFilma() + "\n" +
                "Sala " + karta.getProjekcija().getSala().getBrojSale() + "\n" +
                "Pocetak: " + DateParser.timeToString(karta.getProjekcija().getPocetakProjekcije()) + "\n" +
                "Red: 2, Mesto: 5";
        assertEquals(expectedAllDetails, karta.AllDetails());
    }

    private ResultSet createMockResultSet() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getLong("kartaid")).thenReturn(1L);
        Mockito.when(resultSet.getInt("red")).thenReturn(2);
        Mockito.when(resultSet.getInt("mesto")).thenReturn(5);
        return resultSet;
    }
    
    @Test
    @DisplayName("Test setting valid Karta ID")
    void testSetKartaIDValid() {
        Karta karta = new Karta();
        long kartaID = 1L;
        karta.setKartaID(kartaID);

        assertEquals(kartaID, karta.getKartaID());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-150"
    })
    @DisplayName("Test setting invalid Karta ID")
    void testSetKartaIDInvalid(long kartaID) {

        assertThrows(IllegalArgumentException.class, () -> karta.setKartaID(kartaID));
    }

    @Test
    @DisplayName("Test setting valid Tip Karte")
    void testSetTipKarteValid() {
        String tipKarte = "Obicna";
        karta.setTipKarte(tipKarte);

        assertEquals(tipKarte, karta.getTipKarte());
    }

    @ParameterizedTest
    @CsvSource({
            "''",
            "' "
    })
    @DisplayName("Test setting invalid Tip Karte")
    void testSetTipKarteInvalid(String tipKarte) {

        assertThrows(IllegalArgumentException.class, () -> karta.setTipKarte(tipKarte));
    }

    @Test
    @DisplayName("Test setting valid Red")
    void testSetRedValid() {
        int red = 1;
        karta.setRed(red);

        assertEquals(red, karta.getRed());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-10"
    })
    @DisplayName("Test setting invalid Red")
    void testSetRedInvalid(int red) {

        assertThrows(IllegalArgumentException.class, () -> karta.setRed(red));
    }

    @Test
    @DisplayName("Test setting valid Mesto")
    void testSetMestoValid() {
        int mesto = 1;
        karta.setMesto(mesto);

        assertEquals(mesto, karta.getMesto());
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "-10"
    })
    @DisplayName("Test setting invalid Mesto")
    void testSetMestoInvalid(int mesto) {

        assertThrows(IllegalArgumentException.class, () -> karta.setMesto(mesto));
    }

    @Test
    @DisplayName("Test setting valid Projekcija")
    void testSetProjekcijaValid() {
        Projekcija projekcija = new Projekcija();
        karta.setProjekcija(projekcija);

        assertEquals(projekcija, karta.getProjekcija());
    }

    @Test
    @DisplayName("Test setting null Projekcija")
    void testSetProjekcijaNull() {

        assertThrows(IllegalArgumentException.class, () -> karta.setProjekcija(null));
    }

    

    @Test
    @DisplayName("Test setting null Korisnik")
    void testSetKorisnikNull() {

        assertThrows(IllegalArgumentException.class, () -> karta.setKorisnik(null));
    }
}

