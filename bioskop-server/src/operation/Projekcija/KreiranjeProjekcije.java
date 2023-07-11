package operation.Projekcija;

import domen.Projekcija;
import operation.AbstractGenericOperation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KreiranjeProjekcije extends AbstractGenericOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null) throw new Exception("Parametar je null");

        Projekcija projekcija = (Projekcija) param;
        if (projekcija.getFilm() == null) throw new Exception("Film je null");
        if (projekcija.getPocetakProjekcije() == null) throw new Exception("Pocetak projekcije je null");
        if (projekcija.getPocetakProjekcije().before(projekcija.getFilm().getPocetakPrikazivanja())) throw new Exception("Projekcija zakazana pre pocetka prikazivanja filma");
        if (projekcija.getPocetakProjekcije().before(new Date())) throw new Exception("Nemoguce zakazati projekciju u proslosti");
        if (projekcija.getPocetakProjekcije().getHours()<8) throw new Exception("Bioskop radi od 08 do 24h");
        if (projekcija.getSala() == null) throw new Exception("Sala je null");
        if (projekcija.getSala().getBrojSale()<1 || projekcija.getSala().getBrojSale()>4) throw new Exception("Sala moze biti 1,2,3 ili 4");
        if (projekcija.getVrstaProjekcije() == null || projekcija.getVrstaProjekcije().isBlank()) throw new Exception("Vrsta projekcije je prazna");

        List<Projekcija> projekcijaList = repository.getAll(new Projekcija());
        for (Projekcija p: projekcijaList){
            if (projekcija.getSala().getId() != p.getSala().getId()) continue;

            if (jelSePreklapaju(projekcija, p)) {
                throw new Exception("Vec postoji projekcija u sali " + p.getSala().getId() +"\n" +
                        "u terminu od " + vreme(pocetak(p)) + " do " + vreme(kraj(p)));
            }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Projekcija)param);
    }

    private Date pocetak(Projekcija projekcija){
        return projekcija.getPocetakProjekcije();
    }

    private Date kraj(Projekcija projekcija){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(projekcija.getPocetakProjekcije());
        calendar.add(Calendar.MINUTE, projekcija.getFilm().getTrajanje());
        Date end = calendar.getTime();
        return end;
    }

    private boolean jelUIntervalu(Date date, Projekcija projekcija){
        return date.after(pocetak(projekcija)) && date.before(kraj(projekcija));
    }

    private boolean jelSePreklapaju(Projekcija p1, Projekcija p2){
        Date p1StartTime = new Date(pocetak(p1).getTime());
        Date p1EndTime = new Date(kraj(p2).getTime());

        return jelUIntervalu(p1StartTime, p2) || jelUIntervalu(p1EndTime, p2);
    }

    private String vreme(Date date){
        return date.getHours() + ":" + date.getMinutes();
    }
}
