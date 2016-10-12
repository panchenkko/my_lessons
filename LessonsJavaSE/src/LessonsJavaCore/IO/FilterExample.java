package LessonsJavaCore.IO;

import java.io.*;
import java.util.Arrays;

public class FilterExample {

    /**
     * Метод для фильтрации бинарных данных. Фильтрует нули, записывает единицы.
     * @param input Для чтения данных с одного источника
     * @param output Для записи данных в другой источник
     * @param buffSize По сколько байтов будет считываться за раз
     */
    public static void filter(InputStream input, OutputStream output, int buffSize) throws IOException {
        // Поля для определения состояния
        final int ZERO_STATE = 1;
        final int NUMBERS_STATE = 0;
        // Массив, с помощью какого считываем данные
        byte[] buffer = new byte[buffSize];
        // Переменная, для записи кол-ва считанных байтов
        int count;
        // 1. Записываем байты в buffer
        // 2. Устанавливаем кол-во счмтанных байтов в count
        // 3. Действуем, пока данные не закончатся
        while ((count = input.read(buffer)) != -1) {
            // Устанавливаем состояние на нуль
            int state = ZERO_STATE;
            // Переменные, для определения диапазона подряд идущих единиц
            int start = 0, end = 0;
            // Цикл для чтения каждого байта отдельно
            for (int index = 0; index < count; index++) {
                // Проверка на состояние
                switch (state) {
                    // Если состояние было на нуле
                    case ZERO_STATE:
                        // Если считываемый байт равен единице,
                        // то переходим в состояние NUMBERS и устанавливаем диапазон
                        if (buffer[index] == 1) {
                            start = index; end++;
                            state = NUMBERS_STATE;

                            System.out.print(buffer[index]);
                        }
                        break;
                    // Если состояние было на единице
                    case NUMBERS_STATE:
                        // Есди считываемый байт равен нулю,
                        // то записываем все единицы, какие находятся в диапазоне от start до end,
                        // скидываем диапазон и переходим в состояние ZERO
                        if (buffer[index] == 0) {
                            output.write(buffer, start, end);
                            start = 0; end = 0;
                            state = ZERO_STATE;
                            // Если считываемый байт равен единице, то обновляем конец диапазона
                        } else if (buffer[index] == 1) {
                            end++;

                            System.out.print(buffer[index]);
                        }
                        break;
                }
            }
            // Если цикл закончился, а состояние было на NUMBERS,
            // то записываем все единицы, какие находятся в диапазоне от start до end,
            if (state == NUMBERS_STATE)
                output.write(buffer, start, end);
        }
        output.flush();
    }

    public static void main(String[] args) {
        try {
            // Устанавливаем кол-во считываемых байт за раз
            final int BUFFER_LENGTH = 4;
            // Массив байтов, какой поток будет считывать
            final byte[] buffer = {0, 1, 0, 1, 0, 1, 1, 1};
            // Массив байтов, в какой будет происходит запись
            final byte[] filteredBuffer;

            // Инициализация класса для чтения потока данных
            ByteArrayInputStream input = new ByteArrayInputStream(buffer);
            // Инициализация класса для записи потока данных
            ByteArrayOutputStream outputByte = new ByteArrayOutputStream(buffer.length);

            FilterExample.filter(input, outputByte, BUFFER_LENGTH);

            System.out.println();

            // Записываем в массив данные, какие были считаны
            filteredBuffer = outputByte.toByteArray();

            System.out.println("filteredBuffer: " + Arrays.toString(filteredBuffer));

            input.close();
            outputByte.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
