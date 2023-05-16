-- books
INSERT INTO book VALUES (1, 'Kathy Sierra & Bert Bates', 'Head First Java', 'NEW_RELEASES', 38.50, TRUE);
INSERT INTO book VALUES (2, 'Herbert Schildt', 'Java: A Beginner´s Guide', 'NEW_RELEASES', 35.68, TRUE);
INSERT INTO book VALUES (3, 'Joshua Block', 'Effective Java', 'OLD_EDITIONS', 42.29, TRUE);
INSERT INTO book VALUES (4, 'Robert C. Martin', 'Clean Code', 'REGULAR', 25.33, TRUE);
INSERT INTO book VALUES (5, 'Linus Dietz & Simon Harrer', 'Java by Comparison', 'REGULAR', 41.15, TRUE);
INSERT INTO book VALUES (6, 'Iuliana Cosmina', 'Pro Spring 5', 'OLD_EDITIONS', 30.00, TRUE);
INSERT INTO book VALUES (7, 'Scott Selikoff & Jeanne Boyarsky', 'OCP Oracle Certified Professional Java SE 17 Developer Study', 'NEW_RELEASES', 42.00, FALSE);

-- users
INSERT INTO users VALUES ('username', '$2a$12$Q04Yd2jVQ6eNyJSoTkO1l./YqKUQwJBWKmaqpPFGRODecz9iefeji', TRUE, 3); -- (username & password)

-- authorities
INSERT INTO authorities VALUES ('username', 'USER');
