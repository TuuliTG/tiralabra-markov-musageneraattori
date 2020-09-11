/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

import java.util.ArrayList;
import java.util.Random;
import javax.naming.CannotProceedException;

/**
 * Tämä luokka on vielä hyvin vaiheessa. 
 * On epävarmaa, kannattaako rytmiä edes käsitellä Markovin ketjun tai trien avulla tässä yhteydessä. 
 * En siis aio siistiä tätä luokkaa ainakaan hetkeen.
 * @author tgtuuli
 */
public class RytmiGeneraattori {
    
    Trie trie;

    public RytmiGeneraattori(Trie trie) {
        
        this.trie = trie;
        
    }
    
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param alku mistä kappale halutaan aloittaa
     * @param aste kertoo, millä Markovin ketjun asteella hakuavaimia voi käyttää (esim. 3).
     * @param tahtilaji
     * @return 
     */
    /*
    public byte[] muodostaSekvenssi(int tahtienLkm, byte alku, int aste, int tahtilaji) throws Exception{
        int tahteja = 0;
        
        double tahdissaJaljella = tahtilaji;
        //taulukko, johon generoitu rytmisekvenssi tulee
        byte[] taulukko = new byte[tahtienLkm*10];
        //tarkistetaan voiko alkupituuden lisätä tahtiin:
        double tahti = this.lisaaRytmiTahtiin(alku, tahtilaji, 0);
        if(tahti == 0) {
            throw new CannotProceedException();
        } // jos tahti tuli täyteen, aloitetaan uusi tahti
        if(tahti == tahtilaji) {
            tahteja++;
            tahti = 0;
        }
        tahdissaJaljella = tahtilaji-tahti;
        byte[] ensimmainen = new byte[1];
        taulukko[0]=alku;
        ensimmainen[0] = alku;
        byte seuraava = haeSeuraava(ensimmainen, tahdissaJaljella);

        tahti = this.lisaaRytmiTahtiin(seuraava, tahtilaji, tahti);
        
        if(tahti == 0) {
            throw new CannotProceedException();
        } // jos tahti tuli täyteen, aloitetaan uusi tahti
        if(tahti == tahtilaji) {
            tahti = 0;
        }
        
        tahdissaJaljella = tahtilaji-tahti;
        
        taulukko[1]=seuraava;
        byte toinen = taulukko[1];
        byte[] kaksiEnsimmaista = new byte[2];
        kaksiEnsimmaista[0]=alku;
        kaksiEnsimmaista[1] = toinen;
        seuraava = haeSeuraava(kaksiEnsimmaista, tahdissaJaljella);
        tahti = this.lisaaRytmiTahtiin(seuraava, tahtilaji, tahti);
        if(tahti == 0) {
            throw new CannotProceedException();
        } // jos tahti tuli täyteen, aloitetaan uusi tahti
        if(tahti == tahtilaji) {
            tahti = 0;
        }
        
        tahdissaJaljella = tahtilaji-tahti;
        
        taulukko[2]=seuraava;
        byte[] valiaikainen = new byte[3];
        int indeksi = 0;
        while (tahteja < tahtienLkm) {
            System.out.println("tahteja nyt : " + tahteja);
            for(int i = 0; i < 3; i++) {

                valiaikainen[i] = taulukko[indeksi + i];
            }
            seuraava = haeSeuraava(valiaikainen, tahdissaJaljella);
            tahti = this.lisaaRytmiTahtiin(seuraava, tahtilaji, tahti);
            if(tahti == 0) {
                ;
            } // jos tahti tuli täyteen, aloitetaan uusi tahti
            if(tahti == tahtilaji) {
                tahti = 0;
                tahteja++;
            }
            tahdissaJaljella = tahtilaji-tahti;
            
            taulukko[indeksi+3] = seuraava;
            indeksi++;

            }
            return taulukko;
        
    }
    */
  
