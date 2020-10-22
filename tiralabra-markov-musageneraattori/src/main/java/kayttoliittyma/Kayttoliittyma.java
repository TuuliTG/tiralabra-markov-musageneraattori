/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import markovgeneraattori.Suorituskykytestit;
import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.generaattori.RytmiArpoja;
import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 * Luo käyttäjälle tekstipohjaisen käyttöliittymän.
 * @author tgtuuli
 */
public class Kayttoliittyma {
    
    private final Scanner lukija;
    
    
    public Kayttoliittyma() {
        this.lukija = new Scanner(System.in);
    }
    
    public Kayttoliittyma(Scanner lukija) {
        this.lukija = lukija;
    }

    public void kaynnista() {
       
        valikko1();
        
        String valinta = lukija.nextLine();
        valinta = valinta.trim();
        if (valinta.equals("1")) {
            valikko2();
            
        } else if (valinta.equals("2")) {
            ajaSuorituskykytestit();
        } else if (valinta.equals("L")) {
            return;
        }
    }
    
    private void valikko1() {
        System.out.println("TERVETULOA MUSAGENERAATTORIIN!");
        System.out.println("--------------------------------");
        System.out.println("Valitse seuraavista:");
        System.out.println("1. Generoi musiikkia");
        System.out.println("2. Aja suorituskykytestit");
        System.out.println("[L] lopeta");
    }
    
    private void valikko2() {
        int materiaali = 0;
        String valinta = "";
        
        while (materiaali != 1 && materiaali != 2) {
            System.out.println("Valitse opetusmateriaali:");
            System.out.println("[1] Bach viulusonaatti g-molli osa Presto");
            System.out.println("[2] Lastenlaulupotpuri");
            valinta = lukija.nextLine();
            if (valinta.equals("L")) {
                return;
            }
            materiaali = Integer.parseInt(valinta);
        }
        
        int aste = 0;
        while (aste < 1 || aste > 6) {
            System.out.println("Millä asteella haluat käyttää generaattoria? (1 - 6) ");
            valinta = lukija.nextLine();
            if (valinta.equals("L")) {
                return;
            }
            aste = Integer.parseInt(valinta);
        }
        
        int pituus = 0;
        while (pituus < 1) {
            System.out.println("Generoidun kappaleen pituus tahteina:");
            valinta = lukija.nextLine();
            if (valinta.equals("L")) {
                return;
            }
            pituus = Integer.parseInt(valinta);
        }
        int rytmingenerointitapa = 0;
        
        if (materiaali == 2) {
            System.out.println("Haluatko generoida rytmin Markovin ketjulla [1]"
                    + " vai arpoa tahdit satunnaisessa järjestyksessä [2]?");
            valinta = lukija.nextLine();
            rytmingenerointitapa = Integer.parseInt(valinta);
        }
        
        System.out.println("Mihin kansioon talletetaan?");
        String polku = lukija.nextLine();
        if (polku.equals("L")) {
            return;
        }
        if (polku.isEmpty()) {
            System.out.println("Et syöttänyt polkua."); 
            System.out.println("----------------------");
            valikko2();
        }
        String generoituKappale = "";
        if (materiaali == 1) {
            generoituKappale = generoiMusiikkiaBachinTyyliin(aste, pituus);
            polku += "/bach.ly";
        } else if (materiaali == 2) {
            generoituKappale = generoiMusiikkiaLastenlaulunTyyliin(aste,
                    pituus, rytmingenerointitapa);
            polku += "/laulu.ly";
        } else {
            System.out.println("numero ei kelpaa");
        }
        
        File file = new File(polku);

        try {
           FileWriter kirjoittaja = new FileWriter(file);
           kirjoittaja.write(generoituKappale);
           kirjoittaja.close();
        } catch (Exception e) {
            System.out.println("Virhe " + e.getMessage());
            valikko2();
        }
    }
    
