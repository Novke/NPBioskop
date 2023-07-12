package rs.np.ac.bg.bioskop_common.domen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import domen.*;

public class ProjekcijaTest {

    private Projekcija projekcija;

    @BeforeEach
    void setUp() {
        projekcija = new Projekcija(1, "2D", new Date(), new Sala(1, 100), new Film(1, "Film 1", 8, 0, null, null));
    }

    @AfterEach
    void tearDown() {
        projekcija = null;
    }

    @Test
    @DisplayName("Getters and Setters test")
    void gettersAndSettersTest() {
        assertEquals(1, projekcija.getProjekcijaID());
        assertEquals("2D", projekcija.getVrstaProjekcije());
        assertNotNull(projekcija.getPocetakProjekcije());
        assertNotNull(projekcija.getSala());
        assertNotNull(projekcija.getFilm());

        Projekcija testProjekcija = new Projekcija();
        testProjekcija.setProjekcijaID(2);
        testProjekcija.setVrstaProjekcije("3D");
        testProjekcija.setPocetakProjekcije(new Date());
        testProjekcija.setSala(new Sala(2, 150));
        testProjekcija.setFilm(new Film(2, "Film 2", 0, 0, null, null));

        assertEquals(2, testProjekcija.getProjekcijaID());
        assertEquals("3D", testProjekcija.getVrstaProjekcije());
        assertNotNull(testProjekcija.getPocetakProjekcije());
        assertNotNull(testProjekcija.getSala());
        assertNotNull(testProjekcija.getFilm());
    }

    @Test
    @DisplayName("ToString test")
    void toStringTest() {
        assertNotNull(projekcija.toString());
    }

    @Test
    @DisplayName("Table name test")
    void getTableNameTest() {
        assertEquals("projekcija", projekcija.getTableName());
    }

    @Test
    @DisplayName("Column names test")
    void getColumnNamesTest() {
        String expectedColumnNames = "(projekcijaid, vrstaprojekcije, pocetakprojekcije, salaid, filmid)";
        assertEquals(expectedColumnNames, projekcija.getColumnNames());
    }

    @Test
    @DisplayName("Column names without ID test")
    void getColumnNamesWithoutIdTest() {
        String expectedColumnNamesWithoutId = "(vrstaprojekcije, pocetakprojekcije, salaid, filmid)";
        assertEquals(expectedColumnNamesWithoutId, projekcija.getColumnNamesWithoutId());
    }

    @Test
    @DisplayName("Get values test")
    void getValuesTest() {
        assertNotNull(projekcija.getValues());
    }

    @Test
    @DisplayName("Get insert values test")
    void getInsertValuesTest() {
        assertNotNull(projekcija.getInsertValues());
    }

    @Test
    @DisplayName("Set ID test")
    void setIdTest() {
        projekcija.setId(2);
        assertEquals(2, projekcija.getProjekcijaID());
    }

    @Test
    @DisplayName("Get ID test")
    void getIdTest() {
        assertEquals(1L, projekcija.getId());
    }

    @Test
    @DisplayName("Extract from ResultSet test")
    void extractFromResultSetTest() throws SQLException {
        ResultSet resultSet = createMockResultSet();
        Projekcija extractedProjekcija = (Projekcija) projekcija.extractFromResultSet(resultSet);

        assertNotNull(extractedProjekcija);
        assertEquals(1, extractedProjekcija.getProjekcijaID());
        assertEquals("2D", extractedProjekcija.getVrstaProjekcije());
        assertNotNull(extractedProjekcija.getPocetakProjekcije());
        assertNotNull(extractedProjekcija.getSala());
        assertNotNull(extractedProjekcija.getFilm());
    }

    @Test
    @DisplayName("Get where condition test")
    void getWhereConditionTest() {
        String expectedWhereCondition = "(projekcijaid = 1)";
        assertEquals(expectedWhereCondition, projekcija.getWhereCondition());
    }

    @Test
    @DisplayName("All details test")
    void allDetailsTest() {
        assertNotNull(projekcija.AllDetails());
    }

    private ResultSet createMockResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("projekcijaid")).thenReturn(1L);
        when(resultSet.getString("vrstaprojekcije")).thenReturn("2D");
        when(resultSet.getTimestamp("pocetakprojekcije")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(resultSet.getLong("salaid")).thenReturn(1L);
        when(resultSet.getLong("filmid")).thenReturn(1L);

        return resultSet;
    }
}


