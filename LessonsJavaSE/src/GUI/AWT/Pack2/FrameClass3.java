package GUI.AWT.Pack2;

import java.awt.*;
import java.awt.event.*;

/**
 * Обработка событий во вложенных классах
 *
 *  ** Плюсы **
 * Вложенный класс имеет неявную ссылку на внешний объект
 * Вспомогательного кода для хранения ссылки не требуется
 */
public class FrameClass3 {
    int counter;
    Frame f = new Frame();
    Label label = new Label("" + counter);
    Button incButton = new Button("+");
    Button decButton = new Button("-");

    public FrameClass3() {
        incButton.addActionListener(new IncActionListener());
        decButton.addActionListener(new DecActionListener());
        f.add(incButton, BorderLayout.EAST);
        f.add(decButton, BorderLayout.WEST);
        f.add(label, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new FrameClass();
    }

    class IncActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("" + ++counter); // Доступ через неявную ссылку
        }
    } // Конец вложенного класса

    class DecActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("" + --counter); // Доступ через неявную ссылку
        }
    } // Конец вложенного класса

} // Конец основного класса

