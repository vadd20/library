alter table book
    add column total_number INT,
    add constraint book_isbn_key UNIQUE (isbn);

CREATE TABLE book_instance
(
    id           VARCHAR(255) PRIMARY KEY NOT NULL,
    book_id      VARCHAR(255)             NOT NULL,
    is_available BOOLEAN                  NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id)
);