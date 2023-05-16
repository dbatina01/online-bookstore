DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    username            VARCHAR(50)     NOT NULL PRIMARY KEY,
    password            VARCHAR(500)    NOT NULL,
    enabled             BOOLEAN         NOT NULL,
    loyalty_points      INTEGER         NOT NULL DEFAULT 0
);
