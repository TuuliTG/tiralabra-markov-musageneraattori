
import UI.Kayttoliittyma;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tgtuuli
 */
public class KayttoliittymaTest {
    
    private final ByteArrayOutputStream syoteUlos = new ByteArrayOutputStream();
    private Kayttoliittyma k;
    @Rule
    public TemporaryFolder kansio = new TemporaryFolder();
    
    
    @Before
    public void alustus() {
        this.k = new Kayttoliittyma();
        System.setOut(new PrintStream(syoteUlos));
    }
    
    @Test
    public void kayttoliittymaValikkoToimii() throws Exception{
        Scanner lukija = new Scanner(liita(new String[]{"1","1","3","12","testing"}));
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(lukija);
        kayttoliittyma.kaynnista();
        String[] syote = syoteUlos.toString().split("\n");
        assertTrue(syote[0].startsWith("TERVETULOA"));
        assertTrue(syote[6].startsWith("Valitse"));
        assertTrue(syote[9].startsWith("Millä asteella"));
        assertTrue(syote[10].startsWith("Generoidun"));
        assertTrue(syote[11].startsWith("Mihin kansioon"));
    }
    
    @Test
    public void kayttoliittymaLuoTiedoston() {
        Scanner lukija = new Scanner(liita(new String[]{"1","1","3","12","testing"}));
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(lukija);
        kayttoliittyma.kaynnista();
        File tiedosto = new File("testing/bach.ly");
        assertTrue(tiedosto.exists());
          
    }
    
    private String liita(String[] komennot) {
        StringBuilder sb = new StringBuilder();
        for (String s : komennot) {
            sb.append(s);
            
            sb.append("\n");
        }
        return sb.toString();
    }

}
