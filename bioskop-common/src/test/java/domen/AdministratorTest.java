package domen;

import rs.np.ac.bg.bioskop_common.domen.Administrator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
