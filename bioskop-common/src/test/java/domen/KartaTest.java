package domen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Util.DateParser;
import domen.*;
import java.sql.*;

public class KartaTest {

    private Karta karta;

    @BeforeEach
    void setUp() {
        karta = new Karta(1, "VIP", 2, 5, new Projekcija(), new Korisnik());
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
}

