-- CREATE TABLE weather (
--   id SERIAL PRIMARY KEY,
--   temperature INT,
--   atmospherePressure INT
-- );
--
-- CREATE TABLE rain (
--   id SERIAL PRIMARY KEY,
--   weather_id  INT,
--   speed INT,
--   powerLevel VARCHAR(20),
--   FOREIGN KEY (weather_id) REFERENCES weather(id)
-- );
--
-- CREATE TABLE snow (
--   id SERIAL PRIMARY KEY,
--   weather_id int,
--   size int,
--   speed int,
--   FOREIGN KEY (weather_id) REFERENCES weather (id)
-- );
--
-- CREATE TABLE blizzard (
--   id SERIAL PRIMARY KEY,
--   snow_id int,
--   powerLevel VARCHAR(20),
--   FOREIGN KEY (snow_id) REFERENCES snow (id)
-- );

CREATE TABLE blizzard (
  id SERIAL PRIMARY KEY,
  powerLevel VARCHAR(20)
);

CREATE TABLE snow (
  id SERIAL PRIMARY KEY,
  size INT,
  speed INT,
  blizzard_id INT,
  FOREIGN KEY (blizzard_id) REFERENCES blizzard(id)
);

CREATE TABLE rain (
  id SERIAL PRIMARY KEY,
  speed INT,
  powerLevel VARCHAR(20)
);

CREATE TABLE weather (
  id SERIAL PRIMARY KEY,
  temperature INT,
  atmospherePressure INT,
  rain_id INT,
  snow_id INT,
  FOREIGN KEY (rain_id) REFERENCES rain(id),
  FOREIGN KEY (snow_id) REFERENCES snow(id)
);

