package rs.np.ac.bg.bioskop_common.domen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import domen.*;

public class SalaTest {

    private Sala sala;

    @BeforeEach
    void setUp() {
        sala = new Sala(1, 100);
    }

    @AfterEach
    void tearDown() {
        sala = null;
    }

    @Test
    @DisplayName("Getters and Setters test")
    void gettersAndSettersTest() {
        assertEquals(1, sala.getBrojSale());
        assertEquals(100, sala.getBrojSedista());

        Sala testSala = new Sala();
        testSala.setBrojSale(2);
        testSala.setBrojSedista(150);

        assertEquals(2, testSala.getBrojSale());
        assertEquals(150, testSala.getBrojSedista());
    }

    @Test
    @DisplayName("ToString test")
    void toStringTest() {
        String expectedString = "Sala{" +
                "brojSale=1" +
                ", brojSedista=100" +
                "}";
        assertEquals(expectedString, sala.toString());
    }

    @Test
    @DisplayName("Table name test")
    void getTableNameTest() {
        assertEquals("sala", sala.getTableName());
    }

    @Test
    @DisplayName("Column names test")
    void getColumnNamesTest() {
        String expectedColumnNames = "(brojsale, brojsedista)";
        assertEquals(expectedColumnNames, sala.getColumnNames());
    }

    @Test
    @DisplayName("Column names without ID test")
    void getColumnNamesWithoutIdTest() {
        String expectedColumnNamesWithoutId = "(brojsale, brojsedista)";
        assertEquals(expectedColumnNamesWithoutId, sala.getColumnNamesWithoutId());
    }

    @Test
    @DisplayName("Get values test")
    void getValuesTest() {
        String expectedValues = "brojSale = 1, brojSedista = 100";
        assertEquals(expectedValues, sala.getValues());
    }

    @Test
    @DisplayName("Get insert values test")
    void getInsertValuesTest() {
        String expectedInsertValues = "(1, 100)";
        assertEquals(expectedInsertValues, sala.getInsertValues());
    }

    @Test
    @DisplayName("Set ID test")
    void setIdTest() {
        sala.setId(2);
        assertEquals(2, sala.getBrojSale());
    }

    @Test
    @DisplayName("Get ID test")
    void getIdTest() {
        assertEquals(1L, sala.getId());
    }

    @Test
    @DisplayName("Extract from ResultSet test")
    void extractFromResultSetTest() throws SQLException {
        ResultSet resultSet = createMockResultSet();
        Sala extractedSala = (Sala) sala.extractFromResultSet(resultSet);

        assertNotNull(extractedSala);
        assertEquals(1, extractedSala.getBrojSale());
        assertEquals(100, extractedSala.getBrojSedista());
    }

    @Test
    @DisplayName("Get where condition test")
    void getWhereConditionTest() {
        String expectedWhereCondition = "(brojSale = 1)";
        assertEquals(expectedWhereCondition, sala.getWhereCondition());
    }

    @Test
    @DisplayName("All details test")
    void allDetailsTest() {
        String expectedAllDetails = "Sala 1\nKapacitet: 100";
        assertEquals(expectedAllDetails, sala.AllDetails());
    }

    private ResultSet createMockResultSet() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("brojsale")).thenReturn(1L);
        when(resultSet.getInt("brojsedista")).thenReturn(100);

        return resultSet;
    }
    
    @Test
    @DisplayName("Test setBrojSale with invalid value")
    void testSetBrojSaleWithInvalidValue() {
        Sala sala = new Sala();
        sala.setBrojSale(-1); 

        assertThrows(IllegalArgumentException.class, sala::getBrojSale);
    }
}

