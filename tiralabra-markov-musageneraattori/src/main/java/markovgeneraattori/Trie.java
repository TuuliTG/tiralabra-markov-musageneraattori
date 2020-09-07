
package markovgeneraattori;

import java.util.ArrayList;

/**
 *
 * @author tgtuuli
 */

public class Trie {
    
    private TrieSolmu juuri;

    public Trie() {
        juuri = new TrieSolmu();
    }
    
    public void lisaa(byte[] aanet) {
        if(aanet.length > 3) {
            this.lisaaTriehen(aanet, 3);
        } 
        byte[] loput;
        loput = new byte[3];
        if(aanet.length > 2) {
            
            int laskuri = 0;
            for(int i = aanet.length-3; i < aanet.length; i++){

                loput[laskuri]=aanet[i];
                laskuri++;
            }
        }
        if(aanet.length > 1) {
            this.lisaaTriehen(loput, 2);
        }
        if(aanet.length > 0) {
            this.lisaaTriehen(loput, 1);
        }
        
    }
    //palauttaa true, jos avain löytyy
    public boolean haku(byte[] avain){
        
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < avain.length; i++) {
            byte aani = avain[i];
            TrieSolmu solmu = nykyinen.getLapset()[aani];
            if (solmu == null) {
                return false;
            }
            nykyinen = solmu;
        }
        return true;
    }
    
    public ArrayList<TrieSolmu> getSeuraajat(int[] hakuavain){
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < hakuavain.length; i++) {
            int aani = hakuavain[i];
            TrieSolmu solmu = nykyinen.getLapset()[aani];
            if (solmu == null) {
                return null;
            }
            nykyinen = solmu;
        }
        return nykyinen.getSeuraajatListassa();
    }
    
    private void lisaaTriehen(byte[] aanet, int aste){
        TrieSolmu nykyinen = juuri;
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
                        nykyinen.getSeuraajatListassa().add(solmu);
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
