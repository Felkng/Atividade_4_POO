CREATE DATABASE IF NOT EXISTS library ;

USE library;

DROP TABLE IF EXISTS `book`;
CREATE TABLE book (
    title VARCHAR(150) NOT NULL,
    authors VARCHAR(250) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acquisition DATE,
    pages SMALLINT,
    year SMALLINT NOT NULL,
    edition TINYINT,
    price DECIMAL(10,2)
)ENGINE=INNODB;

DROP TABLE IF EXISTS `role`;
CREATE TABLE role (
    name VARCHAR(20) NOT NULL UNIQUE,
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
)ENGINE=INNODB;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES role(id)
)ENGINE=INNODB;

DROP TABLE IF EXISTS `credential`;
CREATE TABLE credential (
  username varchar(15) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  last_access date,
  enabled bit,
  id BIGINT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (id) REFERENCES user(id)
)ENGINE=INNODB;

DROP TABLE IF EXISTS `librarian`;
CREATE TABLE librarian (
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES user(id)
)ENGINE=INNODB;

DROP TABLE IF EXISTS `reader`;
CREATE TABLE reader (
    name VARCHAR(150) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id) REFERENCES user(id)
)ENGINE=INNODB;


