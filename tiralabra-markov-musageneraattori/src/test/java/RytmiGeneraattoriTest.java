/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import markovgeneraattori.generaattori.RytmiGeneraattori;
import markovgeneraattori.generaattori.RytminMuuntaja;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class RytmiGeneraattoriTest {
    
    private Taulukkolista<Byte> rytmit;
    private RytmiGeneraattori rytmiGeneraattori;

    
    @Before
    public void alusta() {
        Byte[] rytmi = {4, 4, 4, 2, 2, 1, 4, 8, 8, 8, 8, 4, 4, 6, 4, 2, 2, 1};
        rytmit = new Taulukkolista<>();
        rytmit.lisaaMonta(rytmi);
        rytmiGeneraattori = new RytmiGeneraattori(3, 4, 4, 12);
        rytmiGeneraattori.lisaaTriehenRytmit(rytmit);
    }
    
    
    @Test
    public void rytmiAlkaaOikeallaRytmilla() {
        rytmiGeneraattori.generoiRytmi((byte) 4);
        Taulukkolista<Byte> muodostettuRytmi = rytmiGeneraattori.getMuodostettuRytmi();
        assertEquals((byte) 4, (byte) muodostettuRytmi.get(0));
    }
    
    @Test
    public void oikeaMaaraIskujaTahdeissa() {
        boolean toimii =  true;
        RytminMuuntaja r =  new RytminMuuntaja();
        rytmiGeneraattori.generoiRytmi((byte) 4);
        Taulukkolista<Byte> muodostettuRytmi = rytmiGeneraattori.getMuodostettuRytmi();
        double tilaaTahdissa = 4.0;
        for (int i = 0; i < muodostettuRytmi.koko(); i++) {
            double kesto = r.getKestoDoublena(muodostettuRytmi.get(i));
            tilaaTahdissa -= kesto;
            if (tilaaTahdissa == 0) {
                tilaaTahdissa = 4;
            } else if (tilaaTahdissa < 0) {
                toimii = false;
            }
        }
        
        assertTrue(toimii);
        assertEquals((int) 4, (int) tilaaTahdissa);
    }
    
    @Test
    public void oikeaMaaraIskujaTahdeissa2() {
        boolean toimii =  true;
        Byte[] rytmi2 = {3, 3, 3, 3, 3, 3};
        Taulukkolista<Byte> rytmitaulukko = new Taulukkolista<>();
        rytmitaulukko.lisaaMonta(rytmi2);
        RytminMuuntaja r =  new RytminMuuntaja();
        RytmiGeneraattori gen = new RytmiGeneraattori(3, 4, 4, 4);
        gen.lisaaTriehenRytmit(rytmitaulukko);
        gen.generoiRytmi((byte) 3);
        Taulukkolista<Byte> muodostettuRytmi = gen.getMuodostettuRytmi();
        double tilaaTahdissa = 4.0;
        for (int i = 0; i < muodostettuRytmi.koko(); i++) {
            double kesto = r.getKestoDoublena(muodostettuRytmi.get(i));
            tilaaTahdissa -= kesto;
            if (tilaaTahdissa == 0) {
                tilaaTahdissa = 4;
            } else if (tilaaTahdissa < 0) {
                toimii = false;
            }
        }
        
        assertTrue(toimii);
        assertEquals((int) 4, (int) tilaaTahdissa);
    }
    
   
}
