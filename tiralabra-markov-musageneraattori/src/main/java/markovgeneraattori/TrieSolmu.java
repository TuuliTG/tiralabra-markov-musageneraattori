
package markovgeneraattori;

import java.util.ArrayList;
/**
 *
 * @author tgtuuli
 */
public class TrieSolmu {
    
    private byte tunnus;
    private final TrieSolmu[] lapset;
    private Taulukkolista<TrieSolmu> seuraajat;
    private ArrayList<TrieSolmu> seuraajatListassa;
    public int laskuri;

    public TrieSolmu() {
        this.lapset = new TrieSolmu[256];
        this.laskuri = 1;
        this.seuraajatListassa = new ArrayList<>();
        this.seuraajat = new Taulukkolista<>();
    }
    
    public TrieSolmu(byte tunnus) {
        this.lapset = new TrieSolmu[256];
        this.laskuri = 1;
        this.tunnus = tunnus;
        this.seuraajatListassa = new ArrayList<>();
        this.seuraajat = new Taulukkolista<>();
    }

    public TrieSolmu[] getLapset() {
        return this.lapset;
    }
    
    public int getLaskuri(){
        return this.laskuri;
    }
    
    public void lisaaLaskuriin(){
        this.laskuri += 1;
    }

    public byte getTunnus() {
        return this.tunnus;
    }

    public ArrayList<TrieSolmu> getSeuraajatListassa() {
        return this.seuraajatListassa;
    }

    public Taulukkolista<TrieSolmu> getSeuraajat() {
        return this.seuraajat;
    }
    
    

    
    
}
