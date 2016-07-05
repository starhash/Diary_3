/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.awt.HeadlessException;
import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class NewClass extends JFrame {

    static Integer i = 0;

    

    public static void main(String[] args) throws IOException {
        Runtime r = Runtime.getRuntime();
        Process p = r.exec("C:/Program Files/Java/jdk1.6.0_20/bin/javac C:/Users/HP/Documents/Chain.java");

        BufferedReader b2 = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        System.out.println(b2.readLine());
    }
}
