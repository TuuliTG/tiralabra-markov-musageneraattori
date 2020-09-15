/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class TekstinkasittelijaTest {
    
    private Tekstinkasittelija kasittelija;
    
    @Before
    public void alustus() {
        this.kasittelija = new Tekstinkasittelija();
        
    }
    
    @Test
    public void muunnaTekstistaByteiksiTest(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c' e' g' e'\n" +
            "}";
        Taulukkolista<Byte> bytet = kasittelija.muunnaKappaleTekstistaByteiksi(t);
        byte[] todellinen = new byte[bytet.koko()];
        byte[] oletus = {0,4,7,4};
        for(int i = 0; i < bytet.koko(); i++) {
            todellinen[i] = bytet.get(i);
        }
        
        assertArrayEquals(oletus, todellinen);
        assertEquals(oletus.length, todellinen.length);
        
    }
    
    @Test
    public void muunnaByteistaTekstiksiTest() {
        byte[] bytet = {0,1,2,3,4,0};
        String t = kasittelija.muunnaByteistaTekstiksi(bytet);
        String oletus = "\\version \"2.20.0\"\n" +
            "\\language \"suomi\"\n" +
            "\\score {\n" +
            "{c' cis' d' dis' e' c' }\n" +
            "\\layout {} \n" +
            " \\midi {\\tempo 4 = 90} \n" +
            "}";
        assertEquals(oletus, t);
    }
}
