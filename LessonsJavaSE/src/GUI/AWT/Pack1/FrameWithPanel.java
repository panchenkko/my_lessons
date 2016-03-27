package GUI.AWT.Pack1;

import java.awt.*;


/**
 * Panel
 * Позволяет размещать компоненты и другие панели
 * Вложенные панели могут иметь различные менеджеры размещения
 */
public class FrameWithPanel {

    private Frame f;
    private Panel pan;

    public FrameWithPanel(String title) {
        f = new Frame(title);
        pan = new Panel();
    }

    public void launchFrame() {
        f.setSize(200,200);
        f.setBackground(Color.blue);
        f.setLayout(null); // Попробуйте закомментировать эту сроку
        pan.setSize(100,100);
        pan.setBackground(Color.yellow);
        f.add(pan);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        FrameWithPanel guiWindow = new FrameWithPanel("Frame with Panel");
        guiWindow.launchFrame();
    }
}

