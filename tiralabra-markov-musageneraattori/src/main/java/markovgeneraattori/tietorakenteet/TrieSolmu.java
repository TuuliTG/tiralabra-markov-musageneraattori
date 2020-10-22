
package markovgeneraattori.tietorakenteet;

/**
 * 
 * @author tgtuuli
 */
public class TrieSolmu {
    
    private byte tunnus;
    private final TrieSolmu[] lapset;
    private Taulukkolista<TrieSolmu> seuraajat;
    public int laskuri;

    public TrieSolmu() {
        this.tunnus = -128;
        this.lapset = new TrieSolmu[256];
        this.laskuri = 1;
        this.seuraajat = new Taulukkolista<>();
    }
    
    public TrieSolmu(byte tunnus) {
        this.lapset = new TrieSolmu[256];
        this.laskuri = 1;
        this.tunnus = tunnus;
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

    public Taulukkolista<TrieSolmu> getSeuraajat() {
        return this.seuraajat;
    }
    
    

    
    
}
