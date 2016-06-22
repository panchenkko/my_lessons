# Создаём базу данных
# CREATE DATABASE my_database;

# Меняем базу данных
# USE my_database2;

# Изменение имени столбца
# ALTER TABLE table_name RENAME COLUMN column_name TO new_column_name

# Добавление столбца
# ALTER TABLE table_name ADD field_name parameters



INSERT INTO weather (temperature, atmospherePressure) VALUES (20, 768);
# Пример экранирования символов
# INSERT INTO users SET user_name = 'O\'Reilly';'

### INSERT ... ON DUPLICATE KEY UPDATE ###
# Это наиболее часто используемое условие. Сначала запрос пытается выполнить INSERT,
# и если запрос терпит неудачу в следствии дублирования первичного (PRIMARY KEY)
# или уникального (UNIQUE KEY) ключа, то выполняется запрос UPDATE.
# Ошибка
# INSERT INTO name_table SET unique_column = 'существующее_имя';

# Можно давать значения в случае, если не нужно делать дубликат #
INSERT INTO name_table SET unique_column = 'существующее_имя', dop_column = 'new_value'
# Если нужно делать дубликат, то меняем значение
ON DUPLICATE KEY UPDATE dop_column = 'new_value2';

### REPLACE INTO ###
# Если запись уже существует, то она удаляется, а потом выполняется запрос INSERT,
# при этом мы не получим никаких сообщений об ошибке.
## поле автоинкремента увеличивается на единицу ##
REPLACE INTO name_table SET unique_column = 'существующее_имя', dop_column = 'new_value';

### INSERT IGNORE ###
# Может понадобится вставить новую строку без вывода каких-либо ошибок, если даже произошло дублирование.
INSERT IGNORE INTO name_table SET unique_column= 'существующее_имя', dop_column = 'new_value';



UPDATE weather SET temperature = 30, atmospherePressure = 800;
# UPDATE weather SET temperature = 30, atmospherePressure = 800 where id = 1;



DELETE FROM weather;
# DELETE FROM weather WHERE id = 1;



# TRUNCATE сбрасыват счетчик поля AUTO_INCREMENT
TRUNCATE TABLE weather;



SELECT * FROM weather;
# SELECT * FROM weather where temperature = 20;

### DISTINCT ###
# Берем уникальные значение с столбца "temperature"
SELECT DISTINCT (temperature) FROM weather;

### ORDER BY ###
# Вывести все данные и отсортировать их по данным столбца "temperature".
# Так же можно дописать "desc", для обратного упорядочивания
SELECT * FROM weather ORDER BY temperature;
# Случайная сортировка. Можно сделать например в игре, для вывода случайного вопроса
SELECT * FROM weather ORDER BY RAND();

### LIMIT ###
# Выведем первые две записи
SELECT * FROM weather LIMIT 2;
# Выведем одну строку, после первых двух
SELECT * FROM weather LIMIT 1 OFFSET 2;
# Тоже самое. Только первое число это смещение, а второе - ограничивает количество строк
SELECT * FROM weather LIMIT 2,1;

##### Агрегатные функции #####

### COUNT ###
# Выведем количество записей в столбце "temperature"
SELECT count(temperature) FROM weather;
# Выведем кол-во всех записей
SELECT count(*) FROM weather;
# Выведем кол-во записей, удовлетворяющие условие where
SELECT count(*) FROM weather WHERE temperature = 768;

### MAX ###
# Выведем макс. число в столбце "temperature"
SELECT max(temperature) FROM weather;

### MIN ###
# Выведем мин. число в столбце "temperature"
SELECT min(temperature) FROM weather;

### AVG ###
# Выведем среднее. число в столбце "temperature"
SELECT avg(temperature) FROM weather;

### MIN, MAX, AVG - Example ###
SELECT min(temperature), max(temperature), avg(temperature) FROM weather;

### SUM ###
# Выведем сумму всех записей в столбце "temperature"
SELECT sum(temperature) FROM weather;

##### Конец агрегатных функций #####

### AS ###
# С помощью "as" можем изменять название столбца
SELECT count(temperature) as sumTemperature FROM weather;

### AND | OR ###
# Выведем все записи, где температура = 20 и атмосферное давление = 768 или атмосферное давление = 800
SELECT * FROM weather WHERE temperature = 20 AND atmospherePressure = 768 OR atmospherePressure = 800;

### IN() ###
# IN() Применяется для сравнения с несколькими значениями | Выведем все записи, где температура = 10, 20, 30
SELECT * FROM weather WHERE temperature IN (10, 20, 30);
# Тоже самое, только наоборот
SELECT * FROM weather WHERE temperature NOT IN (10, 20, 30);

