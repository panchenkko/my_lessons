package Other.GUI.AWT.Pack2;

import java.awt.*;
import java.awt.event.*;

/**
 * Обработка событий в анонимных классах
 *
 *  ** Плюсы **
 *  Анонимный класс имеет неявную ссылку на внешний объект
 *  Вспомогательного кода для хранения ссылки не требуется
 *  Имеет смысл использовать для коротких и простых обработчиков событий
 */
public class FrameClass4 {
    int counter;
    Frame f = new Frame();
    Label label = new Label("" + counter);
    Button incButton = new Button("+");
    Button decButton = new Button("-");

    public static void main(String[] args) {
        new FrameClass4();
    }

    // Конструктор
    public FrameClass4() {
        incButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("" + ++counter);
            }
        });
        decButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("" + --counter);
            }
        });
        f.add(incButton, BorderLayout.EAST);
        f.add(decButton, BorderLayout.WEST);
        f.add(label, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

} // Конец основного класса


