import domen.*;
import operacije.Receiver;
import repository.db.DbConnectionFactory;
import repository.db.impl.RepositoryDbGeneric;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


        System.out.println("Hello world!");

//        Connection connection = DbConnectionFactory.getInstance().getConnection();
// FILM add, edit, delete, getAll
// SALA add, edit, delete, getAll
// KORISNIK add, edit, delete, getAll
// OCENA add, edit, delete, getAll
// PROJEKCIJA add, edit, delete, getAll



        RepositoryDbGeneric db = new RepositoryDbGeneric();
        Korisnik korisnik = new Korisnik();
        korisnik.setId(1);
        Sala sala = new Sala();
        sala.setBrojSale(1);
        Film film = new Film();
        film.setId(5);
        film.setTrajanje(90);
//
        Projekcija projekcija1 = new Projekcija();
        projekcija1.setSala(sala);
        projekcija1.setFilm(film);
        projekcija1.setPocetakProjekcije(new Date(1,2, 3, 4, 0));
        projekcija1.setVrstaProjekcije("5D");
        projekcija1.setId(2);

        Projekcija projekcija2 = new Projekcija();
        projekcija2.setFilm(film);
        projekcija2.setPocetakProjekcije(new Date(1,2,3,2,58));

        System.out.println(jelSePreklapaju(projekcija1,projekcija2));

//
//        Karta karta = new Karta();
//        karta.setProjekcija(projekcija);
//        karta.setKorisnik(korisnik);
//        karta.setRed(6);
//        karta.setMesto(10);
//        karta.setTipKarte("AAA");
//        karta.setId(29);
//
//        db.add(karta);
//        System.out.println(db.getById(film));
//        db.commit();
    }

    private static Date pocetak(Projekcija projekcija) {
        return projekcija.getPocetakProjekcije();
    }

    private static Date kraj(Projekcija projekcija) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(projekcija.getPocetakProjekcije());
        calendar.add(Calendar.MINUTE, projekcija.getFilm().getTrajanje());
        Date end = calendar.getTime();
        return end;
    }

    private static boolean jelUIntervalu(Date date, Projekcija projekcija) {
        return date.after(pocetak(projekcija)) && date.before(kraj(projekcija));
    }

    private static boolean jelSePreklapaju(Projekcija p1, Projekcija p2) {
        Date p1StartTime = new Date(pocetak(p1).getTime());
        Date p1EndTime = new Date(kraj(p2).getTime());

        return jelUIntervalu(p1StartTime, p2) || jelUIntervalu(p1EndTime, p2);
    }
}