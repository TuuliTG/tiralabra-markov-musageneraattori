/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import org.junit.Test;
import markovgeneraattori.generaattori.RytmiArpoja;
import markovgeneraattori.tietorakenteet.Taulukkolista;

/**
 *
 * @author tgtuuli
 */
public class RytmiArpojaTest {
    
    @Test
    public void rytmiArpojaTest() {
        RytmiArpoja r = new RytmiArpoja();
        Taulukkolista<Byte> rytmi = r.haeRytmi(4);
        
        assertTrue(!rytmi.onTyhja());
        assertTrue(rytmi.koko() > 8);
        
        
    } 
}
