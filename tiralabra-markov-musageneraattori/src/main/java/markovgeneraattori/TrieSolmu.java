
package markovgeneraattori;

import java.util.HashMap;
/**
 *
 * @author tgtuuli
 */
public class TrieSolmu {
    
    private HashMap<Byte, TrieSolmu> lapset;
    private boolean onSananLoppu;

    public TrieSolmu() {
        this.lapset = new HashMap<>();
        this.onSananLoppu = false;
    }

    public HashMap<Byte, TrieSolmu> getLapset() {
        return lapset;
    }
    
    
     
}
