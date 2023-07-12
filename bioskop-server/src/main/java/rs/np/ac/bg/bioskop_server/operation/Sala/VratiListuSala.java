package rs.np.ac.bg.bioskop_server.operation.Sala;

import rs.np.ac.bg.bioskop_common.domen.Sala;
import rs.np.ac.bg.bioskop_server.operation.AbstractGenericOperation;

import java.util.List;

/**
 * Operacija koja vraća listu sala.
 */
public class VratiListuSala extends AbstractGenericOperation {

	/**
	 * Lista sala namenjena za vracanje kontroleru preko Getter-a
	 */
    private List<Sala> list;

    /**
     * Proverava preuslove za izvršavanje operacije.
     *
     * @param param parametar operacije
     * @throws Exception ukoliko dođe do greške prilikom provere preuslova
     */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * Izvršava operaciju vraćanja liste sala.
     *
     * @param param parametar operacije
     * @throws Exception ukoliko dođe do greške prilikom izvršavanja operacije
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Sala());
    }

    /**
     * Vraća listu sala.
     *
     * @return lista sala
     */
    public List<Sala> getList() {
        return list;
    }
}
