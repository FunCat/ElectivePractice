-- Add GROUPS

INSERT INTO groups
VALUES  (1, 'student'),
        (2, 'teacher'),
        (3, 'admin');

-- Add AUTHORITIES

INSERT INTO group_authorities
VALUES  ('ROLE_USER', 1),
        ('ROLE_USER', 2),
        ('ROLE_TEACHER', 2),
        ('ROLE_USER', 3),
        ('ROLE_TEACHER', 3),
        ('ROLE_ADMIN', 3);

-- Add USERS

INSERT INTO users (birthday, firstname, lastname, login, middlename, password, group_id)
VALUES ('1987-11-5', 'Leonardo', 'Stewart', 'leonardo.stewart', '', 'Carrots/*891', '1'),
       ('1985-12-2', 'Ashley', 'Fowler', 'ashley.fowler', '', 'Network-+3259', '1'),
       ('1992-8-12', 'Peter', 'Austin', 'peter.austin', '', 'Knock.+6885', '1'),
       ('1987-10-12', 'Eleanor', 'Peterson', 'eleanor.peterson', '', 'Pomegranates.+6987', '1'),
       ('1994-7-20', 'UserI', 'UserF', 'user', 'UserO', 'user', '1'),
       ('1986-11-12', 'James', 'James', 'james', '', 'james', '2'),
       ('1986-11-12', 'Ivan', 'Ivanov', 'admin', '', 'admin', '3');

-- Add COURSES

INSERT INTO courses (createdate, name, status, updatedate)
VALUES ('2017-7-16', 'Course 1', 0, '2017-7-20'),
       ('2017-7-15', 'Course 2', 1, '2017-7-23'),
       ('2017-7-18', 'Course 3', 0, '2017-7-22'),
       ('2017-7-20', 'Course 4', 2, '2017-7-21'),
       ('2017-7-19', 'Course 5', 0, '2017-7-20');

