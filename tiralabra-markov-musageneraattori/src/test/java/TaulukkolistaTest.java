/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.tietorakenteet.Taulukkolista;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class TaulukkolistaTest {
    
    private Taulukkolista lista;
    
    @Before
    public void alustus() {
        this.lista = new Taulukkolista();
    }
    
    @Test
    public void lisaaListaan15alkiotaTest(){
        for (int i = 0; i < 15; i++) {
            lista.lisaa(i);
        }
        assertEquals(15, lista.koko());
    }
    
    @Test(expected = NullPointerException.class)
    public void virheellinenIndeksiAiheuttaaVirheen() {
        lista.get(-1);
    }
    
    @Test(expected = NullPointerException.class)
    public void virheellinenIndeksiAiheuttaaVirheen2() {
        for (int i = 0; i < 15; i++) {
            lista.lisaa(i);
        }
        lista.get(20);
    }
    
}
