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
    
    private final int tahtilajiOsoittaja;
    private final int tahtilajiNimittaja;
    private final Taulukkolista<Byte> muodostettuRytmi;
    private final int aste;
    private final Trie trie;
    private final int pituusTahteina;
    private final RytminMuuntaja rytmi;
    private final SekvenssiApuri apuri;

    public RytmiGeneraattori(int aste, int tahtilajiOsoittaja, 
            int tahtilajiNimittaja, int pituusTahteina) {
        this.aste = aste;
        this.tahtilajiNimittaja = tahtilajiNimittaja;
        this.tahtilajiOsoittaja = tahtilajiOsoittaja;
        this.trie = new Trie(aste);
        this.muodostettuRytmi = new Taulukkolista<>();
        this.pituusTahteina = pituusTahteina;
        this.rytmi = new RytminMuuntaja();
        this.apuri = new SekvenssiApuri();
        
    }
    /**
     * 
     * @param rytmit 
     */
    public void lisaaTriehenRytmit(Taulukkolista<Byte> rytmit) {
        this.trie.lisaa(rytmit);
    }
    
    /**
     * @param alku asettaa ensimmäisen rytmin
     */
    public void generoiRytmi(byte alku) {
        
        byte[] generoituRytmi = apuri.muodostaSekvenssi(pituusTahteina * 10, trie, alku, aste);
        
        //generoituRytmi toimii pohjana uuden kappaleen luomiselle
        //Jos tahtiin tulee liikaa iskuja, täytyy ohjelman valita tilalle joku muu rytmi
        int rytmejaGeneroitu = 0;
        int tahtienLkm = 0;
        double taysiTahti = rytmi.getKestoDoublena((byte) tahtilajiNimittaja) * tahtilajiOsoittaja;
        double tilaaTahdissa = taysiTahti;
        for (int i = 0; i < generoituRytmi.length; i++) {
            if (tahtienLkm == pituusTahteina) { //Tahteja tarpeeksi valmiina
                break;
            }
            double seuraavaRytmi = rytmi.getKestoDoublena(generoituRytmi[i]); 
            
            
            //Tarkastetaan, onko tahdissa tilaa generaattorin ehdottamalle rytmille
            if (seuraavaRytmi <= tilaaTahdissa) {
                this.muodostettuRytmi.lisaa(generoituRytmi[i]);
                rytmejaGeneroitu++;
                tilaaTahdissa -= seuraavaRytmi;
                if (tilaaTahdissa == 0) { //Tahti tuli täyteen ja aloitetaan uusi tahti
                    tahtienLkm++;
                    tilaaTahdissa = taysiTahti;
                }
            } else { //Ei ollut tilaa, joten haetaan rytmi, joka sopii tahtiin
                byte seuraava = haeSopivaRytmi(muodostettuRytmi.get(rytmejaGeneroitu - 1),
                        tilaaTahdissa);
                muodostettuRytmi.lisaa(seuraava);
                tilaaTahdissa -= rytmi.getKestoDoublena(seuraava);
                rytmejaGeneroitu++;
                if (tilaaTahdissa == 0) {
                    tahtienLkm++;
                    tilaaTahdissa = taysiTahti;
                }
            }
        }
      
    }

    public Trie getTrie() {
        return trie;
    }

    public Taulukkolista<Byte> getMuodostettuRytmi() {
        return muodostettuRytmi;
    }
    
    /**
     * Hakee tahtiin sopivan rytmin ensisijaisesti trien perusteella.
     * Jos triestä ei löydy sopivaa, metodi valitsee sen pituisen rytmin, joka tahtiin sopii
     * @param edeltaja jonka perusteella haetaan triestä mahdolliset seuraavat rytmit
     * @param maksimikesto kertoo paljonko tahdissa on vielä tilaa
     * @return byte rytmi
     */
    private byte haeSopivaRytmi(byte edeltaja, double maksimikesto) {
        byte[] haku = new byte[1];
        haku[0] = edeltaja;
        Taulukkolista<TrieSolmu> solmut = trie.getSeuraajat(haku);
        if (solmut == null) {
            return rytmi.getRytmiBytena(maksimikesto); 
        }
        for (int i = 0; i < solmut.koko(); i++) {
            double kesto = rytmi.getKestoDoublena(solmut.get(i).getTunnus());
            if (kesto < maksimikesto) {
                return solmut.get(i).getTunnus();
            }
        }
        return rytmi.getRytmiBytena(maksimikesto);
    }
    
}
