package rs.np.ac.bg.bioskop.common2.domen;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.np.ac.bg.bioskop.common2.domen.Administrator;

public class AdministratorTest {

    private Administrator administrator;

    @BeforeEach
    public void setUp() {
        administrator = new Administrator("admin", "password");
    }

    @Test
    public void testGetUser() {
        String username = administrator.getUser();
        Assertions.assertEquals("admin", username);
    }

    @Test
    public void testSetUser() {
        administrator.setUser("newAdmin");
        String username = administrator.getUser();
        Assertions.assertEquals("newAdmin", username);
    }
    
    @Test
    public void testSetUserIllegal() {
    	assertThrows(IllegalArgumentException.class, () -> administrator.setUser(null));
    }

    @Test
    public void testGetPass() {
        String password = administrator.getPass();
        Assertions.assertEquals("password", password);
    }

    @Test
    public void testSetPass() {
        administrator.setPass("newPassword");
        String password = administrator.getPass();
        Assertions.assertEquals("newPassword", password);
    }

    @Test
    public void testSetPassIllegal() {
    	assertThrows(IllegalArgumentException.class, () -> administrator.setPass(null));
    }
    
    @ParameterizedTest
    @CsvSource({
            "John, false",
            "'', true",
            "John, false"
    })
    public void testSetUserWithInvalidInput(String user, boolean expectException) {
        Administrator administrator = new Administrator();

        if (expectException) {
            assertThrows(IllegalArgumentException.class, () -> administrator.setUser(user));
        } else {
        	administrator.setUser(user);
        	assertEquals(user, administrator.getUser());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "password123, false",
            "'', true",
            "password123, false"
    })
    public void testSetPassWithInvalidInput(String pass, boolean expectException) {
        Administrator administrator = new Administrator();

        if (expectException) {
            assertThrows(IllegalArgumentException.class, () -> administrator.setPass(pass));
        } else {
        	administrator.setPass(pass);
        	assertEquals(pass, administrator.getPass());
        }
    }
}
