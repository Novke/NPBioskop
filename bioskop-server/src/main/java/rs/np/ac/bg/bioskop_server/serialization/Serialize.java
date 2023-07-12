package rs.np.ac.bg.bioskop_server.serialization;


import java.util.List;
import java.util.ArrayList;
import domen.*;
import rs.np.ac.bg.bioskop_server.Controller.Controller;
import java.io.*;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Serialize {

	Gson gson;
	Controller controller;
	
	public static void main(String[] args) {
		Serialize s = new Serialize();
		s.start();

	}

	private void start() {
		gson = new GsonBuilder()
			    .setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			    .setPrettyPrinting()
			    .create();
		
		controller = Controller.getInstance();
		
		serijalizujKorisnike();
		serijalizujFilmove();
		serijalizujProjekcije();
		serijalizujKarte();
		serijalizujOcene();
		
		
	}

	private void serijalizujKorisnike() {
		try {
			List<Korisnik> korisnici = controller.getAllKorisnik(null);
			String json = gson.toJson(korisnici);
			//
			FileWriter writer = new FileWriter("json/korisnik.txt");
	        writer.write(json);
	        writer.close();
	        System.out.println("USPESNO!");
	        System.out.println(json);
		} catch (Exception e) {
			System.out.println("Nemoguce serijalizovati korisnike");
			e.printStackTrace();
		}
		
	}
	
	private void serijalizujFilmove() {
	    try {
	        List<Film> filmovi = controller.getAllFilm(null);
	        String json = gson.toJson(filmovi);
	        
	        FileWriter writer = new FileWriter("json/film.txt");
	        writer.write(json);
	        writer.close();
	        
	        System.out.println("Filmovi su uspešno serijalizovani!");
	        System.out.println(json);
	    } catch (Exception e) {
	        System.out.println("Nemoguće serijalizovati filmove");
	        e.printStackTrace();
	    }
	}
	
	private void serijalizujProjekcije() {
	    try {
	        List<Projekcija> projekcije = controller.getAllProjekcija(null);
	        String json = gson.toJson(projekcije);
	        
	        FileWriter writer = new FileWriter("json/projekcija.txt");
	        writer.write(json);
	        writer.close();
	        
	        System.out.println("Projekcije su uspešno serijalizovane!");
	        System.out.println(json);
	    } catch (Exception e) {
	        System.out.println("Nemoguće serijalizovati projekcije");
	        e.printStackTrace();
	    }
	}
	
	private void serijalizujKarte() {
	    try {
	    	List<Korisnik> korisnici = controller.getAllKorisnik(null);
	        List<Karta> karte = new ArrayList<>();
	    	for (Korisnik k : korisnici) {
	    		List<Karta> karteKorisnika = controller.getAllKarta(k);
	    		karte.addAll(karteKorisnika);
	    	}
	        String json = gson.toJson(karte);
	        
	        FileWriter writer = new FileWriter("json/karta.txt");
	        writer.write(json);
	        writer.close();
	        
	        System.out.println("Karte su uspešno serijalizovane!");
	        System.out.println(json);
	    } catch (Exception e) {
	        System.out.println("Nemoguće serijalizovati karte");
	        e.printStackTrace();
	    }
	}
	
	private void serijalizujOcene() {
	    try {
	        List<Ocena> ocene = controller.getAllOcena();
	        String json = gson.toJson(ocene);
	        
	        FileWriter writer = new FileWriter("json/ocena.txt");
	        writer.write(json);
	        writer.close();
	        
	        System.out.println("Ocene su uspešno serijalizovane!");
	        System.out.println(json);
	    } catch (Exception e) {
	        System.out.println("Nemoguće serijalizovati ocene");
	        e.printStackTrace();
	    }
	}





}
