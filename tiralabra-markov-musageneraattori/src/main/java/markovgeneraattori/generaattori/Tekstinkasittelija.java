/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import java.util.ArrayList;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class Tekstinkasittelija {
    
    public Taulukkolista<Byte> muunnaKappaleTekstistaByteiksi(String teksti) {
        String[] tiedostonOsat = teksti.trim().split("[{}]");
        String kappale = tiedostonOsat[1]; 
        String[] palat = kappale.trim().split("\\s+");
        Taulukkolista<Byte> bytet = new Taulukkolista<>();
        for (int i = 0; i < palat.length; i++) {
            byte aani = muunnaStringistaByteksi(palat[i]);
            System.out.println(palat[i]);
            bytet.lisaa(aani);
        }
        return bytet;
    }
    
    public String muunnaByteistaTekstiksi(byte[] bytet) {
        String s = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{";
        for (int i = 0; i < bytet.length; i++) {
            byte b = bytet[i];
            s = s + this.muunnaBytestaMerkiksi(b) + " ";
        }
        
        s = s + "}\n\\layout {} \n \\midi {\\tempo 4 = 90} \n}";
        
        return s;
        
    }
    
    
    
    private String muunnaBytestaMerkiksi(byte tunnus) {
        if (tunnus == 0) return "c'";
        if (tunnus == 1) return "cis'";
        if (tunnus == 2) return "d'";
        if (tunnus == 3) return "dis'";
        if (tunnus == 4) return "e'";
        if (tunnus == 5) return "f'";
        if (tunnus == 6) return "fis'";
        if (tunnus == 7) return "g'";
        if (tunnus == 8) return "gis'";
        if (tunnus == 9) return "a'";
        if (tunnus == 10) return "b'";
        if (tunnus == 11) return "h'";
        if (tunnus == 12) return "c''";
        if (tunnus == 13) return "cis''";
        if (tunnus == 14) return "d''";
        if (tunnus == 15) return "dis''";
        if (tunnus == 16) return "e''";
        if (tunnus == 17) return "f''";
        if (tunnus == 18) return "fis''";
        if (tunnus == 19) return "g''";
        if (tunnus == 20) return "gis''";
        if (tunnus == 21) return "a''";
        if (tunnus == 22) return "b''";
        if (tunnus == 23) return "h''";
        
        else return "Kääntäminen ei onnistunut";
    }
    
    private byte muunnaStringistaByteksi(String savel) {
        if (savel.equals("c'")) return 0;
        if (savel.equals("cis'")) return 1;
        if (savel.equals("d'")) return 2;
        if (savel.equals("dis'")) return 3;
        if (savel.equals("e'")) return 4;
        if (savel.equals("f'")) return 5;
        if (savel.equals("fis'")) return 6;
        if (savel.equals("g'")) return 7;
        if (savel.equals("gis'")) return 8;
        if (savel.equals("a'")) return 9;
        if (savel.equals("b'")) return 10;
        if (savel.equals("h'")) return 11;
        if (savel.equals("c''")) return 12;
        if (savel.equals("cis''")) return 13;
        if (savel.equals("d''")) return 14;
        if (savel.equals("dis''")) return 15;
        if (savel.equals("e''")) return 16;
        if (savel.equals("f''")) return 17;
        if (savel.equals("fis''")) return 18;
        if (savel.equals("g''")) return 19;
        if (savel.equals("gis''")) return 20;
        if (savel.equals("a''")) return 21;
        if (savel.equals("b''")) return 22;
        if (savel.equals("h''")) return 23;
        
        
        else return -128;
    }
    
}
