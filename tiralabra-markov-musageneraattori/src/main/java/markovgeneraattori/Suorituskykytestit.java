/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.Arrays;
import java.util.Random;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;

/**
 *
 * @author tgtuuli
 */
public class Suorituskykytestit {
    
    private final int[] koko = {10, 100, 1000, 10000, 100000, 1000000};
    private final int suoritustenMaara = koko.length;
    private final double[] lisayksetTriehen = new double[suoritustenMaara];
    private final double[] hakuTriesta = new double[suoritustenMaara];
    private final Random random;

    public Suorituskykytestit() {
        this.random = new Random();
    }
    
    
    
    public void suorita(int aste) {
        int n = 100;
        for (int suoritus = 0; suoritus < koko.length; suoritus++) {
            System.out.println("suoritus " + koko[suoritus]);
            int maara = koko[suoritus];
            Taulukkolista<Byte> lista = new Taulukkolista<>();
            long[] ajat = new long[n];
            long t;
            Trie trie = new Trie(aste);
            
            for (int j = 0; j < maara; j++) {
                int luku = random.nextInt(256);
                byte b = (byte) (luku -128);
                lista.lisaa(b);
            }
            for (int k = 0; k < n; k++) {
                
                t = System.nanoTime();
                trie.lisaa(lista);
                t = System.nanoTime() - t;
                ajat[k] = t;
            }
            
            Arrays.sort(ajat);
            lisayksetTriehen[suoritus] = ajat[ajat.length / 2] / 1000000.0;
            
            //Testataan hakuavaimella hakeminen
            System.out.println("testataan hakuavaimella hakeminen triestä");
            for (int i = 0; i < n; i++) { 
                t = System.nanoTime();
                for (int j = 0; j < maara; j++) {
                    byte[] avain = new byte[aste];
                    for (int k = 0; k < aste; k++) {
                        int luku = random.nextInt(256);
                        byte b = (byte) (luku -128);
                        avain[k] = b;
                    }
                    
                    trie.getSeuraajat(avain);
                
                }
                    
                    t = System.nanoTime() - t;
                    ajat[i] = t;
            }
                Arrays.sort(ajat);
                hakuTriesta[suoritus] = ajat[ajat.length / 2] / 1000000.0;
                
            
        }
        System.out.println("Tulokset: ");
        System.out.println("lisäys triehen");
        for(int i = 0; i< koko.length; i++) {
            System.out.println(koko[i] + ": " + lisayksetTriehen[i] + "ms");
        }
        
        System.out.println("haku");
        for(int i = 0; i< koko.length; i++) {
            System.out.println(koko[i] + ": " + hakuTriesta[i] + "ms");
        }
       
        
        
        
    }
    
    
}
