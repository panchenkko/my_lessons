/**
 * Для установки соответствущего формата в поля ввода
 * @param value Получаем значение выбранного формата
 */
function saveFormat(value) {
    // Получаем все нужные значения
    var first = $('input[name="first"]');
    var second = $('input[name="second"]');

    // Пользователь поменял формат, соответственно мы очищаем поля ввода
    first.val('');
    second.val('');

    // Устанавливаем соответствующий формату шаблон ввода для полей
    if (value == 'binary') {
        first.attr('pattern','^[0-1]+$');
        second.attr('pattern','^[0-1]+$');
    } else
    if (value == 'octal') {
        first.attr('pattern','^[0-7]+$');
        second.attr('pattern','^[0-7]+$');
    } else
    if (value == 'decimal') {
        first.attr('pattern','^[0-9]+$');
        second.attr('pattern','^[0-9]+$');
    } else
    if (value == 'hexadecimal') {
        first.attr('pattern','^[a-fA-F\\d]+$');
        second.attr('pattern','^[a-fA-F\\d]+$');
    }
}

/**
 * Функция для проверки валидации
 * @param first Первое число в выражении
 * @param second Второе число в выражении
 * @returns {boolean} Если валидацию прошли успешно, возвращаем истину
 */
function validation(first, second) {
    // Получаем рег. выражение, соответствующее выбранному формату
    var checkFormat = new RegExp(first.attr('pattern'));

    // Если поля пусты, говорим пользователю, чтобы он их заполнил и отменяем дальнейшие функции
    if (first.val() == '' && second.val() == '') {
        alert('Поля пусты. Заполните их пожалуйста!');
        return false;
    }

    // Если значения полей не соответствуют формату, говорим пользователю,
    // чтобы он их заново заполнил и отменяем дальнейшие функции
    if (!checkFormat.test(first.val()) || !checkFormat.test(second.val())) {
        alert('Заполните поля в соответствии с выбранным форматом!');

        first.val('');
        second.val('');

        return false;
    }

    // Если проверка валидации прошла успешно, разрешаем выполнять дальнейшие функции
    return true;
}

/**
 * Функция для отправки запроса на сервер. Последний уже считывает результат и отправляет обратно
 */
function calc() {
    // Получаем все нужные значения
    var format = $('input[name="format"]:checked');
    var first = $('input[name="first"]');
    var second = $('input[name="second"]');
    var operation = $("#operation option:selected");

    // Проверка валидации
    if (validation(first, second)) {
        // Если значения полей соответсвуют всем правилам, отправляем запрос на обработку этих значений
        $.ajax({
            url: '/calc', // url, какой будет принимать данный запрос
            type: 'get', // тип запроса
            data: ({ // данные, какие отправляются с запросом
                format: format.val(),
                first: first.val(),
                second: second.val(),
                operation: operation.val()
            }),
            success: function(result) { // Если запрос прошел успешно, выводим результат
                console.log(result);
                $("input[name='result']").val(result);
            },
            error: function() { // Если запрос провалился, выводим сообщение об ошибке
                // alert('Ошибка! Попробуйте ещё раз.');
            }
        });
    }
}