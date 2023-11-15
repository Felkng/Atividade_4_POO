CREATE DATABASE IF NOT EXISTS library ;

USE library;

CREATE TABLE book (
    title VARCHAR(150) NOT NULL,
    authors VARCHAR(250) NOT NULL
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acquisition DATE,
    pages SMALLINT,
    year SMALLINT NOT NULL,
    edition TINYINT,
    price DECIMAL(10,2)
);

INSERT INTO book ( title, authors, acquisition, pages, year, edition, price ) VALUES ( ?,?,?,?,?,?,? );

SELECT id, title, authors, acquisition, pages, year, edition, price FROM book WHERE id = ?;

UPDATE book SET title = ?, authors = ?, acquisition = ?, pages = ?, year = ?, edition = ?, price = ? WHERE id = ?;

SELECT * FROM book;

DELETE FROM book WHERE id = ?;