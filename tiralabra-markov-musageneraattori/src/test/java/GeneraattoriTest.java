/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.tietorakenteet.Trie;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class GeneraattoriTest {
    
    @Test
    public void muodostaaOikeanPituisenSekvenssin() {
        Trie t = new Trie(3);
        byte[] alkiot = {2,2,5,4,7,6,6,1,2,5,6,1,2,3,8,9,6,4,5,3,7,7,5,4,4,3,3,1};
        t.lisaa(alkiot);
        Generaattori g = new Generaattori(t);
        byte[] sekvenssi = g.muodostaSekvenssi(20, (byte)4, 3);
        
        assertEquals(20, sekvenssi.length);
        
    }
    @Test
    public void sekvenssiAlkaaOikeallaAlkiolla() {
        Trie t = new Trie(3);
        byte[] alkiot = {2,2,5,4,7,6,6,1,2,5,6,1,2,3,8,9,6,4,5,3,7,7,5,4,4,3,3,1};
        t.lisaa(alkiot);
        Generaattori g = new Generaattori(t);
        byte[] sekvenssi = g.muodostaSekvenssi(20, (byte)4, 3);
        
        assertEquals(4, sekvenssi[0]);
        
    }
    
}
