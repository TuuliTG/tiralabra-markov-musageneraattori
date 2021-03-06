/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.generaattori;

import markovgeneraattori.tietorakenteet.Taulukkolista;
import markovgeneraattori.tietorakenteet.Trie;

/**
 * Generaattori tuottaa uuden sekvenssin annettuun opetusmateriaaliin perustuen.
 * 
 * @author tgtuuli
 */
public class Generaattori {
    
    private final Trie savelTrie;
    private final Tekstinkasittelija tekstinkasittelija;
    private final int aste;
    private Taulukkolista<Byte> rytmi;
    private final RytmiGeneraattori rytmigeneraattori;
    private final SekvenssiApuri apuri;

    public Generaattori(int aste, int tahtilajiOsoittaja,
            int tahtilajiNimittaja, int pituusTahteina) {
        
        this.aste = aste;
        this.savelTrie = new Trie(aste);
        this.tekstinkasittelija = new Tekstinkasittelija();
        this.rytmigeneraattori =  new RytmiGeneraattori(aste, 
                tahtilajiOsoittaja, tahtilajiNimittaja, pituusTahteina);
        this.rytmi = new Taulukkolista<>();
        this.apuri = new SekvenssiApuri();
        
    }
    /**
     * Käsittelee tekstinkäsittelijällä opetusmateriaalin ja lisää sen triehen.
     * Ottaa samalla talteen rytmin ja lisää sen rytmiTriehen.
     * @param kerataanRytmi boolean, kertoo, kerätäänkö opetusmateriaalista rytmi triehen vai ei.
     * @param opetusmateriaali .ly-tiedostomuodossa
     */
    public void lueOpetusmateriaali(String opetusmateriaali, boolean kerataanRytmi) {
        Taulukkolista<Byte> aanet = 
                tekstinkasittelija.muunnaKappaleTekstistaByteiksi(opetusmateriaali);
        if (kerataanRytmi) {
            rytmi = tekstinkasittelija.getRytmi();
            rytmigeneraattori.lisaaTriehenRytmit(rytmi);
        }
        
        savelTrie.lisaa(aanet);
         
    }
    
    /**
     * Generoi opetusmateriaalista poimitun rytmin perusteella uuden rytmin käyttäen trietä.
     * 
     * @return Taulukkolista muodostettu rytmi
     */
    public Taulukkolista<Byte> generoiRytmi() {
        rytmigeneraattori.generoiRytmi(rytmi.get(0));
        return rytmigeneraattori.getMuodostettuRytmi();
    }
    
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param trie jonka perusteella sekvenssi luodaan
     * @param alkusavel mistä kappale halutaan aloittaa
     * @param asteluku mitä Markovin ketjun astetta käytetään
     * @return taulukko eli valmis sekvenssi
     */
    public byte[] muodostaSekvenssi(int pituus, Trie trie, byte alkusavel, int asteluku){
        return apuri.muodostaSekvenssi(pituus, trie, alkusavel, asteluku);
    }

    public Trie getSavelTrie() {
        return savelTrie;
    }
    
    public Taulukkolista<Byte> getRytmi() {
        return this.rytmi;
    }

}
