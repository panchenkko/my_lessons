package ru.minesweeperLesson.minesweeper.console;

import ru.minesweeperLesson.minesweeper.BaseAction;
import ru.minesweeperLesson.minesweeper.interfaces.ICell;
import ru.minesweeperLesson.minesweeper.interfaces.IGeneratorBoard;
import ru.minesweeperLesson.minesweeper.StandardLogicConsole;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Запуск игры в консольной версии
 */

public class Runner {

    // Генерация поля
    public void generateField(Scanner sc, StandardLogicConsole level, ICell[][] cells) {
        final BaseAction action = new BaseAction(level, new ConsoleBoard(), new IGeneratorBoard() {
            @Override
            public ICell[][] generate() {
                generateEmptyCells(level, cells);
                System.out.println("Бомб: " + level.sumBombs());
                System.out.println("Пустых клеток: " + ((level.sumRow() * level.sumColumn()) - level.sumBombs()));
                return cells;
            }
        });
        action.initGame();
        cycleInput(sc, action, level);
    }

    // При первом ходе делаем все клетки пустыми
    public void generateEmptyCells(StandardLogicConsole level, ICell[][] cells) {
        for (int i = 0; i < level.sumRow(); i++)
            for (int j = 0; j < level.sumColumn(); j++)
                cells[i][j] = new ConsoleCell(false);
    }

    // Вводим ряд и столбец. Работает до тех пор, пока пользователь не выиграет или не проиграет
    public void cycleInput(Scanner sc, BaseAction action, StandardLogicConsole level) {
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
                if (row - 1 < level.sumRow() && column - 1 < level.sumColumn() && row - 1 >= 0 && column - 1 >= 0) {
                    if (Objects.equals(answer, "да"))
                        action.select(row - 1, column - 1, true);
                    else if (Objects.equals(answer, "нет"))
                        action.select(row - 1, column - 1, false);
                // Создаем исключение, если пользователь ввел некорректные данные
                } else throw new ArrayIndexOutOfBoundsException("Такого ряда или столбца не существует!\n");
            } while (!level.finish() && !level.shouldBang(row - 1, column - 1));
        // Перехватываем и выводим исключение
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            cycleInput(sc, action, level);
        }
    }

    /**
     * Выбираем уровень и запускаем генерацию поля
     */
    public static void main(String[] args) {
        Runner runner = new Runner();
        StandardLogicConsole level = new StandardLogicConsole();
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
                case 1: level.easy();
                        break;
                case 2: level.medium();
                        break;
                case 3: level.expert();
                        break;
                default:
                    System.out.println("\nВы не выбрали уровень. По умолчанию запущен \"Легкий\"!\n");
                    level.easy();
            }
            ICell[][] cells = level.sizeField();
            runner.generateField(sc, level, cells);
        } catch (InputMismatchException ime) {
            System.out.println(ime.getMessage());
        }
    }
}
