package domen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Util.DateParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import domen.*;

public class KorisnikTest {

    private Korisnik korisnik;

    @BeforeEach
    void setUp() {
        korisnik = new Korisnik(1, "John Doe", new Date());
    }

    @AfterEach
    void tearDown() {
        korisnik = null;
    }

    @Test
    @DisplayName("Getters and Setters test")
    void gettersAndSettersTest() {
        assertEquals(1, korisnik.getKorisnikID());
        assertEquals("John Doe", korisnik.getIme());
        assertNotNull(korisnik.getDatumRodjenja());

        Korisnik testKorisnik = new Korisnik();
        testKorisnik.setKorisnikID(2);
        testKorisnik.setIme("Jane Smith");
        Date datumRodjenja = new Date();
        testKorisnik.setDatumRodjenja(datumRodjenja);

        assertEquals(2, testKorisnik.getKorisnikID());
        assertEquals("Jane Smith", testKorisnik.getIme());
        assertEquals(datumRodjenja, testKorisnik.getDatumRodjenja());
    }

    @Test
    @DisplayName("ToString test")
    void toStringTest() {
        String expectedString = "Korisnik{" +
                "korisnikID=1" +
                ", ime='John Doe'" +
                ", datumRodjenja=" + korisnik.getDatumRodjenja() +
                "}";
        assertEquals(expectedString, korisnik.toString());
    }

    @Test
    @DisplayName("Table name test")
    void getTableNameTest() {
        assertEquals("korisnik", korisnik.getTableName());
    }

    @Test
    @DisplayName("Column names test")
    void getColumnNamesTest() {
        String expectedColumnNames = "(korisnikid, ime, datumrodjenja)";
        assertEquals(expectedColumnNames, korisnik.getColumnNames());
    }

    @Test
    @DisplayName("Column names without ID test")
    void getColumnNamesWithoutIdTest() {
        String expectedColumnNamesWithoutId = "(ime, datumrodjenja)";
        assertEquals(expectedColumnNamesWithoutId, korisnik.getColumnNamesWithoutId());
    }

    @Test
    @DisplayName("Get values test")
    void getValuesTest() {
        String expectedValues = "ime = 'John Doe',datumrodjenja = '" + DateParser.toString(korisnik.getDatumRodjenja()) + "'";
        assertEquals(expectedValues, korisnik.getValues());
    }

    @Test
    @DisplayName("Get insert values test")
    void getInsertValuesTest() {
        String expectedInsertValues = "('John Doe', '" + DateParser.toString(korisnik.getDatumRodjenja()) + "')";
        assertEquals(expectedInsertValues, korisnik.getInsertValues());
    }

    @Test
    @DisplayName("Set ID test")
    void setIdTest() {
        korisnik.setId(2);
        assertEquals(2, korisnik.getKorisnikID());
    }

    @Test
    @DisplayName("Get ID test")
    void getIdTest() {
        assertEquals(1L, korisnik.getId());
    }

    @Test
    @DisplayName("Extract from ResultSet test")
    void extractFromResultSetTest() throws SQLException {
        ResultSet resultSet = createMockResultSet();
        Korisnik extractedKorisnik = (Korisnik) korisnik.extractFromResultSet(resultSet);

        assertNotNull(extractedKorisnik);
        assertEquals(1, extractedKorisnik.getKorisnikID());
        assertEquals("John Doe", extractedKorisnik.getIme());
        assertNotNull(extractedKorisnik.getDatumRodjenja());
    }

    @Test
    @DisplayName("Get where condition test")
    void getWhereConditionTest() {
        String expectedWhereCondition = "(korisnikid = 1)";
        assertEquals(expectedWhereCondition, korisnik.getWhereCondition());
    }

    @Test
    @DisplayName("All details test")
    void allDetailsTest() {
        String expectedAllDetails = "KORISNIK: \n" +
                "Ime: John Doe\n" +
                "Datum rodjenja: " + DateParser.toString(korisnik.getDatumRodjenja());
        assertEquals(expectedAllDetails, korisnik.AllDetails());
    }

    private ResultSet createMockResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("korisnikid")).thenReturn(1L);
        when(resultSet.getString("ime")).thenReturn("John Doe");
        when(resultSet.getDate("datumrodjenja")).thenReturn(new java.sql.Date(new Date().getTime()));

        return resultSet;
    }
}

