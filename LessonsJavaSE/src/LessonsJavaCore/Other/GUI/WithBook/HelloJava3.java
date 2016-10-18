package LessonsJavaCore.Other.GUI.WithBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HelloJava3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("HelloJava3");
        frame.add(new HelloComponent3("Hello, Java!"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}

/**
 * Component — это базовый класс, из которого происходят все компо­
 * ненты интерфейса GUI Java. Он содержит переменные, которые пред­
 * ставляют положение, форму, общий вид и статус объекта и методов для
 * общего рисования и обработки событий, avax.swing. JComponent расши­
 * ряет базовый класс Component и улучшает его для набора инструмен­
 * тов Swing. Метод paintComponent (), который мы использовали в нашем
 * примере, унаследован от класса JComponent. HelloComponent — это вид
 * JComponent и наследует все его публичные члены, как другие компонен­
 * ты GUI.
 *
 * Класс Container является расширенным типом класса Component, ко­
 * торый содержит список дочерних компонентов в иерархическом виде
 * и помогает группировать их. Container заставляет своих детей показы­
 * ваться и упорядочивает их на экране согласно отдельной стратегии раз­
 * работки. Поскольку класс Container также является классом Component,
 * он может быть помещен рядом с другими объектами Component в других
 * классах Container иерархически,
 */

class HelloComponent3 extends JComponent implements MouseMotionListener, ActionListener {
    String theMessage;
    int messageX = 125, messageY = 95;
    JButton theButton;
    int colorIndex; // текущий указатель в объекте someColors

    /**
     * Экземпляр класса Color представляет видимый цвет. Для удобства
     * класс Color содержит некоторые статические, предопределенные объ­
     * екты с дружелюбными именами GREEN, RED и MAGENTA. Переменная GREEN,
     * например, является статическим членом в классе Color. Тип данных пе­
     * ременной GREEN — Color. Внутри, в стране Java, она инициализируется
     * таким образом:
     *
     * public final static Color GREEN = new Color(0, 255, 0);
     *
     * Альтернативой использованию этих
     * предопределенных цветов является создание цвета вручную, при этом
     * нужно указать красный, зеленый и синий компоненты (RGB), исполь­
     * зуя конструкторы класса.
     */

    static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};

    public HelloComponent3(String theMessage) {
        this.theMessage = theMessage;

        theButton = new JButton("Change Color");
        setLayout(new FlowLayout());

        // Диспетчер компоновки - это layouts
        //
        // Чтобы добавить кнопку в диспетчер, мы вызываем метод add(), ко­
        // торый HelloComponent3 наследует от класса Container, передавая объект
        // JButton как параметр:
        //
        // add () — это метод, унаследованный нашим классом от класса
        // Container. Он добавляет наш JButton к списку компонентов, которыми
        // управляет контейнер HelloComponent3. Соответственно, HelloComponent3
        // ответственен за JButton: он делает кнопку видимой и определяет, где
        // в окне должна размещаться кнопка.

        add(theButton);
        // Регистрируем этот класс, как приемник событий действия кнопки
        theButton.addActionListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawString(theMessage, messageX, messageY);
    }

    /**
     * Объект JButton генерирует особый тип событий, называ­
     * емый ActionEvent, когда кто-то щелкает по нем мышыо. Чтобы получить
     * эти события, мы добавили еще один метод к классу HelloComponent3:
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Кто-то нажимал нашу кнопку?
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

    synchronized private void changeColor() {
        // Изменить индекс на другой цвет, неудобно
        if (++colorIndex == someColors.length)
            colorIndex = 0;

        // Меняет цвет, используемый для рисования текста в нашем компоненте.
        setForeground(currentColor()); // Использовать новый цвет

        repaint();
    }

    /**
     * Мы точно так же могли бы использовать выражение someColors
     * [colorlndex] везде, где мы используем currentColor(); однако созда­
     * ние методов для скрытия общих задач является еще одним способом
     * укрыться от деталей нашего класса. В альтернативной реализации мы,
     * возможно, отправили бы детали всего кода, связанного с цветом, в от­
     * дельный класс. Мы бы могли создать класс, который принимает массив
     * цветов в свой конструктор и затем предоставляет два метода: один для
     * запроса текущего цвета и один для перехода к другому цвету (просто
     * пища для размышления).
     */
    synchronized private Color currentColor() {
        return someColors[colorIndex];
    }
}