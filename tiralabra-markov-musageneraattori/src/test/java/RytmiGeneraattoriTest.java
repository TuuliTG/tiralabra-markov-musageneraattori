/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import markovgeneraattori.generaattori.RytmiGeneraattori;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tgtuuli
 */
public class RytmiGeneraattoriTest {
    
    private Taulukkolista<Byte> rytmit;
    private RytmiGeneraattori rytmiGeneraattori;

    
    @Before
    public void alusta() {
        Byte[] rytmi = {4,4,4,2,2,1,4,8,8,8,8,4,4,6,4,2,2,1};
        rytmit = new Taulukkolista<>();
        rytmit.lisaaMonta(rytmi);
        rytmiGeneraattori = new RytmiGeneraattori(3, 4, 4, 12);
    }
    
    
    @Test
    public void rytmiAlkaaOikeallaRytmilla() {
        rytmiGeneraattori.generoiRytmi((byte) 4);
        Taulukkolista<Byte> muodostettuRytmi = rytmiGeneraattori.getMuodostettuRytmi();
        
        assertEquals((byte) 4, (byte) muodostettuRytmi.get(0));
    }
    
   
}
