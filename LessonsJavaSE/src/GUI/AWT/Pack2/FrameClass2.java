package GUI.AWT.Pack2;

import java.awt.*;
import java.awt.event.*;

/**
 * Обработка событий во внешних классах
 */
class IncActionListener implements ActionListener {
    private FrameClass2 fm;

    public IncActionListener(FrameClass2 fm) {
        this.fm = fm;
    }

    public void actionPerformed(ActionEvent e) {
        fm.label.setText("" + ++fm.counter);
    }
}

class DecActionListener implements ActionListener {
    private FrameClass2 fm;

    public DecActionListener(FrameClass2 fm) {
        this.fm = fm;
    }

    public void actionPerformed(ActionEvent e) {
        fm.label.setText("" + --fm.counter);
    }
}


public class FrameClass2 {
    int counter;
    Frame f = new Frame();
    Label label = new Label("" + counter);
    Button incButton = new Button("+");
    Button decButton = new Button("-");

    public FrameClass2() {
        incButton.addActionListener(new IncActionListener(this));
        decButton.addActionListener(new DecActionListener(this));
        f.add(incButton, BorderLayout.EAST);
        f.add(decButton, BorderLayout.WEST);
        f.add(label, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new FrameClass();
    }
}
