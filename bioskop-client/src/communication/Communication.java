package communication;

import domen.*;
import operacije.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Communication {

    Socket socket;
    Sender sender;
    Receiver receiver;

    private static Communication instance;
    private Communication() throws Exception{
        socket=new Socket("localhost", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }

    public boolean login(HashMap<Integer, String> map) throws Exception {

        Request request = new Request(Operation.LOGIN, map);
        sender.send(request);
        Response response = (Response) receiver.receive();

       if (response.getException()==null){
           return (boolean) response.getResult();
       } else {
           throw response.getException();
       }
    }

    public List<GenericEntity> getAllFilm() throws Exception {
        Request request = new Request(Operation.VRATI_FILMOVE, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()==null){
            return (List<GenericEntity>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<GenericEntity> getAllProjekcija() throws Exception {
        Request request = new Request(Operation.VRATI_PROJEKCIJE, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()==null){
            return (List<GenericEntity>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<GenericEntity> getAllKorisnik() throws Exception{
        Request request = new Request(Operation.VRATI_KORISNIKE, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()==null){
            return (List<GenericEntity>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        Request request = new Request(Operation.OBRISI_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();

    }

    public void deleteProjekcija(Projekcija projekcija) throws Exception {
        Request request = new Request(Operation.OBRISI_PROJEKCIJU, projekcija);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();
    }

    public void deleteFilm(Film film) throws Exception {
        Request request = new Request(Operation.OBRISI_FILM, film);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();

    }

    public void createKorisnik(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.KREIRAJ_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException()!=null) throw response.getException();
    }

    public void editKorisnik(Korisnik korisnik) throws Exception{

        Request request = new Request(Operation.IZMENI_KORISNIKA, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException()!=null) throw response.getException();

    }

    public List<Karta> getAktivneKarte(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.VRATI_KARTE, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException()!=null) throw response.getException();
        List<Karta> list = (List<Karta>) response.getResult();
        List<Karta> vrati = new ArrayList<>();
        for (Karta k: list){
            if (k.getProjekcija().getPocetakProjekcije().after(new Date())){
                vrati.add(k);
            }
        }
        return vrati;
    }

    public List<Karta> getAllKarte(Korisnik korisnik) throws Exception{
        Request request = new Request(Operation.VRATI_KARTE, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException()!=null) throw response.getException();
        List<Karta> list = (List<Karta>) response.getResult();

        return list;
    }

    public List<Ocena> getOcene(Film film) throws Exception{
        Request request = new Request(Operation.VRATI_OCENE_FILMA, film);
        sender.send(request);
        Response response = (Response) receiver.receive();


        if (response.getException()!=null) throw response.getException();
        return (List<Ocena>) response.getResult();

    }

    public void insertOcena(Ocena ocena) throws Exception{
        Request request = new Request(Operation.OCENI_FILM, ocena);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();
    }

    public List<Ocena> getAllOcena() throws Exception {
        Request request = new Request(Operation.VRATI_OCENE, null);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();
        return (List<Ocena>) response.getResult();
    }

    public void createProjekcija(Projekcija projekcija) throws Exception {
        Request request = new Request(Operation.KREIRAJ_PROJEKCIJU, projekcija);
        sender.send(request);
        Response response = (Response) receiver.receive();


        if (response.getException()!=null) throw response.getException();
    }


    public void createFIlm(Film film) throws Exception {
        Request request = new Request(Operation.KREIRAJ_FILM, film);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();
    }

    public void editFilm(Film film) throws Exception{

        Request request = new Request(Operation.IZMENI_FILM, film);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();

    }

    public List<Karta> getAllKarte(Projekcija projekcija) throws Exception{
        Request request = new Request(Operation.VRATI_REZERVACIJE, projekcija);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();

        return (List<Karta>) response.getResult();
    }

    public void bookTickets(List<Karta> sveKarte) throws Exception {

        Request request = new Request(Operation.REZERVISI_KARTE, sveKarte);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();

    }

    public void deleteKarte(Korisnik korisnik) throws Exception {
        Request request = new Request(Operation.OBRISI_REZERVACIJE, korisnik);
        sender.send(request);
        Response response = (Response) receiver.receive();

        if (response.getException()!=null) throw response.getException();
    }
}
