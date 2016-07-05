/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.awt.Component;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author HP
 */
class MyRenderer extends DefaultTreeCellRenderer {

    ImageIcon tutorialIcon;

    public MyRenderer() {
        tutorialIcon = new ImageIcon("images/middle.gif");
    }

    @Override
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
        if (sel) {
            setIcon(tutorialIcon);
            setToolTipText("This book is in the Tutorial series.");
        } else {
            setToolTipText(null);
        }

        return this;
    }
}