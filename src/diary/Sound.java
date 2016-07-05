/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author HP
 */
public class Sound {
    DiaryApp d;

    public Sound(DiaryApp d) {
        this.d = d;
    }
    
    public void play(String s) {
        AudioClip audioClip;
        try {
            audioClip = Applet.newAudioClip(new URL("file:///C:/Program Files/Diary/Themes/" + d.theme + "/" + s + ".wav"));
            audioClip.play();
            audioClip.play();
            System.out.println("file:///C:/Program Files/Diary/Themes/" + d.theme + "/" + s + ".wav\nPlayed!");
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
    }
}
