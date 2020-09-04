
package markovgeneraattori;
/**
 *
 * @author tgtuuli
 */

public class Trie {
    
    private TrieSolmu juuri;
    private byte aste;

    public Trie(byte aste) {
        juuri = new TrieSolmu();
        this.aste = aste;
    }
    
    public void lisaa(byte aani) {
        TrieSolmu nykyinen = juuri;
        //Tarkistetaan, onko ääni jo triessä
        TrieSolmu solmu = nykyinen.getLapset().get(aani);
        //Jos ei, niin luodaan uusi solmu
        if (solmu == null) {
            solmu = new TrieSolmu();
            nykyinen.getLapset().put(aani, solmu);
        }
        
    }
    
    public void etsi(){
        
    }
    
}
