package thread;

import Controller.Controller;
import domen.*;
import operacije.Receiver;
import operacije.Request;
import operacije.Response;
import operacije.Sender;

import java.util.ArrayList;
import java.util.List;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessClientsRequests extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {


        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            HashMap<Integer, String> map = (HashMap<Integer, String>) request.getArgument();
                            boolean successful = Controller.getInstance().login(map.get(1), map.get(2));
                            response.setResult(successful);
                            break;
                        case VRATI_KORISNIKE:
                            List<Korisnik> korisnikList = Controller.getInstance().getAllKorisnik(new Korisnik());

                            List<GenericEntity> entityList = new ArrayList<>();
                            for (Korisnik k: korisnikList){
                                entityList.add(k);
                            }
                            response.setResult(entityList);
                            break;
                        case VRATI_FILMOVE:
                            List<Film> filmList = Controller.getInstance().getAllFilm(new Film());

                            entityList = new ArrayList<>();
                            for (Film f: filmList){
                                entityList.add(f);
                            }
                            response.setResult(entityList);
                            break;
                        case VRATI_PROJEKCIJE:
                            List<Projekcija> projekcijaList = Controller.getInstance().getAllProjekcija(new Projekcija());

                            entityList = new ArrayList<>();
                            for (Projekcija p: projekcijaList){
                                entityList.add(p);
                            }
                            response.setResult(entityList);
                            break;

                        case OBRISI_FILM:
                            Film film = (Film) request.getArgument();
                            Controller.getInstance().deleteFilm(film);
                            break;

                        case OBRISI_KORISNIKA:
                            Korisnik korisnik = (Korisnik) request.getArgument();
                            Controller.getInstance().deleteKorisnik(korisnik);
                            break;

                        case OBRISI_PROJEKCIJU:
                            Projekcija projekcija = (Projekcija) request.getArgument();
                            Controller.getInstance().deleteProjekcija(projekcija);
                            break;

                        case KREIRAJ_KORISNIKA:
                            korisnik = (Korisnik) request.getArgument();
                            Controller.getInstance().createKorisnik(korisnik);
                            break;

                        case IZMENI_KORISNIKA:
                            korisnik = (Korisnik) request.getArgument();
                            Controller.getInstance().editKorisnik(korisnik);
                            break;

                        case VRATI_KARTE:
                            korisnik = (Korisnik) request.getArgument();
                            List<Karta> kartaList = Controller.getInstance().getAllKarta(korisnik);
                            response.setResult(kartaList);
                            break;

                        case VRATI_OCENE_FILMA:
                            film = (Film) request.getArgument();
                            List<Ocena> ocenaList = Controller.getInstance().getAllOcena(film);
                            response.setResult(ocenaList);
                            break;

                        case OCENI_FILM:
                            Ocena ocena = (Ocena) request.getArgument();
                            Controller.getInstance().createOcena(ocena);
                            break;

                        case VRATI_OCENE:
                            ocenaList = Controller.getInstance().getAllOcena();
                            response.setResult(ocenaList);
                            break;

                        case KREIRAJ_PROJEKCIJU:
                            projekcija = (Projekcija) request.getArgument();
                            Controller.getInstance().createProjekcija(projekcija);
                            break;

                        case KREIRAJ_FILM:
                            film = (Film) request.getArgument();
                            Controller.getInstance().createFilm(film);
                            break;

                        case IZMENI_FILM:
                            film = (Film) request.getArgument();
                            Controller.getInstance().editFilm(film);
                            break;

                        case VRATI_REZERVACIJE:
                            projekcija = (Projekcija) request.getArgument();
                            kartaList = Controller.getInstance().getAllKarta(projekcija);
                            response.setResult(kartaList);
                            break;

                        case REZERVISI_KARTE:
                            kartaList = (List<Karta>) request.getArgument();
                            Controller.getInstance().createKarte(kartaList);
                            break;

                        case OBRISI_REZERVACIJE:
                            korisnik = (Korisnik) request.getArgument();
                            Controller.getInstance().deleteKarte(korisnik);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }

}
