drop table librarian cascade;
drop table admin cascade;

CREATE TABLE "user"
(
    id       VARCHAR(255) PRIMARY KEY NOT NULL,
    name     VARCHAR(255)             NOT NULL,
    login    VARCHAR(255)             NOT NULL,
    password varchar(255)             NOT NULL
);