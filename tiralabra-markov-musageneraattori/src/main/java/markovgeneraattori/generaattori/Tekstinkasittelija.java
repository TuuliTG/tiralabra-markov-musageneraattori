/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Pino;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 * Käsittelee tekstejä.
 * Kerää tiedon opetusmateriaalista ohjelman käyttöön.
 * Muuntaa generoidun materiaalin lilypond-ohjelman luettavaan muotoon.
 * @author tgtuuli
 */
public class Tekstinkasittelija {
    
    private String[] aanetMerkkijonoina;
    private Taulukkolista<Byte> rytmi;
    private byte viimeisinRytmi;
    private RytminMuuntaja rytminMuuntaja; 

    public Tekstinkasittelija() {
        this.aanetMerkkijonoina = new String[] {"c", "cis", "des",  "d", "dis", "es", "e", "f", 
            "fis", "ges", "g", "gis", "as", "a", "ais", "b", "h"};
        this.rytmi = new Taulukkolista<>();
        this.viimeisinRytmi = 0;
        this.rytminMuuntaja = new RytminMuuntaja();
    }
    
    
    /**
     * Pilkkoo opetusmateriaalin ja ottaa siitä talteen rytmin ja sävelen.
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
            if (aani != -128) {
                bytet.lisaa(aani);
            }
        }
        
        return bytet;
        
    }
    
    /**
     * Luo generoidusta byte-taulukosta (sävelet) lilypond-ohjelman luettavan tekstin. 
     * Tässä opetusmateriaalina on Bach ja ulostulo on myös siihen sopiva.
     * 
     * @param bytet sävelet
     * @return String
     */
    public String muunnaByteistaTekstiksiBach(byte[] bytet) {
        String s = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key f \\major \\time 3/8 \n";
        for (int i = 0; i < bytet.length; i++) {
            byte b = bytet[i];
            s = s + this.muunnaBytestaMerkiksi(b) + "16 ";
        }
        
        s = s + "g'4. }\n\\layout {} \n \\midi {\\tempo 8 = 150} \n}";
        
        return s;
        
    }
    /**
     * Luo generoidusta byte-taulukosta (sävelet) lilypond-ohjelman luettavan tekstin. 
     * Tässä opetusmateriaalina on Bach ja ulostulo on myös siihen sopiva.
     * @param bytet
     * @param rytmi
     * @return 
     */
    public String muunnaByteistaTekstiksiLastenLaulu(byte[] bytet, Taulukkolista<Byte> rytmi) {
        String s = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key c \\major \\time 4/4 \n";
        int taukoja = 0;
        for (int i = 0; i < rytmi.koko(); i++) {
            
            byte r = rytmi.get(i);
            
            if (r < 0) {
                taukoja++;
                r *= -1;
                s = s + "r" + rytminMuuntaja.muunnaBytestaMerkiksi(r) + " ";
            } else {
                byte b = bytet[i - taukoja];
                s = s + this.muunnaBytestaMerkiksi(b) +
                        rytminMuuntaja.muunnaBytestaMerkiksi(r) + " ";
            }
            
        }
        s += "c'1";
        
        s = s + "}\n\\layout {} \n \\midi {\\tempo 4 = 70} \n}";
        
        return s;
    }
    
    public Taulukkolista<Byte> getRytmi() {
        return rytmi;
    }
    
    /**
     * Muuntaa nuotin numerosta merkkijonomuotoon.
     * @param tunnus säveltä kuvaava numero byte-muodossa
     * @return String
     */
    private String muunnaBytestaMerkiksi(byte tunnus) {
        int oktaaviala = 0;
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
    private byte muunnaStringistaByteksi(String savel) {
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
            if (merkki == 'r') {
                onTauko = true;
            } else if (!onValimerkki(merkki) && !onNumero(merkki)) {
                savelNimi += merkki;
            } else if (onValimerkki(merkki)) {
                if (merkki == '\'') {
                    oktaaviala++;
                } else if (merkki == ',') {
                    oktaaviala--;
                } else if (merkki == '.') {
                    onPisteellinen = true;
                }
            } else if (onNumero(merkki)) {
                rytmiPino.lisaa(merkki);
            } 
        }
        
        byte savelBytena = -128;
        for (int i = 0; i < 17; i++) {
            if (savelNimi.equals(aanetMerkkijonoina[i])) {
                savelBytena = (byte) (i + oktaaviala * 17);
            }
        }
        if (rytmiPino.getKoko() == 0) {
            this.rytmi.lisaa(viimeisinRytmi);
            return savelBytena; 
        }
        String rytmiMerkkijonona = "";
        
        while (rytmiPino.getKoko() > 0) {
            rytmiMerkkijonona += rytmiPino.otaAlimmainen();
        }
        
        byte rytmiBytena = Byte.parseByte(rytmiMerkkijonona);
        if (onPisteellinen) {
            rytmiBytena = (byte) (rytmiBytena * 1.5);
        }
        if (onTauko) {
            rytmiBytena *= -1;
        }
        viimeisinRytmi = rytmiBytena;
        
        this.rytmi.lisaa(viimeisinRytmi);
        
        return savelBytena;
        
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