    /**
     * Ajaa suorituskykytestit käyttäjän valitsemalla Markovin ketjun asteella (1-4).
     */
    private void ajaSuorituskykytestit() {
        int aste = 0;
        while (aste < 1 || aste > 4) {
            System.out.println("Millä asteella suoritetaan? [1-4]");
            String valinta = lukija.nextLine();
            
            if (valinta.equals("L")) {
                return;
            }
            aste = Integer.parseInt(valinta);
            
        }
        
        Suorituskykytestit testit = new Suorituskykytestit();
        testit.suorita(aste);
        testit.testaaGenerointi(aste);
        testit.testaaGenerointiArvotullaRytmilla(aste);
        
    }
    /**
     * Generoi alkuperäiseen Bachin viulusonaattiin perustuvan musiikin.
     * <p>Tahtilaji 3/8, Sävellaji g-molli (F-duurin etumerkintä)</p>
     * @param aste Markovin ketjun asteluku
     * @param pituus tahtien lukumäärä
     * @return String .ly-muotoinen teksti
     */
    private String generoiMusiikkiaBachinTyyliin(int aste, int pituus) {
        String opetusmateriaali = this.lueTiedosto("Bach.ly");
        
        Generaattori gen = new Generaattori(aste, 3, 8, pituus);
        gen.lueOpetusmateriaali(opetusmateriaali, false);
        pituus = pituus * 6; 
        byte[] bytet = gen.muodostaSekvenssi(pituus, gen.getSavelTrie(), (byte) 27, aste);
        Tekstinkasittelija kasittelija = new Tekstinkasittelija();
        String tiedosto = kasittelija.muunnaByteistaTekstiksiBach(bytet);
        return tiedosto;
        
    }
    /**
     * Generoi lastenlaulun tyylistä musiikkia.
     * <p>Tahtilaji 4/4, sävellaji C-duuri.</p>
     * @param aste Markovin ketjun asteluku
     * @param pituus tahtien lukumäärä
     * @return String .ly-muotoinen teksti
     */
    private String generoiMusiikkiaLastenlaulunTyyliin(int aste,
            int pituus, int rytmingenerointitapa) {
        String opetusmateriaali = this.lueTiedosto("Ukko-Nooa.ly");
        //Lisätään triehen kaikki opetusmateriaalit
        Generaattori gen = new Generaattori(aste, 4, 4, pituus);
        gen.lueOpetusmateriaali(opetusmateriaali, true);
        opetusmateriaali = this.lueTiedosto("morkolahtipiiriin.ly");
        gen.lueOpetusmateriaali(opetusmateriaali, true);
        opetusmateriaali = this.lueTiedosto("nallepuh.ly");
        gen.lueOpetusmateriaali(opetusmateriaali, true);
        opetusmateriaali = this.lueTiedosto("pieniveturi.ly");
        gen.lueOpetusmateriaali(opetusmateriaali, true);
        opetusmateriaali = this.lueTiedosto("vihreavalo.ly");
        gen.lueOpetusmateriaali(opetusmateriaali, true);
        
        Taulukkolista<Byte> rytmi = new Taulukkolista<>();
        
        if (rytmingenerointitapa == 1) {
            rytmi = gen.generoiRytmi();
        } else if (rytmingenerointitapa == 2) {
            RytmiArpoja arpoja = new RytmiArpoja();
            rytmi = arpoja.haeRytmi(pituus);
        }
        
        byte[] bytet = gen.muodostaSekvenssi(rytmi.koko(), gen.getSavelTrie(), (byte) 0, aste);
        Tekstinkasittelija kasittelija = new Tekstinkasittelija();
        String tiedosto = kasittelija.muunnaByteistaTekstiksiLastenLaulu(bytet, rytmi);
        return tiedosto;
    }
    
    private String lueTiedosto2(String tiedostonNimi) {
        String tiedostonSisalto = "";
        try (Scanner tiedostonlukija = 
                new Scanner(Paths.get(tiedostonNimi))) {
                    
            while (tiedostonlukija.hasNextLine()) {
                String rivi = tiedostonlukija.nextLine();
                if (!rivi.isEmpty()) {
                    tiedostonSisalto += rivi;
                }
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        return tiedostonSisalto;
    }
    
    // works everywhere, IDEA, unit test and JAR file.
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
    
    private String lueTiedosto(String tiedostonNimi){
        InputStream is = this.getFileFromResourceAsStream(tiedostonNimi);
        String materiaali = "";
        try (InputStreamReader streamReader =
                    new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                materiaali += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return materiaali;
    }
}
