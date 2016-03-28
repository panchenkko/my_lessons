# insert into weather (temperature, atmospherePressure) values(20, 768);
SELECT * FROM weather;  
# SELECT * FROM weather where temperature = 20;
UPDATE weather SET temperature = 30, atmospherePressure = 800;
# UPDATE weather SET temperature = 30, atmospherePressure = 800 where id = 1;
DELETE * FROM weather;
# DELETE * FROM weather WHERE id = 1;