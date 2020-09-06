/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generaattorin tehtävänä on tuottaa uusi sekvenssi annetun syötteen perusteella.
 * 
 * @author tgtuuli
 */
public class Generaattori {
    
    
    Trie trie;

    public Generaattori(Trie trie) {
        
        this.trie = trie;
        
    }
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param alkusavel mistä kappale halutaan aloittaa
     * @param aste kertoo, millä Markovin ketjun asteella hakuavaimia voi käyttää (esim. 3).
     * @return 
     */
    public byte[] kappale(int pituus, byte alkusavel, int aste){
        byte[] taulukko = new byte[pituus];
        byte[] alku = new byte[1];
        alku[0] = alkusavel;
        taulukko[0]=alkusavel;
        byte seuraava = haeSeuraava(alku);
        taulukko[1]=seuraava;
        byte toinen = taulukko[1];
        byte[] kaksiEns = new byte[2];
        kaksiEns[0]=alkusavel;
        kaksiEns[1] = toinen;
        seuraava = haeSeuraava(kaksiEns);
        taulukko[2]=seuraava;
        byte[] valiaikainen = new byte[3];
        for (int i = 0; i < pituus-aste; i++) {
            for(int j = 0; j < 3; j++) {
                
                valiaikainen[j] = taulukko[i+j];
            }
            seuraava = haeSeuraava(valiaikainen);
            taulukko[i+3] = seuraava;
            
        }
        return taulukko;
    }
  
    /**
     * Hakee triestä hakuavaimen perusteella mahdolliset seuraajat ja valitsee seuraavan sävelen
     * @param hakuavain täytyy olla pienempi kuin aste, jolla Trie on rakennettu
     * @return 
     */
    private byte haeSeuraava(byte[] hakuavain){
        System.out.print("hakuavain: ");
        for (int i = 0; i < hakuavain.length; i++) {
            System.out.print(hakuavain[i] + ", ");
        }
        System.out.println("");
        HashMap<Byte, TrieSolmu> lapset = trie.getSeuraajat(hakuavain);
        
        //Jos ei löydy seuraajia, kokeillaan pienemmällä haulla
        if (lapset == null) {
            System.out.println("ei lapsia");
            byte[] uusiHaku = new byte[2];
            uusiHaku[0] = hakuavain[hakuavain.length-2];
            uusiHaku[1] = hakuavain[hakuavain.length-1];
            lapset = trie.getSeuraajat(uusiHaku);
        } //pienennetään vielä hakua, jos ei vieläkään löydy
        if (lapset == null) {
            System.out.println("ei lapsia");
            byte[] uusiHaku = new byte[1];
            uusiHaku[0] = hakuavain[hakuavain.length-1];
            lapset = trie.getSeuraajat(uusiHaku);
        }
        //Käsittele jos ei vieläkään seuraajia (arvotaan lähistöltä seuraaja)
        if(lapset == null){
        }
        
        int koko = 0;
        koko = lapset.size();
        //Luodaan seuraajille vielä oma taulukko
        TrieSolmu[] solmut = new TrieSolmu[koko];
        int laskuri = 0;
        int kokonaissumma = 0;
        
        System.out.println("lapsia " + lapset.size());
        //Käydään läpi mahdolliset seuraajat ja lasketaan niiden esiintyvyydet yhteen
        for (Map.Entry<Byte,TrieSolmu> entry : lapset.entrySet()) {

            System.out.println("Key = " + entry.getKey() + 
                             ", laskuri = " + entry.getValue().getLaskuri()); 
            
            kokonaissumma += entry.getValue().getLaskuri();
            solmut[laskuri] = entry.getValue(); //lisätään mahdollinen seuraaja listaan
            laskuri++;
            //jos lapsia on vaan yksi, valitaan se suoraan arpomatta
            if(lapset.size()==1) {
                System.out.println("vain yksi lapsi, joten valitaan se");
                return entry.getKey();
            }

        }
            
            System.out.println("kokonaissumma " + kokonaissumma);
            //Arvotaan seuraava
            TrieSolmu seuraava = this.getSatunnainen(kokonaissumma, solmut);
            System.out.println("seuraava sävel " + seuraava.getTunnus());
            return seuraava.getTunnus();
            
    }
    
     /**
     * satunnaisen solmun valitseminen, perustuen kuitenkin todennäköisyyteen
     * 
     * @param kokonaissumma "lapsien" yhteenlaskettu ilmentyvyys
     * @param solmut solmut, joista valitaan 
     * @return seuraavaksi tuleva solmu
     */
    private TrieSolmu getSatunnainen(int kokonaissumma, TrieSolmu[] solmut) {
        Random rand = new Random();
        int indeksi = 1 + rand.nextInt(kokonaissumma);
        System.out.println("random = " + indeksi);
        int summa = 0;
        int i = 0;
        while(summa < indeksi) {
            summa += solmut[i].getLaskuri();
            i++;
        }
        System.out.println("valitaan solmu " + solmut[Math.max(0, i-1)].getTunnus());
        return solmut[Math.max(0, i-1)];
    }
}
