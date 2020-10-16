/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import org.junit.Test;
import markovgeneraattori.tietorakenteet.TrieSolmu;

/**
 *
 * @author tgtuuli
 */
public class TrieSolmuTest {
    
    @Test
    public void trieSolmuTyhjaKonstruktori() {
        TrieSolmu s = new TrieSolmu();
        assertEquals(s.getLaskuri(), 1);
        assertEquals(s.getLapset().length, 256);
        
    }
    
    @Test 
    public void trieSolmuTunnusLoytyy() {
        TrieSolmu s = new TrieSolmu((byte) 1);
        assertEquals(s.getTunnus(), 1);
    }
    
    @Test 
    public void lisaaLaskuriinToimii() {
        TrieSolmu s = new TrieSolmu((byte) 1);
        s.lisaaLaskuriin();
        assertEquals(s.getLaskuri(), 2);
    }
    
    @Test
    public void seuraajatListaLoytyy() {
        TrieSolmu s = new TrieSolmu((byte) 1);
        assertTrue(s.getSeuraajat().onTyhja());
    }
      
}
