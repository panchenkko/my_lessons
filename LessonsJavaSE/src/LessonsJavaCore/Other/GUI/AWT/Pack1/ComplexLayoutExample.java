package LessonsJavaCore.Other.GUI.AWT.Pack1;

import java.awt.*;

public class ComplexLayoutExample {
    private Frame f;
    private Panel p;
    private Button bw, bc, bfile, bhelp;

    public ComplexLayoutExample() {
        f = new Frame("Other.Other.GUI example 3");
        bw = new Button("West"); bc = new Button("Work space region");
        bfile = new Button("File"); bhelp = new Button("Help");
    }

    public void launchFrame() {
        f.add(bw, BorderLayout.WEST);
        f.add(bc, BorderLayout.CENTER);
        p = new Panel();
        p.add(bfile);
        p.add(bhelp);
        f.add(p, BorderLayout.NORTH);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String args[]) {
        ComplexLayoutExample gui = new ComplexLayoutExample();
        gui.launchFrame();
    }
}


