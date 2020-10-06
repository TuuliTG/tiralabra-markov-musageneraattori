/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.Arrays;
import java.util.Random;
import markovgeneraattori.generaattori.Generaattori;
import markovgeneraattori.generaattori.Tekstinkasittelija;
import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;

/**
 *
 * @author tgtuuli
 */
public class Suorituskykytestit {
    
    private final int[] koko = {10, 100, 1000, 10000, 100000};
    private final int suoritustenMaara = koko.length;
    private final double[] lisayksetTriehen = new double[suoritustenMaara];
    private final double[] hakuTriesta = new double[suoritustenMaara];
    private final double[] generointi = new double[suoritustenMaara];
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
                byte b = (byte) (luku - 128);
                lista.lisaa(b);
            }
            
            for (int k = 0; k < n; k++) {
                
                t = System.nanoTime();
                trie.lisaa(lista);
                t = System.nanoTime() - t;
                ajat[k] = t;
            }
            //Lasketaan mediaani
            Arrays.sort(ajat);
            lisayksetTriehen[suoritus] = ajat[ajat.length / 2] / 1000000.0;
            
            //Testataan hakuavaimella hakeminen
            //System.out.println("testataan hakuavaimella hakeminen triestä");
            for (int i = 0; i < n; i++) { 
                t = System.nanoTime();
                for (int j = 0; j < maara; j++) {
                    byte[] avain = new byte[aste];
                    for (int k = 0; k < aste; k++) {
                        int luku = random.nextInt(256);
                        byte b = (byte) (luku - 128);
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
        System.out.println("Tulokset asteella " + aste);
        System.out.println("lisäys triehen");
        for (int i = 0; i < koko.length; i++) {
            System.out.println(koko[i] + ": " + lisayksetTriehen[i] + "ms");
        }
        
        System.out.println("haku");
        for (int i = 0; i < koko.length; i++) {
            System.out.println(koko[i] + ": " + hakuTriesta[i] + "ms");
        }
       
    }
    
    public void testaaGenerointi(int aste) {
        System.out.println("suoritetaan geneointitestit");
        Generaattori gen = new Generaattori(aste, 4, 4, 100);
        int n = 100;
        for (int suoritus = 0; suoritus < koko.length; suoritus++) {
            System.out.println("suoritus " + koko[suoritus]);
            int maara = koko[suoritus];
            long[] ajat = new long[n];
            long t;
            
            String opetusmateriaali = this.luoTestiopetusmateriaali(maara);
            
            for (int i = 0; i < n; i++) {
                t = System.nanoTime();
                gen.lueOpetusmateriaali(opetusmateriaali);
                Taulukkolista<Byte> rytmi = gen.generoiRytmi();
                byte[] bytet = gen.muodostaSekvenssi(rytmi.koko(), gen.getSavelTrie(), (byte) 13, aste);
                t = System.nanoTime() - t;
                ajat[i] = t;
                
            }
            
            Arrays.sort(ajat);
            generointi[suoritus] = ajat[ajat.length / 2] / 1000000.0;

            for (int i = 0; i < koko.length; i++) {
                System.out.println(koko[i] + ": " + generointi[i] + "ms");
            }   
            
        }
    }
    
    private String luoTestiopetusmateriaali(int koko) {
        
        String[] s = {"a", "b", "c", "d", "e", "f", "g", "h", "cis", "dis"};
        String[] r = {"1", "2", "4", "8", "16", "2.", "4."};
        String[] o = {"", "\'", "\'\'","\'\'\'", ",", ",,", ",,,"};
        
        String opetusmateriali = "\\version \"2.20.0\"\n{ a'4 ";
        for (int i = 0; i < koko; i++) {
            int savel = random.nextInt(10);
            int rytmi = random.nextInt(7);
            int oktaavi = random.nextInt(7);
            opetusmateriali += s[savel];
            opetusmateriali += o[oktaavi];
            opetusmateriali += r[rytmi];
            opetusmateriali += " ";
        }
        
        opetusmateriali += "}";
        
        return opetusmateriali;
    }
    
    
}
