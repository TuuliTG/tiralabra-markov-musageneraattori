/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import markovgeneraattori.Suorituskykytestit;
import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;


/**
 *
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
        
        System.out.println("Valitse opetusmateriaali:");
        System.out.println("[1] Bach viulusonaatti g-molli osa Presto");
        System.out.println("[2] Lastenlaulupotpuri");
        //TÄHÄN TARVITAAN VIELÄ VALIDOINTI
        String valinta = lukija.nextLine();
        if (valinta.equals("L")) {
            return;
        }
        int kappale = Integer.parseInt(valinta);
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
        if (kappale == 1) {
            generoituKappale = generoiMusiikkiaBachinTyyliin(aste, pituus);
            polku += "/bach.ly";
        } else if (kappale == 2) {
            generoituKappale = generoiMusiikkiaLastenlaulunTyyliin(aste, pituus);
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
    
    private void ajaSuorituskykytestit() {
        System.out.println("Millä asteella suoritetaan? [1-8]"); // TEE TÄHÄN VIELÄ SYÖTTEEN VALIDOINTI
        int aste = Integer.parseInt(lukija.nextLine());
        Suorituskykytestit testit = new Suorituskykytestit();
        testit.suorita(aste);
        testit.testaaGenerointi(aste);
        
    }
    /**
     * 
     * @param aste
     * @param pituus
     * @return 
     */
    private String generoiMusiikkiaBachinTyyliin(int aste, int pituus) {
        String opetusmateriaali = this.lueTiedosto("Bach.ly");
        
        Generaattori gen = new Generaattori(aste, 3, 8, pituus);
        gen.lueOpetusmateriaali(opetusmateriaali);
        pituus = pituus * 6; 
        byte[] bytet = gen.muodostaSekvenssi(pituus, gen.getSavelTrie(), (byte) 27, aste);
        Tekstinkasittelija kasittelija = new Tekstinkasittelija();
        String tiedosto = kasittelija.muunnaByteistaTekstiksiBach(bytet);
        return tiedosto;
        
    }
    /**
     * 
     * @param aste
     * @param pituus
     * @return 
     */
    private String generoiMusiikkiaLastenlaulunTyyliin(int aste, int pituus) {
        String opetusmateriaali = this.lueTiedosto("Ukko-Nooa.ly");
        //Lisätään triehen kaikki opetusmateriaalit
        Generaattori gen = new Generaattori(aste, 4, 4, pituus);
        gen.lueOpetusmateriaali(opetusmateriaali);
        opetusmateriaali = this.lueTiedosto("morkolahtipiiriin.ly");
        gen.lueOpetusmateriaali(opetusmateriaali);
        opetusmateriaali = this.lueTiedosto("nallepuh.ly");
        gen.lueOpetusmateriaali(opetusmateriaali);
        opetusmateriaali = this.lueTiedosto("pieniveturi.ly");
        gen.lueOpetusmateriaali(opetusmateriaali);
        opetusmateriaali = this.lueTiedosto("vihreavalo.ly");
        gen.lueOpetusmateriaali(opetusmateriaali);
        
        Taulukkolista<Byte> rytmi = gen.generoiRytmi();
        byte[] bytet = gen.muodostaSekvenssi(rytmi.koko(), gen.getSavelTrie(), (byte) 0, aste);
        Tekstinkasittelija kasittelija = new Tekstinkasittelija();
        String tiedosto = kasittelija.muunnaByteistaTekstiksiLastenLaulu(bytet, rytmi);
        return tiedosto;
    }
    
    private String lueTiedosto(String tiedostonNimi) {
        String tiedostonSisalto = "";
        try (Scanner tiedostonlukija = 
                new Scanner(Paths.get("opetusmateriaali/" + tiedostonNimi))) {
                    
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
    
    
}
