package LessonsJavaCore.Urvanov.ru;

/**
 * Все строковые литералы в Java являются объектами класса String.
 *
 * Примеры создания строк:
 * * * String greeting1 = "Hello world!";
 * * * char[] charData = { 'h', 'e', 'l', 'l', 'o', '.' };
 * * * String greeting2 = new String(charData);
 * * *
 * * * Для строковых литералов используются двойный кавычки, а для примитивного типа char - одинарные.
 *
 * Когда компилятор видит строковый литерал, например "Hello world!", в коде, он создаёт объект класса java.lang.String.
 *
 * Так как строковые литеры являются объектами, то у них можно вызывать методы:
 * * * System.out.println("Hi, Larry.".length());  // 9
 *
 * Класс String НЕ имеет специальной поддержки для == , поэтому сравнивать строки нужно либо через метод equals(),
 * * * либо equalsIgnoreCase(), либо compareTo(), либо compareToIgnoreCase().
 *
 * В Java используется пул строковых литералов. Одинаковые строковые литералы всегда ссылаются на один и тот же
 * * * экземпляр класса String:
 * * * * * String vasya = "Vasya", ya = "ya";
 * * * * * System.out.println(vasya == "Vasya"); // 1                  (true)
 * * * * * System.out.println(vasya == ("Vas" + ya)); // 2             (false)
 * * * * * System.out.println(vasya == ("Vas" + "ya")); // 3           (true)
 * * * * * System.out.println(vasya == ("Vas" + ya).intern()); // 4    (true)
 * * *
 * * * Объяснение:
 * * * 1. Одинаковые строковые литералы всегда ссылаются на один и тот же экземпляр класса String.
 * * * 2. Экземпляры классы String, вычисленные во время выполнения, создаются заново, автоматически в пуле
 * * *    не участвуют и потому различны.
 * * * 3. Строковые литералы в константных выражениях вычисляются на этапе компиляции и затем расцениваются как
 * * *    обычные литералы.
 * * * 4. С помощью метода intern() можно добавить строку в пул либо получить ссылку на такую строку из пула.
 *
 * Многие методы строки принимают в качестве параметра или возвращают в качестве результата индекс. Если переданный
 * * * в параметр индекс выходит за пределы строки, то методы генерируют исключение java.lang.IndexOutOfBoundsException.
 *
 * public String concat(StringLibrary str)
 * * * Если длина строки str равна 0, то возвращает this, иначе возвращает строку, являющуюся конкатенацией
 * * * (объединением) текущей строки и str.
 *
 * public boolean contains(CharSequence s)
 * * * Возвращает true, если текущая строка содержит последовательность символов s. Класс java.lang.String реализует
 * * * интерфейс java.lang.CharSequence, так что в качестве параметра можно передавать экземпляр класса String.
 *
 * public boolean endsWith(String suffix)
 * * * Возвращает true, если текущая строка заканчивается на suffix или равна ему. Если suffix является пустой строкой,
 * * * то возвращает true.
 *
 * public byte[] getBytes(Charset charset)
 * public byte[] getBytes(String charsetName) throws UnsupportedEncodingException
 * * * Возвращают массив байт, содержащий строку в указанной кодировке.
 *
 * public int indexOf(int ch)
 * public int indexOf(int ch, int fromIndex)
 * * * Возвращает индекс первого вхождения символа ch, начиная с fromIndex. Если символ не найден, то возвращает -1.
 *
 * public String intern()
 * * * Магический метод. В Java существует пул строк. Этот метод проверяет наличие строки в пуле, если в пуле есть
 * * * такая строка, то метод возвращает ссылку на неё. Если в пуле нет такой строки, то строка добавляется в пул,
 * * * и возвращается ссылка на неё.
 *
 * public static String join(CharSequence delimiter, CharSequence... elements)
 * * * Объединяет несколько CharSequence в одну строку, используя в качестве разделителя delimiter.
 *
 * public String replace(char oldChar, char newChar)                                                                  *
 * * * Возвращает строку, в которой все oldChar заменены на newChar. Если oldChar в строке нет, то возвращается
 * * * исходная строка.
 *
 * public boolean startsWith(String prefix)
 * * * Возвращает true, если строка начинается с prefix либо равна ему. Если prefix является пустой строкой,
 * * * то возвращается true.
 *
 * public String toLowerCase()                                                                                        *
 * public String toLowerCase(Locale locale)
 * * * Возвращает строку, где все символы приведены в нижний регистр относительно текущей локали или указанной.
 *
 * public String toUpperCase()                                                                                        *
 * public String toUpperCase(Locale locale)
 * * * Возвращает строку, где все символы приведены в верхний регистр относительно текущей локали или указанной.
 *
 * public String trim()                                                                                               *
 * * * Возвращает строку, в которой убраны пробелы в начале и в конце строки. Если строка начинается и заканчивается
 * * * на непробельные символы (больше '\u0020'), то возвращается ссылка на this.
 *
 * В отличие от String у StringBuilder кроме длины есть ещё вместимость/ёмкость (capacity). Вместительность можно
 * * * узнать с помощью метода capacity(), она всегда больше или равна длине.
 *
 * StringBuilder(int initCapacity)                                                                                    *
 * * * Создаёт пустой StringBuilder с начальной вместительностью в initCapacity элементов.
 *
 * StringBuilder(String s)                                                                                            *
 * * * Создаёт StringBuilder, который содержит указанную строку и 16 дополнительных пустых элементов.
 *
 * StringBuilder содержит пару дополнительных методов, связанных с длиной, которых нет в String:
 * * * void setLength(int newLength)
 * * * * * Устанавливает длину последовательности символов. Если newLength меньше length(), то последние символы
 * * * * * обрезаются. Если newLength больше length(), то в конец последовательности добавляются нулевые символы.
 * * * void ensureCapacity(int minCapacity)
 * * * * * Обеспечивает, что вместительность будет как минимум равной minCapacity.
 *
 * StringBuilder delete(int start, int end)
 * StringBuilder deleteCharAt(int index)
 * * * Первый метод удаляет подпоследовательность с start до end-1 включительно. Второй метод удаляет символ по индексу.
 *
 * StringBuilder reverse()
 * * * Меняет порядок символов в StringBuilder на обратный.
 *
 * Пример использования:
 * * * String andStr = " and Petya";
 * * * StringBuilder stringBuilder = new StringBuilder();
 * * * stringBuilder.append("Vasya");
 * * * stringBuilder.append(andStr);
 * * * stringBuilder.append(" go to school.");
 * * * System.out.println(stringBuilder);
 */

public class StringLibrary {
}
