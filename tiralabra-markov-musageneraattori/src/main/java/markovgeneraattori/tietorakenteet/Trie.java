
package markovgeneraattori.tietorakenteet;

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
    
    public void lisaa(Taulukkolista<Byte> aanet) {
        if (aanet.koko() > maksimiAste) {
            this.lisaaTriehen(aanet, maksimiAste);
        } else {
            this.lisaaTriehen(aanet, aanet.koko());
        }
    }
    /**
     * Hakee triestä avaimen perusteella. 
     * Viimeisenä triehen lisätyn alkion hakeminen yksinään 
     * ei toimi, koska sitä ei tässä ohjelmassa tarvita.
     * @param avain
     * @return true, jos avain löytyy triestä
     */
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
    /**
     * 
     * @param hakuavain
     * @return 
     */
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
    
    /**
     * 
     * @param aanet
     * @param aste 
     */
    private void lisaaTriehen(Taulukkolista<Byte> aanet, int aste){
       
        TrieSolmu nykyinen = juuri;
        if (aanet.koko() == 1) {
            this.lisaaUusiTaiPaivita(juuri, aanet.get(0));
        } else {
                for (int i = 0; i < aanet.koko() - aste; i++) {
                    for (int j = i; j <= i + aste; j++) {
                        int aani = aanet.get(j);
                        this.lisaaUusiTaiPaivita(nykyinen, aani);
                        nykyinen = nykyinen.getLapset()[aani + 128];
                    }
                    System.out.println("---------");
                    nykyinen = juuri;
                }
            }
        if (aste > 1 && aanet.koko() > 1) {
            byte[] loput = new byte[aste];
            Taulukkolista loput2 = new Taulukkolista();
            
            //System.arraycopy(aanet, aanet.koko() - aste, loput, 0, aste);
            for (int i = 0; i < loput.length; i++) {
                loput2.lisaa(loput[i]);
            }
            if (loput.length > 1) {
                lisaaTriehen(loput2, aste - 1);
            }
        }
    }
    
    /**
     * 
     * @param juuri solmu, jonka perään uusi solmu lisätään tai jonka lasta päivitetään
     * @param aani, jota ollaan lisäämässä
     */
    private void lisaaUusiTaiPaivita(TrieSolmu juuri, int aani) {
        byte tunnus = (byte) aani;
        aani += 128;
        
        if (juuri.getLapset()[aani] == null) {
            TrieSolmu lisattava = new TrieSolmu(tunnus);
            juuri.getLapset()[aani] = lisattava;
            juuri.getSeuraajat().lisaa(lisattava);
            System.out.println("lisätään " + tunnus);
        }  else {

            TrieSolmu paivitettava = juuri.getLapset()[aani];
            paivitettava.lisaaLaskuriin();
            System.out.println("päivitetään " + paivitettava.getTunnus());
            juuri.getLapset()[aani] = paivitettava;
        }
    }
}
