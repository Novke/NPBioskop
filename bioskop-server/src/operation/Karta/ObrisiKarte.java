package operation.Karta;

import domen.Karta;
import operation.AbstractGenericOperation;

import java.util.List;

public class ObrisiKarte extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        List<Karta> list = (List<Karta>) param;
        for (Karta karta: list){
            if (karta.getId() == null) throw new Exception("ID Karte je null");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Karta> list = (List<Karta>) param;
        for (Karta karta: list){
            repository.delete(karta);
        }
    }
}
