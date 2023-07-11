package operation.Sala;

import domen.Sala;
import operation.AbstractGenericOperation;

import java.util.List;

public class VratiListuSala extends AbstractGenericOperation {

    List<Sala> list;
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        list = repository.getAll(new Sala());
    }

    public List<Sala> getList() {
        return list;
    }
}
