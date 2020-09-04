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
public class Tekstinkasittelija {
    
    
    
    public char[] chars(String text){
        char[] chars = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 97 && text.charAt(i) <= 104){
                chars[i] = text.charAt(i);
            }
        }
        System.out.println(chars);
        return chars;
    }
    
}
