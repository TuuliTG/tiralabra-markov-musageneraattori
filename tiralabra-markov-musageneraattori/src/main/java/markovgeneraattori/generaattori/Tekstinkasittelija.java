/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Pino;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class Tekstinkasittelija {
    
    private String[] aanetMerkkijonoina;
    private Taulukkolista<Byte> rytmi;
    private byte viimeisinRytmi;
    RytminMuuntaja rytminkasittelija = new RytminMuuntaja();

    public Tekstinkasittelija() {
        this.aanetMerkkijonoina = new String[] {"c", "cis", "des",  "d", "dis", "es", "e", "f", 
            "fis", "ges", "g", "gis", "as", "a", "ais", "b", "h"};
        this.rytmi = new Taulukkolista<>();
        this.viimeisinRytmi = 0;
    }
    
    
    /**
     * Pilkkoo opetusmateriaalin ja ottaa siitä talteen rytmin ja sävelen
     * @param teksti
     * @return palauttaa sävelet byte-muodossa listana
     */
    public Taulukkolista<Byte> muunnaKappaleTekstistaByteiksi(String teksti) {
        String[] tiedostonOsat = teksti.trim().split("[{}]");
        String kappale = tiedostonOsat[1]; 
        String[] palat = kappale.trim().split("\\s+");
        Taulukkolista<Byte> bytet = new Taulukkolista<>();
        for (int i = 0; i < palat.length; i++) {
            byte aani = muunnaStringistaByteksi(palat[i]);
            //System.out.println(palat[i]);
            if (aani != -128) {
                bytet.lisaa(aani);
            }
            
        }
        return bytet;
    }
    
    /**
     * Luo generoidusta byte-taulukosta lilypond-ohjelmalle sopivan tekstin. 
     * Tässä opetusmateriaalina on Bach ja ulostulo on myös siihen sopiva
     * @param bytet
     * @return String
     */
    public String muunnaByteistaTekstiksiBach(byte[] bytet) {
        String s = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key f \\major \\time 3/8 \n";
        for (int i = 0; i < bytet.length; i++) {
            byte b = bytet[i];
            s = s + this.muunnaBytestaMerkiksi(b) + "16 ";
        }
        
        s = s + "}\n\\layout {} \n \\midi {\\tempo 8 = 150} \n}";
        
        return s;
        
    }
    /**
     * 
     * @param bytet
     * @param rytmi
     * @return 
     */
    public String muunnaByteistaTekstiksiLastenLaulu(byte[] bytet, Taulukkolista<Byte> rytmi) {
        String s = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key c \\major \\time 4/4 \n";
        for (int i = 0; i < bytet.length; i++) {
            byte b = bytet[i];
            byte r = rytmi.get(i);
            
            if(r < 0) {
                r *= -1;
                s = s + "r" + rytminkasittelija.muunnaBytestaMerkiksi(r) + " ";
            } else {
                s = s + this.muunnaBytestaMerkiksi(b) + rytminkasittelija.muunnaBytestaMerkiksi(r) + " ";
            }
            
        }
        s += "c'1";
        
        s = s + "}\n\\layout {} \n \\midi {\\tempo 4 = 70} \n}";
        
        return s;
    }
    
    
    /**
     * Muuntaa nuotin numerosta merkkijonomuotoon.
     * @param tunnus numeron tunnus
     * @return String
     */
    private String muunnaBytestaMerkiksi(byte tunnus) {
        int oktaaviala = 0;
        //System.out.println("tunnus "  + tunnus);
        if (tunnus > 16) {
            while (tunnus > 16) {
                tunnus -= 17;
                oktaaviala++;
                
            }
            String vastaus = "" + this.aanetMerkkijonoina[tunnus];
            for (int i = 0; i <= oktaaviala; i++) {
                vastaus += "'";
            }
            return vastaus;
        } else if (tunnus >= 0) {
            return "" + this.aanetMerkkijonoina[tunnus] + "'";
        } else if (tunnus < 0 && tunnus > -17) {
            return "" + this.aanetMerkkijonoina[tunnus + 17];
        } else {
            while (tunnus < 0) {
                tunnus += 17;
                oktaaviala++;
                
            }
            String vastaus = "" + this.aanetMerkkijonoina[tunnus];
            for (int i = 1; i < oktaaviala; i++) {
                vastaus += ",";
            }
            return vastaus;
        }
        
    }
    
    /**
     * Muuntaa merkkijonon (esim. d''16) numeroksi (byte). 
     * @param savel merkkijono, joka kuvaa muunnettavaa säveltä
     * @return byte 
     */
    public byte muunnaStringistaByteksi(String savel) { //muunna vielä privatiksi
        int oktaaviala = -1;
        boolean onTauko = false;
        boolean onPisteellinen = false;
        if (viimeisinRytmi < 0) {
            viimeisinRytmi *= -1;
        }
        
        Pino<Character> pino = new Pino<>();
        for (int i = 0; i < savel.length(); i++) {
            pino.lisaa(savel.charAt(i));
        }
        Pino<Character> rytmiPino = new Pino<>();
        String savelNimi = "";
        
        while (pino.getKoko() > 0) {
            char merkki = pino.otaAlimmainen();
            if(merkki == 'r') {
                onTauko = true;
            } else if(!onValimerkki(merkki) && !onNumero(merkki)) {
                savelNimi += merkki;
            } else if (onValimerkki(merkki)) {
                if (merkki == '\'') {
                    oktaaviala++;
                } else if (merkki == ',') {
                    oktaaviala--;
                }
            } else if (onNumero(merkki)) {
                rytmiPino.lisaa(merkki);
            } else if (merkki == '.') {
                onPisteellinen = true;
            }
            //System.out.println("merkki " + merkki);
            //System.out.println(this.onNumero(merkki));
            
        }
        //System.out.println("sävelnimi = " + savelNimi);
        byte savelBytena = -128;
        for (int i = 0; i < 17; i++) {
            if (savelNimi.equals(aanetMerkkijonoina[i])) {
                savelBytena = (byte) (i + oktaaviala * 17);
            }
        }
        if(rytmiPino.getKoko() == 0) {
            this.rytmi.lisaa(viimeisinRytmi);
            return savelBytena; 
        }
        String rytmiMerkkijonona = "";
        
        while (rytmiPino.getKoko()>0) {
            rytmiMerkkijonona += rytmiPino.otaAlimmainen();
        }
        
        byte rytmiBytena = Byte.parseByte(rytmiMerkkijonona);
        if(onPisteellinen) {
            rytmiBytena = (byte) (rytmiBytena * 1.5);
        }
        if (onTauko) {
            rytmiBytena *= -1;
        }
        viimeisinRytmi = rytmiBytena;
        
        this.rytmi.lisaa(viimeisinRytmi);
        
        return savelBytena;
        
    }

    public Taulukkolista<Byte> getRytmi() {
        return rytmi;
    }


    private boolean onNumero(char merkki) {
        if (merkki == '1' || merkki == '2' || merkki == '3' || merkki == '4' ||
                merkki == '5' || merkki == '6' || merkki == '7' || merkki == '8' ||
                merkki == '9' || merkki == '0') {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean onValimerkki(char merkki) {
        if (merkki == '\'' || merkki == ',' || merkki == '.') {
            return true;
        } else {
            return false;
        }
    }
     
}
