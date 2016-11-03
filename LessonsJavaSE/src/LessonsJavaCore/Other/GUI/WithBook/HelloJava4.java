package LessonsJavaCore.Other.GUI.WithBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HelloJava4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("HelloJava4");
        frame.add(new HelloComponent4("Hello, Java!"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

class HelloComponent4 extends JComponent implements MouseMotionListener, ActionListener, Runnable {
    String theMessage;
    int messageX = 125, messageY = 95;
    JButton theButton;
    int colorIndex;
    static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};
    boolean blinkState;

    public HelloComponent4(String theMessage) {
        this.theMessage = theMessage;
        theButton = new JButton("Change Color");
        setLayout(new FlowLayout());
        add(theButton);
        theButton.addActionListener(this);
        addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(blinkState ? getBackground() : currentColor());
        g.drawString(theMessage, messageX, messageY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == theButton) {
            changeColor();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        messageX = getX();
        messageY = getY();

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Поток выполнения — это алгоритм управления внутри программы. По
     * существу потоки идентичны процессам. В отличие от процессов много­
     * численные потоки делят одно и то же пространство программы — это
     * значит, что они могут делить переменные и методы (но также иметь
     * свои собственные локальные переменные). Потоки являются достаточ­
     * но легковесными в сравнении с процессами, поэтому одно приложение
     * может работать сразу с множеством потоков (возможно, сотнями или
     * тысячами) параллельно.
     */

    @Override
    public void run() {
        try {
            while (true) {
                blinkState = !blinkState;
                repaint();
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
        }
    }

    /**
     * В отличие от синхронизации в других языках ключевое слово
     * synchronized в Java обеспечивает блокировку на уровне языка. Это озна­
     * чает, что ни в коем случае вы не забудете разблокировать класс. Даже
     * если метод выдает исключение или поток остановлен, Java снимет бло­
     * кировку. Это свойство делает программирование при помощи потоков
     * в Java более простым, чем в других языках.
     */

    synchronized private void changeColor() {
        if (++colorIndex == someColors.length)
            colorIndex = 0;

        setForeground(currentColor()); // Использовать новый цвет
        repaint();
    }

    synchronized private Color currentColor() {
        return someColors[colorIndex];
    }
}