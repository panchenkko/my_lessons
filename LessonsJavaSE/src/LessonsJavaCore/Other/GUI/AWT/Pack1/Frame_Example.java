package LessonsJavaCore.Other.GUI.AWT.Pack1;

import java.awt.*;

/**
 * Наследник Windows
 * Имеет заголовок
 * Можно изменять размер мышью
 * Изначально невидим
 * Используйте setVisible(true)
 * По умолчанию использует менеджер размещения BorderLayout
 * Для изменения используйте setLayout
 */
public class Frame_Example {

    private Frame f;

    public Frame_Example() {
        f = new Frame("Hello Out There!");
    }

    public void launchFrame() {
        f.setSize(170,170);
        f.setBackground(Color.blue);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        Frame_Example guiWindow = new Frame_Example();
        guiWindow.launchFrame();
    }
}

