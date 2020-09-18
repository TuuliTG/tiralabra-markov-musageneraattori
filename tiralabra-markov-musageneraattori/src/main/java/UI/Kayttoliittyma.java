/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;


/**
 *
 * @author tgtuuli
 */
public class Kayttoliittyma {
    
    private Scanner lukija;
    
    
    public Kayttoliittyma() {
        this.lukija = new Scanner(System.in);
    }
    
    public void kaynnista(String[] args) {
       
        tervetuloa();
        
        String valinta = lukija.nextLine();
        valinta = valinta.trim();
        if (valinta.equals("1")) {
            valikko2();
            
        } else if (valinta.equals("2")) {
            ajaSuorituskykytestit();
        }
    }
    
    private void valikko2() {
        System.out.println("Valitse opetusmateriaali:");
        System.out.println("1. Bach viulusonaatti g-molli osa Presto");
        System.out.println("2. Lastenlaulupotpuri");
        int kappale = lukija.nextInt();
        
        System.out.println("Millä asteella haluat käyttää generaattoria? (1 - 6) ");
        int aste = lukija.nextInt();
        System.out.println("Generoidun kappaleen pituus tahteina:");
        int pituus = lukija.nextInt();
        System.out.println("Mihin kansioon talletetaan?");
        String polku = lukija.next();
        if(polku.isEmpty()) {
            System.out.println("ei polkua"); 
            return;
        }
        String generoituKappale = generoiMusiikkia(kappale, aste, pituus);
        
        polku += "/bach.ly";
        File file = new File(polku);
        
        try {
           FileWriter writer = new FileWriter(file);
           writer.write(generoituKappale);
           writer.close();
        } catch (Exception e) {
            System.out.println("Virhe" + e.getMessage());
        }
    }
    
    private void tervetuloa() {
        System.out.println("TERVETULOA MUSAGENERAATTORIIN!");
        System.out.println("--------------------------------");
        System.out.println("Valitse seuraavista:");
        System.out.println("1. Generoi musiikkia");
        System.out.println("2. Aja suorituskykytestit");
    }
    
    private void ajaSuorituskykytestit() {
        System.out.println("testit");
    }
    
    private String generoiMusiikkia(int kappale, int aste, int pituus) {
        String opetusmateriaali = "";
        try (Scanner tiedostonlukija = 
                new Scanner(Paths.get("opetusmateriaali/Bach.ly"))) {
                    
            while (tiedostonlukija.hasNextLine()) {
                String rivi = tiedostonlukija.nextLine();
                if(!rivi.isEmpty()) {
                    opetusmateriaali += rivi;
                }
            }
            
        
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        pituus = pituus*6;
        Generaattori gen = new Generaattori(aste);
        gen.lueOpetusmateriaali(opetusmateriaali);
        byte[] bytet= gen.muodostaSekvenssi(pituus, (byte) 27);
        Tekstinkasittelija kasittelija = new Tekstinkasittelija();
        String tiedosto = kasittelija.muunnaByteistaTekstiksiBach(bytet);
        return tiedosto;
        
    }
}
