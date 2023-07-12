package rs.np.ac.bg.bioskop_common.domen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class GenericEntityTest {
    protected GenericEntity genericEntity;

    @Test
    void getTableName() {
        assertFalse(genericEntity.getTableName() == null);
        assertFalse(genericEntity.getTableName().isBlank());
    }

    @Test
    void getColumnNames() {
        assertFalse(genericEntity.getColumnNames() == null);
        assertFalse(genericEntity.getColumnNames().isBlank());
    }

    @Test
    void getValues() {
        assertFalse(genericEntity.getValues() == null);
        assertFalse(genericEntity.getValues().isBlank());
        assertTrue(genericEntity.getValues().contains("="));
    }

    @Test
    void getInsertValues() {
        assertFalse(genericEntity.getInsertValues() == null);
        assertFalse(genericEntity.getInsertValues().isBlank());
        assertTrue(genericEntity.getInsertValues().contains(")"));
        assertTrue(genericEntity.getInsertValues().contains("("));
    }

    

    @Test
    void setIdNormal() {
        genericEntity.setId(1L);
        assertEquals(1L, genericEntity.getId());
    }


}

