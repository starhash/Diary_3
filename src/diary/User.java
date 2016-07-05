/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author HP
 */
public class User {

    public String name;
    public String pwd;
    public boolean match;
    public JTree jTree;

    public User(String name, String pwd) throws IOException {
        File f = new File("C:/Users/Diary/" + name);
        f.mkdirs();
        File f2 = new File("C:/Users/Diary/" + name + "/Messages");
        f2.mkdir();
        File f3 = new File("C:/Users/Diary/" + name + "/Messages/Inbox");
        f3.mkdir();
        File f4 = new File("C:/Users/Diary/" + name + "/Messages/Outbox");
        f4.mkdir();
        File f5 = new File("C:/Users/Diary/" + name + "/Contacts");
        f5.mkdir();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/" + name + ".pwd")));
        pw.println(new JuliusCrypter().crypt(pwd));
        pw.close();
        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Documents.docs")));
        pw.println();
        pw.close();
        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Pictures.pics")));
        pw.println();
        pw.close();
        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Videos.vids")));
        pw.println();
        pw.close();
        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/FavMusic.m3u")));
        pw.println();
        pw.close();
        pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Creations.m3u")));
        pw.println();
        pw.close();
        System.out.println("User Created");
    }

    public User(String name, String pwd, JTree j) {
        BufferedReader br = null;
        jTree = j;
        try {
            this.name = name;
            this.pwd = pwd;
            br = new BufferedReader(new FileReader(new File("C:\\Users\\Diary\\" + name + "\\" + name + ".pwd")));
            if (br.readLine().equals(new JuliusCrypter().crypt(pwd))) {
                list(j);
                match = true;
            } else {
                System.out.println("User name or password incorrect!");
            }
        } catch (IOException ex) {
            System.out.println("User name or password incorrect!");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("User name or password incorrect!");
            }
        }
    }

    public void createMe() {
        PrintWriter pw = null;
        try {
            if (!(new File("C:\\Users\\Diary").exists())) {
                boolean mkdir = new File("C:\\Users\\Diary").mkdir();
                System.out.println(mkdir);
            }
            File f = new File("C:\\Users\\Diary\\" + name);
            f.mkdir();
            File f2 = new File("C:\\Users\\Diary\\" + name + "\\" + name + ".pwd");
            pw = new PrintWriter(new BufferedWriter(new FileWriter(f2)));
            pw.println(new JuliusCrypter().crypt(pwd));
        } catch (IOException ex) {
        } finally {
            pw.close();
        }
    }

    public void addToday(String txt) {
        PrintWriter pw = null;
        try {
            Date d = new Date();
            String day = d.toString().replaceAll(":", ".");
            System.out.println(day);
            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Diary\\" + name + "\\" + day)));
            pw.println(txt);
        } catch (IOException ex) {
        } finally {
            pw.close();
        }
        list(jTree);
    }

    public void saveLog(String txt, String day) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Diary\\" + name + "\\" + day)));
            pw.println(txt);
        } catch (IOException ex) {
        } finally {
            pw.close();
        }
    }

    public void removeLog(String day) {
        String dd = "C:\\Users\\Diary\\" + name + "\\" + day;
        File f = new File(dd);
        boolean delete = f.delete();
        System.out.println(delete);
        list(jTree);
    }

    public Date parse(String s) {
        System.out.println(s);
        s = s.substring(s.indexOf(" ") + 1);
        String mnth = s.substring(0, s.indexOf(" "));
        s = s.substring(s.indexOf(" ") + 1);
        int year = Integer.parseInt(s.substring(s.lastIndexOf(" ") + 1)) - 1900;
        s = s.substring(0, s.lastIndexOf(" "));
        s = s.substring(0, s.lastIndexOf(" "));
        int day = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s = s.substring(s.indexOf(" ") + 1);
        StringTokenizer st = new StringTokenizer(s, ":");
        String hr = st.nextToken();
        String min = st.nextToken();
        String sec = st.nextToken();
        int hri = Integer.parseInt(hr);
        int mini = Integer.parseInt(min);
        int seci = Integer.parseInt(sec);
        System.out.println(year + " " + s);
        int mnt = 0;
        if (mnth.equals("Jan")) {
            mnt = 1;
        } else if (mnth.equals("Feb")) {
            mnt = 2;
        } else if (mnth.equals("Mar")) {
            mnt = 3;
        } else if (mnth.equals("Apr")) {
            mnt = 4;
        } else if (mnth.equals("May")) {
            mnt = 5;
        } else if (mnth.equals("Jun")) {
            mnt = 6;
        } else if (mnth.equals("Jul")) {
            mnt = 7;
        } else if (mnth.equals("Aug")) {
            mnt = 8;
        } else if (mnth.equals("Sep")) {
            mnt = 9;
        } else if (mnth.equals("Oct")) {
            mnt = 10;
        } else if (mnth.equals("Nov")) {
            mnt = 11;
        } else if (mnth.equals("Dec")) {
            mnt = 12;
        }
        return new Date(year, mnt - 1, day, hri, mini, seci);
    }

    public void list(JTree j) {
        j.removeAll();
        File[] list = new File("C:\\Users\\Diary\\" + name).listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (new File(dir.getAbsolutePath() + "/" + name).isDirectory()) {
                    return false;
                } else {
                    if (name.endsWith(".pwd") || name.endsWith(".m3u")
                            || name.endsWith(".pics") || name.endsWith(".docs")
                            || name.endsWith(".vids")) {
                        return false;
                    }
                }
                return true;
            }
        });
        String[] n = new String[list.length];
        DefaultMutableTreeNode year = new DefaultMutableTreeNode("Year", true);
        String years = "";
        for (int i = 0; i < list.length; i++) {
            n[i] = list[i].getName();
            System.out.println(n[i]);
            Date d = parse(n[i].replace('.', ':'));
            if (years.contains(d.getYear() + "")) {
            } else {
                years += d.getYear() + " ";
            }
        }
        String sy = "";
        StringTokenizer st = new StringTokenizer(years.trim(), " ");
        System.out.println(years + " y");
        int nn = st.countTokens();
        for (int i = 0; i < nn; i++) {
            final String cyr = st.nextToken();
            System.out.println(i + " " + cyr);
            DefaultMutableTreeNode cyrn = new DefaultMutableTreeNode((Integer.parseInt(cyr) + 1900));
            File[] liyr = new File("C:\\Users\\Diary\\" + name).listFiles();
            String months = "";
            DefaultMutableTreeNode[] m = {new DefaultMutableTreeNode("Jan"),
                new DefaultMutableTreeNode("Feb"),
                new DefaultMutableTreeNode("Mar"),
                new DefaultMutableTreeNode("Apr"),
                new DefaultMutableTreeNode("May"),
                new DefaultMutableTreeNode("Jun"),
                new DefaultMutableTreeNode("Jul"),
                new DefaultMutableTreeNode("Aug"),
                new DefaultMutableTreeNode("Sep"),
                new DefaultMutableTreeNode("Oct"),
                new DefaultMutableTreeNode("Nov"),
                new DefaultMutableTreeNode("Dec")
            };

            for (int k = 0; k < list.length; k++) {
                Date d = parse(list[k].getName().replace('.', ':'));
                DefaultMutableTreeNode ad = new DefaultMutableTreeNode(list[k].getName());
                if (cyr.equals(d.getYear() + "")) {
                    m[d.getMonth()].add(ad);
                }
            }
            for (int ff = 0; ff < 12; ff++) {
                cyrn.add(m[ff]);
            }
            year.add(cyrn);
        }
        DefaultTreeModel m = new DefaultTreeModel(year);

        j.setModel(m);
    }

    public String read(File f) {
        String ss = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s = "";
            while ((s = br.readLine()) != null) {
                ss += s + "\n";
            }
            br.close();
        } catch (IOException ex) {
        }
        System.out.println(ss);
        return ss;
    }

    public String readLog(String day) {
        return read(new File("C:\\Users\\Diary\\" + name + "\\" + day));
    }

    public void addDocsFolder(String text) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Documents.docs", true)));
            pw.append(text+"\n");
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void addMusicFolder(String text) {
        File f = new File(name);
        if (f.isDirectory()) {
            File[] ff = f.listFiles();
            for (int i = 0; i < ff.length; i++) {
                if (ff[i].isDirectory()) {
                    addMusicFolder(ff[i].getAbsolutePath());
                } else {
                    addMusicFile(ff[i].getAbsolutePath());
                }
            }
        }
    }

    void addPicsFolder(String text) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Pictures.pics", true)));
            pw.append(text+"\n");
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void addVidsFolder(String text) {
        File f = new File(name);
        if (f.isDirectory()) {
            File[] ff = f.listFiles();
            for (int i = 0; i < ff.length; i++) {
                addVideoFile(ff[i].getAbsolutePath());
            }
        }
    }

    void addMusicFile(String text) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/FavMusic.m3u", true)));
            pw.append(text+"\n");
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void addCreationsFolder(String text) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Creations.m3u", true)));
            pw.append(text+"\n");
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    void addVideoFile(String absolutePath) {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Diary/" + name + "/Videos.vids", true)));
            pw.append("\n" + absolutePath);
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void loadMessages(JList in, JList out) {
        File[] fl = new File("C:/Users/Diary/" + name + "/Messages/Inbox").listFiles();
        DefaultListModel d = new DefaultListModel();
        for (int i = 0; i < fl.length; i++) {
            d.addElement(fl[i].getName());
        }
        in.setModel(d);
        File[] fl2 = new File("C:/Users/Diary/" + name + "/Messages/Outbox").listFiles();
        DefaultListModel d2 = new DefaultListModel();
        for (int i = 0; i < fl2.length; i++) {
            d.addElement(fl2[i].getName());
        }
        out.setModel(d2);
    }
}
