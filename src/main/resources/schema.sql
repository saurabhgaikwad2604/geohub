CREATE TABLE IF NOT EXISTS country(
    countryId INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255),
    currency VARCHAR(255),
    population INT,
    latitude VARCHAR(255),
    longitude VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS city(
    cityId INT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(255),
    population INT,
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    countryId INT,
    FOREIGN KEY (countryId) REFERENCES country(countryId)
);