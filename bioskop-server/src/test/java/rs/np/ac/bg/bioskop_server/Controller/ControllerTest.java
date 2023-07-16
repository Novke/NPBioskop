package rs.np.ac.bg.bioskop_server.Controller;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.np.ac.bg.bioskop_common.domen.*;
import rs.np.ac.bg.bioskop_common.domen.Projekcija;
import rs.np.ac.bg.bioskop_server.property.PropertyFileOperation;
import rs.np.ac.bg.bioskop_server.repository.db.DbConnectionFactory;


class ControllerTest {
	

	private static Controller controller;
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
	@AfterEach
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
	void preconditionsFilmIsNullTest() throws ParseException {
	    Projekcija projekcija = new Projekcija(1, "vrsta", sdf.parse("2024-10-10"), new Sala(1,1), null);

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Film je null");
	}

	@Test
	@DisplayName("Preconditions - Pocetak projekcije is null")
	void preconditionsPocetakProjekcijeIsNullTest() throws ParseException {
	    Projekcija projekcija = new Projekcija(1, "vrsta", null, new Sala(1,1), randomFilm());

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
	void preconditionsSalaIsNullTest() throws ParseException {
	    Projekcija projekcija = new Projekcija(1, "vrsta", sdf.parse("2024-10-10"), null, randomFilm());

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Sala je null");
	}

	@Test
	@DisplayName("Preconditions - Sala moze biti 1, 2, 3 ili 4")
	void preconditionsInvalidSalaNumberTest() throws ParseException {
	    Sala sala = new Sala(5, 5); 
	    Projekcija projekcija = new Projekcija(1, "vrsta", sdf.parse("2024-10-10"), sala, randomFilm());

	    assertThrows(Exception.class, () -> controller.createProjekcija(projekcija), "Sala moze biti 1, 2, 3 ili 4");
	}

	@Test
	@DisplayName("Preconditions - Vrsta projekcije je prazna")
	void preconditionsVrstaProjekcijeIsBlankTest() throws ParseException {
	    Projekcija projekcija = new Projekcija(1, "", sdf.parse("2024-10-10"), new Sala(1,1), randomFilm());

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

	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = randomKorisnik();
	    
	    Karta karta1 = new Karta(1, "a", 1, 2, proj, kor);
	    Karta karta2 = new Karta(2, "a", 1, 1, proj, kor);
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

	    for (int i = 0; i < 2; i++) {
	    	assertEquals(karte.get(i).getId(), createdKarte.get(i).getId());
	    	assertEquals(karte.get(i).getKorisnik().getId(), createdKarte.get(i).getKorisnik().getId());
	    	assertEquals(karte.get(i).getProjekcija().getId(), createdKarte.get(i).getProjekcija().getId());
	    	assertEquals(karte.get(i).getRed(), createdKarte.get(i).getRed());
	    	assertEquals(karte.get(i).getMesto(), createdKarte.get(i).getMesto());
	    }
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
	    

	    Projekcija dbP2 = dbProj.get(1);
	    assertEquals(p2.getId(), dbP2.getId());
	    assertEquals(p2.getVrstaProjekcije(), dbP2.getVrstaProjekcije());
	    assertEquals(p2.getPocetakProjekcije(), dbP2.getPocetakProjekcije());
	    assertEquals(p2.getSala().getBrojSale(), dbP2.getSala().getBrojSale());
	    assertEquals(p2.getFilm().getId(), dbP2.getFilm().getId());
	    
		
	}
	
	
	
	@Test
	@DisplayName("Preconditions Karte - Param is null")
	void preconditionsKarteIsNullTest() {
	    List<Karta> karte = null;

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Parametar je null");
	}

	@Test
	@DisplayName("Preconditions Karte - Empty list")
	void preconditionsEmptyKartaListTest() {
	    List<Karta> karte = new ArrayList<>();

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "0 karata prosleđeno");
	}

	@Test
	@DisplayName("Preconditions Karte - Korisnik is null")
	void preconditionsKorisnikIsNullTest() throws ParseException {
	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = null;

	    Karta karta = new Karta(1, "a", 1, 2, proj, kor);
	    List<Karta> karte = Collections.singletonList(karta);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Korisnik je null");
	}

