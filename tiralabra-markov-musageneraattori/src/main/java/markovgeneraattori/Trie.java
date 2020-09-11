
package markovgeneraattori;

import java.util.ArrayList;

/**
 * Trie tietorakenne, joka tallettaa syötteenä saadut byte-tyyppiset alkiot niin, 
 * että sitä voi käyttää Markovin ketjun luomiseen.
 
 * @author tgtuuli
 */

public class Trie {
    
    private TrieSolmu juuri;
    private int maksimiAste;

    public Trie(int maksimiaste) {
        juuri = new TrieSolmu();
        this.maksimiAste = maksimiaste;
    }
    
    public void lisaa(byte[] aanet) {
        if(aanet.length > maksimiAste) {
            this.lisaaTriehen(aanet, maksimiAste);
        } 
        for (int i = aanet.length-1; i >= aanet.length-maksimiAste; i--) {
            byte[] aani = new byte[1];
            aani[0] = aanet[i];
            this.lisaaTriehen(aani, 1);
        }
       
        
    }
    //palauttaa true, jos avain löytyy
    public boolean haku(byte[] avain){
        
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < avain.length; i++) {
            
            int aani = avain[i] + 128;
            TrieSolmu solmu = nykyinen.getLapset()[aani];
            if (solmu == null) {
                return false;
            }
            nykyinen = solmu;
        }
        return true;
    }
    
    public Taulukkolista<TrieSolmu> getSeuraajat(byte[] hakuavain){
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < hakuavain.length; i++) {
            int aani = hakuavain[i] + 128;
            TrieSolmu solmu = nykyinen.getLapset()[aani];
            if (solmu == null) {
                return null;
            }
            nykyinen = solmu;
        }
        return nykyinen.getSeuraajat();
    }
    
    private void lisaaTriehen(byte[] aanet, int aste){
        TrieSolmu nykyinen = juuri;
        if(aanet.length == 1) {
            int aani = aanet[0] +128;
            if (nykyinen.getLapset()[aani] == null) {
                        System.out.println("lisätään " + aani + " solmuun" + nykyinen);
                        aani -= 128;
                        byte tunnus = (byte)aani;
                        aani += 128;
                        TrieSolmu solmu = new TrieSolmu(tunnus);
                        nykyinen.getLapset()[aani] = solmu;
                        nykyinen.getSeuraajat().lisaa(solmu);
            }
        } else {
                for (int i = 0; i < aanet.length - aste; i++) {
                    for(int j = i; j <= i+aste; j++) {
                        int aani = aanet[j];
                        aani += 128; // skaalataan niin, ettei tule negatiivisia lukuja
                        System.out.println("ääni: " + aani);

                        if (nykyinen.getLapset()[aani] == null) {
                            System.out.println("lisätään " + aani + " solmuun" + nykyinen);
                            aani -= 128;
                            byte tunnus = (byte)aani;
                            aani += 128;
                            TrieSolmu solmu = new TrieSolmu(tunnus);
                            nykyinen.getLapset()[aani] = solmu;
                            nykyinen.getSeuraajat().lisaa(solmu);
                        } else {
                            TrieSolmu paivitettava = nykyinen.getLapset()[aani];
                            paivitettava.lisaaLaskuriin();
                            nykyinen.getLapset()[aani]=paivitettava;
                        }
                        nykyinen = nykyinen.getLapset()[aani];
                    }
                    nykyinen = juuri;
                }
            }
        
    }
    
}
