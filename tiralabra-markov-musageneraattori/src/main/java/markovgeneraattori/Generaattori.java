/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.ArrayList;

import java.util.Random;

/**
 * Generaattorin tehtävänä on tuottaa uusi sekvenssi annetun syötteen perusteella.
 * 
 * @author tgtuuli
 */
public class Generaattori {
    
    
    private Trie trie;

    public Generaattori(Trie trie) {
        
        this.trie = trie;
        
    }
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param alkusavel mistä kappale halutaan aloittaa
     * @param aste kertoo, millä Markovin ketjun asteella hakuavaimia voi käyttää (esim. 3).
     * @return taulukko eli valmis sekvenssi
     */
    public byte[] muodostaSekvenssi(int pituus, byte alkusavel, int aste){
        
        //Taulukko, johon sekvenssi tallennetaan
        byte[] taulukko = new byte[pituus];
        
        taulukko[0]=alkusavel;
        
        //Ensimmäiset täytyy hakea erikseen, ennen kuin sekvenssin pituus on asteluvun pituinen
        int indeksi = 0;
        while (indeksi < aste -1) {
            byte seuraava = haeSeuraava(taulukko, 0,indeksi);
            taulukko[indeksi+1]=seuraava;
            indeksi++;
        }
        //Tästä eteenpäin generoidaan sekvenssiä asteluvun pituisen hakusanan perusteella
        for(int i = 0; i < pituus-aste; i++) {
            byte seuraava = haeSeuraava(taulukko, i,i+aste-1);
            taulukko[i+aste]=seuraava;
        }
        return taulukko;
    }
  
    /**
     * Hakee triestä hakuavaimen perusteella mahdolliset seuraajat ja valitsee seuraavan sävelen
     * @param hakuavain taulukko, jossa on tähän asti luotu sekvenssi
     * @param mista mistä taulukon kohdasta avain alkaa
     * @param mihin mihin taulukon kohtaan avain loppuu
     * @return seuraava sekvenssiin tuleva alkio byte-muodossa
     */
    private byte haeSeuraava(byte[] hakuavain, int mista, int mihin){
        //luodaan hakuavainta varten int-muotoinen taulukko
        int[] avain = new int[mihin+1-mista];
        int indeksi = 0;
        //luodaan hakuavain
        while(mista <= mihin) {
            avain[indeksi] = hakuavain[mista] + 128; //skaalataan, jotta luku on positiivinen
            indeksi++;
            mista++;
        }
        //haetaan hakuavaimella löytyvät seuraajat
        ArrayList<TrieSolmu> lapset = trie.getSeuraajat(avain);
        
        //pienennetään hakua, jos seuraajia ei löydy
        if (lapset == null) {
            int[] uusiHaku = new int[1];
            uusiHaku[0] = avain[avain.length-1];
            lapset = trie.getSeuraajat(uusiHaku);
        }
        //Käsittele jos ei vieläkään seuraajia (palautetaan sama kuin haun ensimmäinen)
        if(lapset == null){
            byte palautus = (byte)avain[0];
            palautus -= 128; //muunnetaan takaisin alkuperäiseksi
            return palautus;
        }
        //jos lapsia on vaan yksi, valitaan se suoraan arpomatta
        if(lapset.size()==1) {
                return lapset.get(0).getTunnus();
        }
        
        //aloitetaan seuraavan alkion valitseminen
        int kokonaissumma = 0;
        
        //Käydään läpi mahdolliset seuraajat ja lasketaan niiden esiintyvyydet yhteen
        for (TrieSolmu solmu : lapset) {

            kokonaissumma += solmu.getLaskuri();
            
        }
            
        //Arvotaan seuraava
        TrieSolmu seuraava = this.getSatunnainen(kokonaissumma, lapset);
        return seuraava.getTunnus();
            
    }
    
     /**
     * satunnaisen solmun valitseminen, perustuen kuitenkin todennäköisyyteen
     * 
     * @param kokonaissumma "lapsien" yhteenlaskettu ilmentyvyys
     * @param solmut solmut, joista valitaan 
     * @return seuraavaksi tuleva solmu
     */
    private TrieSolmu getSatunnainen(int kokonaissumma, ArrayList<TrieSolmu> solmut) {
        //Muuta tämä omaksi toteutukseksi
        Random rand = new Random();
        //arvottu kokonaisluku 
        int indeksi = 1 + rand.nextInt(kokonaissumma);
        int summa = 0;
        int i = 0;
        //etsitään arvottua lukua vastaava alkio
        while(summa < indeksi) {
            summa += solmut.get(i).getLaskuri();
            i++;
        }
        return solmut.get(Math.max(0, i-1));
    }
}
