package Other.GUI.SWING;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;

// Класс окна
class MyFrame extends JFrame{

    // Наши панельки
    private JPanel flowPanel = new JPanel();
    private JPanel boxPanel = new JPanel();

    // Кнопки для flowPanel
    private JButton fb1 = new JButton("Flow 1");
    private JButton fb2 = new JButton("Flow 2");
    private JButton fb3 = new JButton("Flow 3");

    // Кнопки для boxPanel
    private JButton bb1 = new JButton("Box 1");
    private JButton bb2 = new JButton("Box 2");

    public MyFrame(){
        setTitle("Уроки Swing-а");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Создаём flowPanel и добавляем в неё кнопочки
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(fb1);
        flowPanel.add(fb2);
        flowPanel.add(fb3);

        /* Создаём менеджер BoxLayout box, в конструктор
         * которого передаем саму панель и ориентацию панели.
         * Вешаем менеджер box на boxPanel, и добавляем
         * в неё кнопочки
        */
        BoxLayout box = new BoxLayout(boxPanel, BoxLayout.X_AXIS);
        boxPanel.setLayout(box);
        boxPanel.add(bb1);
        boxPanel.add(bb2);

        // Эти 2 строки кода необязательны для данной задачи
        flowPanel.setBackground(java.awt.Color.red);
        boxPanel.setBackground(java.awt.Color.green);

        /* К панели содержимого окна добавляем:
         * flowPanel - в центр
         * boxPanel  - на восток
         */
        this.getContentPane().add(flowPanel, BorderLayout.CENTER);
        this.getContentPane().add(boxPanel, BorderLayout.EAST);

        // размеры окна подгоняются под содержимое
        pack();
    }

}

public class swing2 {
    public static void main(String[] args) {
        // Показываем окно на экране
        new MyFrame().setVisible(true);
    }
}