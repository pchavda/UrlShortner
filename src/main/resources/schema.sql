DROP TABLE IF EXISTS urlShortener;

CREATE TABLE urlShortener (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          long_url VARCHAR(250) NOT NULL,
                          short_url VARCHAR(10) NOT NULL
);