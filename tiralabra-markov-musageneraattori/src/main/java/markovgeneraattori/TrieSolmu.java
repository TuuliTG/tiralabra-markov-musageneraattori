
package markovgeneraattori;

import java.util.ArrayList;
/**
 *
 * @author tgtuuli
 */
public class TrieSolmu {
    
    private byte tunnus;
    private final TrieSolmu[] lapset;
    private ArrayList<TrieSolmu> seuraajatListassa;
    public int laskuri;

    public TrieSolmu() {
        this.lapset = new TrieSolmu[256];
        laskuri = 1;
        seuraajatListassa = new ArrayList<>();
    }
    
    public TrieSolmu(byte tunnus) {
        this.lapset = new TrieSolmu[256];
        laskuri = 1;
        this.tunnus = tunnus;
        seuraajatListassa = new ArrayList<>();
    }

    public TrieSolmu[] getLapset() {
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

    public ArrayList<TrieSolmu> getSeuraajatListassa() {
        return seuraajatListassa;
    }

    
    
}
