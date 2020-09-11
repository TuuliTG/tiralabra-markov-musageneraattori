/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import markovgeneraattori.Taulukkolista;
import markovgeneraattori.Trie;
import markovgeneraattori.TrieSolmu;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tgtuuli
 */
public class TrieTest {
   
    @Test
    public void trienLuontiOnnistuu() {
        Trie t = new Trie(1);
        assertNotNull(t);
    }
    
    @Test
    public void lisaaTriehenYksiAlkio(){
        Trie t = new Trie(1);
        byte[] alkiot = new byte[1];
        alkiot[0] = 1;
        t.lisaa(alkiot);
        assertTrue(t.haku(alkiot));
    }
    
    @Test
    public void lisaaTriehenKaksiAlkiota() {
        Trie t = new Trie(2);
        byte[] alkiot = {1,2};
        t.lisaa(alkiot);
        byte[] haku1 = new byte[1];
        byte[] haku2 = new byte[1];
        haku1[0]=1;
        haku2[0]=2;
        assertTrue(t.haku(haku1));
        assertTrue(t.haku(haku2));
    }
    
    @Test
    public void lisaaTriehen10alkiotaAsteella3(){
        Trie t = new Trie(3);
        byte[] alkiot = {1,2,3,4,5,6,7,8,9,10};
        t.lisaa(alkiot);
        byte[] haku1 = {2,3,4};
        byte[] haku2 = {10};
        
        assertTrue(t.haku(haku1));
        assertTrue(t.haku(haku2));
    }
    
    @Test
    public void hakuEiLoydaJosEiOlemassa() {
        Trie t = new Trie(3);
        byte[] alkiot = {1,2,3,4,5,6,7,8,9,10};
        t.lisaa(alkiot);
        byte[] haku1 = {2,5,4};
        
        assertFalse(t.haku(haku1));
    }
    
    @Test
    public void getSeuraajatLoytaaSeuraajat() {
        Trie t = new Trie(3);
        byte[] alkiot = {1,2,3,4,1,2,5,2,7,2,9};
        t.lisaa(alkiot);
        byte[] haku1 = {1,2};
        Taulukkolista<TrieSolmu> lapset = t.getSeuraajat(haku1);
        byte[] l = new byte[2];
        l[0]=lapset.get(0).getTunnus();
        l[1] = lapset.get(1).getTunnus();
        //oletetaan, että vastauksessa on tunnukset 3 ja 5
        byte[] o = {3,5};
        
        assertArrayEquals(o, l);
    }
    
    @Test
    public void getSeuraajatPalauttaaNullJosHakuavaintaEiLoydy() {
        Trie t = new Trie(3);
        byte[] alkiot = {1,2,3,4,1,2,5,2,7,2,9};
        t.lisaa(alkiot);
        byte[] haku1 = {0,1};
        Taulukkolista<TrieSolmu> lapset = t.getSeuraajat(haku1);
        
        assertNull(lapset);
    }
        
}
