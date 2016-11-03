package ru.minesweeper.console;

import ru.minesweeper.BaseAction;
import ru.minesweeper.StandardLogic;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.IGeneratorBoard;
import ru.minesweeper.BaseAction;
import ru.minesweeper.StandardLogic;
import ru.minesweeper.interfaces.ICell;
import ru.minesweeper.interfaces.IGeneratorBoard;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Запуск игры в консольной версии
 */

public class Runner {

    // Генерация поля
    public void generateField(Scanner sc, StandardLogic logic, ICell[][] cells) {
        final BaseAction action = new BaseAction(logic, new ConsoleBoard(), () -> {
            generateEmptyCells(logic, cells);
            System.out.println("Бомб: " + logic.sumBombs());
            System.out.println("Пустых клеток: " + ((logic.sumRow() * logic.sumColumn()) - logic.sumBombs()));
            return cells;
        });
        action.initGame();
        cycleInput(sc, action, logic);
    }

    // При первом ходе делаем все клетки пустыми
    public void generateEmptyCells(StandardLogic logic, ICell[][] cells) {
        for (int i = 0; i < logic.sumRow(); i++)
            for (int j = 0; j < logic.sumColumn(); j++)
                cells[i][j] = new ConsoleCell(false);
    }

    // Вводим ряд и столбец. Работает до тех пор, пока пользователь не выиграет или не проиграет
    public void cycleInput(Scanner sc, BaseAction action, StandardLogic logic) {
        int row = 0, column = 0;
        try {
            do {
                try {
                    System.out.print("Введите номер ряда: ");
                    row = sc.nextInt();
                    System.out.print("Введите номер столбца: ");
                    column = sc.nextInt();
                    System.out.print("Там бомба (да/нет)? ");
                } catch (InputMismatchException ignored) {}
                String answer = sc.next();
                System.out.println();
                if (row - 1 < logic.sumRow() && column - 1 < logic.sumColumn() && row - 1 >= 0 && column - 1 >= 0) {
                    switch (answer) {
                        case "да":  action.select(row - 1, column - 1, true);
                                    break;
                        case "нет": action.select(row - 1, column - 1, false);
                                    break;
                        default: cycleInput(sc, action, logic);
                                 return;
                    }
                // Создаем исключение, если пользователь ввел некорректные данные
                } else throw new ArrayIndexOutOfBoundsException("Такой клетки не существует!\n");
            } while (!logic.finish() && !logic.shouldBang(row - 1, column - 1));
        // Перехватываем и выводим исключение
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            cycleInput(sc, action, logic);
        }
    }

    /**
     * Выбираем уровень и запускаем генерацию поля
     */
    public static void main(String[] args) {
        Runner runner = new Runner();
        StandardLogic logic = new StandardLogic();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(
                    "\n<-- ВВЕДИТЕ ЦИФРУ -->\n\n"
                    + "Выберите уровень: "
                    + "\n   1. Легкий (9x9)"
                    + "\n   2. Средний (16x16)"
                    + "\n   3. Эксперт (30x16)");
            System.out.print("Ваш выбор: ");
            int answer = sc.nextInt();
            switch (answer) {
                case 1: logic.easy();
                        break;
                case 2: logic.medium();
                        break;
                case 3: logic.expert();
                        break;
                default:
                    System.out.println("\nВы не выбрали уровень. По умолчанию запущен \"Легкий\"!\n");
                    logic.easy();
            }
            ICell[][] cells = logic.sizeField();
            runner.generateField(sc, logic, cells);
        } catch (InputMismatchException ime) {
            System.out.println(ime.getMessage());
        }
    }
}
