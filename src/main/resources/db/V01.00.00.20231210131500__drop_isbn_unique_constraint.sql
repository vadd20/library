-- isbn не обязательно уникален.
ALTER TABLE book
    DROP CONSTRAINT book_isbn_key;