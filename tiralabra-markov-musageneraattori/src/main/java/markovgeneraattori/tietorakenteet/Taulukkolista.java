
package markovgeneraattori.tietorakenteet;

/**
 * @param <T> tyyppi
 * @author tgtuuli
 */
public class Taulukkolista<T> {
    
    private Object[] taulukkolista;
    private int koko;
    private int maksimiKoko;

    public Taulukkolista() {
        
        this.taulukkolista = new Object[10];
        this.koko = 0;
        this.maksimiKoko = 10;
    }
    
    public void lisaa(T alkio) {
        if (this.koko == this.maksimiKoko) {
            this.lisaaTaulukonKokoa();
        }
        this.taulukkolista[this.koko] = alkio;
        this.koko++;
    }
    
    private void lisaaTaulukonKokoa() {
        maksimiKoko *= 2;
        Object[] uusiLista = new Object[maksimiKoko];
        System.arraycopy(this.taulukkolista, 0, uusiLista, 0, this.koko);
        this.taulukkolista = uusiLista;
    }
    
    public int koko(){
        return this.koko;
    }
    
    public T get(int i) {
        if (i < 0 || i >= this.koko) {
            throw  new NullPointerException();
        }
        return (T) this.taulukkolista[i];
    }
    
    public boolean onTyhja(){
        return this.koko == 0;
    }
    
    public void lisaaMonta(T[] array) {
        
        for (int i = 0; i < array.length; i++) {
            this.lisaa((T) array[i]);
        }
    }
  
}
