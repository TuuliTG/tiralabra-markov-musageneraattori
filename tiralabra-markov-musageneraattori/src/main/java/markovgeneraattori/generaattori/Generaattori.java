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
 * Generaattori tuottaa uuden sekvenssin annettuun opetusmateriaaliin perustuen.
 * 
 * @author tgtuuli
 */
public class Generaattori {
    
    
    private Trie trie;
    private Tekstinkasittelija t;
    private int aste;

    public Generaattori(int aste) {
        this.aste = aste;
        this.trie = new Trie(aste);
        t = new Tekstinkasittelija();
        
    }
    /**
     * 
     * @param opetusmateriaali 
     */
    public void lueOpetusmateriaali(String opetusmateriaali) {
        Taulukkolista<Byte> aanet = t.muunnaKappaleTekstistaByteiksi(opetusmateriaali);
        trie.lisaa(aanet);
        
    }
    /**
     * 
     * @param pituus sekvenssin pituus
     * @param alkusavel mistä kappale halutaan aloittaa
     * @return taulukko eli valmis sekvenssi
     */
    public byte[] muodostaSekvenssi(int pituus, byte alkusavel){
        
        //Taulukko, johon sekvenssi tallennetaan
        byte[] taulukko = new byte[pituus];
        
        taulukko[0] = alkusavel;
        
        //Ensimmäiset täytyy hakea erikseen, ennen kuin sekvenssin pituus on asteluvun pituinen
        //System.out.println("haetaan ensimmäiset");
        int indeksi = 0;
        while (indeksi < aste - 1) {
            byte seuraava = haeSeuraava(taulukko, 0, indeksi);
            //System.out.println("seuraava " + seuraava);
            taulukko[indeksi + 1] = seuraava;
            indeksi++;
        }
        //Tästä eteenpäin generoidaan sekvenssiä asteluvun pituisen hakusanan perusteella
        //System.out.println("haetaan seuraavat");
        for (int i = 0; i < pituus - aste; i++) {
            byte seuraava = haeSeuraava(taulukko, i, i + aste - 1);
            //System.out.println("seuraava " + seuraava);
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
    private byte haeSeuraava(byte[] hakuavain, int mista, int mihin){
        //luodaan hakuavainta varten int-muotoinen taulukko
        byte[] avain = new byte[mihin + 1 - mista];
        System.arraycopy(hakuavain, mista, avain, 0, (mihin + 1 - mista));
        
        
        //haetaan hakuavaimella löytyvät seuraajat
        Taulukkolista<TrieSolmu> lapset = trie.getSeuraajat(avain);
        //System.out.println("haulla löytyi " + lapset.koko() + "lasta");
        
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
            
        //System.out.println("");
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
        //Muuta tämä omaksi toteutukseksi
        
        //System.out.println("haetaan satunnainen");
        //System.out.println("kokonaissumma " + kokonaissumma);
        //arvottu kokonaisluku 
        int indeksi2 = 1 + this.satunnainen(kokonaissumma);
        //System.out.println("uusi satunnainen : " + indeksi2);
        int summa = 0;
        int i = 0;
        //etsitään arvottua lukua vastaava alkio
        while (summa < indeksi2) {
            summa += solmut.get(i).getLaskuri();
            i++;
        }
        return solmut.get(Math.max(0, i - 1));
    }
    
    private int satunnainen(int arvo){
        return (int) (System.nanoTime() % arvo);
    }
}