    /**
     * Hakee triestä hakuavaimen perusteella mahdolliset seuraajat ja valitsee seuraavan sävelen
     * @param hakuavain täytyy olla pienempi kuin aste, jolla Trie on rakennettu
     * @return 
     */
    /*
    private byte haeSeuraava(byte[] hakuavain, double maksimiPituus){
        
        ArrayList<TrieSolmu> lapset = trie.getSeuraajat(hakuavain);
        ArrayList<TrieSolmu> sopivat = new ArrayList<>();
        
        //Jos ei löydy seuraajia, kokeillaan pienemmällä haulla
        
        if (lapset == null) {
            byte[] uusiHaku = new byte[2];
            uusiHaku[0] = hakuavain[hakuavain.length-2];
            uusiHaku[1] = hakuavain[hakuavain.length-1];
            lapset = trie.getSeuraajat(uusiHaku);
        }
        
        //pienennetään vielä hakua, jos ei vieläkään löydy
        if (lapset == null) {
            byte[] uusiHaku = new byte[1];
            uusiHaku[0] = hakuavain[hakuavain.length-1];
            lapset = trie.getSeuraajat(uusiHaku);
        }
        //Käsittele jos ei vieläkään seuraajia (arvotaan lähistöltä seuraaja)
        if(lapset == null){
            byte palautus = (byte)hakuavain[0];
            palautus -= 128; //muunnetaan takaisin
            
            return palautus;
        }
        

        int kokonaissumma = 0;
        
        System.out.println("lapsia " + lapset.size());
        //Käydään läpi mahdolliset seuraajat ja lasketaan niiden esiintyvyydet yhteen
        Rytmi r = new Rytmi();
        for (TrieSolmu solmu : lapset) {
            double kesto = r.getKestoAsDouble(solmu.getTunnus());
            if(kesto <= maksimiPituus){
                sopivat.add(solmu);
                kokonaissumma += solmu.getLaskuri();
            }
            System.out.println("Tunnus = " + solmu.getTunnus() + 
                             ", laskuri = " + solmu.getLaskuri()); 
            
            
        }
        
        //jos lapsia on vaan yksi, valitaan se suoraan arpomatta
        if(sopivat.size()==1) {
                return sopivat.get(0).getTunnus();
        }
        if(sopivat.isEmpty()) {
            System.out.println("ei sopivaa rytmiä");
            return r.getRytmiBytena(maksimiPituus);
        }
            
            System.out.println("kokonaissumma " + kokonaissumma);
            //Arvotaan seuraava
            
            TrieSolmu seuraava = this.getSatunnainen(kokonaissumma, lapset);
            System.out.println("seuraava rytmi " + seuraava.getTunnus());
            return seuraava.getTunnus();
            
    }
    */
     /**
     * satunnaisen solmun valitseminen, perustuen kuitenkin todennäköisyyteen
     * 
     * @param kokonaissumma "lapsien" yhteenlaskettu ilmentyvyys
     * @param solmut solmut, joista valitaan 
     * @return seuraavaksi tuleva solmu
     */
    /*
    private TrieSolmu getSatunnainen(int kokonaissumma, ArrayList<TrieSolmu> solmut) {
        Random rand = new Random();
        int indeksi = 1 + rand.nextInt(kokonaissumma);
        System.out.println("random = " + indeksi);
        int summa = 0;
        int i = 0;
        while(summa < indeksi) {
            summa += solmut.get(i).getLaskuri();
            i++;
        }
        System.out.println("valitaan solmu " + solmut.get(Math.max(0, i-1)).getTunnus());
        return solmut.get(Math.max(0, i-1));
    }
    
    private double lisaaRytmiTahtiin(byte rytmi, int tahtilaji, double tahti) {
        System.out.println("tahdissa nyt : " + tahti);
        
        Rytmi r = new Rytmi();
        double kesto = r.getKestoAsDouble(rytmi);
        System.out.println("yritetään lisätä rytmi : " + rytmi +" kesto: " + kesto);
        if(tahti + kesto <= tahtilaji) {
            return tahti + kesto;
        } else {
            return 0;
        }
     
    }
*/
}
