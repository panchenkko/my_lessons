package LessonsJavaCore.Other.GUI.WithBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HelloJava2 {
    public static void main(String[] args) {
        // Создаем и вызываем в методе main() графическое окно
        JFrame frame = new JFrame("HelloJava2");
        frame.add(new HelloComponent2("Hello, Java!"));

        // Эта строка говорит фрейму закрыть приложение, когда нажата кноп­
        // ка выхода. Это называется операцией закрытия по умолчанию, посколь­
        // ку эта операция, как почти любое другое взаимодействие с GUI, управ-
        // ляется событием. Мы могли бы зарегистрировать окно приемника, чтоб
        // получать уведомление о том, когда пользователь нажимает на кнопку
        // закрытия, и производить любое действие, которое нам нравится, но этот
        // удобный метод управляет общими процессами.

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

class HelloComponent2 extends JComponent implements MouseMotionListener {

    private String theMessage;
    private int messageX = 125, messageY = 95;

    public HelloComponent2(String theMessage) {
        this.theMessage = theMessage;
        // Этот метод является частью механизма события.
        // Он говорит системе: «Эй, мне интересно все, что происходит с мышью».
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawString(theMessage, messageX, messageY);
    }

    /**
     * Метод mouseDraggedO вызывается
     * автоматически на приемнике для получения событий, генерирующихся,
     * когда пользователь перемещает мышь, то есть двигает мышь с любой зажатой кнопкой.
     *
     * @param e объект MouseEvent, e, который содержит всю необходимую нам информацию об этом событии.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // Сохранить координаты мыши и нарисовать сообщение.
        messageX = getX();
        messageY = getY();

        // Мы можем использовать метод repaint () класса JComponent, что­
        // бы потребовать перерисовки нашего компонента, repaint () вынуж­
        // дает оконную систему Java включить в план запрос к нашему методу
        // paintComponent () в ближайшее возможное время; Java предоставляет не­
        // обходимый объект класса Graphics, как показано на рис. 2.8.
        //
        // Этот режим выполнения — не просто неудобство, вызванное неи­
        // мением подходящего графического контекста под рукой. Главное пре­
        // имущество этого режима — то, что поведение перерисовки управляется
        // кем-то другим, в то время как мы можем заниматься своими делами.
        // Система Java имеет отдельный, выделенный поток выполнения, кото­
        // рый управляет всеми запросами repaint (). Он может упорядочивать
        // и объединять запросы repaint(), как это необходимо, что позволяет
        // предотвратить перезагрузку оконной системы во время ситуаций с ин­
        // тенсивным рисованием, таких как прокрутка. Другое преимущество
        // это то, что весь функционал рисования должен быть воплощен в наш
        // метод paintComponent (); нас не прельщает распространять его по всему
        // приложению.

        repaint();
    }

    /**
     * Метод mouseMovedO вызывается всегда, когда пользователь
     * двигает мышью по области, не нажимая на кнопку.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
