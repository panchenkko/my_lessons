package LessonsJavaCore.Other.GUI.AWT.Pack1;

import java.awt.*;

public class BorderExample {
    private Frame f;
    private Button bn, bs, bw, be, bc;
    public BorderExample() {
        f = new Frame("Border Layout");
        bn = new Button("North");
        bs = new Button("South");
        bw = new Button("West");
        be = new Button("East");
        bc = new Button("Center");
    }
    public void launchFrame() {
        f.add(bn, BorderLayout.NORTH);
        f.add(bs, BorderLayout.SOUTH);
        f.add(bw, BorderLayout.WEST);
        f.add(be, BorderLayout.EAST);
        f.add(bc, BorderLayout.CENTER);
        f.setSize(200,200); // Попробуйте заменить на f.pack();
        f.setVisible(true);
    }
    public static void main(String args[]) {
        BorderExample guiWindow2 = new BorderExample();
        guiWindow2.launchFrame();
    }

    public static void closePerform(Frame frame) {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}

