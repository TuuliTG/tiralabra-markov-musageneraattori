/**
 * 
 */


package markovgeneraattori.tietorakenteet;

/**
 * Trie tallettaa syötteenä saadut byte-tyyppiset alkiot niin, 
 * että sitä voi käyttää Markovin ketjun luomiseen.
 
 * @author Tuuli Toivanen-Gripentrog
 */
public class Trie {
    
    private TrieSolmu juuri;
    private int aste;

    public Trie(int maksimiaste) {
        juuri = new TrieSolmu();
        this.aste = maksimiaste;
    }
    /**
     * 
     * @param aanet 
     */
    public void lisaa(Taulukkolista<Byte> aanet) {
        if (aanet.koko() > aste) {
            this.lisaaTriehen(aanet, aste);
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
     * Hakee opetusmateriaalissa esiintyvät seuraajat hakuavaimelle.
     * @param hakuavain
     * @return seuraajat
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
     * Lisää listalla olevat alkiot Triehen markovin asteen mukaisesti.
     * @param aanet
     * @param aste 
     */
    private void lisaaTriehen(Taulukkolista<Byte> aanet, int aste){
       //Aloitetaan lisääminen Trien juuresta
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
                    //System.out.println("---------");
                    nykyinen = juuri;
                }
            }
        if (aste > 1 && aanet.koko() > 1) { //lisätään Triehen taulukosta loput pienemmällä asteella, jotta triestä voidaan hakea esim. 2 viimeistä
            Taulukkolista loput = new Taulukkolista(); //tehdään oma taulukko lopuista alkioista
            for (int i = aanet.koko() - aste; i < aanet.koko(); i++) {
                loput.lisaa(aanet.get(i));
            }
            
            if (loput.koko() > 1) { //lisää kunnes jäljellä 2. Opetusnäytteen viimeistä alkiota ei voi hakea yksinään.
                lisaaTriehen(loput, aste - 1);
            }
        }
    }
    
    /**
     * lisaa Triehen uuden solmun tai päivittää laskurin, jos solmu löytyy.
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
            //System.out.println("lisätään " + tunnus);
        }  else {

            TrieSolmu paivitettava = juuri.getLapset()[aani];
            paivitettava.lisaaLaskuriin();
            //System.out.println("päivitetään " + paivitettava.getTunnus());
            juuri.getLapset()[aani] = paivitettava;
        }
    }
}
