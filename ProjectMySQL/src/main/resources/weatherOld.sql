CREATE TABLE weather (
  id SERIAL PRIMARY KEY,
  temperature INT,
  atmospherePressure INT
);

CREATE TABLE rain (
  id SERIAL PRIMARY KEY,
  weather_id  INT,
  speed INT,
  powerLevel VARCHAR(20),
  FOREIGN KEY (weather_id) REFERENCES weather(id)
);

CREATE TABLE snow (
  id SERIAL PRIMARY KEY,
  weather_id int,
  size int,
  speed int,
  FOREIGN KEY (weather_id) REFERENCES weather (id)
);

CREATE TABLE blizzard (
  id SERIAL PRIMARY KEY,
  snow_id int,
  powerLevel VARCHAR(20),
  FOREIGN KEY (snow_id) REFERENCES snow (id)
);
