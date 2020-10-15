/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori.tietorakenteet;

/**
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
    
    public void lisaa(T alkio){
        if (seuraava >= maksimiKoko) {
            this.kasvataPinonKokoa();
        }
        pino[seuraava] = alkio;
        paallimmainen = seuraava;
        seuraava++;
        koko++;
        //System.out.println("koko:" + koko);
    }
    
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
    
    public T katsoPaallimmainen() {
        if (koko > 0) {
            return (T) pino[paallimmainen];
        } else {
            throw new RuntimeException("Pino on tyhjä");
        }
    }
    
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
