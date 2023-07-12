package rs.np.ac.bg.bioskop_server.serialization;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.util.*;
import java.io.*;

import rs.np.ac.bg.bioskop_common.domen.Film;
import rs.np.ac.bg.bioskop_common.domen.Karta;
import rs.np.ac.bg.bioskop_common.domen.Korisnik;
import rs.np.ac.bg.bioskop_common.domen.Ocena;
import rs.np.ac.bg.bioskop_common.domen.Projekcija;
import rs.np.ac.bg.bioskop_server.Controller.Controller;

public class Deserialize {
	
	Gson gson;
	
	public static void main(String[] args) {
		Deserialize s = new Deserialize();
		s.start();

	}

	private void start() {
		gson = new GsonBuilder()
	            .setDateFormat("MMM d, yyyy")
			    .setFieldNamingStrategy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			    .setPrettyPrinting()
			    .create();
		
		System.out.println("Ucitano korisnika: " + deserijalizujKorisnike().size());
		System.out.println(deserijalizujKorisnike());
		
		System.out.println("Ucitano filmova: " + deserijalizujFilmove().size());
		System.out.println(deserijalizujFilmove());
		
		System.out.println("Ucitano projekcija: " + deserijalizujProjekcije().size());
		System.out.println(deserijalizujProjekcije());
		
		System.out.println("Ucitano karata: " + deserijalizujKarte().size());
		System.out.println(deserijalizujKarte());
		
		System.out.println("Ucitano ocena: " + deserijalizujOcene().size());
		System.out.println(deserijalizujOcene());
		
		
		
	}
	private List<Korisnik> deserijalizujKorisnike() {
	    try {
	        FileReader reader = new FileReader("json/korisnik.txt");
	        List<Korisnik> korisnici = gson.fromJson(reader, new TypeToken<List<Korisnik>>() {}.getType());
	        reader.close();
	        return korisnici;
	    } catch (Exception e) {
	        System.out.println("Nemoguce deserijalizovati korisnike");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	private List<Ocena> deserijalizujOcene() {
	    try {
	        FileReader reader = new FileReader("json/ocena.txt");
	        List<Ocena> ocene = gson.fromJson(reader, new TypeToken<List<Ocena>>() {}.getType());
	        reader.close();
	        return ocene;
	    } catch (Exception e) {
	        System.out.println("Nemoguće deserijalizovati ocene");
	        e.printStackTrace();
	        return null;
	    }
	}

	private List<Karta> deserijalizujKarte() {
	    try {
	        FileReader reader = new FileReader("json/karta.txt");
	        List<Karta> karte = gson.fromJson(reader, new TypeToken<List<Karta>>() {}.getType());
	        reader.close();
	        return karte;
	    } catch (Exception e) {
	        System.out.println("Nemoguće deserijalizovati karte");
	        e.printStackTrace();
	        return null;
	    }
	}

	private List<Projekcija> deserijalizujProjekcije() {
	    try {
	        FileReader reader = new FileReader("json/projekcija.txt");
	        List<Projekcija> projekcije = gson.fromJson(reader, new TypeToken<List<Projekcija>>() {}.getType());
	        reader.close();
	        return projekcije;
	    } catch (Exception e) {
	        System.out.println("Nemoguće deserijalizovati projekcije");
	        e.printStackTrace();
	        return null;
	    }
	}
	
	private List<Film> deserijalizujFilmove() {
	    try {
	        FileReader reader = new FileReader("json/film.txt");
	        List<Film> filmovi = gson.fromJson(reader, new TypeToken<List<Film>>() {}.getType());
	        reader.close();
	        return filmovi;
	    } catch (Exception e) {
	        System.out.println("Nemoguće deserijalizovati filmove");
	        e.printStackTrace();
	        return null;
	    }
	}




}
