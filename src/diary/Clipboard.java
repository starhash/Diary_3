/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.util.Vector;

/**
 *
 * @author HP
 */
public class Clipboard {
    Vector v;

    public Clipboard() {
        v = new Vector();
    }
    
    public void add(Object a) {
        v.add(a);
    }
    
    public Object pop() {
        return v.remove(v.size()-1);
    }
    
    public Object[] getAllObjects() {
        Object[] o = new Object[v.size()];
        for(int i = 0; i<v.size(); i++) {
            o[i] = v.elementAt(i);
            System.out.println(o[i]);
        }
        return o;
    }
}
