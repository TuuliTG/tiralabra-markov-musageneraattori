
package markovgeneraattori;

import java.util.HashMap;

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
            System.out.println("haettava ääni: " + aani);
            TrieSolmu solmu = nykyinen.getLapset().get(aani);
            if (solmu == null) {
                return false;
            }
            nykyinen = solmu;
        }
        return true;
    }
    
    public HashMap<Byte, TrieSolmu> getSeuraajat(byte[] hakuavain){
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < hakuavain.length; i++) {
            byte aani = hakuavain[i];
            TrieSolmu solmu = nykyinen.getLapset().get(aani);
            if (solmu == null) {
                return null;
            }
            nykyinen = solmu;
            
        }
        return nykyinen.getLapset();
    }
    
    private void lisaaTriehen(byte[] aanet, int aste){
        TrieSolmu nykyinen = juuri;
        for (int i = 0; i < aanet.length - aste; i++) {
                for(int j = i; j <= i+aste; j++) {
                    byte aani = aanet[j];
                    System.out.println("ääni: " + aani);
                    if (nykyinen.getLapset().get(aani) == null) {
                        System.out.println("lisätään " + aani + " solmuun" + nykyinen);
                        nykyinen.getLapset().put(aani, new TrieSolmu(aani));
                    } else {
                        TrieSolmu paivitettava = nykyinen.getLapset().
                                get(aani);
                        paivitettava.lisaaLaskuriin();
                        nykyinen.getLapset().put(aani, paivitettava);
                    }
                    nykyinen = nykyinen.getLapset().get(aani);
                }
                nykyinen = juuri;
            }
    }
    
}
