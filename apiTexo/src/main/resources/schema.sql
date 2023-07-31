DROP TABLE IF EXISTS movie;

CREATE TABLE IF NOT EXISTS movie(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    year_movie  INT NOT NULL,
    title       VARCHAR(300) NOT NULL,
    studio      VARCHAR(300) NOT NULL,
    producers   VARCHAR(300) NOT NULL,
    winner      VARCHAR(15)
);
