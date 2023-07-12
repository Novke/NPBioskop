package domen;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domen.*;

public class OcenaTest {
    private Ocena ocena;
    private Korisnik korisnik;
    private Film film;

    @BeforeEach
    public void setUp() {
        korisnik = new Korisnik();
        korisnik.setId(1L);  
        korisnik.setIme("John Doe");  
        
        film = new Film();
        film.setId(1L);  
        film.setImeFilma("Movie 1");  
        
        ocena = new Ocena(1L, 5, korisnik, film);
    }

    @Test
    public void testGetOcena() {
        int expectedOcena = 5;
        int actualOcena = ocena.getOcena();
        Assertions.assertEquals(expectedOcena, actualOcena);
    }

    @Test
    public void testSetOcena() {
        int newOcena = 4;
        ocena.setOcena(newOcena);
        int updatedOcena = ocena.getOcena();
        Assertions.assertEquals(newOcena, updatedOcena);
    }

    @Test
    public void testGetKorisnik() {
        Korisnik expectedKorisnik = korisnik;
        Korisnik actualKorisnik = ocena.getKorisnik();
        Assertions.assertEquals(expectedKorisnik, actualKorisnik);
    }

    @Test
    public void testGetOcenaID() {
        long expectedOcenaID = 1L;
        long actualOcenaID = ocena.getOcenaID();
        Assertions.assertEquals(expectedOcenaID, actualOcenaID);
    }

    @Test
    public void testSetOcenaID() {
        long newOcenaID = 2L;
        ocena.setOcenaID(newOcenaID);
        long updatedOcenaID = ocena.getOcenaID();
        Assertions.assertEquals(newOcenaID, updatedOcenaID);
    }

    @Test
    public void testGetFilm() {
        Film expectedFilm = film;
        Film actualFilm = ocena.getFilm();
        Assertions.assertEquals(expectedFilm, actualFilm);
    }
    
    @Test
    public void testToString() {
        String expectedToString = "Ocena{ocenaID=1, ocena=5, korisnik=" + korisnik + ", film=" + film + "}";
        String actualToString = ocena.toString();
        Assertions.assertEquals(expectedToString, actualToString);
    }


}

