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
public class SekvenssiApuri {
    
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param trie jonka perusteella sekvenssi luodaan
     * @param alkusavel mistä kappale halutaan aloittaa
     * @param aste
     * @return taulukko eli valmis sekvenssi
     */
    public byte[] muodostaSekvenssi(int pituus, Trie trie, byte alkusavel, int aste){
        
        //Taulukko, johon sekvenssi tallennetaan
        byte[] taulukko = new byte[pituus];
        
        taulukko[0] = alkusavel;
        
        //Ensimmäiset täytyy hakea erikseen, ennen kuin sekvenssin pituus on asteluvun pituinen
        int indeksi = 0;
        while (indeksi < aste - 1) {
            byte seuraava = haeSeuraava(taulukko, trie, 0, indeksi);
            taulukko[indeksi + 1] = seuraava;
            indeksi++;
        }
        //Tästä eteenpäin generoidaan sekvenssiä asteluvun pituisen hakusanan perusteella
        for (int i = 0; i < pituus - aste; i++) {
            byte seuraava = haeSeuraava(taulukko, trie, i, i + aste - 1);
            taulukko[i + aste] = seuraava;
        }
        return taulukko;
    }
    
    /**
     * Hakee triestä hakuavaimen perusteella mahdolliset seuraajat ja valitsee seuraavan sävelen.
     * @param hakuavain taulukko, jossa on tähän asti luotu sekvenssi
     * @param mista mistä taulukon kohdasta avain alkaa
     * @param mihin mihin taulukon kohtaan avain loppuu
     * @return seuraava sekvenssiin tuleva alkio byte-muodossa
     */
    private byte haeSeuraava(byte[] hakuavain, Trie trie, int mista, int mihin){
        //luodaan hakuavainta varten int-muotoinen taulukko
        byte[] avain = new byte[mihin + 1 - mista];
        System.arraycopy(hakuavain, mista, avain, 0, (mihin + 1 - mista));
        
        
        //haetaan hakuavaimella löytyvät seuraajat
        Taulukkolista<TrieSolmu> lapset = trie.getSeuraajat(avain);
        
        //pienennetään hakua, jos seuraajia ei löydy
        if (lapset == null || lapset.onTyhja()) {
            byte[] uusiHaku = new byte[1];
            uusiHaku[0] = avain[avain.length - 1];
            lapset = trie.getSeuraajat(uusiHaku);
        }
        //Käsittele jos ei vieläkään seuraajia (palautetaan sama kuin haun ensimmäinen)
        if (lapset == null){
            byte palautus = (byte) avain[0];
            return palautus;
        }
        //jos lapsia on vaan yksi, valitaan se suoraan arpomatta
        if (lapset.koko() == 1) {
            return lapset.get(0).getTunnus();
        }
        
        //aloitetaan seuraavan alkion valitseminen
        int kokonaissumma = 0;
        
        //Käydään läpi mahdolliset seuraajat ja lasketaan niiden esiintyvyydet yhteen
        for (int i = 0; i < lapset.koko(); i++) {
            TrieSolmu solmu = lapset.get(i);
            kokonaissumma += solmu.getLaskuri();
        }
        //Arvotaan seuraava
        TrieSolmu seuraava = this.getSatunnainen(kokonaissumma, lapset);
        return seuraava.getTunnus();
            
    }
    
     /**
     * satunnaisen solmun valitseminen, perustuen kuitenkin todennäköisyyteen.
     * 
     * @param kokonaissumma "lapsien" yhteenlaskettu ilmentyvyys
     * @param solmut solmut, joista valitaan 
     * @return seuraavaksi tuleva solmu
     */
    private TrieSolmu getSatunnainen(int kokonaissumma, Taulukkolista<TrieSolmu> solmut) {
        
        
        //arvottu kokonaisluku 
        int indeksi = 1 + this.satunnainen(kokonaissumma);
        int summa = 0;
        int i = 0;
        //etsitään arvottua lukua vastaava alkio
        while (summa < indeksi) {
            summa += solmut.get(i).getLaskuri();
            i++;
        }
        return solmut.get(Math.max(0, i - 1));
    }
    /*
    private TrieSolmu getSatunnainenPehmennetty(int kokonaissumma, Taulukkolista<TrieSolmu> solmut) {
        int[] summat = new int[solmut.koko()];
        for (int i = 0; i < solmut.koko(); i++) {
            int summa = solmut.get(i).getLaskuri();
            double osuus = summa / kokonaissumma;
            if (osuus < 0.2) {
                summat[i] = summa * 2;
            } else if () {
                
            }
        }
    }
    */
    private int satunnainen(int arvo){
        return (int) (System.nanoTime() % arvo);
    }

}
