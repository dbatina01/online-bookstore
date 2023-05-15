DROP SEQUENCE IF EXISTS book_id_seq CASCADE;

CREATE SEQUENCE book_id_seq INCREMENT BY 1 START WITH 1;

DROP TABLE IF EXISTS book CASCADE;

CREATE TABLE book
(
    id              BIGINT          NOT NULL DEFAULT nextval('book_id_seq'),
    author          VARCHAR(255)    NOT NULL,
    title           VARCHAR(255)    NOT NULL,
    book_type       VARCHAR(50)     NOT NULL,
    base_price      NUMERIC         NOT NULL,
    available       BOOLEAN         NOT NULL,
    CONSTRAINT book_pk PRIMARY KEY (id)
);
