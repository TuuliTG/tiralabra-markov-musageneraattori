/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package markovgeneraattori;

/**
 *
 * @author tgtuuli
 */
public class koeGeneraattori extends Generaattori{
    
    private Trie trie;
    
    public koeGeneraattori(Trie trie) {
        this.trie = trie;
    }

    @Override
    public byte[] muodostaSekvenssi(int pituus, byte alkusavel, int aste) {
        return super.muodostaSekvenssi(pituus, alkusavel, aste); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
