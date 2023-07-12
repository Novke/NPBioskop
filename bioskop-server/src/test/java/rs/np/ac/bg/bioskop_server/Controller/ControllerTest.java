package rs.np.ac.bg.bioskop_server.Controller;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.*;

import rs.np.ac.bg.bioskop_server.property.PropertyFileOperation;
import rs.np.ac.bg.bioskop_server.repository.*;
import rs.np.ac.bg.bioskop_server.repository.db.DbConnectionFactory;

public class ControllerTest {
	
	private static Controller controller;
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		List<String> params = Arrays.asList("jdbc:mysql://localhost:3306/psbioskop_test",
				"root", "");
		PropertyFileOperation.writeDataToPropertyFile(params, "config/dbconfig.properties");
		controller = Controller.getInstance();
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
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
//		truncateTable("sala");
	}
	
	@AfterEach
	void tearDown() throws Exception {
		System.out.println("TearDown");
		truncateTable("korisnik");
		truncateTable("projekcija");
		truncateTable("karta");
		truncateTable("film");
		truncateTable("ocena");
//		truncateTable("sala");
	}

	
	
	private static void truncateTable(String tableName) throws Exception{
		if(tableName == null || tableName.isBlank()) {
			throw new NullPointerException("Table name must not be empty");
		}
		String query2 = "DELETE FROM "+ tableName +";";
		
			Connection conn = DbConnectionFactory.getInstance().getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query2);
	}
	
	
	
}
