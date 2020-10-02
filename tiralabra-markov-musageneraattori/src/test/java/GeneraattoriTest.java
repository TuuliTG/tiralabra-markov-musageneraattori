/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class GeneraattoriTest {
    private Generaattori g;
    private Trie t;
    private Taulukkolista<Byte> lista;

    public GeneraattoriTest() {
        this.g = new Generaattori(3, 4, 4, 8);
        this.t = new Trie(3);
        this.lista = new Taulukkolista();
        Byte[] alkiot = {2,2,5,4,7,6,6,1,2,5,6,1,2,3,8,9,6,4,5,3,7,7,5,4,4,3,3,1};
        lista.lisaaMonta(alkiot);
        t.lisaa(lista);
    }
    
    @Test
    public void muodostaaOikeanPituisenSekvenssin() {
        
        byte[] sekvenssi = g.muodostaSekvenssi(20, t, (byte) 4, 3);
        
        assertEquals(20, sekvenssi.length);
        
    }
    @Test
    public void sekvenssiAlkaaOikeallaAlkiolla() {
        
        byte[] sekvenssi = g.muodostaSekvenssi(20, t, (byte) 4, 3);
        assertEquals(4, sekvenssi[0]);
        
    }
    
    
}
