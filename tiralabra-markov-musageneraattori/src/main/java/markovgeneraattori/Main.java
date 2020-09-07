/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

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
       /*
        byte[] rytmit = {5,5,5,5, 5,5,5,5, 5,5,5,5, 2, 5,5,5,5,3,3, 5,5,5,5,3,3, 5,5,5,5, 5,5,5,5, 5,5,5,5, 2};
        Trie rytmiTrie = new Trie();
        rytmiTrie.lisaa(rytmit);
        RytmiGeneraattori rg = new RytmiGeneraattori(rytmiTrie);
        try{
            byte[] rytmisekvenssi = rg.muodostaSekvenssi(6, (byte)5, 3, 4);
            System.out.println("rytmisekvenssin pituus " + rytmisekvenssi.length);
            System.out.println("rytmi:");
            for (int i = 0; i < rytmisekvenssi.length; i++) {
                System.out.print(rytmisekvenssi[i] + ":");
            }
        } catch (Exception e){
            System.out.println("virhe");
        }
        */

        //byte[] aanet = {0, 2, 4, 5, 7, 9, 11, 12, 2, 4, 8, 0, 2, 12, 2, 4, 7 , 2, 4, 9, 2,4,9, 2,4,9, 2,4,8,3,4,5,6,7,2,1,4,6,7,8,9,11,4,12,5,7,4,3,9,7,11,0,1,4,5,6,7,8,4,10};
       byte[] aanet = {0,0, 0, 4,2,2,2,5,4,4,2,2,0,4,4,4,4,7,5,2,2,2,2,5,4,0,0,0,4,2,2,2,5,4,4,2,2,0}; //ukko-noa
       //byte[] aanet = {1};
        Trie trie = new Trie();
        trie.lisaa(aanet);
        //byte[] haku = {12,4};
        
        //System.out.println(trie.haku(haku));
        /*
        HashMap<Byte, TrieSolmu> lapset = trie.getLapsetJosAvainLoytyy(haku);
        if(lapset != null) {
            for (Map.Entry<Byte,TrieSolmu> entry : lapset.entrySet()) {
                System.out.println("Key = " + entry.getKey() + 
                                 ", laskuri = " + entry.getValue().getLaskuri()); 
            } 
        }
         */   
        Generaattori gen = new Generaattori(trie);
        byte[] savellys = gen.muodostaSekvenssi(20, (byte)0, 3);
        System.out.println("sävellys:");
        for (int i = 0; i < savellys.length; i++) {
            System.out.print(savellys[i] + ":");
        }
    }
    
}
