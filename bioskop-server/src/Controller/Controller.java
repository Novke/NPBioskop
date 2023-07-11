package Controller;

import domen.*;
import operation.AbstractGenericOperation;
import operation.Film.KreirajFilm;
import operation.Film.NadjiFilmove;
import operation.Film.ObrisiFilm;
import operation.Film.VratiFIlm;
import operation.Karta.*;
import operation.Korisnik.*;
import operation.Ocena.VratiOcene;
import operation.Ocena.VratiOceneFilma;
import operation.Ocena.ZapamtiOcenu;
import operation.Projekcija.KreiranjeProjekcije;
import operation.Projekcija.NadjiProjekcije;
import operation.Projekcija.ObrisiProjekciju;
import operation.Projekcija.VratiProjekciju;
import operation.Sala.VratiListuSala;
import repository.Repository;
import repository.db.impl.RepositoryDbGeneric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {

    private final Repository repository;
    private static Controller instance;

    private Controller(){
        repository = new RepositoryDbGeneric();
    }
    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();

        return instance;
    }

    public List<Film> getAllFilm(Film film) throws Exception {
        AbstractGenericOperation op = new NadjiFilmove();
        op.excecute(film);
        return ((NadjiFilmove)op).getList();
    }

    public Film getFilm(Film film) throws Exception {
        AbstractGenericOperation op = new VratiFIlm();
        op.excecute(film);
        return ((VratiFIlm)op).getFilm();
    }

    public Projekcija getProjekcija(Projekcija projekcija) throws Exception {
        AbstractGenericOperation op = new VratiProjekciju();
        op.excecute(projekcija);
        return ((VratiProjekciju)op).getProjekcija();
    }

    public List<Projekcija> getAllProjekcija(Projekcija projekcija) throws Exception {
        AbstractGenericOperation op = new NadjiProjekcije();
        op.excecute(projekcija);
        return ((NadjiProjekcije)op).getList();
    }

    public void createProjekcija(Projekcija projekcija) throws Exception {
        AbstractGenericOperation op = new KreiranjeProjekcije();
        op.excecute(projekcija);
    }

    public List<Sala> getAllSala() throws Exception{
        AbstractGenericOperation op = new VratiListuSala();
        op.excecute(new Sala());
        return ((VratiListuSala)op).getList();
    }

    public void createKarte(List<Karta> karta) throws Exception {
        AbstractGenericOperation op = new KreiranjeKarte();
        op.excecute(karta);
    }

    public void deleteKarte(List<Karta> karte) throws Exception {
        AbstractGenericOperation op = new ObrisiKarte();
        op.excecute(karte);
    }

    public void editKarta(Karta karta) throws Exception {
        AbstractGenericOperation op = new IzmenaKarte();
        op.excecute(karta);
    }

    public List<Karta> getAllKarta(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new VratiListuKarata();
        op.excecute(korisnik);
        return ((VratiListuKarata)op).getList();
    }

    public void createKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new KreiranjeKorisnika();
        op.excecute(korisnik);
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new BrisanjeKorisnika();
        op.excecute(korisnik);
    }

    public void editKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new IzmenaKorisnika();
        op.excecute(korisnik);
    }

    public List<Korisnik> getAllKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new NadjiKorisnike();
        op.excecute(korisnik);
        return ((NadjiKorisnike)op).getList();
    }

    public Korisnik getKorisnik(Korisnik korisnik) throws Exception {
        AbstractGenericOperation op = new VratiKorisnika();
        op.excecute(korisnik);
        return ((VratiKorisnika)op).getKorisnik();
    }

    public boolean login(String un, String pw) {
        return un.equalsIgnoreCase("admin") && pw.equalsIgnoreCase("admin");
    }

    public void deleteFilm(Film film) throws Exception {
        AbstractGenericOperation op = new ObrisiFilm();
        op.excecute(film);
    }

    public void deleteProjekcija(Projekcija projekcija) throws Exception{
        AbstractGenericOperation op = new ObrisiProjekciju();
        op.excecute(projekcija);
    }

    public List<Ocena> getAllOcena(Film param) throws Exception {
        AbstractGenericOperation op = new VratiOceneFilma();
        op.excecute(param);
        List<Ocena> list = ((VratiOceneFilma)op).getList();


        for (Ocena o : list){
            o.setFilm(param);
            o.setKorisnik((Korisnik) repository.getById(o.getKorisnik()));
        }

        return list;
    }

    public void createOcena(Ocena param) throws Exception{
        AbstractGenericOperation op = new ZapamtiOcenu();
        op.excecute(param);
    }

    public List<Ocena> getAllOcena() throws Exception{
        AbstractGenericOperation op = new VratiOcene();
        op.excecute(new Ocena());
        List<Ocena> list = ((VratiOcene)op).getList();
        for (Ocena o : list){
            o.setFilm((Film)repository.getById(o.getFilm()));
            o.setKorisnik((Korisnik) repository.getById(o.getKorisnik()));
        }
        return list;
    }

    public void createFilm(Film film) throws Exception {
        AbstractGenericOperation op = new KreirajFilm();
        op.excecute(film);
    }

    public List<Karta> getAllKarta(Projekcija projekcija) throws Exception {
        AbstractGenericOperation op = new VratiRezervacije();
        op.excecute(projekcija);
        return ((VratiRezervacije)op).getList();
    }

    public void editFilm(Film film) {
    }

    public void deleteKarte(Korisnik korisnik) throws Exception {

        List<Karta> allKartas = getAllKarta(korisnik);
        List<Karta> kartas = new ArrayList<>();

        for (Karta karta : allKartas){
            if (karta.getProjekcija().getPocetakProjekcije().after(new Date()))
                kartas.add(karta);
        }
        deleteKarte(kartas);
    }
}
