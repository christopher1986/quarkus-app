INSERT INTO role (id, name)
    VALUES (1, 'ADMIN');
INSERT INTO role (id, name)
    VALUES (2, 'USER');

INSERT INTO users (id, firstname, lastname, password, username)
    VALUES (1, 'Chris', 'Harris', 'Xzn1iL4H1nnqVlLBEkrweNQlUEBKrCnealdlalgpexM=', 'chris');

INSERT INTO users_role (user_id, roles_id)
    VALUES (1, 1);
INSERT INTO users_role (user_id, roles_id)
    VALUES (1, 2);
