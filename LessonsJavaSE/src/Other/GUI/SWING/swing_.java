package Other.GUI.SWING;

import javax.swing.*;

class SimpleWindow extends JFrame {

    SimpleWindow() {
        super("Пробное окно");
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Закрывает при нажатии на крестик

//      // 1.
//                JPanel panel = new JPanel(); // экземпляр класса  jpanel
//        panel.setLayout(new FlowLayout()); // Менеджен размещения FlowLayout. Он размещает всё строго (строка за строкой)
//        //  МОЖНО И ТАК ОБЪЯВИТЬ
//        //FlowLayout newLayout = new FlowLayout();
//        //panel.setLayout(newLayout);
//        panel.add(new JButton("Кнопка"));
//        panel.add(new JButton("+"));
//        panel.add(new JButton("-"));
//        panel.add(new JButton("Кнопка с длинной надписью"));
//        setContentPane(panel);

        // 2.
//        getContentPane().add(new JButton("Кнопка"), BorderLayout.NORTH); // Менеджер граничного размещения BorderLayout
//        getContentPane().add(new JButton("+"), BorderLayout.EAST);
//        getContentPane().add(new JButton("-"), BorderLayout.WEST);
//        getContentPane().add(new JButton("Кнопка с длинной надписью"), BorderLayout.SOUTH);
//        getContentPane().add(new JButton("В ЦЕНТР!"));

        // 3.
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(2,3,5,10)); // Менеджер табличного размещения GridLayout
//        panel.add(new JButton("Кнопка"));
//        panel.add(new JButton("+"));
//        panel.add(new JButton("-"));
//        panel.add(new JButton("Кнопка с длинной надписью"));
//        panel.add(new JButton("еще кнопка"));
//        setContentPane(panel);

        // 4.
//        Box box = Box.createVerticalBox();
//        box.add(new JButton("Кнопка"));
//        box.add(Box.createVerticalStrut(10));
//        box.add(new JButton("+"));
//        box.add(Box.createVerticalGlue());
//        box.add(new JButton("-"));
//        box.add(Box.createVerticalStrut(10));
//        box.add(new JButton("Кнопка с длинной надписью"));
//        setContentPane(box);

        // 5.
//        Box box = Box.createVerticalBox();
//
//        JButton centerButton = new JButton("Кнопка");
//        centerButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
//        box.add(centerButton);
//
//        JButton leftButton = new JButton("+");
//        leftButton.setAlignmentX(JComponent.LEFT_ALIGNMENT);
//        box.add(leftButton);
//
//        JButton rightButton = new JButton("-");
//        rightButton.setAlignmentY(JComponent.RIGHT_ALIGNMENT); // Выравнивание по оси X и по ПРАВОМУ краю
//        box.add(rightButton);
//
//        JButton centerButton2 = new JButton("Кнопка с длинной надписью");
//        centerButton2.setAlignmentY(JComponent.CENTER_ALIGNMENT);
//        box.add(centerButton2);
//        setContentPane(box);

//        // 6. - Настройки вручную
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        JButton button = new JButton("Кнопка");
//        button.setSize(80, 30);
//        button.setLocation(20,20);
//        panel.add(button);
//        button = new JButton("Кнопка с длинной надписью");
//        button.setSize(220, 40);
//        button.setLocation(20,70);
//        panel.add(button);
//        setContentPane(panel);

        setSize(250, 150); // Размер окна
    }

}

public class swing_ {

    public static void main (String [] args) {
        JFrame myWindow = new SimpleWindow();
        myWindow.setVisible(true); // False - если нужно, чтобы окно стало невидимым

    }
}
