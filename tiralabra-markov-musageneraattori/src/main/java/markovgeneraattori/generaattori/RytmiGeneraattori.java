/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;
import markovgeneraattori.tietorakenteet.TrieSolmu;

/**
 *
 * @author tgtuuli
 */
public class RytmiGeneraattori {
    
    private int tahtilajiOsoittaja;
    private int tahtilajiNimittaja;
    private Taulukkolista<Byte> muodostettuRytmi;
    private int aste;
    private Trie trie;
    private int pituusTahteina;
    private Rytmi rytmi = new Rytmi();

    public RytmiGeneraattori(int aste, int tahtilajiOsoittaja, int tahtilajiNimittaja, int pituusTahteina) {
        this.aste = aste;
        this.tahtilajiNimittaja = tahtilajiNimittaja;
        this.tahtilajiOsoittaja = tahtilajiOsoittaja;
        trie = new Trie(aste);
        this.muodostettuRytmi = new Taulukkolista<>();
        this.pituusTahteina = pituusTahteina;
        
    }
    
    public void lisaaTriehenRytmit(Taulukkolista<Byte> rytmit) {
        this.trie.lisaa(rytmit);
    }
    
    public void generoiRytmi() {
        
        Generaattori gen = new Generaattori(aste, tahtilajiOsoittaja, tahtilajiNimittaja, pituusTahteina*10);
        byte[] generoituRytmi = gen.muodostaSekvenssi(pituusTahteina*10, trie, (byte) 4);
        int rytmejaGeneroitu = 0;
        int tahtienLkm = 0;
        
        double tilaaTahdissa = rytmi.getKestoDoublena((byte) tahtilajiNimittaja) * tahtilajiOsoittaja;
        for (int i = 0; i < generoituRytmi.length; i++) {
            if(tahtienLkm == pituusTahteina) {
                break;
            }
            double seuraavaRytmi = rytmi.getKestoDoublena(generoituRytmi[i]);
            //System.out.println("tahteja generoitu " + tahtienLkm);
            //System.out.println("tilaa tahdissa " + tilaaTahdissa);
            //System.out.println("seuraava rytmi doublena " + seuraavaRytmi);
            
            if (seuraavaRytmi <= tilaaTahdissa) {
                this.muodostettuRytmi.lisaa(generoituRytmi[i]);
                rytmejaGeneroitu++;
                tilaaTahdissa -= seuraavaRytmi;
                if (tilaaTahdissa == 0) {
                    tahtienLkm++;
                    
                    tilaaTahdissa = rytmi.getKestoDoublena((byte) tahtilajiNimittaja) * tahtilajiOsoittaja;
                }
            } else {
                byte seuraava = haeSopivaRytmi(muodostettuRytmi.get(rytmejaGeneroitu-1), tilaaTahdissa);
                System.out.println("haettiin sopiva rytmi " + seuraava);
                muodostettuRytmi.lisaa(seuraava);
                tilaaTahdissa -= rytmi.getKestoDoublena(seuraava);
                rytmejaGeneroitu++;
                if (tilaaTahdissa == 0) {
                    tahtienLkm++;
                    
                    tilaaTahdissa = rytmi.getKestoDoublena((byte) tahtilajiNimittaja) * tahtilajiOsoittaja;
                }
            }
        }
        System.out.println("tahtilaji: " + tahtilajiOsoittaja + "/" + tahtilajiNimittaja);
        for (int i = 0; i < muodostettuRytmi.koko(); i++) {
            System.out.print(muodostettuRytmi.get(i) + " ");
        }
        System.out.println("");
    }

    public Trie getTrie() {
        return trie;
    }

    public Taulukkolista<Byte> getMuodostettuRytmi() {
        return muodostettuRytmi;
    }
    
    
    private byte haeSopivaRytmi(byte edeltaja, double maksimikesto) {
        byte[] haku = new byte[1];
        haku[0] = edeltaja;
        Taulukkolista<TrieSolmu> solmut = trie.getSeuraajat(haku);
        for (int i = 0; i < solmut.koko(); i++) {
            double kesto = rytmi.getKestoDoublena(solmut.get(i).getTunnus());
            if (kesto < maksimikesto) {
                return solmut.get(i).getTunnus();
            }
        }
        return rytmi.getRytmiBytena(maksimikesto);
    }
    
    
    
    
    
    
    
    
    
}
