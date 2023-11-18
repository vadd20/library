-- Таблица Клиенты
CREATE TABLE client
(
    id                   VARCHAR(255) PRIMARY KEY NOT NULL,
    name                 VARCHAR(255)             NOT NULL,
    date_of_birth        DATE                     NOT NULL,
    email                VARCHAR(255),
    phone_number         VARCHAR(15)              NOT NULL,
    reader_ticket_number VARCHAR(255) UNIQUE      NOT NULL,
    registration_date    DATE                     NOT NULL,
    is_active            BOOLEAN                  NOT NULL
);

-- Таблица Библиотекари
CREATE TABLE librarian
(
    id   VARCHAR(255) PRIMARY KEY NOT NULL,
    name VARCHAR(255)             NOT NULL
);

-- Таблица Журнал выдачи книг
CREATE TABLE book
(
    id             VARCHAR(255) PRIMARY KEY NOT NULL,
    isbn           VARCHAR(13) UNIQUE       NOT NULL,
    title          VARCHAR(255)             NOT NULL,
    author         VARCHAR(255)             NOT NULL,
    genre          VARCHAR(255)             NOT NULL,
    published_date DATE,
    publisher      VARCHAR(255),
    page_count     INT,
    location       VARCHAR(100)             NOT NULL,
    is_available   BOOLEAN                  NOT NULL,
    price          INT
);

-- Таблица Журнал бронирования книг
CREATE TABLE book_issue
(
    id           VARCHAR(255) PRIMARY KEY NOT NULL,
    book_id      VARCHAR(255)             NOT NULL,
    client_id    VARCHAR(255)             NOT NULL,
    librarian_id VARCHAR(255)             NOT NULL,
    issue_date   DATE                     NOT NULL,
    due_date     DATE                     NOT NULL,
    return_date  DATE,
    is_overdue   BOOLEAN                  NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (librarian_id) REFERENCES librarian (id)
);

-- Таблица Каталог книг
CREATE TABLE book_reservation
(
    id               VARCHAR(255) PRIMARY KEY NOT NULL,
    book_id          VARCHAR(255)             NOT NULL,
    client_id        VARCHAR(255)             NOT NULL,
    reservation_date DATE                     NOT NULL,
    expiration_date  DATE                     NOT NULL,
    status           VARCHAR(10)              NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);

-- Таблица Отчетность о выдаче книг
CREATE TABLE book_issue_report
(
    id          VARCHAR(255) PRIMARY KEY NOT NULL,
    book_id     VARCHAR(255)             NOT NULL,
    issue_count INT                      NOT NULL,
    month       INT                      NOT NULL,
    year        INT                      NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id)
);

-- Таблица Отзывы о книгах
CREATE TABLE book_review
(
    id          VARCHAR(255) PRIMARY KEY,
    book_id     VARCHAR(255)                       NOT NULL,
    client_id   VARCHAR(255),
    review_date DATE                               NOT NULL,
    review_text TEXT                               NOT NULL,
    rating      INT CHECK (rating BETWEEN 1 AND 5) NOT NULL,
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (client_id) REFERENCES client (id)
);
