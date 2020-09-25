/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import kayttoliittyma.Kayttoliittyma;
import java.nio.file.Paths;
import java.util.Scanner;
import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;


/**
 *
 * @author tgtuuli
 */
public class Main {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
       Kayttoliittyma k = new Kayttoliittyma();
       k.kaynnista();
        
    }

    
}
