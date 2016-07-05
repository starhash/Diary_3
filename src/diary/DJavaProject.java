/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author HP
 */
public class DJavaProject {
    File main;

    public DJavaProject(File main) {
        this.main = main;
    }
    
    public void create() throws IOException {
        String mpath = main.getAbsolutePath();
        main.mkdirs();
        File src = new File(mpath+"/source");
        src.mkdir();
        src = new File(mpath+"/classes");
        src.mkdir();
        src = new File(mpath+"/jars");
        src.mkdir();
        src = new File(mpath+"/project.djav");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(src)));
        pw.println("<proj-name"+main.getName()+">");
        pw.close();
    }
    
    public void newPackage(String name) {
        String path = main.getAbsolutePath()+"/"+name;
        File f = new File(path);
        f.mkdirs();
    }
    
    public void load(JTree j) {
        TreeModel t = j.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) t.getRoot();
        DefaultMutableTreeNode proj = new DefaultMutableTreeNode(main.getName());
        listNode(proj, main.getAbsolutePath());
        root.add(proj);
        j.setModel(new DefaultTreeModel(root));
    }
    
    public void listNode(DefaultMutableTreeNode d, String pth) {
        File[] f = new File(pth).listFiles();
        for(File fi : f) {
            DefaultMutableTreeNode d1 = new DefaultMutableTreeNode(fi.getName());
            if(fi.isDirectory()) {
                listNode(d1, fi.getAbsolutePath());
            } else {
            }
            d.add(d1);
        }
    }
    
    public void newClass(String name){
        PrintWriter pw = null;
        try {
            String path = main.getAbsolutePath()+"/source/"+name+".java";
            File f = new File(path);
            pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            pw.println(""
                    + "/*"
                    + "\n * project "+main.getName()
                    + "\n */"
                    + "\n\npublic class "+name
                    + "\n{"
                    + "\n\t"
                    + "\n}");
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            pw.close();
        }
    }
}
