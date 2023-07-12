package rs.np.ac.bg.bioskop_common.domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.np.ac.bg.bioskop_common.Util.DateParser;

import java.util.Date;

import java.sql.*;

public class FilmTest {

    private Film film;

    @BeforeEach
    void setUp() {
        film = new Film(1, "Test film", 7.5, 120, "Ovo je testni film", new Date());
    }

    @AfterEach
    void tearDown() {
        film = null;
    }

    @Test
    @DisplayName("Getters and Setters test")
    void gettersAndSettersTest() {
        assertEquals(1, film.getFilmID());
        assertEquals("Test film", film.getImeFilma());
        assertEquals(7.5, film.getOcena());
        assertEquals(120, film.getTrajanje());
        assertEquals("Ovo je testni film", film.getOpis());

        Date pocetakPrikazivanja = new Date();
        film.setPocetakPrikazivanja(pocetakPrikazivanja);
        assertEquals(pocetakPrikazivanja, film.getPocetakPrikazivanja());
    }

    @Test
    @DisplayName("ToString test")
    void toStringTest() {
        String expectedString = "Film{" +
                "filmID=1" +
                ", imeFilma='Test film'" +
                ", ocena=7.5" +
                ", trajanje=120" +
                ", opis='Ovo je testni film'" +
                ", pocetakPrikazivanja=" + film.getPocetakPrikazivanja() +
                "}";
        assertEquals(expectedString, film.toString());
    }

    @Test
    @DisplayName("Table name test")
    void getTableNameTest() {
        assertEquals("film", film.getTableName());
    }

    @Test
    @DisplayName("Column names test")
    void getColumnNamesTest() {
        String expectedColumnNames = "(filmid, imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
        assertEquals(expectedColumnNames, film.getColumnNames());
    }

    @Test
    @DisplayName("Column names without ID test")
    void getColumnNamesWithoutIdTest() {
        String expectedColumnNamesWithoutId = "(imefilma, ocena, trajanje, opis, pocetakprikazivanja)";
        assertEquals(expectedColumnNamesWithoutId, film.getColumnNamesWithoutId());
    }

    @Test
    @DisplayName("Get values test")
    void getValuesTest() {
        String expectedValues = "filmId = 1, imefilma = 'Test film', ocena = 7.5, trajanje = 120, opis = 'Ovo je testni film', pocetakprikazivanja = '" + DateParser.toString(film.getPocetakPrikazivanja()) + "'";
        assertEquals(expectedValues, film.getValues());
    }

    @Test
    @DisplayName("Get insert values test")
    void getInsertValuesTest() {
        String expectedInsertValues = "('Test film', 7.5, 120, 'Ovo je testni film', '" + DateParser.toString(film.getPocetakPrikazivanja()) + "')";
        assertEquals(expectedInsertValues, film.getInsertValues());
    }

    @Test
    @DisplayName("Set ID test")
    void setIdTest() {
        film.setId(2);
        assertEquals(2, film.getFilmID());
    }

    @Test
    @DisplayName("Get ID test")
    void getIdTest() {
        assertEquals(1L, film.getId());
    }

    @Test
    @DisplayName("Extract from ResultSet test")
    void extractFromResultSetTest() throws SQLException {
        ResultSet resultSet = createMockResultSet();
        Film extractedFilm = (Film) film.extractFromResultSet(resultSet);

        assertNotNull(extractedFilm);
        assertEquals(1, extractedFilm.getFilmID());
        assertEquals("Test film", extractedFilm.getImeFilma());
        assertEquals(7.5, extractedFilm.getOcena());
        assertEquals(120, extractedFilm.getTrajanje());
        assertEquals("Ovo je testni film", extractedFilm.getOpis());
        assertNotNull(extractedFilm.getPocetakPrikazivanja());
    }

    @Test
    @DisplayName("Get where condition test")
    void getWhereConditionTest() {
        String expectedWhereCondition = "(filmId = 1)";
        assertEquals(expectedWhereCondition, film.getWhereCondition());
    }

    @Test
    @DisplayName("All details test")
    void allDetailsTest() {
        String expectedAllDetails = "FILM:\n" +
                "Naziv: Test film\n" +
                "Trajanje: 120min\n" +
                "IMDB ocena: 7.5\n" +
                "Premijera: " + DateParser.toString(film.getPocetakPrikazivanja()) + "\n" +
                "Ovo je testni film";
        assertEquals(expectedAllDetails, film.AllDetails());
    }

    private ResultSet createMockResultSet() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.getLong("filmid")).thenReturn(1L);
        Mockito.when(resultSet.getString("imeFilma")).thenReturn("Test film");
        Mockito.when(resultSet.getDouble("ocena")).thenReturn(7.5);
        Mockito.when(resultSet.getInt("trajanje")).thenReturn(120);
        Mockito.when(resultSet.getString("opis")).thenReturn("Ovo je testni film");
        Date startDate = new Date();
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        Mockito.when(resultSet.getDate("pocetakPrikazivanja")).thenReturn(sqlStartDate);
return resultSet;
    }
}

