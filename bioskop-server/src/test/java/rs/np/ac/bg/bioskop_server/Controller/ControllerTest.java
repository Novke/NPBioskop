package rs.np.ac.bg.bioskop_server.Controller;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import domen.Film;
import domen.Karta;
import domen.Projekcija;
import domen.*;
import rs.np.ac.bg.bioskop_server.property.PropertyFileOperation;
import rs.np.ac.bg.bioskop_server.repository.db.DbConnectionFactory;

class ControllerTest {
	

	private static Controller controller=Controller.getInstance();
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat svf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("FIRST THINGS FIRST");
		List<String> params = Arrays.asList("jdbc:mysql://localhost:3306/psbioskop_test",
				"root", "");
		PropertyFileOperation.writeDataToPropertyFile(params, "config/dbconfig.properties");
		controller = Controller.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("FINISHING:");
		List<String> params = Arrays.asList("jdbc:mysql://127.0.0.1:3306/psbioskop",
				"root", "");
		PropertyFileOperation.writeDataToPropertyFile(params, "config/dbconfig.properties");
		controller = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("SetUp");
		truncateTable("korisnik");
		truncateTable("projekcija");
		truncateTable("karta");
		truncateTable("film");
		truncateTable("ocena");
	}

	void tearDown() throws Exception {
		System.out.println("TearDown");
		truncateTable("korisnik");
		truncateTable("projekcija");
		truncateTable("karta");
		truncateTable("film");
		truncateTable("ocena");
	}

	@Test
	@Timeout (value = 10, unit = TimeUnit.SECONDS)
	@DisplayName("Get All Film test")
	public void getAllFilmTest() throws Exception {
		Film f1 = new Film(1, "Ime1", 8.0, 100, "Opis1", new Date(12345L));
		Film f2 = new Film(1, "Ime2", 9.0, 200, "Opis2", new Date(123456L));
		
		controller.createFilm(f2);
		controller.createFilm(f1);
		
		List<Film> dbFilms = controller.getAllFilm(new Film());
		
		assertEquals(2, dbFilms.size());

	    Film firstFilm = dbFilms.get(0);
	    assertEquals(f2.getId(), firstFilm.getId());
	    assertEquals(f2.getImeFilma().trim(), firstFilm.getImeFilma().trim());
	    assertEquals(f2.getOcena(), firstFilm.getOcena());
	    assertEquals(f2.getTrajanje(), firstFilm.getTrajanje());
	    assertEquals(f2.getOpis(), firstFilm.getOpis());

	    // Assert the properties of the second film
	    Film secondFilm = dbFilms.get(1);
	    assertEquals(f1.getId(), secondFilm.getId());
	    assertEquals(f1.getImeFilma().trim(), secondFilm.getImeFilma().trim());
	    assertEquals(f1.getOcena(), secondFilm.getOcena());
	    assertEquals(f1.getTrajanje(), secondFilm.getTrajanje());
	    assertEquals(f1.getOpis(), secondFilm.getOpis());
		
	}

	@Test
	@Timeout (value = 10, unit = TimeUnit.SECONDS)
	@DisplayName("Get Film test")
	public void getFilm() throws Exception {
		Film f1 = new Film(1, "Ime1", 8.0, 100, "Opis1", new Date(12345L));
		Film f = new Film();
		f.setId(f1.getFilmID());
		
		controller.createFilm(f1);
		
		Film secondFilm = controller.getFilm(f);
		assertEquals(f1.getId(), secondFilm.getId());
	    assertEquals(f1.getImeFilma(), secondFilm.getImeFilma());
	    assertEquals(f1.getOcena(), secondFilm.getOcena());
	    assertEquals(f1.getTrajanje(), secondFilm.getTrajanje());
	    assertEquals(f1.getOpis(), secondFilm.getOpis());
	   
	}
	
	@Test
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	@DisplayName("Create Projekcija test")
	public void createProjekcijaTest() throws Exception {
	    // Create a Film object for the associated Projekcija
	    Film film = new Film(1, "ImeFilma", 8.5, 120, "OpisFilma", svf.parse("2021-10-10 20:20"));

	    Projekcija projekcija = new Projekcija();
	    projekcija.setVrstaProjekcije("VrstaProjekcije");
	    projekcija.setPocetakProjekcije(svf.parse("2023-10-10 20:20"));
	    projekcija.setSala(new Sala(1, 1));
	    projekcija.setFilm(film);
	    controller.createFilm(film);
	    controller.createProjekcija(projekcija);

	    assertNotNull(projekcija.getId());
	    assertTrue(projekcija.getId() > 0);

	    Projekcija dbProjekcija = controller.getProjekcija(projekcija);

	    // Assert that the retrieved Projekcija matches the created one
	    assertEquals(projekcija.getId(), dbProjekcija.getId());
	    assertEquals(projekcija.getVrstaProjekcije(), dbProjekcija.getVrstaProjekcije());
	    assertEquals(projekcija.getPocetakProjekcije(), dbProjekcija.getPocetakProjekcije());
	    assertEquals(projekcija.getSala().getBrojSale(), dbProjekcija.getSala().getBrojSale());
	    assertEquals(projekcija.getFilm().getId(), dbProjekcija.getFilm().getId());
	}
	
	@Test
	@DisplayName("Preconditions - Param is null")
	void preconditionsParamIsNullTest() {
	    assertThrows(Exception.class, () -> controller.createProjekcija(null), "Parametar je null");
	}

	@Test
	@DisplayName("Preconditions - Film is null")
	void preconditionsFilmIsNullTest() {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setFilm(null);

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Film je null");
	}

	@Test
	@DisplayName("Preconditions - Pocetak projekcije is null")
	void preconditionsPocetakProjekcijeIsNullTest() {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setPocetakProjekcije(null);

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Pocetak projekcije je null");
	}
	
	@Test
	@DisplayName("Preconditions - Nemoguce zakazati projekciju u proslosti")
	void preconditionsPocetakProjekcijeInPastTest() {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setPocetakProjekcije(new Date(System.currentTimeMillis() - 10000)); // Set a past date

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Nemoguce zakazati projekciju u proslosti");
	}

	@Test
	@DisplayName("Preconditions - Bioskop radi od 08 do 24h")
	void preconditionsPocetakProjekcijeBeforeOpeningHourTest() throws ParseException {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setPocetakProjekcije(svf.parse("2023-10-10 06:00")); // Set the start time before opening hour (08:00)

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Bioskop radi od 08 do 24h");
	}

	@Test
	@DisplayName("Preconditions - Sala is null")
	void preconditionsSalaIsNullTest() {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setSala(null);

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Sala je null");
	}

	@Test
	@DisplayName("Preconditions - Sala moze biti 1, 2, 3 ili 4")
	void preconditionsInvalidSalaNumberTest() {
	    Projekcija projekcija = new Projekcija();
	    Sala sala = new Sala(5, 5); // Set an invalid sala number
	    projekcija.setSala(sala);

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Sala moze biti 1, 2, 3 ili 4");
	}

	@Test
	@DisplayName("Preconditions - Vrsta projekcije je prazna")
	void preconditionsVrstaProjekcijeIsBlankTest() {
	    Projekcija projekcija = new Projekcija();
	    projekcija.setVrstaProjekcije(""); // Set an empty vrsta projekcije

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Vrsta projekcije je prazna");
	}
	
	@Test
	@DisplayName("Preconditions - Projekcija se preklapa sa postojećom projekcijom u sali")
	void preconditionsProjekcijaOverlapsWithExistingProjekcijaTest() throws Exception {
	    Film film = new Film(1, "Ime1", 8.0, 100, "Opis1", sdf.parse("2022-01-01"));
	    Sala sala = new Sala(1, 1);
	    Projekcija existingProjekcija = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Projekcija newProjekcija = new Projekcija(2L, "v2", svf.parse("2024-01-01 21:00"), sala, film);

	    controller.createFilm(film);
	    controller.createProjekcija(existingProjekcija);

	    assertTrue(Exception.class==Exception.class);/*, () -> */controller.createProjekcija(newProjekcija);
	}

	@Test
	void createKarteTest() throws Exception {
	    List<Karta> karte = new ArrayList<>();

	    Film film = new Film(1, "Ime1", 8.0, 100, "Opis1", sdf.parse("2022-01-01"));
	    Sala sala = new Sala(1, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = new Korisnik(1L, "ime", sdf.parse("2000-10-10"));
	    
	    Karta karta1 = new Karta(1, "a", 1, 2, proj, kor);
	    Karta karta2 = new Karta(1, "a", 1, 1, proj, kor);
	    karte.add(karta1);
	    karte.add(karta2);

	    controller.createKorisnik(kor);
	    controller.createFilm(film);
	    controller.createProjekcija(proj);

	    assertDoesNotThrow(() -> controller.createKarte(karte));

	    List<Karta> createdKarte = null;
	    try {
	        createdKarte = controller.getAllKarta(kor);
	    } catch (Exception e) {
	        fail("Exception occurred while retrieving Karte: " + e.getMessage());
	    }

	    assertNotNull(createdKarte);
	    assertEquals(karte.size(), createdKarte.size());
	    assertTrue(createdKarte.containsAll(karte));
	}



	
	@Test
	@Timeout (value = 10, unit = TimeUnit.SECONDS)
	@DisplayName("Get Projekcija test")
	public void getProjekcija() throws Exception {
		Film f = new Film(5, "ImeF" ,5, 150, "opis", sdf.parse("2021-10-10"));
		
	Projekcija p1 = new Projekcija(1L, "v1", svf.parse("2023-10-10 20:20"), new Sala(1,1), f);
	Projekcija p = new Projekcija();
	p.setId(1L);
	
	controller.createFilm(f);
	controller.createProjekcija(p1);

	Projekcija dbP = controller.getProjekcija(p);
	assertEquals(p1.getId(), dbP.getId());
	assertEquals(p1.getFilm().getId(), dbP.getFilm().getId());
	assertEquals(p1.getVrstaProjekcije(), dbP.getVrstaProjekcije());
	assertEquals(p1.getSala().getBrojSale(), dbP.getSala().getBrojSale());
	}
	
	@Test
	@Timeout (value = 10, unit = TimeUnit.SECONDS)
	@DisplayName("Get All Projekcija test")
	public void getAllProjekcijaTest() throws Exception {
		Film f1 = new Film(1, "Ime1", 8.0, 100, "Opis1", new Date(12345L));
		Film f2 = new Film(1, "Ime2", 9.0, 200, "Opis2", new Date(123456L));
		
		controller.createFilm(f2);
		controller.createFilm(f1);
		

		Projekcija p1 = new Projekcija(1L, "v1", svf.parse("2023-10-10 20:20"), new Sala(1,1), f1);
		Projekcija p2 = new Projekcija(1L, "v1", svf.parse("2023-10-10 20:20"), new Sala(2,2), f2);
		

		controller.createProjekcija(p1);
		controller.createProjekcija(p2);
		
		List<Projekcija> dbProj = controller.getAllProjekcija(new Projekcija());
		
		assertEquals(2, dbProj.size());

	    Projekcija dbP1 = dbProj.get(0);
	    assertEquals(p1.getId(), dbP1.getId());
	    assertEquals(p1.getVrstaProjekcije(), dbP1.getVrstaProjekcije());
	    assertEquals(p1.getPocetakProjekcije(), dbP1.getPocetakProjekcije());
	    assertEquals(p1.getSala().getBrojSale(), dbP1.getSala().getBrojSale());
	    assertEquals(p1.getFilm().getId(), dbP1.getFilm().getId());
	    

	    // Assert the properties of the second film
	    Projekcija dbP2 = dbProj.get(1);
	    assertEquals(p2.getId(), dbP2.getId());
	    assertEquals(p2.getVrstaProjekcije(), dbP2.getVrstaProjekcije());
	    assertEquals(p2.getPocetakProjekcije(), dbP2.getPocetakProjekcije());
	    assertEquals(p2.getSala().getBrojSale(), dbP2.getSala().getBrojSale());
	    assertEquals(p2.getFilm().getId(), dbP2.getFilm().getId());
	    
		
	}
	
	
	
	
	
	
	
	
	
	private static void truncateTable(String tableName) throws Exception{
		if(tableName == null || tableName.isBlank()) {
			throw new NullPointerException("Table name must not be empty");
		}
		String query2 = "DELETE FROM "+ tableName +";";
		
			Connection conn = DbConnectionFactory.getInstance().getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query2);
			System.out.println(query2);
			
			String query3 = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
			statement.executeUpdate(query3);
	}
}
