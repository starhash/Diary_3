/*
 * To change th

 @Override


 @Override
 public boolean accept(File f) {
 throw new UnsupportedOperationException("Not supported yet.");
 }

 @Override
 public String getDescription() {
 throw new UnsupportedOperationException("Not supported yet.");
 }
 }           public boolean accept(File pathname) {
 throw new UnsupportedOperationException("Not supported yet.");
 }
 }s template, choose Tools | Templates
 * and openInIDE the template in the editor.
 */
package diary;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import de.javasoft.plaf.synthetica.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.TextUI;
import javax.swing.text.DefaultTextUI;
import javax.swing.tree.*;
import sun.awt.Win32GraphicsEnvironment;

/**
 *
 * @author HP
 */
public class DiaryApp extends javax.swing.JFrame {

    private boolean isGrenglish;
    ActivityListener a;
    Image img;
    DeskChanger deskChanger;
    java.util.Timer deskChangerTimer;
    java.util.Timer deskDivideSweeper;
    Clipboard c;
    TrayIcon trayIcon;

    /**
     * Creates new form DiaryApp
     */
    public DiaryApp() {
        initDiary();
        img = new ImageIcon("C:/Program Files/Diary/Pictures/3D8.jpg").getImage();
        a = new ActivityListener();
        initComponents();
        u = new User("Guest", "", jTree1);
        u.createMe();
        logInWn.setBounds(logInWn.getX(), logInWn.getY(), 191, 190);
        dayLogList.hide();
        logWin.hide();
        allmusic.setLayout(new BoxLayout(allmusic, BoxLayout.Y_AXIS));
        creations.setLayout(new BoxLayout(creations, BoxLayout.Y_AXIS));
        videos.setLayout(new BoxLayout(videos, BoxLayout.Y_AXIS));
        c = new Clipboard();
        listFavMusic();
        listAllPics();
        listAllDocs();
        listFavVideos();
        u.loadMessages(inbox, outbox);
        DefaultTreeCellRenderer dc = new DefaultTreeCellRenderer();
        dc.setLeafIcon(jButton8.getIcon());
        dc.setOpenIcon(dwn.getIcon());
        dc.setClosedIcon(rgt.getIcon());
        picsTree.setCellRenderer(dc);
        desk.getGraphics().setColor(Color.WHITE);
        desk.getGraphics().drawString("Hello!", 100, 100);
        play("Logon Sound");
        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getHeight(), 94);
        deskSplit.setDividerLocation(95);
        d = new DeskDivideSweeper();
        loadThemes();
        LookAndFeelInfo[] l = UIManager.getInstalledLookAndFeels();
        appears = new String[l.length];
        for (int i = 0; i < l.length; i++) {
            String s = l[i].getName();
            appearance.addItem(s);
            appears[i] = l[i].getClassName();
        }
        a.started();
        wallIndex = 0;
        userName.setText(lastuser);
        File[] f = new File("C:/Program Files/Diary/Pictures").listFiles();
        for (File f1 : f) {
            wall.addItem(f1.getName());
        }
        jButton16.hide();
        loadDesk();
        openProjects = new Vector();
        delay = new Integer(10000);
        deskChanger = new DeskChanger();
        deskChangerTimer = new java.util.Timer();
        INITED = true;
        lookAndFeelTimer = new Timer();
        lookAndFeelTimer.schedule(new LookAndFeelManager(), 0, 500);
        
