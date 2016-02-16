/*
 * Created by JFormDesigner on Sun Feb 14 00:50:28 EET 2016
 */

package ru.minesweeperLesson.minesweeper;

import java.awt.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Vlad Panvhenko
 */
public class fbsfd extends JFrame {
    public fbsfd() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Vlad Panvhenko
        label1 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- label1 ----
        label1.setText("text");
        label1.setAlignmentY(-0.5F);
        label1.setVerticalAlignment(SwingConstants.TOP);
        contentPane.add(label1, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Vlad Panvhenko
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
