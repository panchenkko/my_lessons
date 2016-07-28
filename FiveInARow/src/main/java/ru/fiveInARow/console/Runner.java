package ru.fiveInARow.console;

import ru.fiveInARow.Logic;
import ru.fiveInARow.BaseAction;
import ru.fiveInARow.interfaces.ICell;
import ru.fiveInARow.interfaces.IGeneratorBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Запуск игры в консольной версии
 */
public class Runner {

    // Генерация поля
    public void generateField(Scanner sc, Logic logic, ICell[][] cells) {
        final BaseAction action = new BaseAction(logic, new ConsoleBoard(), new IGeneratorBoard() {
            @Override
            public ICell[][] generate() {
                generateEmptyCells(logic, cells);
                return cells;
            }
        });
        action.initGame();
        cycleInput(sc, action, logic);
    }

    // При первом ходе делаем все клетки пустыми
    public void generateEmptyCells(Logic logic, ICell[][] cells) {
        for (int i = 0; i < logic.sumRow(); i++)
            for (int j = 0; j < logic.sumColumn(); j++) {
                cells[i][j] = new ConsoleCell();
            }
    }

    // Вводим координаты двух ячеек. Работает до тех пор, пока пользователь не выиграет или не проиграет
    public void cycleInput(Scanner sc, BaseAction action, Logic logic) {
        int row = 0, column = 0, row2 = 0, column2 = 0;
        try {
            do {
                try {
                    System.out.println("*** Какую закрашенную ячейку вы хотите взять ***");
                    System.out.println();
                    System.out.print("Введите номер ряда: ");
                    row = sc.nextInt();
                    System.out.print("Введите номер столбца: ");
                    column = sc.nextInt();

                    System.out.println();

                    System.out.println("*** В какую ячейку вы хотите переместить ***");
                    System.out.println();
                    System.out.print("Введите номер ряда: ");
                    row2 = sc.nextInt();
                    System.out.print("Введите номер столбца: ");
                    column2 = sc.nextInt();
                } catch (InputMismatchException ignored) {}
                System.out.println();
                if (row - 1 < logic.sumRow() && column - 1 < logic.sumColumn() && row - 1 >= 0 && column - 1 >= 0 &&
                    row2 - 1 < logic.sumRow() && column2 - 1 < logic.sumColumn() && row2 - 1 >= 0 && column2 - 1 >= 0) {
                    // Действительно ли первая клетка полностью закрашенная, а вторая пустая
                    if (logic.checkingCells(row - 1, column - 1, row2 - 1, column2 - 1)) {
                        action.select(row - 1, column - 1, row2 - 1, column2 - 1);
                    } else {
                        System.out.println("Первая клетка должна быть закрашенная, " +
                                "а вторая со звездочкой или пустая! Попробуйте ещё раз.");
                        System.out.println();
                    }
                // Создаем исключение, если пользователь ввел некорректные данные
                } else throw new ArrayIndexOutOfBoundsException("Такого ряда или столбца не существует!\n");
            } while (!logic.finish());
        // Перехватываем и выводим исключение
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            cycleInput(sc, action, logic);
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        Logic logic = new Logic();
        ICell[][] cells = logic.sizeField();
        try (Scanner sc = new Scanner(System.in)) {
            runner.generateField(sc, logic, cells);
        }
    }
}
