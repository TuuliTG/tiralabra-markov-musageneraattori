
package markovgeneraattori;

import java.util.HashMap;
/**
 *
 * @author tgtuuli
 */
public class TrieSolmu {
    
    private byte tunnus;
    private HashMap<Byte, TrieSolmu> lapset;
    public int laskuri;

    public TrieSolmu() {
        this.lapset = new HashMap<>();
        laskuri = 1;
    }
    public TrieSolmu( byte tunnus) {
        this.lapset = new HashMap<>();
        laskuri = 1;
        this.tunnus = tunnus;
    }
    

    public HashMap<Byte, TrieSolmu> getLapset() {
        return lapset;
    }
    
    public int getLaskuri(){
        return laskuri;
    }
    
    public void lisaaLaskuriin(){
        laskuri += 1;
    }

    public byte getTunnus() {
        return tunnus;
    }
    
    
    
     
}
