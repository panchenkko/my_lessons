package ru.fiveInARow.gui;

import ru.fiveInARow.Logic;
import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.IGeneratorBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	private static final JPanel controlPanel = new JPanel();
	private static JLabel score = new JLabel("");
	private static final GUIBoard board = new GUIBoard();

	public static void main(String[] arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame frame = new JFrame();
				frame.setTitle("5 в ряд");
				frame.setLayout(new BorderLayout());
				frame.setSize(510, 500);
				frame.add(board, BorderLayout.CENTER);
				board.setBorder(new EmptyBorder(10, 10, 10, 10));
				frame.add(controlPanel, BorderLayout.PAGE_END);

				score.setVerticalAlignment(SwingConstants.TOP);
				frame.add(score, BorderLayout.EAST);

				controlPanel.setLayout(new FlowLayout());
				final JButton generate = new JButton("Начать");
				Logic logic = new Logic();
				generate.addActionListener(new GUIAction (logic, board, new IGeneratorBoard() {
					@Override
					public ICell[][] generate() {
						logic.setScore(0);
						ICell[][] cells = logic.sizeField();
						score.setText("Ваш счет: " + logic.getScore() + " ");
						for (int i = 0; i < logic.sumRow(); i++) {
							for (int j = 0; j < logic.sumColumn(); j++) {
								cells[i][j] = new GUICell();
							}
						}
						return cells;
					}
				}
				));
				controlPanel.add(generate);
				centre(frame);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						closePerform(frame);
					}
				});
				frame.setVisible(true);
			}
		});
	}

	public static void centre(Window w) {
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

	public static void setScore(String textOnLabel) {
		score.setText(textOnLabel);
		score.repaint();
	}

	public static void setLosing() {
		JOptionPane.showMessageDialog(null,
				"Нажмите 'OK', чтобы закрыть окно.",
				" К сожалению Вы проиграли",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
