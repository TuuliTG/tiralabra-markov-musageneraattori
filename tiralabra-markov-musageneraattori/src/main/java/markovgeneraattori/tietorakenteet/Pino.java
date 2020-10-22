/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.tietorakenteet;

/**
 * Pino, josta voi ottaa päällimmäisen tai alimmaisen alkion.
 * @param <T> 
 * @author tgtuuli
 */
public class Pino<T> {
    
    private int paallimmainen;
    private int alimmainen;
    private int seuraava;
    private int maksimiKoko;
    private int koko;
    private Object[] pino;

    public Pino() {
        this.maksimiKoko = 10;
        this.pino = new Object[maksimiKoko];
        this.seuraava = 0;
        this.koko = 0;
        this.alimmainen = 0;
    }
    
    /**
     * Lisää pinon päällimmäiseksi alkion T.
     * @param alkio lisättävä
     */
    public void lisaa(T alkio){
        if (seuraava >= maksimiKoko) {
            this.kasvataPinonKokoa();
        }
        pino[seuraava] = alkio;
        paallimmainen = seuraava;
        seuraava++;
        koko++;
    }
    
    /**
     * Ottaa päällimmäisen pois pinosta ja palauttaa sen. 
     * Pinon koko pienenee yhdellä.
     * @return T päällimmäinen alkio
     */
    public T otaPaallimmainen(){
        if (koko > 0){
            T palautettava = (T) pino[paallimmainen];
            paallimmainen--;
            seuraava--;
            koko--;
            return palautettava;
            
        } else {
            throw new RuntimeException("Pino on tyhjä");
        }
        
    }
    /**
     * Ottaa pinon alimman alkion ja palauttaa sen.
     * Pinon koko pienenee yhdellä.
     * @return T alimmainen alkio
     */
    public T otaAlimmainen(){
        if (koko > 0){
            T palautettava = (T) pino[alimmainen];
            alimmainen++;
            koko--;
            return palautettava;
            
        } else {
            throw new RuntimeException("Pino on tyhjä");
        }
        
    }
    
    /**
     * Palauttaa pinon päällimmäisen alkion, mutta ei poista sitä pinosta.
     * @return T päällimmäinen alkio
     */
    public T katsoPaallimmainen() {
        if (koko > 0) {
            return (T) pino[paallimmainen];
        } else {
            throw new RuntimeException("Pino on tyhjä");
        }
    }
    
    /**
     * Pinon koko
     * @return int
     */
    public int getKoko() {
        return koko;
    }
    
    private void kasvataPinonKokoa() {
        maksimiKoko *= 2;
        Object[] uusiLista = new Object[maksimiKoko];
        System.arraycopy(this.pino, 0, uusiLista, 0, this.koko);
        this.pino = uusiLista;
    }
    
}