        trayIcon = new TrayIcon(new ImageIcon("C:/Program Files/Diary/Diary.png").getImage());
        trayIcon.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isVisible()) {
                    hide();
                } else {
                    show();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        SystemTray st = SystemTray.getSystemTray();
        try {
            st.add(trayIcon);
        } catch (AWTException ex) {
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }
    boolean INITED = false;
    Integer delay;
    public String[] appears;
    Timer lookAndFeelTimer;

    public void listFavMusic() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Diary\\" + u.name + "\\FavMusic.m3u")));
            boolean eof = false;
            while (!eof) {
                try {
                    String s = br.readLine();
                    if (new File(s).exists()) {
                        System.out.println(s);
                        final JButton b = new JButton(s.substring(s.lastIndexOf("\\") + 1));
                        b.setToolTipText(s);
                        b.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Runtime r = Runtime.getRuntime();
                                try {
                                    Process p = r.exec("explorer " + b.getToolTipText());
                                } catch (IOException ex) {
                                }
                            }
                        });
                        b.setIcon(jTabbedPane3.getIconAt(0));
                        allmusic.add(b);
                    }
                } catch (Exception ex) {
                    eof = true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        listMusicCreations();
    }

    public void listFavVideos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Diary\\" + u.name + "\\Videos.vids")));
            boolean eof = false;
            while (!eof) {
                try {
                    String s = br.readLine();
                    if (new File(s).exists()) {
                        System.out.println(s);
                        final JButton b = new JButton(s.substring(s.lastIndexOf("\\") + 1));
                        b.setToolTipText(s);
                        b.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Runtime r = Runtime.getRuntime();
                                try {
                                    Process p = r.exec("explorer " + b.getToolTipText());
                                } catch (IOException ex) {
                                }
                            }
                        });
                        b.setIcon(jTabbedPane1.getIconAt(1));
                        videos.add(b);
                    }
                } catch (Exception ex) {
                    eof = true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void listMusicCreations() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Diary\\" + u.name + "\\Creations.m3u")));
            boolean eof = false;
            while (!eof) {
                try {
                    String s = br.readLine();
                    System.out.println(s);
                    if (new File(s).exists()) {
                        System.out.println(s);
                        final JButton b = new JButton(s.substring(s.lastIndexOf("\\") + 1));
                        b.setToolTipText(s);
                        b.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Runtime r = Runtime.getRuntime();
                                try {
                                    Process p = r.exec("explorer " + b.getToolTipText());
                                } catch (IOException ex) {
                                }
                            }
                        });
                        b.setIcon(jTabbedPane3.getIconAt(1));
                        creations.add(b);
                    }
                } catch (Exception ex) {
                    eof = true;
                    System.out.println("EOF");
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dwn = new javax.swing.JButton();
        rgt = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        removeFolder = new javax.swing.JMenuItem();
        addFolder = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        deskPopup = new javax.swing.JPopupMenu();
        viewIcons = new javax.swing.JCheckBoxMenuItem();
        changeDeskWallpaper = new javax.swing.JCheckBoxMenuItem();
        input = new javax.swing.JDialog();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        message = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        deskSplit = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        themes = new javax.swing.JList();
        sounds = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        appearance = new javax.swing.JComboBox();
        setThemeBtn = new javax.swing.JButton();
        wall = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jdk = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        activity = new javax.swing.JTextArea();
        desk = new javax.swing.JDesktopPane() {

            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.drawImage(img, 0, 0, null);
            }
        };
        logWin = new javax.swing.JInternalFrame();
        jSplitPane2 = new javax.swing.JSplitPane();
        toolLog = new javax.swing.JToolBar();
        addToday = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        log = new javax.swing.JTextArea();
        dayLogList = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        logInWn = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        userPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        newUserCheck = new javax.swing.JCheckBox();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jFileChooser1 = new javax.swing.JFileChooser();
        docs = new javax.swing.JInternalFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        docsDesk = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        picsTree = new javax.swing.JTree();
        pics = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        allmusic = new javax.swing.JDesktopPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        creations = new javax.swing.JDesktopPane();
        videos = new javax.swing.JDesktopPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        path = new javax.swing.JTextField();
        type = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        messages = new javax.swing.JInternalFrame();
        jSplitPane4 = new javax.swing.JSplitPane();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        inbox = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        outbox = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jToolBar3 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        javaIDE = new javax.swing.JInternalFrame();
        jSplitPane7 = new javax.swing.JSplitPane();
        jToolBar2 = new javax.swing.JToolBar();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        openFiles = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        chooseFile1 = new javax.swing.JInternalFrame();
        jFileChooser3 = new javax.swing.JFileChooser();
        chooseFile = new javax.swing.JInternalFrame();
        jFileChooser2 = new javax.swing.JFileChooser();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jScrollPane12 = new javax.swing.JScrollPane();
        terminal = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        logInMenuItem = new javax.swing.JMenuItem();
        logOutMenuItem = new javax.swing.JMenuItem();
        createNewMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        dwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1downarrow.png"))); // NOI18N
        dwn.setText("jButton9");

        rgt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1rightarrow.png"))); // NOI18N
        rgt.setText("jButton9");

        removeFolder.setText("Remove");
        removeFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFolderActionPerformed(evt);
            }
        });
        jPopupMenu1.add(removeFolder);

        addFolder.setText("Add");
        addFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFolderActionPerformed(evt);
            }
        });
        jPopupMenu1.add(addFolder);

        jPopupMenu2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/view-fullscreen.png"))); // NOI18N
        jMenuItem1.setText("Set As Desk Background");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jPopupMenu2.add(jMenuItem1);

        viewIcons.setSelected(true);
        viewIcons.setText("View Desk Icons");
        viewIcons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewIconsMouseClicked(evt);
            }
        });
        viewIcons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewIconsActionPerformed(evt);
            }
        });
        deskPopup.add(viewIcons);

        changeDeskWallpaper.setText("Auto Change Desk Wallpaper");
        changeDeskWallpaper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changeDeskWallpaperMouseClicked(evt);
            }
        });
        changeDeskWallpaper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDeskWallpaperActionPerformed(evt);
            }
        });
        deskPopup.add(changeDeskWallpaper);

        javax.swing.GroupLayout inputLayout = new javax.swing.GroupLayout(input.getContentPane());
        input.getContentPane().setLayout(inputLayout);
        inputLayout.setHorizontalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        inputLayout.setVerticalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Close (Ctrl + Q)");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuItem7.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem7MenuKeyPressed(evt);
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jPopupMenu3.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setText("Close All Ctrl + Shift + Q)");
        jPopupMenu3.add(jMenuItem8);

        message.setMinimumSize(new java.awt.Dimension(370, 120));

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        jButton16.setText("jButton8");

        javax.swing.GroupLayout messageLayout = new javax.swing.GroupLayout(message.getContentPane());
        message.getContentPane().setLayout(messageLayout);
        messageLayout.setHorizontalGroup(
            messageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messageLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(ok)
                .addGap(24, 24, 24)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancel)
                .addContainerGap(87, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        messageLayout.setVerticalGroup(
            messageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(messageLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(messageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ok)
                    .addComponent(cancel)
                    .addComponent(jButton16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Diary");
        setUndecorated(true);
        setState(6);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        deskSplit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deskSplitMouseClicked(evt);
            }
        });

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("tab_mouse_over_fill_bright_upper"));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tools", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM));
        jPanel1.setMinimumSize(new java.awt.Dimension(98, 418));
        jPanel1.setPreferredSize(new java.awt.Dimension(98, 418));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });

        jButton1.setText("Log In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setText("Files");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setText(" Docs ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jPanel8.setOpaque(false);

        jTabbedPane4.setMinimumSize(new java.awt.Dimension(0, 0));
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(0, 0));

        jScrollPane10.setOpaque(false);

        jPanel9.setOpaque(false);

        jLabel5.setText("Themes :");

        themes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                themesValueChanged(evt);
            }
        });
        jScrollPane11.setViewportView(themes);

        sounds.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                soundsItemStateChanged(evt);
            }
        });

        jLabel4.setText("Appearance :");

        appearance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appearanceMouseClicked(evt);
            }
        });
        appearance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                appearanceItemStateChanged(evt);
            }
        });
        appearance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appearanceActionPerformed(evt);
            }
        });

        setThemeBtn.setText("Set");
        setThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setThemeBtnActionPerformed(evt);
            }
        });

        wall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wallMouseClicked(evt);
            }
        });
        wall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wallActionPerformed(evt);
            }
        });

        jLabel6.setText("Wall Paper :");

        jButton15.setText("Set JDK");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerListModel(new String[] {"5 sec.", "10 sec.", "20 sec.", "30 sec.", "1 min.", "2 min.", "5 min.", "10 min.", "20 min.", "30 min.", "40 min.", "50 min.", "1 hr.", "2 hr."}));
        jSpinner1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinner1PropertyChange(evt);
            }
        });

        jLabel7.setText("Change Desk Wallpaper TIming :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(setThemeBtn))
                            .addComponent(appearance, 0, 181, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(sounds, 0, 181, Short.MAX_VALUE)
                            .addComponent(wall, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdk, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15)))
                    .addComponent(jLabel6))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(setThemeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(appearance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sounds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(wall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel9);

        jTabbedPane4.addTab("Settings", jScrollPane10);

        activity.setColumns(20);
        activity.setRows(5);
        jScrollPane7.setViewportView(activity);

        jTabbedPane4.addTab("Activity Log", jScrollPane7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addGap(0, 344, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        deskSplit.setLeftComponent(jPanel1);

        desk.setComponentPopupMenu(deskPopup);
        desk.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        desk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deskMouseClicked(evt);
            }
        });
        desk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskKeyPressed(evt);
            }
        });

        logWin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Log", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.ABOVE_TOP));
        logWin.setIconifiable(true);
        logWin.setMaximizable(true);
        logWin.setResizable(true);
        logWin.setTitle("Log");
        logWin.setEnabled(false);
        logWin.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/document-properties.png"))); // NOI18N
        logWin.setName("logWIn"); // NOI18N
        logWin.setVisible(false);

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setOpaque(false);

        toolLog.setFloatable(false);
        toolLog.setRollover(true);

        addToday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/gtk-add.png"))); // NOI18N
        addToday.setToolTipText("Add Log For Today");
        addToday.setFocusable(false);
        addToday.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addToday.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTodayActionPerformed(evt);
            }
        });
        toolLog.add(addToday);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/document-save.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        toolLog.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit-delete.png"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        toolLog.add(jButton4);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/rebuild.png"))); // NOI18N
        jToggleButton1.setToolTipText("<html>\n<body>\nConverts the log contents to <B><I>Grenglish</I></B> : A script developed by <B>Harsh Srivastava</B>.");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        toolLog.add(jToggleButton1);

        jSplitPane2.setTopComponent(toolLog);

        log.setColumns(20);
        log.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        log.setRows(5);
        log.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                logKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(log);

        jSplitPane2.setRightComponent(jScrollPane1);

        javax.swing.GroupLayout logWinLayout = new javax.swing.GroupLayout(logWin.getContentPane());
        logWin.getContentPane().setLayout(logWinLayout);
        logWinLayout.setHorizontalGroup(
            logWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
        );
        logWinLayout.setVerticalGroup(
            logWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        logWin.setBounds(280, 50, 300, 250);
        desk.add(logWin, javax.swing.JLayeredPane.DEFAULT_LAYER);

        dayLogList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Diary Day logs", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.ABOVE_TOP));
        dayLogList.setIconifiable(true);
        dayLogList.setMaximizable(true);
        dayLogList.setResizable(true);
        dayLogList.setTitle("Diary Day Logs");
        dayLogList.setEnabled(false);
        dayLogList.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit-select-all.png"))); // NOI18N
        dayLogList.setName("dayLogList"); // NOI18N
        dayLogList.setVisible(false);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Year");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setEditable(true);
        jTree1.setEnabled(false);
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout dayLogListLayout = new javax.swing.GroupLayout(dayLogList.getContentPane());
        dayLogList.getContentPane().setLayout(dayLogListLayout);
        dayLogListLayout.setHorizontalGroup(
            dayLogListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        dayLogListLayout.setVerticalGroup(
            dayLogListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );

        dayLogList.setBounds(30, 50, 180, 320);
        desk.add(dayLogList, javax.swing.JLayeredPane.DEFAULT_LAYER);

        logInWn.setTitle("Log IN :");
        logInWn.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/dialog-password.png"))); // NOI18N
        logInWn.setName("logInWin"); // NOI18N
        logInWn.setOpaque(true);
        try {
            logInWn.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        logInWn.setVisible(false);

        jButton2.setText("Log In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Password :");

        jLabel1.setText("User Name :");

        newUserCheck.setText("New User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userName)
                    .addComponent(userPassword)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(newUserCheck)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(newUserCheck))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout logInWnLayout = new javax.swing.GroupLayout(logInWn.getContentPane());
        logInWn.getContentPane().setLayout(logInWnLayout);
        logInWnLayout.setHorizontalGroup(
            logInWnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        logInWnLayout.setVerticalGroup(
            logInWnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        logInWn.setBounds(140, 140, 200, 170);
        desk.add(logInWn, javax.swing.JLayeredPane.POPUP_LAYER);

        jInternalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Explorer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP));
        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Explorer");
        jInternalFrame1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jInternalFrame1.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/system-search.png"))); // NOI18N
        jInternalFrame1.setVisible(false);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jInternalFrame1.setBounds(50, 40, 510, 390);
        desk.add(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        docs.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Panel", javax.swing.border.TitledBorder.TRAILING, javax.swing.border.TitledBorder.BOTTOM));
        docs.setIconifiable(true);
        docs.setMaximizable(true);
        docs.setResizable(true);
        docs.setTitle("\nPersonal Files & Music");
        docs.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bookmark_toolbar.png"))); // NOI18N
        docs.setName("docs"); // NOI18N
        docs.setVisible(false);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jTabbedPane2.addTab("Documents", new javax.swing.ImageIcon(getClass().getResource("/resources/applications-other13.png")), docsDesk); // NOI18N

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1leftarrow.png"))); // NOI18N
        jButton6.setText("Back");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton6);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/applications-photography3.png"))); // NOI18N
        jButton8.setText("Pictures");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1rightarrow.png"))); // NOI18N
        jButton7.setText("Forward");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pictures"));

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Pictures");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("A.jpg");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("B");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("C.png");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("D.gif");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        picsTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        picsTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                picsTreeValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(picsTree);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
        );

        jSplitPane3.setLeftComponent(jPanel4);

        jLabel3.setComponentPopupMenu(jPopupMenu2);
        pics.setViewportView(jLabel3);

        jSplitPane3.setRightComponent(pics);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
            .addComponent(jSplitPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane3))
        );

        jTabbedPane2.addTab("Pictures", new javax.swing.ImageIcon(getClass().getResource("/resources/applications-photography3.png")), jPanel3); // NOI18N

        jTabbedPane1.addTab("Files", new javax.swing.ImageIcon(getClass().getResource("/resources/fileimport.png")), jTabbedPane2); // NOI18N

        jScrollPane4.setViewportView(allmusic);

        jTabbedPane3.addTab("All Music", new javax.swing.ImageIcon(getClass().getResource("/resources/stock_music-librar3.png")), jScrollPane4); // NOI18N

        jScrollPane5.setViewportView(creations);

        jTabbedPane3.addTab("Creations", new javax.swing.ImageIcon(getClass().getResource("/resources/applications-office3.png")), jScrollPane5); // NOI18N

        jTabbedPane1.addTab("Music", new javax.swing.ImageIcon(getClass().getResource("/resources/stock_music-librar2.png")), jTabbedPane3); // NOI18N
        jTabbedPane1.addTab("Videos", new javax.swing.ImageIcon(getClass().getResource("/resources/applications-photography2.png")), videos); // NOI18N

        jInternalFrame2.setIconifiable(true);
        jInternalFrame2.setTitle("Add Folder");
        jInternalFrame2.setVisible(true);

        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Documents", "Pictures", "Videos", "Music", "Own Creations" }));

        jButton9.setText("Add");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(path)
                            .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jButton9)
                        .addGap(0, 147, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jInternalFrame2.setBounds(30, 20, 370, 150);
        jDesktopPane2.add(jInternalFrame2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.addTab("Add", new javax.swing.ImageIcon(getClass().getResource("/resources/add-folder-to-archive.png")), jDesktopPane2); // NOI18N

        javax.swing.GroupLayout docsLayout = new javax.swing.GroupLayout(docs.getContentPane());
        docs.getContentPane().setLayout(docsLayout);
        docsLayout.setHorizontalGroup(
            docsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        docsLayout.setVerticalGroup(
            docsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        docs.setBounds(20, 10, 490, 380);
        desk.add(docs, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            docs.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        messages.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        messages.setIconifiable(true);
        messages.setMaximizable(true);
        messages.setTitle("Messenger");
        messages.setName("messages"); // NOI18N
        messages.setVisible(false);

        jSplitPane4.setDividerLocation(150);
        jSplitPane4.setDividerSize(1);

        inbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inboxMouseClicked(evt);
            }
        });
        inbox.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                inboxAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane8.setViewportView(inbox);

        jTabbedPane5.addTab("Inbox", jScrollPane8);

        jScrollPane9.setViewportView(outbox);

        jTabbedPane5.addTab("Outbox", jScrollPane9);

        jSplitPane4.setLeftComponent(jTabbedPane5);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jToolBar3.setFloatable(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/filenew.png"))); // NOI18N
        jButton11.setToolTipText("New Message");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(jButton11);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/gtk-remove.png"))); // NOI18N
        jButton12.setToolTipText("Delete Message");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(jButton12);

        jSplitPane5.setTopComponent(jToolBar3);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 535, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        jSplitPane5.setRightComponent(jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(192, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(191, Short.MAX_VALUE)))
        );

        jSplitPane4.setRightComponent(jPanel7);

        javax.swing.GroupLayout messagesLayout = new javax.swing.GroupLayout(messages.getContentPane());
        messages.getContentPane().setLayout(messagesLayout);
        messagesLayout.setHorizontalGroup(
            messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );
        messagesLayout.setVerticalGroup(
            messagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
        );

        messages.setBounds(10, 10, 693, 465);
        desk.add(messages, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            messages.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        javaIDE.setIconifiable(true);
        javaIDE.setMaximizable(true);
        javaIDE.setResizable(true);
        javaIDE.setTitle("JAVA IDE");
        javaIDE.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/gtk-indent-ltr.png"))); // NOI18N
        javaIDE.setName("javaIDE"); // NOI18N
        try {
            javaIDE.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        javaIDE.setVisible(false);

        jSplitPane7.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jToolBar2.setRollover(true);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add-folder-to-archive.png"))); // NOI18N
        jButton18.setToolTipText("New Project (Ctrl + N)");
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton18);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/filenew.png"))); // NOI18N
        jButton19.setToolTipText("New File (Ctrl + Shift + N)");
        jButton19.setFocusable(false);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton19);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fileimport.png"))); // NOI18N
        jButton20.setToolTipText("Open Project (Ctrl + O)");
        jButton20.setFocusable(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton20);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/edit-select-all.png"))); // NOI18N
        jButton21.setToolTipText("Open File (Ctrl + Shift + O)");
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton21);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/gtk-no.png"))); // NOI18N
        jButton14.setFocusable(false);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton14);

        jSplitPane7.setTopComponent(jToolBar2);

        jSplitPane1.setDividerLocation(110);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Projects");
        jTree2.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree2MouseClicked(evt);
            }
        });
        jTree2.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree2ValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(jTree2);

        jSplitPane1.setLeftComponent(jScrollPane6);

        openFiles.setComponentPopupMenu(jPopupMenu3);
        jSplitPane1.setRightComponent(openFiles);

        jSplitPane7.setRightComponent(jSplitPane1);

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("New Project");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("New File");
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Open Project");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Open File");
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator2);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("Exit");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        javaIDE.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout javaIDELayout = new javax.swing.GroupLayout(javaIDE.getContentPane());
        javaIDE.getContentPane().setLayout(javaIDELayout);
        javaIDELayout.setHorizontalGroup(
            javaIDELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );
        javaIDELayout.setVerticalGroup(
            javaIDELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
        );

        javaIDE.setBounds(30, 20, 510, 390);
        desk.add(javaIDE, javax.swing.JLayeredPane.DEFAULT_LAYER);

        chooseFile1.setTitle("New :");
        chooseFile1.setName("chooseFile"); // NOI18N
        chooseFile1.setVisible(false);

        jFileChooser3.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        jFileChooser3.setApproveButtonText("Create");
        jFileChooser3.setApproveButtonToolTipText("Create New");
        jFileChooser3.setDialogTitle("New :");
        jFileChooser3.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser3.setMultiSelectionEnabled(true);
        jFileChooser3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser3ActionPerformed(evt);
            }
        });
        jFileChooser3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFileChooser3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout chooseFile1Layout = new javax.swing.GroupLayout(chooseFile1.getContentPane());
        chooseFile1.getContentPane().setLayout(chooseFile1Layout);
        chooseFile1Layout.setHorizontalGroup(
            chooseFile1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        chooseFile1Layout.setVerticalGroup(
            chooseFile1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        chooseFile1.setBounds(0, 0, 566, 535);
        desk.add(chooseFile1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        chooseFile.setTitle("Open :");
        chooseFile.setName("chooseFile"); // NOI18N
        chooseFile.setVisible(false);

        jFileChooser2.setDialogTitle("Open :");
        jFileChooser2.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser2.setMultiSelectionEnabled(true);
        jFileChooser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser2ActionPerformed(evt);
            }
        });
        jFileChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFileChooser2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout chooseFileLayout = new javax.swing.GroupLayout(chooseFile.getContentPane());
        chooseFile.getContentPane().setLayout(chooseFileLayout);
        chooseFileLayout.setHorizontalGroup(
            chooseFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        chooseFileLayout.setVerticalGroup(
            chooseFileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
        );

        chooseFile.setBounds(0, 0, 566, 519);
        desk.add(chooseFile, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrame3.setClosable(true);
        jInternalFrame3.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jInternalFrame3.setMaximizable(true);
        jInternalFrame3.setResizable(true);
        jInternalFrame3.setTitle("Terminal");
        jInternalFrame3.setVisible(false);

        terminal.setColumns(20);
        terminal.setRows(5);
        jScrollPane12.setViewportView(terminal);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        jInternalFrame3.setBounds(70, 50, 390, 320);
        desk.add(jInternalFrame3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        deskSplit.setRightComponent(desk);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        userMenu.setText("User");

        logInMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        logInMenuItem.setText("Log In");
        logInMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInMenuItemActionPerformed(evt);
            }
        });
        userMenu.add(logInMenuItem);

        logOutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        logOutMenuItem.setText("Log Out");
        logOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutMenuItemActionPerformed(evt);
            }
        });
        userMenu.add(logOutMenuItem);

        createNewMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        createNewMenuItem.setText("Create New");
        createNewMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewMenuItemActionPerformed(evt);
            }
        });
        userMenu.add(createNewMenuItem);

        menuBar.add(userMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jMenu3.setText("Window");
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem9.setText("Restore");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_COMMA, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem10.setText("Minimize");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PERIOD, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem11.setText("Maximize");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PERIOD, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem13.setText("Minimize To Tray");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem12.setText("Close");
        jMenu3.add(jMenuItem12);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskSplit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskSplit, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        AudioClip audioClip = null;
        fileMenu.hide();
        try {
            audioClip = Applet.newAudioClip(new URL("file:///C:/Users/Diary/Sounds/Shutdown.wav"));
            audioClip.play();
            long i = System.currentTimeMillis();
            while (System.currentTimeMillis() < (i + 5000)) {
            }
            System.out.println("Played!");
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        audioClip.stop();
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void logInMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInMenuItemActionPerformed
        // TODO add your handling code here:
        logInWn.show();
    }//GEN-LAST:event_logInMenuItemActionPerformed

    private void logOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutMenuItemActionPerformed
        // TODO add your handling code here:
        jTree1.removeAll();
        log.setText("");
        docs.hide();
        logWin.hide();
        logInWn.show();
        messages.hide();
        dayLogList.hide();
        u = new User("Guest", "", jTree1);
        u.loadMessages(inbox, outbox);
        listAllDocs();
        listAllPics();
        listFavMusic();
        listFavVideos();
        play("Logoff Sound");
        updateDiarySettings();
    }//GEN-LAST:event_logOutMenuItemActionPerformed
    public String key = "";

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int keyCode = evt.getKeyCode();
        char ch = evt.getKeyChar();
        if (keyCode == KeyEvent.VK_CONTROL) {
            System.out.println("Ctrl");
            key += "Ctrl+";
        }
        if (Character.isDigit(ch) || Character.isLetter(ch)) {
            key += ch + "";
            if (key.equalsIgnoreCase("Ctrl+Q")) {
                this.exitMenuItemActionPerformed(null);
            }
        }
        System.out.println(key);
    }//GEN-LAST:event_formKeyPressed

    private void deskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deskKeyPressed

    private void addTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTodayActionPerformed
        // TODO add your handling code here:
        u.addToday(log.getText());
        u.list(jTree1);
        play("Menu Command");
        a.addedLog();
    }//GEN-LAST:event_addTodayActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        logInWn.show();
        docs.hide();
        messages.hide();
        dayLogList.hide();
        logWin.hide();
        desk.repaint();

        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getHeight(), 98);
        deskSplit.setDividerLocation(98);
    }//GEN-LAST:event_jButton1ActionPerformed
    boolean del;
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        if (!del) {
            DefaultMutableTreeNode d = (DefaultMutableTreeNode) evt.getNewLeadSelectionPath().getLastPathComponent();
            if (evt.getNewLeadSelectionPath().getParentPath() == null) {
                System.out.println("This is not a day log.");
            } else if (evt.getNewLeadSelectionPath().getParentPath().getLastPathComponent().equals("Year")) {
                System.out.println("This is not a day log.");
            } else {
                String dd = d.toString();
                String txt = u.readLog(dd);
                log.setText(txt);
                logWin.setTitle(dd);
                a.viewedLog(dd);
            }
        }
    }//GEN-LAST:event_jTree1ValueChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (newUserCheck.isSelected()) {
            try {
                User a = new User(userName.getText(), userPassword.getText());
                newUserCheck.setSelected(false);
                jButton2ActionPerformed(evt);
                userPassword.setText("");
            } catch (IOException ex) {
                System.err.println(ex);
            }

        } else {
            u = new User(userName.getText(), userPassword.getText(), jTree1);
            if (u.match) {
                logInWn.hide();
                dayLogList.enable();
                jTree1.enable();
                logWin.enable();
                toolLog.enable();
                log.enable();
                dayLogList.show();
                logWin.show();
                docs.show();
                messages.hide();
                listFavMusic();
                listAllPics();
                listMusicCreations();
                listAllDocs();
                listFavVideos();
                u.loadMessages(inbox, outbox);
                desk.repaint();
                play("Logon Sound");
                a.loggedIn();
                userPassword.setText("");
                userName.setText(lastuser);
                updateDiarySettings();
            } else {
                dayLogList.disable();
                jTree1.disable();
                logWin.disable();
                toolLog.disable();
                log.disable();
                dayLogList.hide();
                logWin.hide();
                desk.repaint();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (!logWin.getTitle().equals("Log")) {
            u.saveLog(log.getText(), logWin.getTitle());
            a.updatedLog(logWin.getTitle());
        }
        play("Menu Command");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        del = true;
        String s = jTree1.getLeadSelectionPath().getLastPathComponent().toString();
        System.out.println("---------------->:" + s);
        u.removeLog(s);
        u.list(jTree1);
        del = false;
        play("Recycle");
        a.deletedLog(s);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        String s = evt.getActionCommand();
        System.out.println(s);
        if (s.equals("ApproveSelection")) {
            try {
                Runtime r = Runtime.getRuntime();
                String h = "explorer " + jFileChooser1.getSelectedFile().getAbsolutePath();
                Process p = r.exec(h);
            } catch (IOException ex) {
            }
        } else if (s.equals("CancelSelection")) {
            jInternalFrame1.hide();
        }
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jInternalFrame1.show();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void picsTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_picsTreeValueChanged
        // TODO add your handling code here:
        String s = "";
        Object[] ob = evt.getPath().getPath();

        for (int i = 1; i < ob.length; i++) {
            s += ob[i].toString() + "/";
        }
        s = s.substring(0, s.length() - 1);
        Image i = Toolkit.getDefaultToolkit().getImage(s);
        jLabel3.setIcon(new ImageIcon(i));
    }//GEN-LAST:event_picsTreeValueChanged

    private void removeFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeFolderActionPerformed

    private void addFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFolderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFolderActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        File f = new File(path.getText());
        if (f.exists()) {
            if (type.getSelectedItem().toString().contains("Documents")) {
                u.addDocsFolder(path.getText());
            } else if (type.getSelectedItem().toString().contains("Pictures")) {
                u.addPicsFolder(path.getText());
            } else if (type.getSelectedItem().toString().contains("Videos")
                    & f.isDirectory()) {
                u.addVidsFolder(path.getText());
            } else if (type.getSelectedItem().toString().contains("Videos")
                    & f.isFile()) {
                u.addVideoFile(path.getText());
            } else if (type.getSelectedItem().toString().contains("Music")
                    & f.isDirectory()) {
                u.addMusicFolder(path.getText());
            } else if (type.getSelectedItem().toString().contains("Music")
                    & f.isFile()) {
                u.addMusicFile(path.getText());
            } else if (type.getSelectedItem().toString().contains("Own Creations")) {
                u.addCreationsFolder(path.getText());
            }
        }
        listAllDocs();
        listAllPics();
        listFavMusic();
        listFavVideos();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void logKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_logKeyTyped
        // TODO add your handling code here:
        if (isGrenglish) {
            String s = log.getText().toLowerCase();
            s = s.replace('a', 'α');
            s = s.replace('b', 'β');
            s = s.replaceAll("ʗﺡ", "χ");
            s = s.replaceAll("ch", "χ");
            s = s.replace('c', 'ʗ');
            s = s.replace('d', 'δ');
            s = s.replaceAll("ee", "η");
            s = s.replaceAll("ηη", "η");
            s = s.replace('e', 'ε');
            s = s.replace('f', 'ڡ');
            s = s.replace('g', 'γ');
            s = s.replace('h', 'ﺡ');
            s = s.replace('i', 'ί');
            s = s.replace('j', 'ƒ');
            s = s.replace('k', 'κ');
            s = s.replace('l', 'λ');
            s = s.replace('m', 'μ');
            s = s.replace('n', 'ν');
            s = s.replaceAll("oo", "ο");
            s = s.replaceAll("οο", "ο");
            s = s.replace('o', 'ω');
            s = s.replaceAll("πﺡ", "φ");
            s = s.replaceAll("ph", "φ");
            s = s.replaceAll("πσ", "ψ");
            s = s.replaceAll("ps", "ψ");
            s = s.replace('p', 'π');
            s = s.replace('q', 'ʐ');
            s = s.replace('r', 'ρ');
            s = s.replace('s', 'σ');
            s = s.replaceAll("τﺡ", "θ");
            s = s.replaceAll("th", "θ");
            s = s.replace('t', 'τ');
            s = s.replace('u', 'υ');
            s = s.replace('v', 'ɱ');
            s = s.replace('w', 'و');
            s = s.replace('x', 'ξ');
            s = s.replace('y', 'ے');
            s = s.replace('z', 'ζ');
            log.setFont(new Font("Arial", Font.PLAIN, 17));
            log.setText(s);
        }
    }//GEN-LAST:event_logKeyTyped

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (isGrenglish) {
            String s = log.getText().toLowerCase();
            s = s.replace('α', 'a');
            s = s.replace('β', 'b');
            s = s.replaceAll("χ", "ch");
            s = s.replace('ʗ', 'c');
            s = s.replace('δ', 'd');
            s = s.replaceAll("η", "ee");
            s = s.replace('ε', 'e');
            s = s.replace('ڡ', 'f');
            s = s.replace('γ', 'g');
            s = s.replace('ﺡ', 'h');
            s = s.replace('ί', 'i');
            s = s.replace('ƒ', 'j');
            s = s.replace('κ', 'k');
            s = s.replace('λ', 'l');
            s = s.replace('μ', 'm');
            s = s.replace('ν', 'n');
            s = s.replaceAll("ο", "oo");
            s = s.replace('ω', 'o');
            s = s.replaceAll("φ", "ph");
            s = s.replaceAll("ψ", "ps");
            s = s.replace('π', 'p');
            s = s.replace('ʐ', 'q');
            s = s.replace('ρ', 'r');
            s = s.replace('σ', 's');
            s = s.replaceAll("θ", "th");
            s = s.replace('τ', 't');
            s = s.replace('υ', 'u');
            s = s.replace('ɱ', 'v');
            s = s.replace('و', 'w');
            s = s.replace('ξ', 'x');
            s = s.replace('ے', 'y');
            s = s.replace('ζ', 'z');
            log.setText(s);
            log.setFont(new Font("Tahoma", Font.PLAIN, 17));
            isGrenglish = false;
        } else {
            String s = log.getText().toLowerCase();
            s = s.replace('a', 'α');
            s = s.replace('b', 'β');
            s = s.replaceAll("ʗﺡ", "χ");
            s = s.replaceAll("ch", "χ");
            s = s.replace('c', 'ʗ');
            s = s.replace('d', 'δ');
            s = s.replaceAll("ee", "η");
            s = s.replaceAll("ηη", "η");
            s = s.replace('e', 'ε');
            s = s.replace('f', 'ڡ');
            s = s.replace('g', 'γ');
            s = s.replace('h', 'ﺡ');
            s = s.replace('i', 'ί');
            s = s.replace('j', 'ƒ');
            s = s.replace('k', 'κ');
            s = s.replace('l', 'λ');
            s = s.replace('m', 'μ');
            s = s.replace('n', 'ν');
            s = s.replaceAll("oo", "ο");
            s = s.replaceAll("οο", "ο");
            s = s.replace('o', 'ω');
            s = s.replaceAll("πﺡ", "φ");
            s = s.replaceAll("ph", "φ");
            s = s.replaceAll("πσ", "ψ");
            s = s.replaceAll("ps", "ψ");
            s = s.replace('p', 'π');
            s = s.replace('q', 'ʐ');
            s = s.replace('r', 'ρ');
            s = s.replace('s', 'σ');
            s = s.replaceAll("τﺡ", "θ");
            s = s.replaceAll("th", "θ");
            s = s.replace('t', 'τ');
            s = s.replace('u', 'υ');
            s = s.replace('v', 'ɱ');
            s = s.replace('w', 'و');
            s = s.replace('x', 'ξ');
            s = s.replace('y', 'ے');
            s = s.replace('z', 'ζ');
            log.setText(s);
            log.setFont(new Font("Arial", Font.PLAIN, 17));
            isGrenglish = true;
        }
        play("Menu Command");
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        docs.setVisible(true);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void inboxAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_inboxAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_inboxAncestorAdded

    private void inboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inboxMouseClicked
        // TODO add your handling code here:
        String s = (String) inbox.getModel().getElementAt(inbox.getSelectedIndex());
        Message m = new Message(new File("C:/Users/Diary/" + u.name + "/Messages/Inbox/" + s), u);
        jPanel6.add(m);
        m.show();
    }//GEN-LAST:event_inboxMouseClicked

    private void createNewMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewMenuItemActionPerformed
        // TODO add your handling code here:
        logInWn.show();
        newUserCheck.setSelected(true);
        userName.setText("");
        userPassword.setText("");
        play("User Account Control");
    }//GEN-LAST:event_createNewMenuItemActionPerformed

    private void deskSplitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deskSplitMouseClicked
        // TODO add your handling code here:
        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getHeight(), 98);
        deskSplit.setDividerLocation(98);

    }//GEN-LAST:event_deskSplitMouseClicked

    private void themesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_themesValueChanged
        // TODO add your handling code here:
        theme = themes.getSelectedValue().toString();
        System.out.println(theme);
        sounds.removeAllItems();
        File[] f = new File("C:/Program Files/Diary/Themes/" + theme).listFiles();
        for (int i = 0; i < f.length; i++) {
            sounds.addItem(f[i].getName());
        }
        a.changedTheme();
        updateDiarySettings();
    }//GEN-LAST:event_themesValueChanged

    private void soundsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_soundsItemStateChanged
        // TODO add your handling code here:
        try {

            String s = sounds.getSelectedItem().toString();
            play(s.substring(0, s.indexOf(".")));
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_soundsItemStateChanged

    private void appearanceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_appearanceItemStateChanged
        try {
            // TODO add your handling code here:
            String s = (String) appearance.getSelectedItem();
            UIManager.setLookAndFeel(s);
            a.changedAppearance();
            updateDiarySettings();
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
    }//GEN-LAST:event_appearanceItemStateChanged

    private void appearanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appearanceMouseClicked
    }//GEN-LAST:event_appearanceMouseClicked

    private void setThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setThemeBtnActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            laf = appears[appearance.getSelectedIndex()];
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            updateDiarySettings();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_setThemeBtnActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
        Icon icon = jLabel3.getIcon();
        icon.paintIcon(desk, desk.getGraphics(), 0, 0);
        desk.repaint();
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void wallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wallMouseClicked
        // TODO add your handling code here:
        img = new ImageIcon("C:/Program Files/Diary/Pictures/" + wall.getSelectedItem().toString()).getImage();
        desk.getGraphics().drawImage(img, 0, 0, null);
        desk.getParent().repaint();
    }//GEN-LAST:event_wallMouseClicked

    private void wallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wallActionPerformed
        // TODO add your handling code here:
        img = new ImageIcon("C:/Program Files/Diary/Pictures/" + wall.getSelectedItem().toString()).getImage();
        desk.getGraphics().drawImage(img, 0, 0, null);
        desk.getParent().repaint();
    }//GEN-LAST:event_wallActionPerformed

    private void viewIconsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewIconsMouseClicked
        // TODO add your handling code here:
        if (!viewIcons.isSelected()) {
            System.out.println("hiding");
            for (Component c : desk.getComponents()) {
                if (c.getClass().getName().equals("Tile")) {
                    c.hide();
                }
            }
        } else {
            System.out.println("showing");
            for (Component c : desk.getComponents()) {
                if (c.getClass().getName().equals("Tile")) {
                    c.show();
                }
            }
        }
    }//GEN-LAST:event_viewIconsMouseClicked
    boolean started;
    private void changeDeskWallpaperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeDeskWallpaperMouseClicked
        // TODO add your handling code here:
        if (changeDeskWallpaper.isSelected()) {
            deskChangerTimer.cancel();
            deskChangerTimer = new Timer();
        } else {
            try {
                deskChangerTimer.schedule(deskChanger, delay, delay);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }//GEN-LAST:event_changeDeskWallpaperMouseClicked

    private void viewIconsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewIconsActionPerformed
        // TODO add your handling code here:
        if (!viewIcons.isSelected()) {
            System.out.println("hiding");
            for (Component c : desk.getComponents()) {
                if (c.getClass().getName().equals("diary.Tile")) {
                    c.hide();
                }
            }
            desk.repaint();
        } else {
            System.out.println("showing");
            for (Component c : desk.getComponents()) {
                if (c.getClass().getName().equals("diary.Tile")) {
                    c.show();
                }
            }
            desk.repaint();
        }
    }//GEN-LAST:event_viewIconsActionPerformed

    private void changeDeskWallpaperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDeskWallpaperActionPerformed
        // TODO add your handling code here:
        if (!changeDeskWallpaper.isSelected()) {
            deskChangerTimer.cancel();
            deskChanger = new DeskChanger();
            deskChangerTimer = new Timer();
        } else {
            try {
                deskChangerTimer.schedule(deskChanger, delay, delay);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }//GEN-LAST:event_changeDeskWallpaperActionPerformed

    private void deskMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deskMouseClicked
        // TODO add your handling code here:
        for (Component c : desk.getComponents()) {
            if (c.getClass().getName().equals("diary.Tile")) {
                Tile t = (Tile) c;
                t.setOpaque(false);
            }
        }
        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getHeight(), 98);
        deskSplit.setDividerLocation(95);
    }//GEN-LAST:event_deskMouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        jFileChooser2.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {

                if (f.getName().endsWith(".java")) {
                    return true;
                }
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "JAVA Files (.java)";
            }
        });
        chooseFile.show();
    }//GEN-LAST:event_jButton21ActionPerformed
    boolean proj;
    boolean file;
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        proj = true;
        file = false;
        chooseFile1.show();
        jFileChooser3.setFileFilter(null);
        jFileChooser3.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    File ff = new File(f.getAbsolutePath() + "/project.djav");
                    if (ff.exists()) {
                        return true;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Java IDE Project (.djav)";
            }
        });
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jFileChooser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser2ActionPerformed
        // TODO add your handling code here:
        String cmd = evt.getActionCommand();
        System.out.println(cmd);
        if (cmd.equals("ApproveSelection")) {
            System.out.println("approved");
            openInIDE(jFileChooser2.getSelectedFiles());
            chooseFile.hide();
            desk.repaint();
        } else if (cmd.equals("CancelSelection")) {
            chooseFile.hide();
            desk.repaint();
        }
    }//GEN-LAST:event_jFileChooser2ActionPerformed

    private void jFileChooser2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFileChooser2KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            openInIDE(jFileChooser2.getSelectedFiles());
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            chooseFile.hide();
        }
    }//GEN-LAST:event_jFileChooser2KeyPressed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        javaIDE.hide();
        desk.repaint();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        jFileChooser2.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Java Projects (.djav)";
            }
        });
        chooseFile.show();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jdkPath = jdk.getText();
        updateDiarySettings();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        Component selectedComponent = openFiles.getSelectedComponent();
        JJavaDocument j = (JJavaDocument) selectedComponent;
        j.saveQ();
        openFiles.remove(openFiles.getSelectedIndex());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem7MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenuItem7MenuKeyPressed
        // TODO add your handling code here:
        Component selectedComponent = openFiles.getSelectedComponent();
        JJavaDocument j = (JJavaDocument) selectedComponent;
        j.saveQ();
    }//GEN-LAST:event_jMenuItem7MenuKeyPressed

    private void jSpinner1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinner1PropertyChange
        // TODO add your handling code here:
        try {
            String s = jSpinner1.getValue().toString();
            String v = s.substring(0, s.indexOf(" "));
            String t = s.substring(s.indexOf(" ") + 1, s.indexOf("."));
            int vv = Integer.parseInt(v);
            if (t.equals("sec")) {
                vv *= 1000;
            } else if (t.equals("min")) {
                vv *= 60000;
            }
            if (delay == vv) {
            } else {
                delay = new Integer(vv);
                System.out.println("Delay set : " + delay);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jSpinner1PropertyChange
    Vector openProjects;
    DJavaProject curr;
    private void jFileChooser3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser3ActionPerformed
        // TODO add your handling code here:
        if (evt.getActionCommand().equals("ApproveSelection")) {
            File p = jFileChooser3.getSelectedFile();
            if (p != null) {
                if (p.exists()) {
                    play("Error");
                    message("Folder already exists!", "Error!");
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    });
                } else if (proj) {
                    DJavaProject g = new DJavaProject(p);
                    try {
                        g.create();
                        g.load(jTree2);
                        openProjects.add(g);
                        curr = g;
                    } catch (IOException ex) {
                    }
                } else if (file) {
                    System.out.println("creating new file");
                    if (curr == null) {
                        chooseFile1.hide();
                        play("Exclamation");
                        message("First Open a Project!", "Error!");
                    } else {
                        String s = p.getName();
                        if (s.endsWith(".java")) {
                            curr.newClass(s.substring(0, s.indexOf(".")));
                        } else {
                            curr.newClass(s);
                        }
                        curr.newClass(s.substring(0, s.indexOf(".")));
                        JJavaDocument jj = new JJavaDocument(this, c, p);
                        openFiles.add(jj);
                        curr.load(jTree2);
                    }
                }
            }
        } else if (evt.getActionCommand().equals("CancelSelection")) {
            chooseFile1.hide();
            desk.repaint();
        }

    }//GEN-LAST:event_jFileChooser3ActionPerformed

    private void jFileChooser3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFileChooser3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFileChooser3KeyPressed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
        message.hide();
    }//GEN-LAST:event_okActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        file = true;
        proj = false;
        chooseFile1.show();
        jFileChooser3.setFileFilter(null);
        jFileChooser3.setFileFilter(new javax.swing.filechooser.FileFilter() {

            @Override
            public boolean accept(File f) {
                if (f.getName().endsWith(".java")) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Java Class (.java)";
            }
        });
        if (curr != null) {
            System.out.println("Redirectiong!");
            jFileChooser3.setCurrentDirectory(new File(curr.main.getAbsolutePath() + "/source"));
        } else {
            System.out.println("Not redetted.");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jTree2ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree2ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree2ValueChanged

    private void jTree2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree2MouseClicked
        // TODO add your handling code here:
        if (click == 1) {
            System.out.println("Double click opening...!");
            click = 0;
            TreePath tp = jTree2.getLeadSelectionPath();
            Object[] o = tp.getPath();
            String ps = "";
            if (o.length == 1) {
                System.out.println("No project open!");
            } else {
                ps += o[1].toString();
            }
            for (int i = 2; i < o.length; i++) {
                ps += "/" + o[i].toString();
            }
            System.out.println(ps);
            for (int i = 0; i < openProjects.size(); i++) {
                DJavaProject d = (DJavaProject) openProjects.elementAt(i);
                if (d.main.getName().equals(o[1].toString())) {
                    String s = d.main.getParentFile().getAbsolutePath() + "/" + ps;
                    File r = new File(s);
                    if (r.isDirectory()) {
                        jTree2.expandPath(tp);
                    } else {
                        openInIDE(r);
                    }
                }
            }
        }
        long po = System.currentTimeMillis();
        if ((po - pre) <= 500) {
            click = 1;
        } else {
            click++;
            System.out.println("Single click...!");
        }
        pre = po;
    }//GEN-LAST:event_jTree2MouseClicked

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        if (x < clickX) {
            DeskDivideSweeper d = new DeskDivideSweeper();
            d.type = -1;
            d.start();
        } else if (x > clickX) {
            DeskDivideSweeper d = new DeskDivideSweeper();
            d.type = 1;
            d.start();
        }
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        clickX = evt.getX();
        clickY = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        // TODO add your handling code here:
        clickX = 0;
        clickY = 0;
    }//GEN-LAST:event_jPanel1MouseReleased

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        
    }//GEN-LAST:event_formWindowStateChanged

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        setState(1);
        repaint();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        setBounds(previous);
        setState(0);
        repaint();
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    Rectangle previous;
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        previous = getBounds();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle r = ge.getMaximumWindowBounds();
        setBounds(0, 0, r.width, r.height);
        setState(6);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        hide();
    }//GEN-LAST:event_jMenuItem13ActionPerformed
    int clickX, clickY;
    long pre = 0;
    int click = 0;
    User u;
    DeskDivideSweeper d;
    int prePos;
    String laf;
    String lastuser;
    String jdkPath;

    private void initDiary() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:/Program Files/Diary/diary.settings"));
            theme = br.readLine().substring(6);
            laf = br.readLine().substring(4);
            System.out.println(theme + " " + laf);
            lastuser = br.readLine().substring(9);
            jdkPath = br.readLine().substring(8);
            jdk.setText(jdkPath);
            jdk.setText(jdkPath);
            delay = Integer.parseInt(br.readLine().substring(6));
            br.close();
            for (LookAndFeelInfo li : UIManager.getInstalledLookAndFeels()) {
                if (li.getClassName().equalsIgnoreCase(laf)) {
                    lafName = li.getName();
                    break;
                }
            }
        } catch (Exception ex) {
        }
    }

    public void updateDiarySettings() {
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Program Files/Diary/diary.settings")));
            pw.println("theme=" + theme);
            pw.println("laf=" + laf);
            pw.println("lastuser=" + u.name);
            pw.println("jdkpath=" + jdkPath);
            pw.println("delay=" + delay);
            pw.close();
        } catch (IOException ex) {
        }
    }

    public void removeJIDEDoc(JJavaDocument j) {
        openFiles.remove(j);
    }

    public void message(String msg) {
        jLabel8.setText(msg);
        message.setTitle("Message :");
        message.show();
    }

    public void message(String msg, String title) {
        jLabel8.setText(msg);
        message.setTitle(title);
        message.show();
    }

    private void loadDesk() {
        Tile t = new Tile("Computer", "explorer .\\Music", new ImageIcon(getClass().getResource("/resources/icons/ccsm.png")).getImage());
        t.setBounds(20, 20, t.getWidth(), t.getHeight());
        desk.add(t);
        t.setVisible(true);
        Tile t2 = new Tile("JAVA IDE", "#C=javaIDE", new ImageIcon(getClass().getResource("/resources/icons/ccsm.png")).getImage());
        t2.setBounds(20, 20 + t.getHeight(), t2.getWidth(), t2.getHeight());
        desk.add(t2);
        t2.setVisible(true);
    }

    private void openInIDE(File[] selectedFiles) {
        System.out.println("opening");
        for (int i = 0; i < selectedFiles.length; i++) {
            System.out.println("file loop");
            File f = selectedFiles[i];
            if (f.isDirectory()) {
                System.out.println("dirs");
                File f2 = new File(f.getAbsolutePath() + "/project.djav");
                if (f2.exists()) {
                    DJavaProject d = new DJavaProject(f);
                    d.load(jTree2);
                    openProjects.add(d);
                    curr = d;
                }
            } else {
                System.out.println("adding");
                JJavaDocument jj = new JJavaDocument(this, c, selectedFiles[i]);
                openFiles.addTab(f.getName(), jj);
            }
        }
    }

    private void openInIDE(File selec) {
        System.out.println("opening");
        System.out.println("file loop");
        File f = selec;
        if (f.isDirectory()) {
            System.out.println("dirs");
            File f2 = new File(f.getAbsolutePath() + "/project.djav");
            if (f2.exists()) {
                DJavaProject d = new DJavaProject(f);
                d.load(jTree2);
                openProjects.add(d);
            }
        } else {
            System.out.println("adding");
            JJavaDocument jj = new JJavaDocument(this, c, selec);
            openFiles.addTab(f.getName(), jj);
        }
    }

    private void format(JEditorPane c) {
    }

    private void loadTheme() {
        try {
            // TODO add your handling code here:
            laf = appears[appearance.getSelectedIndex()];
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            updateDiarySettings();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class DeskDivideSweeper extends Thread {

        int type = -1;
        int i;

        @Override
        public synchronized void start() {
            super.start();
        }

        @Override
        public void run() {
            super.run();
            i = deskSplit.getDividerLocation();
            int a = getWidth();
            if (type == 1) {
                i = i + 10;
            } else if (type == -1) {
                i = i - 10;
            }
            deskSplit.setDividerLocation(i);
            deskSplit.repaint();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {

        UIManager.installLookAndFeel("Synthetica", "de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");
        UIManager.installLookAndFeel("EASynth", "com.easynth.lookandfeel.EaSynthLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Acryl", "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Aero", "com.jtattoo.plaf.aero.AeroLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Aluminium", "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Bernstein", "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Fast", "com.jtattoo.plaf.fast.FastLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Graphite", "com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Hi-Fi", "com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Luna", "com.jtattoo.plaf.luna.LunaLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Mc-Win", "com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Mint", "com.jtattoo.plaf.mint.MintLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Noire", "com.jtattoo.plaf.noire.NoireLookAndFeel");
        UIManager.installLookAndFeel("Tattoo Smart", "com.jtattoo.plaf.smart.SmartLookAndFeel");

        File f = new File("C:/Users/Diary");
        f.mkdirs();
        if (!(new File("C:/Users/Diary/Guest").exists())) {
            User u = new User("Guest", "");
            System.out.println("Created Guest");
        }
        DiaryApp d = new DiaryApp();
        d.setVisible(true);
        d.initDiary();
        //java.awt.EventQueue.invokeLater(new Runnable() {

        //    public void run() {
        //        d = new DiaryApp();

        //        d.setVisible(true);
        //    }
        //});

        LookAndFeelInfo[] l = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < l.length; i++) {
            String toString = l[i].toString();
            System.out.println(toString);
        }
        /*
         *
         *
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        //</editor-fold>

        /*
         * Create and display the form
         */
    }
    Timer lookAndFeelManager;

    public void setLAF(String s) {
        try {
            UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(this);
            this.pack();
            updateDiarySettings();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DiaryApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        }
    }

    public class LookAndFeelManager extends TimerTask {

        @Override
        public void run() {
            updateDiarySettings();
            LookAndFeel la = UIManager.getLookAndFeel();
            System.out.println(la.toString());
            if (!la.toString().contains(UIManager.getLookAndFeel().getName())) {
                for (LookAndFeelInfo li : UIManager.getInstalledLookAndFeels()) {
                    if (li.getClassName().equalsIgnoreCase(laf)) {
                        setLAF(laf);
                    }
                }
            }
        }
    }
    String lafName;

    public void loadThemes() {
        File[] f = new File("C:/Program Files/Diary/Themes").listFiles();
        DefaultListModel d = new DefaultListModel();
        for (int i = 0; i < f.length; i++) {
            if (f[i].isDirectory()) {
                d.addElement(f[i].getName());
            }
        }
        themes.setModel(d);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem aboutMenuItem;
    public javax.swing.JTextArea activity;
    public javax.swing.JMenuItem addFolder;
    public javax.swing.JButton addToday;
    public javax.swing.JDesktopPane allmusic;
    public javax.swing.JComboBox appearance;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JButton cancel;
    public javax.swing.JCheckBoxMenuItem changeDeskWallpaper;
    public javax.swing.JInternalFrame chooseFile;
    public javax.swing.JInternalFrame chooseFile1;
    public javax.swing.JMenuItem contentsMenuItem;
    public javax.swing.JMenuItem copyMenuItem;
    public javax.swing.JMenuItem createNewMenuItem;
    public javax.swing.JDesktopPane creations;
    public javax.swing.JMenuItem cutMenuItem;
    public javax.swing.JInternalFrame dayLogList;
    public javax.swing.JMenuItem deleteMenuItem;
    public javax.swing.JDesktopPane desk;
    public javax.swing.JPopupMenu deskPopup;
    public javax.swing.JSplitPane deskSplit;
    public javax.swing.JInternalFrame docs;
    public javax.swing.JDesktopPane docsDesk;
    public javax.swing.JButton dwn;
    public javax.swing.JMenu editMenu;
    public javax.swing.JMenuItem exitMenuItem;
    public javax.swing.JMenu fileMenu;
    public javax.swing.JMenu helpMenu;
    public javax.swing.JList inbox;
    public javax.swing.JDialog input;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton10;
    public javax.swing.JButton jButton11;
    public javax.swing.JButton jButton12;
    public javax.swing.JButton jButton14;
    public javax.swing.JButton jButton15;
    public javax.swing.JButton jButton16;
    public javax.swing.JButton jButton18;
    public javax.swing.JButton jButton19;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton20;
    public javax.swing.JButton jButton21;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    public javax.swing.JButton jButton9;
    public javax.swing.JDesktopPane jDesktopPane2;
    public javax.swing.JFileChooser jFileChooser1;
    public javax.swing.JFileChooser jFileChooser2;
    public javax.swing.JFileChooser jFileChooser3;
    public javax.swing.JInternalFrame jInternalFrame1;
    public javax.swing.JInternalFrame jInternalFrame2;
    public javax.swing.JInternalFrame jInternalFrame3;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem10;
    public javax.swing.JMenuItem jMenuItem11;
    public javax.swing.JMenuItem jMenuItem12;
    public javax.swing.JMenuItem jMenuItem13;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem jMenuItem4;
    public javax.swing.JMenuItem jMenuItem5;
    public javax.swing.JMenuItem jMenuItem6;
    public javax.swing.JMenuItem jMenuItem7;
    public javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem jMenuItem9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    public javax.swing.JPopupMenu jPopupMenu1;
    public javax.swing.JPopupMenu jPopupMenu2;
    public javax.swing.JPopupMenu jPopupMenu3;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane10;
    public javax.swing.JScrollPane jScrollPane11;
    public javax.swing.JScrollPane jScrollPane12;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane4;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JScrollPane jScrollPane7;
    public javax.swing.JScrollPane jScrollPane8;
    public javax.swing.JScrollPane jScrollPane9;
    public javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JPopupMenu.Separator jSeparator2;
    public javax.swing.JSpinner jSpinner1;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JSplitPane jSplitPane2;
    public javax.swing.JSplitPane jSplitPane3;
    public javax.swing.JSplitPane jSplitPane4;
    public javax.swing.JSplitPane jSplitPane5;
    public javax.swing.JSplitPane jSplitPane7;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTabbedPane jTabbedPane2;
    public javax.swing.JTabbedPane jTabbedPane3;
    public javax.swing.JTabbedPane jTabbedPane4;
    public javax.swing.JTabbedPane jTabbedPane5;
    public javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JToolBar jToolBar1;
    public javax.swing.JToolBar jToolBar2;
    public javax.swing.JToolBar jToolBar3;
    public javax.swing.JTree jTree1;
    public javax.swing.JTree jTree2;
    public javax.swing.JInternalFrame javaIDE;
    public javax.swing.JTextField jdk;
    public javax.swing.JTextArea log;
    public javax.swing.JMenuItem logInMenuItem;
    public javax.swing.JInternalFrame logInWn;
    public javax.swing.JMenuItem logOutMenuItem;
    public javax.swing.JInternalFrame logWin;
    public javax.swing.JMenuBar menuBar;
    public javax.swing.JDialog message;
    public javax.swing.JInternalFrame messages;
    public javax.swing.JCheckBox newUserCheck;
    public javax.swing.JButton ok;
    public javax.swing.JTabbedPane openFiles;
    public javax.swing.JMenuItem openMenuItem;
    public javax.swing.JList outbox;
    public javax.swing.JMenuItem pasteMenuItem;
    public javax.swing.JTextField path;
    public javax.swing.JScrollPane pics;
    public javax.swing.JTree picsTree;
    public javax.swing.JMenuItem removeFolder;
    public javax.swing.JButton rgt;
    public javax.swing.JMenuItem saveAsMenuItem;
    public javax.swing.JMenuItem saveMenuItem;
    public javax.swing.JButton setThemeBtn;
    public javax.swing.JComboBox sounds;
    public javax.swing.JTextArea terminal;
    public javax.swing.JList themes;
    public javax.swing.JToolBar toolLog;
    public javax.swing.JComboBox type;
    public javax.swing.JMenu userMenu;
    public javax.swing.JTextField userName;
    public javax.swing.JPasswordField userPassword;
    public javax.swing.JDesktopPane videos;
    public javax.swing.JCheckBoxMenuItem viewIcons;
    public javax.swing.JComboBox wall;
    // End of variables declaration//GEN-END:variables

    public void listAllPics() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Diary\\" + u.name + "\\Pictures.pics"));
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Pictures");
            boolean eof = false;
            while (!eof) {
                try {
                    String s = br.readLine();
                    DefaultMutableTreeNode a = new DefaultMutableTreeNode(s);
                    File f = new File(s);
                    if (f.exists()) {
                        System.out.println(f.getAbsolutePath());
                        listAllFiles(f, a);
                    }
                    root.add(a);
                } catch (Exception ex) {
                    eof = true;
                }
            }

            DefaultTreeModel d = new DefaultTreeModel(root);
            picsTree.setModel(d);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public void listAllFiles(File f, DefaultMutableTreeNode d) {
        File[] ff = f.listFiles();
        for (int i = 0; i < ff.length; i++) {
            System.out.println(ff[i].getAbsolutePath());
            DefaultMutableTreeNode s = new DefaultMutableTreeNode(ff[i].getName());
            if (ff[i].isDirectory()) {
                d.add(s);
                listAllFiles(ff[i], s);
            } else {
                d.add(s);
            }
        }
    }

    public class MyRenderer extends DefaultTreeCellRenderer {

        public MyRenderer() {
        }

        public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

            super.getTreeCellRendererComponent(
                    tree, value, sel,
                    expanded, leaf, row,
                    hasFocus);
            if (leaf && (picsTree.getSelectionPath().getLastPathComponent().toString().endsWith(".jpg")
                    || picsTree.getSelectionPath().getLastPathComponent().toString().endsWith(".png")
                    || picsTree.getSelectionPath().getLastPathComponent().toString().endsWith(".gif")
                    || picsTree.getSelectionPath().getLastPathComponent().toString().endsWith(".bmp"))) {
                this.setIcon(jButton8.getIcon());
            } else if (!leaf && expanded) {
                this.setIcon(dwn.getIcon());
            } else if (!leaf && !expanded) {
                this.setIcon(rgt.getIcon());
            } else {
                setToolTipText(null);
            }

            return this;
        }
    }

    public void listAllDocs() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Diary\\" + u.name + "\\Documents.docs"));
            boolean eof = false;
            int x = 10;
            int y = 10;
            docsDesk.setLayout(new BoxLayout(docsDesk, BoxLayout.Y_AXIS));
            while (!eof) {
                try {
                    String s = br.readLine();
                    File f = new File(s);
                    if (f.exists()) {
                        System.out.println(f.getAbsolutePath());
                        JButton j = new JButton(f.getName());
                        j.setToolTipText(f.getAbsolutePath());
                        docsDesk.add(j);
                        j.setIcon(jTabbedPane2.getIconAt(0));
                        System.out.println("Added!!!!");
                        j.show();
                    }

                } catch (Exception ex) {
                    eof = true;
                    System.out.println(ex);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    String theme;

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void play(String s) {
        AudioClip audioClip;
        try {
            audioClip = Applet.newAudioClip(new URL("file:///C:/Program Files/Diary/Themes/" + theme + "/" + s + ".wav"));
            audioClip.play();
            audioClip.play();
            System.out.println("Played!");
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
    }

    public void jPanel1MouseClicked(MouseEvent e) {
        if (deskSplit.getDividerLocation() > 98) {
            deskChangerTimer.cancel();
            deskChangerTimer = new Timer();
            DeskDivideSweeper d = new DeskDivideSweeper();
            d.type = -1;
            d.start();
        }
        jPanel1.setBounds(jPanel1.getX(), jPanel1.getY(), jPanel1.getHeight(), 94);
        deskSplit.setDividerLocation(95);
    }

    public void appearanceActionPerformed(ActionEvent e) {
    }

    public class ActivityListener {

        public void append(String s) {
            PrintWriter pw = null;
            try {
                activity.setText(activity.getText() + "\n" + s);
                pw = new PrintWriter(new BufferedWriter(new FileWriter("C:/Program Files/Diary/activity.log", true)), true);
                pw.println("\n" + s);
                pw.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } finally {
                pw.close();
            }
        }

        public void started() {
            Date d = new Date();
            append("STARTED : " + d.toString() + "\n");
        }

        public void loggedIn() {
            Date d = new Date();
            append("LOGIN : " + u.name
                    + "\n\tAt/On : " + d.toString());
        }

        public void addedLog() {
            Date d = new Date();
            append("LOG ADDED BY : " + u.name
                    + "\n\tAt : " + d.getHours() + ":" + d.getMinutes());
        }

        public void deletedLog(String title) {
            Date d = new Date();
            append("LOG DELETED BY : " + u.name
                    + "\n\t@ " + title
                    + "\n\t\tAt : " + d.getHours() + ":" + d.getMinutes());
        }

        public void updatedLog(String title) {
            Date d = new Date();
            append("LOG UPDATED BY : " + u.name
                    + "\n\t@ " + title
                    + "\n\t\tAt : " + d.getHours() + ":" + d.getMinutes());
        }

        public void viewedLog(String title) {
            Date d = new Date();
            append("LOG VIEWED BY : " + u.name
                    + "\n\t@ " + title
                    + "\n\t\tAt : " + d.getHours() + ":" + d.getMinutes());
        }

        public void changedTheme() {
            Date d = new Date();
            append("THEME CHANGED BY : " + u.name
                    + "\n\t@ " + theme
                    + "\n\t\tAt : " + d.getHours() + ":" + d.getMinutes());
        }

        public void changedAppearance() {
            Date d = new Date();
            append("APPEARANCE CHANGED BY : " + u.name
                    + "\n\t@ " + appearance.getSelectedItem().toString()
                    + "\n\t\tAt : " + d.getHours() + ":" + d.getMinutes());
        }
    }
    int wallIndex;

    public class DeskChanger extends TimerTask {

        @Override
        public void run() {
            System.out.println("Changing.....");
            if (wallIndex < wall.getItemCount()) {
                wallIndex = wallIndex + 1;
            } else {
                wallIndex = 0;
            }
            System.out.println(this.scheduledExecutionTime());
            System.out.println("C:/Program Files/Diary/Pictures/" + wall.getItemAt(wallIndex));
            img = new ImageIcon("C:/Program Files/Diary/Pictures/" + wall.getItemAt(wallIndex)).getImage();
            wall.setSelectedIndex(wallIndex);
            desk.repaint();
        }
    }

    public class Tile extends javax.swing.JPanel {

        String lab;
        String fid;
        Image img;

        /**
         * Creates new form Tile
         */
        public Tile(String lab, String fid, Image img) {
            this.fid = fid;
            this.lab = lab;
            this.img = img;
            initComponents();
            label.setText(lab);
            label.setIcon(new ImageIcon(img));
            this.setBounds(this.getX(), this.getY(), img.getWidth(null) + 10, img.getHeight(null) + 20);
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            label = new javax.swing.JLabel();

            setBackground(java.awt.SystemColor.controlHighlight);
            setOpaque(false);

            label.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/applications-accessories.png"))); // NOI18N
            label.setText("Text");
            label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            label.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            label.addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    labelMouseClicked(evt);
                }
            });
            label.addFocusListener(new java.awt.event.FocusAdapter() {

                public void focusLost(java.awt.event.FocusEvent evt) {
                    labelFocusLost(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        }// </editor-fold>                        
        int click = 0;
        long time = 0;

        public void labelMouseClicked(java.awt.event.MouseEvent evt) {
            // TODO add your handling code here:
            if (label.isOpaque()) {
                label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            } else {
                label.setBorder(null);
            }
            long a = System.currentTimeMillis() - time;
            System.out.println(a);
            if (click == 1 & a <= 500 & label.isOpaque()) {
                click = 0;
                System.out.println("Double Click!");
                try {
                    if (fid.startsWith("#")) {
                        diaryExec(fid);
                    } else {
                        Process p = Runtime.getRuntime().exec(fid);
                    }
                } catch (IOException ex) {
                }
                label.setOpaque(false);
                label.setBorder(null);
            } else if (a > 500) {
                if (label.isOpaque()) {
                    label.setOpaque(false);
                    label.setBorder(null);
                } else {
                    label.setOpaque(true);
                    label.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
                }
                System.out.println("Click!");
                click = 1;
            } else {
                click = 1;
            }
            getParent().repaint();
            time = System.currentTimeMillis();
        }

        private void labelFocusLost(java.awt.event.FocusEvent evt) {
            // TODO add your handling code here:
            label.setOpaque(false);
            label.setBorder(null);
        }
        // Variables declaration - do not modify                     
        private javax.swing.JLabel label;
        // End of variables declaration                   
    }

    private void diaryExec(String cmd) {
        cmd = cmd.substring(1);
        if (cmd.startsWith("C=")) {
            String com = cmd.substring(2);
            for (JInternalFrame c : desk.getAllFrames()) {
                System.out.println(c.getName());
                if (c.getName() != null) {
                    if (c.getName().equalsIgnoreCase(com)) {
                        c.setVisible(!c.isVisible());
                    }
                }
            }
        }
    }

    public void compile(JJavaDocument j) {
        try {
            Runtime r = Runtime.getRuntime();
            String st5 = (jdkPath + "\\bin\\javac.exe " + j.f.getAbsolutePath());
            Process p = r.exec(st5);
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String s = b.readLine();
            jInternalFrame3.show();
            boolean bg = false;
            while (s != null) {
                System.out.println(s);
                bg = terminal.getText().contains("error");
                terminal.setText(terminal.getText() + "\n" + s);
                s = b.readLine();
            }
            if (bg) {
                System.out.println("Compiled!");
                play("Notify");
                message("Compilation of : " + j.f.getName() + "\n\twas Successful!", "Compiled!");
            } else {
                play("Error");
                message("Compilation of : " + j.f.getName() + "\n\twasn't Successful!", "Not Compiled!");
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    int tii;
}
