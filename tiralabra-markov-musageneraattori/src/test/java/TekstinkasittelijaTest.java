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
    public void muunnaTekstistaByteiksiEiRytmiaYksiOktaavialaTest(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c' e' g' e'\n" +
            "}";
        Taulukkolista<Byte> bytet = kasittelija.muunnaKappaleTekstistaByteiksi(t);
        byte[] todellinen = new byte[bytet.koko()];
        byte[] oletus = {0,6,10,6};
        for(int i = 0; i < bytet.koko(); i++) {
            todellinen[i] = bytet.get(i);
        }
        
        assertArrayEquals(oletus, todellinen);
        assertEquals(oletus.length, todellinen.length);
        
    }
    
    @Test
    public void muunnaTekstistaByteiksiSisaltaaRytminYksiOktaavialaTest(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c'4 e'4 g'8 e'8 d'8 d'8\n" +
            "}";
        Taulukkolista<Byte> bytet = kasittelija.muunnaKappaleTekstistaByteiksi(t);
        byte[] todellinen = new byte[bytet.koko()];
        byte[] oletus = {0,6,10,6,3,3};
        for(int i = 0; i < bytet.koko(); i++) {
            todellinen[i] = bytet.get(i);
        }
        
        assertArrayEquals(oletus, todellinen);
        assertEquals(oletus.length, todellinen.length);
        
    }
    
    @Test
    public void muunnaTekstistaByteiksiSisaltaaRytminUseampiOktaavialaTest(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c'4 e''4 g8 e'8 d,,8 d'8\n" +
            "}";
        Taulukkolista<Byte> bytet = kasittelija.muunnaKappaleTekstistaByteiksi(t);
        byte[] todellinen = new byte[bytet.koko()];
        byte[] oletus = {0,23,-7,6,-48,3};
        for(int i = 0; i < bytet.koko(); i++) {
            todellinen[i] = bytet.get(i);
        }
        
        assertArrayEquals(oletus, todellinen);
        assertEquals(oletus.length, todellinen.length);
        
    }
    
    
    
    @Test
    public void haeRytmitAnnetustaMerkkijonosta(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c'4 e''4 g8 e'8 d,,8 d'8\n" +
            "}";
        kasittelija.muunnaKappaleTekstistaByteiksi(t);
        Taulukkolista<Integer> rytmit = kasittelija.getRytmi();
        int[] r = new int[rytmit.koko()];
        for (int i = 0; i < rytmit.koko(); i++) {
            r[i] = rytmit.get(i);
        }
        int[] oletus = {4,4,8,8,8,8};
        assertArrayEquals(oletus, r);
        assertEquals(6, r.length);
        
    }
    
    @Test
    public void haeRytmitAnnetustaMerkkijonosta2(){
        String t = "\\version \"2.20.0\"\n" +
            "{\n" +
            "  c'4 e'' g8 e'16 d,, d'\n" +
            "}";
        kasittelija.muunnaKappaleTekstistaByteiksi(t);
        Taulukkolista<Integer> rytmit = kasittelija.getRytmi();
        int[] r = new int[rytmit.koko()];
        for (int i = 0; i < rytmit.koko(); i++) {
            r[i] = rytmit.get(i);
        }
        int[] oletus = {4,4,8,16,16,16};
        assertArrayEquals(oletus, r);
        assertEquals(6, r.length);
        
    }
    
    @Test
    public void muunnaByteistaTekstiksiBachTestYksiOktaaviala() {
        byte[] bytet = {0,1,3,4,6,0};
        String t = kasittelija.muunnaByteistaTekstiksiBach(bytet);
        String oletus = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key f \\major \\time 3/8 \n"
                + "c'16 cis'16 d'16 dis'16 e'16 c'16 }\n" +
            "\\layout {} \n" +
            " \\midi {\\tempo 8 = 150} \n" +
            "}";
        assertEquals(oletus, t);
    }
    
    @Test
    public void muunnaByteistaTekstiksiBachTestUseampiOktaaviala() {
        byte[] bytet = {0,21,-33,4,38,-1};
        String t = kasittelija.muunnaByteistaTekstiksiBach(bytet);
        String oletus = "\\version \"2.20.0\"\n\\language \"suomi\"\n"
                + "\\score {\n{ \\key f \\major \\time 3/8 \n" +
            "c'16 dis''16 cis,16 dis'16 dis'''16 h16 }\n" +
            "\\layout {} \n" +
            " \\midi {\\tempo 8 = 150} \n" +
            "}";
        assertEquals(oletus, t);
    }
}
