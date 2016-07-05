/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.util.StringTokenizer;

/**
 *
 * @author HP
 */
public class Contact {
    String[] field;
    String[] type;
    String[] value;

    public Contact(String source) {
        StringTokenizer st = new StringTokenizer(source,"\n");
        int n = st.countTokens();
        for(int i = 0; i<st.countTokens(); i++) {
            
        }
    }
}
