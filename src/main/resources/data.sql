DROP TABLE IF EXISTS young;

CREATE TABLE young (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  age INT(11) NOT NULL
);

INSERT INTO young (name, age) VALUES
  ('Bill', 17),
  ('Bailey', 14),
  ('Denis', 18);