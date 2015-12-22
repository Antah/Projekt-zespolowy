CREATE DATABASE IF NOT EXISTS cosplay;

use cosplay;

drop table if exists users;
drop table if exists status;

CREATE TABLE status(
  status_id INT NOT NULL,
  name VARCHAR(16) NOT NULL,
  PRIMARY KEY (status_id)
);

insert into status values(0, 'DELETED');
insert into status values(1, 'NORMAL');

CREATE TABLE users(
  user_id INT AUTO_INCREMENT,
  login VARCHAR(16) NOT NULL UNIQUE, 
  password VARCHAR(256) NOT NULL,
  status_id INT NOT NULL DEFAULT 1,
  PRIMARY KEY (user_id),
  FOREIGN KEY (status_id) REFERENCES status(status_id)
);

