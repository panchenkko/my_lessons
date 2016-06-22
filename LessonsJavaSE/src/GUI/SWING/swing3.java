package GUI.SWING;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

class SimpleWindows extends JFrame {

    private JPanel createPanel(Border border, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JButton(text));
        panel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), border));
        return panel;
    }

//    EmptyBorder — пустая рамка, позволяет создать отступы вокруг панели. Размеры отступов задаются в конструкторе четырьмя целыми числами.
//    TitledBorder — рамка с заголовком. Простейший конструктор имеет один
//                   параметр типа String (текст заголовка). Заголовок может размещаться вдоль любой стороны рамки, иметь различные начертания.
//    EtchedBorder — рамка с тиснением. Может быть вогнутой или выпуклой.
//    BevelBorder — объемная рамка (выпуклая или вогнутая). Можно настроить цвета, требуемые для получения объемных эффектов.
//    SoftBevelBorder — то же самое, что BevelBorder, но позволяет дополнительно скруглить углы.
//    LineBorder — простая рамка, нарисованная сплошной линией. Можно выбирать цвет и толщину линии, скруглить углы.
//    MatteBorder — рамка из повторяющегося рисунка.
//    CompoundBorder — объединяет две рамки, передаваемые в качестве параметров конструктору в одну новую рамку.

    SimpleWindows() {
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 5, 10));
        panel.add(createPanel(new TitledBorder("Рамка с заголовком"), "TitledBorder"));
        panel.add(createPanel(new EtchedBorder(), "EtchedBorder"));
        panel.add(createPanel(new BevelBorder(BevelBorder.LOWERED), "BevelBorder"));
        panel.add(createPanel(new SoftBevelBorder(BevelBorder.RAISED), "SoftBevelBorder"));
        panel.add(createPanel(new LineBorder(Color.ORANGE, 4), "LineBorder"));
        panel.add(createPanel(new MatteBorder(new ImageIcon("1.gif")), "MatteBorder"));
        setContentPane(panel);
        pack();

    }
}

public class swing3 {

    public static void main(String[] args) {

        JFrame myWindow = new SimpleWindows();
        myWindow.setVisible(true); // False - если нужно, чтобы окно стало невидимым
    }
}
