/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.tietorakenteet.Pino;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class PinoTest {
    
    @Test
    public void pinoTest1() {
        Pino<Integer> pino = new Pino();
        assertEquals(0, pino.getKoko());
    }
    
    @Test
    public void pinoTest2() {
        Pino<Integer> pino = new Pino();
        pino.lisaa(3);
        int paallimmainen = pino.katsoPaallimmainen();
        assertEquals(1, pino.getKoko());
        assertEquals(3, paallimmainen);
    }
    
    @Test
    public void pinoTest3() {
        Pino<Integer> pino = new Pino();
        pino.lisaa(3);
        pino.lisaa(5);
        pino.lisaa(7);
        int paallimmainen = pino.otaPaallimmainen();
        assertEquals(7, paallimmainen);
        assertEquals(2, pino.getKoko());
    }
    
    @Test
    public void pinoTest4() {
        Pino<Integer> pino = new Pino();
        for (int i = 0; i < 12; i++) {
            pino.lisaa(i);
        }
        int paallimmainen = pino.katsoPaallimmainen();
        assertEquals(11, paallimmainen);
        assertEquals(12, pino.getKoko());
    }
    
    @Test(expected = RuntimeException.class)
    public void pinoTest5() {
        Pino pino = new Pino();
        pino.katsoPaallimmainen();
    }
    
    @Test(expected = RuntimeException.class)
    public void pinoTest6() {
        Pino pino = new Pino();
        pino.otaPaallimmainen();
    }
}
