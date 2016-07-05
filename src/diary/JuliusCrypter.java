/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

/**
 *
 * @author HP
 */
public class JuliusCrypter {

    public String crypt(String code) {
        if(code.length()==0) {
            return "";
        } else if(code.length()>=1 && code.length()<=3) {
            String a = "";
            for(int i = code.length()-1; i>=0; i--) {
                a+=code.charAt(i);
            }
            return a;
        }
        String ecode = "";
        int len = code.length();
        int a = 0;
        for (int i = 0; Math.pow(i, 2) < len; i++) {
            a = i;
        }
        int left = len - (int) (Math.pow(a, 2));
        int sub = len - left;
        System.out.println(sub);
        String subcode = code.substring(sub);
        String subecode = crypt(subcode);
        int n = 0;
        char[][] jcode = new char[a][a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                jcode[j][i] = code.charAt(n);
                n++;
            }
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                ecode += jcode[i][j];
            }
        }
        ecode = ecode + subecode;
        return ecode;
    }
}
