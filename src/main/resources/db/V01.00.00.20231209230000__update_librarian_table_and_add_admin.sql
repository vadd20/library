ALTER TABLE librarian
    ADD COLUMN login    VARCHAR(255),
    ADD COLUMN password VARCHAR(255);

CREATE TABLE admin
(
    id       VARCHAR(255) PRIMARY KEY NOT NULL,
    name     VARCHAR(255)             NOT NULL,
    login    VARCHAR(255)             NOT NULL,
    password varchar(255)             NOT NULL
);
