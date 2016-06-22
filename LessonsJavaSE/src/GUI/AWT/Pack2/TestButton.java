package GUI.AWT.Pack2;

import java.awt.*;
import java.awt.event.*;

class ButtonHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        System.out.println("Action occurred");
        System.out.println("Button’s command is: "
                + e.getActionCommand());
    }
}


public class TestButton {

    private Frame f;
    private Button b;

    public TestButton() {
        f = new Frame("Test");
        b = new Button("Press Me!");
        b.setActionCommand("ButtonPressed");
    }

    public void launchFrame() {
        b.addActionListener(new ButtonHandler());
        f.add(b, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String args[]) {
        TestButton guiApp = new TestButton();
        guiApp.launchFrame();
    }
}

