INSERT INTO weather (temperature, atmospherePressure) VALUES (20, 768);



SELECT * FROM weather;  
# SELECT * FROM weather where temperature = 20;

# Берем уникальные значение с столбца "temperature"
# SELECT DISTINCT (temperature) FROM weather;

# Вывести все данные и упорядочить их по данным столбца "temperature".
# Так же можно дописать "desc", для обратного упорядочивания
# SELECT * FROM weather ORDER BY temperature;

# Случайное упорядочивание. Можно сделать например в игре, для вывода случайного вопроса
# SELECT * FROM weather ORDER BY RAND();

# Выведем две записи, после третьего id
# SELECT * FROM weather LIMIT 3,2;

# Выведем количество записей в столбце "temperature"
# SELECT count(temperature) FROM weather;

# Выведем макс. число в столбце "temperature"
# SELECT max(temperature) FROM weather;

# Выведем мин. число в столбце "temperature"
# SELECT min(temperature) FROM weather;

# Выведем среднее. число в столбце "temperature"
# SELECT avg(temperature) FROM weather;

# Выведем сумму всех записей в столбце "temperature"
# SELECT sum(temperature) FROM weather;

# С помощью "as" можем изменять название столбца
# SELECT count(temperature) as sumTemperature FROM weather;



UPDATE weather SET temperature = 30, atmospherePressure = 800;
# UPDATE weather SET temperature = 30, atmospherePressure = 800 where id = 1;



DELETE * FROM weather;
# DELETE * FROM weather WHERE id = 1;