package rs.np.ac.bg.bioskop_common.domen;

import java.io.Serializable;

/**
 * Predstavlja administratora.
 */
public class Administrator implements Serializable {
	
	/**
	 * Privatni atribut koji predstavlja username administratora
	 */
	private String user;
	/**
	 * Privatni atribut koji predstavlja lozinku administratora
	 */
	private String pass;
	
	/**
	 * Konstruktor bez parametara.
	 */
	public Administrator() {
		
	}
	
	/**
	 * Konstruktor sa parametrima.
	 *
	 * @param user korisničko ime administratora
	 * @param pass lozinka administratora
	 */
	public Administrator(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	/**
	 * Vraća korisničko ime administratora.
	 *
	 * @return korisničko ime
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * Postavlja korisničko ime administratora.
	 *
	 * @param user korisničko ime
	 * @throws IllegalArgumentException Ako je username null ili prazan string
	 */
	public void setUser(String user) {
		if (user==null || user.isBlank()) throw new IllegalArgumentException();

		this.user = user;
	}
	
	/**
	 * Vraća lozinku administratora.
	 *
	 * @return lozinka
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * Postavlja lozinku administratora.
	 *
	 * @param pass lozinka
	 * @throws IllegalArgumentException ako je password null ili prazan string
	 */
	public void setPass(String pass) {
		if (pass==null || pass.isBlank()) throw new IllegalArgumentException();

		this.pass = pass;
	}
	
	
}

