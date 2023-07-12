package operation;

import repository.db.DbRepository;
import repository.db.impl.RepositoryDbGeneric;

/**
 * Apstraktna generička operacija.
 */
public abstract class AbstractGenericOperation {

    protected final DbRepository repository;

    /**
     * Konstruktor koji inicijalizuje DbRepository.
     */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

    /**
     * Metoda koja izvršava operaciju.
     *
     * @param param parametar operacije
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    public final void excecute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollback();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
     * Apstraktna metoda koja definiše preduslove operacije.
     *
     * @param param parametar operacije
     * @throws Exception ukoliko preduslovi nisu ispunjeni
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
     * Apstraktna metoda koja definiše izvršavanje operacije.
     *
     * @param param parametar operacije
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Metoda koja pokreće transakciju.
     *
     * @throws Exception ukoliko dođe do greške prilikom pokretanja transakcije
     */
    private void startTransaction() throws Exception {
        repository.connect();
    }

    /**
     * Metoda koja potvrđuje transakciju.
     *
     * @throws Exception ukoliko dođe do greške prilikom potvrđivanja transakcije
     */
    private void commit() throws Exception {
        repository.commit();
    }

    /**
     * Metoda koja poništava transakciju.
     *
     * @throws Exception ukoliko dođe do greške prilikom poništavanja transakcije
     */
    private void rollback() throws Exception {
        repository.rollback();
    }

    /**
     * Metoda koja prekida vezu sa bazom podataka.
     *
     * @throws Exception ukoliko dođe do greške prilikom prekidanja veze sa bazom podataka
     */
    private void disconnect() throws Exception {
        repository.disconnect();
    }

}
