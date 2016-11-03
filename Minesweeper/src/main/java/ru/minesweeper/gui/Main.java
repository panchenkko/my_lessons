package ru.minesweeper.gui;

import ru.minesweeper.StandardLogic;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.IGeneratorBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Запуск игры в графической версии
 */

public class Main {
	private static final JPanel controlPanel = new JPanel();
	private static JLabel label = new JLabel("");
	private static final GUIBoard board = new GUIBoard();

	public static void main(String[] arg) {
		SwingUtilities.invokeLater(() -> {
            final JFrame frame = new JFrame();
            frame.setTitle("Сапер");
            frame.setLayout(new BorderLayout());
            frame.setSize(500, 500);
            frame.add(board, BorderLayout.CENTER);
            board.setBorder(new EmptyBorder(10, 10, 10, 10));
            frame.add(controlPanel, BorderLayout.PAGE_END);

            label.setVerticalAlignment(SwingConstants.TOP);
            frame.add(label, BorderLayout.EAST);

            controlPanel.setLayout(new FlowLayout());
            final JButton generate = new JButton("Начать");
            StandardLogic level = new StandardLogic();
            level.easy();
            /*
             * ПЕРЕКЛЮЧЕНИЕ УРОВНЕЙ.
             */
            JMenuBar menuBar = new JMenuBar();  // Верхняя полоска с элементами меню
            frame.setJMenuBar(menuBar);
            JMenu menu = new JMenu("Уровень");    // Меню
            menuBar.add(menu);

            JMenuItem easy = new JMenuItem("Легкий"); // Подменю
            menu.add(easy);

            easy.addActionListener(e -> {
                level.easy();
                generate.doClick();
            });

            JMenuItem medium = new JMenuItem("Средний"); // Подменю
            menu.add(medium);

            medium.addActionListener(e -> {
                level.medium();
                generate.doClick();
            });

            JMenuItem expert = new JMenuItem("Эксперт"); // Подменю
            menu.add(expert);

            expert.addActionListener(e -> {
                level.expert();
                generate.doClick();
            });

            generate.addActionListener(new GUIAction (level, board, () -> {
                board.setIsFinish(false);
                ICell[][] cells = level.sizeField();
                label.setText("Флажки: 0 ");
                for (int i = 0; i < level.sumRow(); i++) {
                    for (int j = 0; j < level.sumColumn(); j++) {
                        cells[i][j] = new GUICell(false);
                    }
                }
                return cells;
            }));
            controlPanel.add(generate);
            center(frame);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    closePerform(frame);
                }
            });
            frame.setVisible(true);
        });
	}

	public static void center(Window w) {
		Dimension us = w.getSize();
		Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height - us.height) / 2;
		w.setLocation(newX, newY);
	}

	public static void closePerform(JFrame frame) {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	public static void setLabel(String textOnLabel) {
		label.setText(textOnLabel);
		label.repaint();
	}

	public static void setLosing(String body, String title) {
		JOptionPane.showMessageDialog(null, body, title, JOptionPane.INFORMATION_MESSAGE);
	}
}