	@Test
	@DisplayName("Preconditions - Mesto is negative")
	void preconditionsMestoIsNegativeTest() throws ParseException {
	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = randomKorisnik();

	    Karta karta = new Karta(1, "a", 1, -2, proj, kor);
	    List<Karta> karte = Collections.singletonList(karta);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Mesto je negativan broj");
	}

	@Test
	@DisplayName("Preconditions - Red is negative")
	void preconditionsRedIsNegativeTest() throws ParseException {
	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = randomKorisnik();

	    Karta karta = new Karta(1, "a", -1, 2, proj, kor);
	    List<Karta> karte = Collections.singletonList(karta);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Red je negativan broj");
	}

	@Test
	@DisplayName("Preconditions - Projekcija is null")
	void preconditionsProjekcijaIsNullTest() throws ParseException {
	    Film film = randomFilm();
	    Korisnik kor = randomKorisnik();

	    Karta karta = new Karta(1, "a", 1, 2, null, kor);
	    List<Karta> karte = Collections.singletonList(karta);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Projekcija je null");
	}

	@Test
	@DisplayName("Preconditions - Tip karte is blank")
	void preconditionsTipKarteIsBlankTest() throws ParseException {
	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = randomKorisnik();

	    Karta karta = new Karta(1, "", 1, 2, proj, kor);
	    List<Karta> karte = Collections.singletonList(karta);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Tip karte je prazan string");
	}

