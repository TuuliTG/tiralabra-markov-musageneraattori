/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class Tekstinkasittelija {
    
    private String[] aanetMerkkijonoina;
    private Taulukkolista<Integer> rytmi;
    private int viimeisinRytmi;

    public Tekstinkasittelija() {
        this.aanetMerkkijonoina = new String[] {"c", "cis", "des",  "d", "dis", "es", "e", "f", 
            "fis", "ges", "g", "gis", "as", "a", "ais", "b", "h"};
        this.rytmi = new Taulukkolista<>();
        this.viimeisinRytmi = 0;
    }
    
    
    /**
     * 
     * @param teksti
     * @return 
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
    private byte muunnaStringistaByteksi(String savel) {
        int oktaaviala = -1;
        if (viimeisinRytmi < 0) {
            viimeisinRytmi *= -1;
        }
        String[] palat = savel.split("[\\'\\,12483\\.]+"); //RATKAISE TÄMÄ - PINOLLA ??? 
        for (int i = 0; i < savel.length(); i++) {
            if (savel.charAt(i) == '\'') {
                oktaaviala++;
            } else if (savel.charAt(i) == ',') {
                oktaaviala--;
            } else if (savel.charAt(i) == '1') {
                if (i < savel.length() - 1){
                    if (savel.charAt(i + 1) == '6') {
                        viimeisinRytmi = 16;
                    }
                } else {
                    viimeisinRytmi = 1;
                }
            } else if (savel.charAt(i) == '2') {
                viimeisinRytmi = 2;
            } else if (savel.charAt(i) == '4') {
                viimeisinRytmi = 4;
            } else if (savel.charAt(i) == '8') {
                viimeisinRytmi = 8;
            } else if (savel.charAt(i) == '3') {
                viimeisinRytmi = 32;
            } else if (savel.charAt(i) == '.') {
                viimeisinRytmi = (int) (viimeisinRytmi * 1.5);
            }
        }
        if (palat[0].equals("r")) {
            viimeisinRytmi *= -1;
        }
        this.rytmi.lisaa(viimeisinRytmi);
         
        for (int i = 0; i < 17; i++) {
            if (palat[0].equals(aanetMerkkijonoina[i])) {
                return (byte) (i + oktaaviala * 17);
            }
        }
        return -128;
    }

    public Taulukkolista<Integer> getRytmi() {
        return rytmi;
    }
    
    
    
}
