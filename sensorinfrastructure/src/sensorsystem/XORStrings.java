/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorsystem;


/**
 *
 * @author triblex
 */
public class XORStrings {

    public String xorHex(String a, String b) {
        // TODO: Validation
        char[] chars = new char[a.length()];
        for (int i = 0; i < chars.length; i++) {
           chars[i] = (char)(((int)(a.charAt(i)))^((int)(b.charAt(i))));
           
        }
        return new String(chars);
    }
}
