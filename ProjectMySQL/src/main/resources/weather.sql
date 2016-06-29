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