### BETWEEN ###
# BETWEEN проверяет находится ли значение внутри какого-то диапазона
# Выведем все записи какие соотвествуют диапазону чисел в столбце "temperature"
SELECT * FROM weather WHERE temperature BETWEEN 600 AND 800;
# Тоже самое, только наоборот
SELECT * FROM weather WHERE temperature NOT BETWEEN 600 AND 800;

### LIKE ###
# LIKE Позволяет задавать шаблон для поиска | Выведем все записи, где в атмосферном давлении содержится цифра 7
SELECT * FROM weather WHERE atmospherePressure LIKE '%7%';

### GROUP BY ###
# Группирует данные и оставляет только уникальные значения в выбранном столбце
SELECT * FROM weather GROUP BY temperature;
# Выведем все уникальные значения, а в столбце "count" выведем кол-во таких значений
SELECT count(*), temperature FROM weather GROUP BY temperature;
# GROUP_CONCAT() Эта функция объединяет все значения внутри группы в одну строку с разделителем.
SELECT group_concat(atmospherePressure SEPARATOR ', '), temperature FROM weather GROUP BY temperature;

### IF() ###
# IF() Эта функция принимает три аргумента.
# Первый аргумент условие, второй аргумент выполняется когда условие истинно, третий - когда ложно.
SELECT
  SUM(
      IF(population > 5000000, 1, 0)
  ) AS big_states,

  SUM(
      IF(population <= 5000000, 1, 0)
  ) AS small_states
FROM states;

### CASE ###
SELECT
  COUNT(*),
  CASE
  WHEN population > 5000000 THEN 'big'
  WHEN population > 1000000 THEN 'medium'
  ELSE 'small' END
    AS state_size
FROM states GROUP BY state_size;

### HAVING ###
# Условие HAVING применяется для "скрытых" полей, таких как результат, возвращаемый агрегирующей функцией.
# Обычно оно используется совместно с GROUP BY.
## Мы хотим вывести кол-во записей, где температура больше 10 градусов ##
# Эта строка выдаст ошибку
SELECT count(*), temperature FROM weather WHERE count(*) > 10 GROUP BY temperature;
# Но тут приходит на помощь HAVING
SELECT count(*), temperature FROM weather GROUP BY temperature HAVING count(*) > 10;
#
SELECT * FROM weather WHERE temperature IN (
  SELECT temperature FROM weather
  GROUP BY temperature
  HAVING COUNT(*) > 10
) ORDER BY temperature;
## Помните, что такая возможность доступна не во всех СУБД. ##

### Подзапросы ###
# Можно использовать результат одного запроса в другом
SELECT * FROM weather WHERE temperature = (
  SELECT MAX(temperature) FROM weather
);

### UNION ###
# UNION: Совмещение данных
# Используя запрос UNION, можно объединять результаты нескольких напросов SELECT
(SELECT * FROM weather WHERE atmospherePressure LIKE '%7%')
UNION
(SELECT * FROM weather WHERE temperature > 10);
#
# Например, у нас есть таблицы employees (сотрудники), managers (менеджеры) и customers (клиенты).
# В каждой таблице есть поле с адресом электронной почты.
# Если мы хотим получить все E-mail адреса в одном запросе, то можем поступить следующим образом:
(SELECT email FROM employees)
UNION
(SELECT email FROM managers)
UNION
(SELECT email FROM customers WHERE subscribed = 1);

### JOIN ###
# Присоединяем три таблицы к таблице "car" и соединяем их по id
# Также в этом примере можно использовать inner join
select car.name, t.name, cb.name, e.name from car
  left join transmission as t on car.transmission_id = t.id
  left join carBody as cb on car.carBody_id = cb.id
  left join engine as e on car.engine_id = e.id;

# Присоединяем левую таблицу к правой и выводим все записи какие не имеют значений
select t.name as "Transmission" from car
  right join transmission as t on car.transmission_id = t.id
where car.transmission_id is null;

select cb.name as "Car body" from car
  right join carBody as cb on car.carBody_id = cb.id
where car.carBody_id is null;

select e.name as "Engine" from car
  right join engine as e on car.engine_id = e.id
where car.engine_id is null;

### TRANSACTION ###
# В начале мы должны написать ключевое слово для начала транзакции (в разных субд, разные ключевые слова)
# SQLite, Postgres - begin;
# MySQL - start transaction;
## Тут записываются запросы один за другим. После всех запросов нужно закоммитить изминения ##
# Также можно сделать сохранение. Имеется ввиду, если изменения откатятся, то до момента сохранения #
# savepoint name_savepoint;
# commit;
# Откатить изминения таким образом:
# rollback;
# Откатить сохранение:
# rollback name_savepoint;
# Пример: (MySQL)
start transaction;
insert into weather (temperature, atmospherePressure) values (20, 768);
select * from weather;
savepoint test;
replace into weather set temperature = 20, atmospherePressure = 800;
commit;

