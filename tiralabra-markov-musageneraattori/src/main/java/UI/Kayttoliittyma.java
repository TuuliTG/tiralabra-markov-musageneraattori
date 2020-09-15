/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Scanner;

/**
 *
 * @author tgtuuli
 */
public class Kayttoliittyma {
    
    private final Scanner lukija;

    public Kayttoliittyma() {
        this.lukija = new Scanner(System.in);
    }
    
    public void kaynnista() {
        String valinta = "";
        while (!valinta.equals("")) {
            System.out.println("luo uusi kappale (u) tai aja suorituskykytestit (t)");
            valinta = lukija.nextLine();
        } 
        
        if (valinta.equals("u")) {
            
        }
        
        
    }
    
    
}
