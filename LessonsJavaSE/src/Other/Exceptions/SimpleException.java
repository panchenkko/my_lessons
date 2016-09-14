package Other.Exceptions;

// getMessage() — получить сообщение об ошибке, которое обычно имеет смысл читать
// printStackTrace() — распечатать полный стек вызовов.
// Стек вызовов — это полный список всех методов внутри которых случилась ошибка.

public class SimpleException extends Exception {
    // Это наше поле для хранения информации, присущей данному
    // классу-исключению. Поле немножко надуманное, но здесь может быть
    // и достаточно важная информация
    private int errorCode;

    // переопределяем конструктор
    public SimpleException(String message) {
        this(0, message);
    }

    // Создаем свой конструктор
    public SimpleException(int errorCode, String message) {
        // Вызываем конструктор предка
        super(message);
        // Добавляем инициализацию своего поля
        this.errorCode = errorCode;
    }

    // Метод для получения кода ошибки
    public int getErrorCode() {
        return errorCode;
    }
}
