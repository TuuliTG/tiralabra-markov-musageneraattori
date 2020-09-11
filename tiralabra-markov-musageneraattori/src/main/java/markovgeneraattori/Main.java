/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author tgtuuli
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        Taulukkolista<TrieSolmu> lista = new Taulukkolista<>();
        lista.lisaa(new TrieSolmu((byte)2));
        
        for(int i = 0; i < lista.koko(); i++) {
            System.out.println(lista.get(i).getTunnus());
        }
        
    }
    
}
