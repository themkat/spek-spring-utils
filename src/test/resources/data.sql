DROP TABLE IF EXISTS Game;

CREATE TABLE Game(
id INT PRIMARY KEY,
name VARCHAR(255),
releaseYear INT,
developer VARCHAR(255)
);


INSERT INTO Game(id, name, releaseYear, developer) VALUES(1, 'Wolfenstein 3D', 1991, 'Id Software');
INSERT INTO Game(id, name, releaseYear, developer) VALUES(2, 'Super Mario Bros', 1984, 'Nintendo');