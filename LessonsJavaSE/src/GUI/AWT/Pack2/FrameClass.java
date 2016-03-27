package GUI.AWT.Pack2;

import java.awt.*;
import java.awt.event.*;

/**
 * Обработка событий в том же классе
 */
public class FrameClass implements ActionListener {

    int counter;

    Frame f = new Frame();
    Label label = new Label("" + counter);
    Button incButton = new Button("+");
    Button decButton = new Button("-");

    public FrameClass() {
        incButton.addActionListener(this);
        decButton.addActionListener(this);
        f.add(incButton, BorderLayout.EAST);
        f.add(decButton, BorderLayout.WEST);
        f.add(label, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("+")) {
            label.setText("" + ++counter);
        } else if (e.getActionCommand().equals("-")) {
            label.setText("" + --counter);
        }

    }

    public static void main(String[] args) {
        new FrameClass();
    }
}
