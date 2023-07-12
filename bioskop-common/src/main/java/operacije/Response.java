package operacije;

import java.io.Serializable;

/**
 * Klasa koja predstavlja odgovor na zahtev izvršavanja operacije.
 * Implementira interfejs Serializable kako bi objekti ove klase mogli biti preneti preko mreže.
 */
public class Response implements Serializable {
	/**
	 * Atribut klase objekat koji predstavlja rezultat sistemske operacije
	 */
    private Object result;
    
    /**
     * Atribut klase izuzetak koji daje detaljan opis nastale greske
     */
    private Exception exception;

    /**
     * Podrazumevani konstruktor.
     */
    public Response() {
    }

    /**
     * Konstruktor koji inicijalizuje Response objekat sa zadatim rezultatom i izuzetkom.
     *
     * @param result    rezultat operacije
     * @param exception izuzetak koji je eventualno nastao prilikom izvršavanja operacije
     */
    public Response(Object result, Exception exception) {
        this.result = result;
        this.exception = exception;
    }

    /**
     * Getter metoda za rezultat.
     *
     * @return rezultat operacije
     */
    public Object getResult() {
        return result;
    }

    /**
     * Setter metoda za rezultat.
     *
     * @param result rezultat operacije koji se postavlja
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * Getter metoda za izuzetak.
     *
     * @return izuzetak koji je eventualno nastao prilikom izvršavanja operacije
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Setter metoda za izuzetak.
     *
     * @param exception izuzetak koji se postavlja
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}