	@Test
	@DisplayName("Preconditions - Zauzeta mesta")
	void preconditionsZauzetaMestaTest() throws ParseException {
	    Film film = randomFilm();
	    Sala sala = new Sala(1L, 1);
	    Projekcija proj = new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), sala, film);
	    Korisnik kor = randomKorisnik();

	    Karta karta1 = new Karta(1, "a", 1, 2, proj, kor);
	    Karta karta2 = new Karta(2, "a", 1, 2, proj, kor); 
	    List<Karta> karte = Arrays.asList(karta1, karta2);

	    assertThrows(Exception.class, () -> controller.createKarte(karte), "Mesto 2 u 1. redu je zauzeto!\n");
	}

	@Test
	void createKorisnikTest() throws Exception{
	    Korisnik korisnik = randomKorisnik();

	    assertDoesNotThrow(() -> controller.createKorisnik(korisnik));

	    // Verify that the created Korisnik is retrievable
	    Korisnik createdKorisnik = null;
	    try {
	        createdKorisnik = controller.getKorisnik(korisnik);
	    } catch (Exception e) {
	        fail("Exception occurred while retrieving Korisnik: " + e.getMessage());
	    }

	    assertNotNull(createdKorisnik);
	    assertEquals(korisnik.getId(), createdKorisnik.getId());
	    assertEquals(korisnik.getIme(), createdKorisnik.getIme());
	    assertEquals(korisnik.getDatumRodjenja(), createdKorisnik.getDatumRodjenja());
	}

	
	@Test
	void createKorisnik_NullParam_ThrowsException() {
	    assertThrows(Exception.class, () -> controller.createKorisnik(null), "Parametar je null");
	}

	@Test
	void createKorisnik_BlankIme_ThrowsException() throws ParseException {
	    Korisnik korisnik = new Korisnik(1L, "", sdf.parse("2000-10-10"));

	    assertThrows(Exception.class, () -> controller.createKorisnik(korisnik), "Ime je prazno");
	}

	@Test
	void createKorisnik_InvalidDatumRodjenja_ThrowsException() throws ParseException {
	    Date futureDate = sdf.parse("2024-01-01");
	    Korisnik korisnik = new Korisnik(1L, "ime", futureDate);

	    assertThrows(Exception.class, () -> controller.createKorisnik(korisnik), "Loš datum rođenja");
	}

	@Test
	void createKorisnik_DuplicateIme_ThrowsException() throws Exception {
	    Korisnik existingKorisnik = randomKorisnik();
	    controller.createKorisnik(existingKorisnik);

	    Korisnik korisnik = new Korisnik(2L, "ime", sdf.parse("1999-05-05"));

	    assertThrows(Exception.class, () -> controller.createKorisnik(korisnik), "Već postoji korisnik sa tim imenom!");
	}

	@Test
	void getAllSalaTest() throws Exception {
	    // Create some Sala objects
	    Sala sala1 = new Sala(1L, 1);
	    Sala sala2 = new Sala(2L, 2);
	    Sala sala3 = new Sala(3L, 3);
	    Sala sala4 = new Sala(4L, 4);

	    // Retrieve all Sala objects
	    List<Sala> allSala = controller.getAllSala();

	    // Assert the size of the retrieved list
	    assertEquals(4, allSala.size());

	    // Assert that the retrieved list contains all the Sala objects
	    assertEquals(allSala.get(0).getBrojSale(), 1L);
	    assertEquals(allSala.get(1).getBrojSale(), 2L);
	    assertEquals(allSala.get(2).getBrojSale(), 3L);
	    assertEquals(allSala.get(3).getBrojSale(), 4L);
	}

	
	@Test
	void deleteKarteTest() throws Exception {
	    Film film = randomFilm();
	    Projekcija proj = randomProjekcija(film);
	    Korisnik kor = randomKorisnik();

	    Karta karta1 = new Karta(1L, "A", 1, 1, proj, kor);
	    Karta karta2 = new Karta(2L, "B", 2, 2, proj, kor);
	    Karta karta3 = new Karta(3L, "C", 3, 3, proj, kor);

	 
	    List<Karta> karte = Arrays.asList(karta1, karta2, karta3);
	    
	    controller.createFilm(film);
	    controller.createKorisnik(kor);
	    controller.createProjekcija(proj);

	    controller.createKarte(karte);
	    
	    assertDoesNotThrow(() -> controller.deleteKarte(karte));

	    for (Karta karta : karte) {
	        assertEquals(0, controller.getAllKarta(kor).size());
	    }
	}

	
	@Test
	void editKartaTest() throws Exception {
	    Film film = randomFilm();
	    Projekcija proj = randomProjekcija(film);
	    Korisnik kor = randomKorisnik();
	    Karta karta = new Karta(1L, "A", 1, 1, proj, kor);


	    List<Karta> karte = new ArrayList<>();
	    karte.add(karta);
	    controller.createKorisnik(kor);
	    controller.createFilm(film);
	    controller.createProjekcija(proj);
	    controller.createKarte(karte);


	    karta.setMesto(2);
	    karta.setRed(2);


	    assertDoesNotThrow(() -> controller.editKarta(karta));


	    Karta editedKarta = controller.getAllKarta(kor).get(0);


	    assertEquals(karta.getMesto(), editedKarta.getMesto());
	    assertEquals(karta.getRed(), editedKarta.getRed());
	}
	
	@Test
	void editKartaParamNullTest() {
	    assertThrows(Exception.class, () -> controller.editKarta(null));
	}

	@Test
	void editKartaMestoNegativeOrZeroTest() {
	    Karta karta = new Karta();
	    karta.setMesto(0);

	    assertThrows(Exception.class, () -> controller.editKarta(karta));
	}

	@Test
	void editKartaKorisnikNullTest() throws ParseException {
	    Karta karta = new Karta(1, "tip", 1, 1, randomProjekcija(randomFilm()), null);

	    assertThrows(Exception.class, () -> controller.editKarta(karta));
	}

	@Test
	void editKartaRedNegativeOrZeroTest() throws ParseException {
	    Karta karta = new Karta(1, "tip", -1, 1, randomProjekcija(randomFilm()), randomKorisnik());

	    assertThrows(Exception.class, () -> controller.editKarta(karta));
	}

	@Test
	void editKartaProjekcijaNullTest() throws ParseException {
	    Karta karta = new Karta(1, "tip", 1, 1, null, randomKorisnik());

	    assertThrows(Exception.class, () -> controller.editKarta(karta));
	}

	@Test
	void editKartaTipKarteBlankTest() throws ParseException {
	    Karta karta = new Karta(1, null, 1, 1, randomProjekcija(randomFilm()), randomKorisnik());

	    assertThrows(Exception.class, () -> controller.editKarta(karta));
	}

	@Test
	void getAllKartaTest() throws Exception {

	    Film film = randomFilm();
	    Projekcija projekcija = randomProjekcija(film);
	    Korisnik korisnik = randomKorisnik();
	    List<Karta> karte = new ArrayList<>();

	    // Create multiple Karta instances
	    for (int i = 0; i < 5; i++) {
	        Karta karta = new Karta();
	        karta.setId((long) (i + 1));
	        karta.setRed(i + 1);
	        karta.setMesto(i + 1);
	        karta.setProjekcija(projekcija);
	        karta.setKorisnik(korisnik);
	        karta.setTipKarte("Tip " + (i + 1));
	        karte.add(karta);
	    }

	    controller.createKorisnik(korisnik);
	    controller.createFilm(film);
	    controller.createProjekcija(projekcija);
	    controller.createKarte(karte);


	    List<Karta> retrievedKarte = controller.getAllKarta(korisnik);


	    assertNotNull(retrievedKarte);
	    assertEquals(karte.size(), retrievedKarte.size());

	    for (int i = 0; i < karte.size(); i++) {
	        Karta expectedKarta = karte.get(i);
	        Karta actualKarta = retrievedKarte.get(i);

	        assertEquals(expectedKarta.getId(), actualKarta.getId());
	        assertEquals(expectedKarta.getRed(), actualKarta.getRed());
	        assertEquals(expectedKarta.getMesto(), actualKarta.getMesto());
	        assertEquals(expectedKarta.getProjekcija().getId(), actualKarta.getProjekcija().getId());
	        assertEquals(expectedKarta.getKorisnik().getId(), actualKarta.getKorisnik().getId());
	    }
	}

	@Test
	void deleteKorisnikTest() throws Exception {
	    // Create a dummy Korisnik
	    Korisnik korisnik = randomKorisnik();
	    controller.createKorisnik(korisnik);

	    // Delete the Korisnik
	    assertDoesNotThrow(() -> controller.deleteKorisnik(korisnik));

	    // Verify the Korisnik is deleted
	    assertThrows(Exception.class, () -> controller.getKorisnik(korisnik));
	}

	@Test
	void deleteKorisnikNullParameterTest() {
	    assertThrows(Exception.class, () -> controller.deleteKorisnik(null));
	}

	@Test
	void editKorisnikTest() throws Exception {
	    Korisnik korisnik = new Korisnik();
	    korisnik.setId(1L);
	    korisnik.setIme("John");
	    korisnik.setDatumRodjenja(sdf.parse("2000-10-10"));

	    controller.createKorisnik(korisnik);

	    korisnik.setIme("Jane");

	    assertDoesNotThrow(() -> controller.editKorisnik(korisnik));

	    Korisnik updatedKorisnik = controller.getKorisnik(korisnik);

	    assertEquals("Jane", updatedKorisnik.getIme());
	}
	
	@Test
    @DisplayName("Edit Korisnik - Null Parameter")
    void editKorisnik_NullParam_ThrowsException() {
        assertThrows(Exception.class, () -> controller.editKorisnik(null));
    }

    @Test
    @DisplayName("Edit Korisnik - Blank Ime")
    void editKorisnik_BlankIme_ThrowsException() throws ParseException {
        Korisnik korisnik = new Korisnik(1, " ", sdf.parse("2000-10-10"));

        assertThrows(Exception.class, () -> controller.editKorisnik(korisnik));
    }

    @Test
    @DisplayName("Edit Korisnik - Future Datum Rodjenja")
    void editKorisnik_FutureDatumRodjenja_ThrowsException() throws ParseException {
        Korisnik korisnik = new Korisnik(1, "ime", sdf.parse("2024-10-10"));

        assertThrows(Exception.class, () -> controller.editKorisnik(korisnik));
    }

    @Test
    @DisplayName("Get Korisnik")
    void getKorisnik_ValidKorisnik_ReturnsKorisnik() throws Exception {
        Korisnik korisnik = randomKorisnik();
        controller.createKorisnik(korisnik);

        Korisnik retrievedKorisnik = controller.getKorisnik(korisnik);

        assertNotNull(retrievedKorisnik);
        assertEquals(korisnik.getId(), retrievedKorisnik.getId());
    }

    @Test
    @DisplayName("Get All Korisnik")
    void getAllKorisnik_ReturnsListOfKorisnik() throws Exception {
        List<Korisnik> korisnikList = controller.getAllKorisnik(null);

        assertNotNull(korisnikList);
    }
	
	@ParameterizedTest
	@DisplayName("Login Test")
	@CsvSource({
		"admin, admin, true",
		"ADMIN, adMIN, true",
        "incorrectUsername, admin, false",
        "admin, incorrectPassword, false"
	})
	void loginTest(String username, String password, boolean result) {
		assertEquals(controller.login(username, password), result);
	}
	
	 @Test
	    @DisplayName("Delete Film Test")
	    void deleteFilm_ValidFilm_DeletesFilm() throws Exception {
	        Film film = new Film(1L, "Film 1", 8.0, 100, "Description", new Date());
	        controller.createFilm(film);
	        List<Film> lista1 = controller.getAllFilm(null);
	        controller.deleteFilm(film);
	        List<Film> lista2 = controller.getAllFilm(null);

	        assertEquals(lista1.size()-lista2.size(), 1);
	    }

	    @Test
	    @DisplayName("Delete Projekcija Test")
	    void deleteProjekcija_ValidProjekcija_DeletesProjekcija() throws Exception {
	    	Film film = randomFilm();
	    	Korisnik korisnik = randomKorisnik();
	        Projekcija projekcija = randomProjekcija(film);
	        
	        controller.createFilm(film);
	        controller.createKorisnik(korisnik);
	        controller.createProjekcija(projekcija);


	        List<Projekcija> list1 = controller.getAllProjekcija(projekcija);
	        controller.deleteProjekcija(projekcija);
	        List<Projekcija> list2 = controller.getAllProjekcija(projekcija);
	        assertEquals(list1.size()-list2.size(), 1);
	    }
	
	    @Test
	    @DisplayName("Delete Film Null Parameter Test")
	    void deleteFilm_NullParameter_ReturnsFalse() {
	        assertThrows(Exception.class, () -> controller.deleteFilm(null));
	    }

	    @Test
	    @DisplayName("Delete Projekcija Null Parameter Test")
	    void deleteProjekcija_NullParameter_ReturnsFalse() {
	        assertThrows(Exception.class, () -> controller.deleteProjekcija(null));
	    }
	
	    @Test
	    @DisplayName("Test getAllOcena with valid film")
	    void testGetAllOcenaWithValidFilm() throws Exception {
	        Film film = randomFilm();
	        Korisnik korisnik = randomKorisnik();
	        Projekcija projekcija = randomProjekcija(film);
	        Ocena ocena1 = new Ocena(1L, 2, korisnik, film);
	        controller.createKorisnik(korisnik);
	        controller.createFilm(film);
	        controller.createOcena(ocena1);

	        List<Ocena> ocene = controller.getAllOcena(film);
	        assertEquals(1, ocene.size());
	        assertNotNull(ocene);
	        assertEquals(ocena1.getId(), ocene.get(0).getId());
	    }

	    @Test
	    @DisplayName("Test getAllOcena with null film")
	    void testGetAllOcenaWithNullFilm() {
	        assertThrows(Exception.class, () -> {

	            controller.getAllOcena(null);
	        });
	    }
	    
	    @Test
	    @DisplayName("Test getAllKarta with Projekcija")
	    void testGetAllKartaWithProjekcija() throws Exception {

	    	Film film = randomFilm();
	        Projekcija projekcija = randomProjekcija(film);
	        Korisnik korisnik = randomKorisnik();
	        Karta k1 = new Karta(1L, "aaa", 1, 2, projekcija, korisnik);
	        Karta k2 = new Karta(2L, "aaa", 1, 2, projekcija, korisnik);
	        Karta k3 = new Karta(3L, "aaa", 1, 2, projekcija, korisnik);
	        List<Karta> karte = List.of(k1,  k2, k3);
	        
	        controller.createFilm(film);
	        controller.createKorisnik(korisnik);
	        controller.createProjekcija(projekcija);
	        controller.createKarte(karte);
	        
	        
	        List<Karta> dbKarte = controller.getAllKarta(projekcija);
	        assertNotNull(dbKarte);
	        assertEquals(karte.size(), dbKarte.size());
	        for (int i = 0; i < karte.size(); i++) {
	        	assertEquals(karte.get(i).getId(), dbKarte.get(i).getId());
	        }
	    }
	

	
	
	
	
	
	
	
	
	

	private Korisnik randomKorisnik() throws ParseException {
		return new Korisnik(1L, "ime", sdf.parse("2000-10-10"));
	}

	private Projekcija randomProjekcija(Film film) throws ParseException {
		return new Projekcija(1L, "v1", svf.parse("2024-01-01 20:00"), new Sala(1,1), film);
	}

	private Film randomFilm() throws ParseException {
		return new Film(1L, "Ime1", 8.0, 100, "Opis1", sdf.parse("2022-01-01"));
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
