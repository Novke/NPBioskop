package operation;

import repository.db.DbRepository;
import repository.db.impl.RepositoryDbGeneric;

public abstract class AbstractGenericOperation {

    protected final DbRepository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

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

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception {
        repository.connect();
    }

    private void commit() throws Exception {
        repository.commit();
    }

    private void rollback() throws Exception {
        repository.rollback();
    }

    private void disconnect() throws Exception {
        repository.disconnect();
    }

}
