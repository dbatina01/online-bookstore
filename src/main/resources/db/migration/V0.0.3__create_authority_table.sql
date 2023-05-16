DROP TABLE IF EXISTS authorities CASCADE;

CREATE TABLE authorities
(
    username    VARCHAR(50)     NOT NULL,
    authority   VARCHAR(50)     NOT NULL,
    CONSTRAINT authorities_users_fk FOREIGN KEY (username) REFERENCES users(username)
);
