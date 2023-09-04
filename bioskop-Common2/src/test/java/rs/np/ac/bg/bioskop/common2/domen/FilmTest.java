package rs.np.ac.bg.bioskop.common2.domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;


import java.util.Date;

import java.sql.*;
import rs.np.ac.bg.bioskop.common2.Util.DateParser;
import rs.np.ac.bg.bioskop.common2.domen.Film;

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

        Mockito.when(resultSet.getDate("pocetakPrikazivanja")).thenReturn(new java.sql.Date( new Date().getTime()));
        return resultSet;
    }
    
    @Test
    void setFilmID_InvalidID_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setFilmID(-1));
    }

    @Test
    void setImeFilma_NullOrEmptyString_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setImeFilma(null));
        assertThrows(IllegalArgumentException.class, () -> film.setImeFilma(""));
    }

    @Test
    void setOcena_InvalidOcena_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setOcena(-1.0));
        assertThrows(IllegalArgumentException.class, () -> film.setOcena(11.0));
    }

    @Test
    void setTrajanje_InvalidTrajanje_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setTrajanje(0));
        assertThrows(IllegalArgumentException.class, () -> film.setTrajanje(-100));
    }

    @Test
    void setOpis_NullOrEmptyString_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setOpis(null));
        assertThrows(IllegalArgumentException.class, () -> film.setOpis(""));
    }

    @Test
    void setPocetakPrikazivanja_Null_ThrowsIllegalArgumentException() {
        Film film = new Film();
        assertThrows(IllegalArgumentException.class, () -> film.setPocetakPrikazivanja(null));
    }
    
    @ParameterizedTest
    @CsvSource({
            "-1, true",
            "0, false", 
            "100, false" 
    })
    void setFilmID_InvalidID_ThrowsIllegalArgumentException(long filmID, boolean shouldThrow) {
        if (shouldThrow) assertThrows(IllegalArgumentException.class, () -> film.setFilmID(filmID));
    }

    @ParameterizedTest
    @CsvSource({
            "-1.0, true", 
            "11.0, true", 
            "7.5, false"
    })
    void setOcena_InvalidOcena_ThrowsIllegalArgumentException(double ocena, boolean shouldThrow) {
    	if (shouldThrow) assertThrows(IllegalArgumentException.class, () -> film.setOcena(ocena));
    }
}

