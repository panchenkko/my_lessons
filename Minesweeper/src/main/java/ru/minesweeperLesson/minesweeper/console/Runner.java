package ru.minesweeperLesson.minesweeper.console;

import ru.minesweeperLesson.minesweeper.BaseAction;
import ru.minesweeperLesson.minesweeper.console.ConsoleBoard;
import ru.minesweeperLesson.minesweeper.console.ConsoleCell;
import ru.minesweeperLesson.minesweeper.interfaces.Cell;
import ru.minesweeperLesson.minesweeper.interfaces.GeneratorBoard;
import ru.minesweeperLesson.minesweeper.logics.Easy;

import java.util.Random;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размерность сапёра: ");
        int size = sc.nextInt();

        final BaseAction action = new BaseAction(new Easy(), new ConsoleBoard(), new GeneratorBoard() {
            @Override
            public Cell[][] generate() {
                Cell[][] cells = new Cell[size][size];
                int sumBomb = 0;
                int sum = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        boolean bool = random.nextBoolean();
                        cells[i][j] = new ConsoleCell(bool);
                        if (!bool) {
                            sumBomb++;
                        } else {
                            sum++;
                        }
                    }
                }
                System.out.println("sumBomb: " + sumBomb);
                System.out.println("sum: " + sum);
                return cells;
            }
        });
        action.initGame();

        sc.close();
    }
}
