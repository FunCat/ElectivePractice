-- Add USERS

INSERT INTO userprofile (birthday, name, lastname, surname, login, password, role)
VALUES ('1987-11-5', 'Leonardo', 'Stewart', '', 'leonardo.stewart',  'Carrots/*891', '2'),
       ('1985-12-2', 'Ashley', 'Fowler', '', 'ashley.fowler',  'Network-+3259', '2'),
       ('1992-8-12', 'Peter', 'Austin', '', 'peter.austin',  'Knock.+6885', '2'),
       ('1987-10-12', 'Eleanor', 'Peterson', '', 'eleanor.peterson',  'Pomegranates.+6987', '2'),
       ('1994-7-20', 'UserI', 'UserF', 'UserO', 'user',  'user', '2'),
       ('1986-11-12', 'James', 'James', '', 'james',  'james', '1'),
       ('1986-11-12', 'James', 'James', '', 'james2',  'james2', '1'),
       ('1986-11-12', 'Ivan', 'Ivanov', '', 'admin',  'admin', '0');

-- Add COURSES

INSERT INTO courses (startdate, name, status, enddate, teacher_id)
VALUES ('2017-7-16', 'Course 1', 0, '2017-7-20', 6),
       ('2017-7-15', 'Course 2', 1, '2017-7-23', 6),
       ('2017-7-18', 'Course 3', 0, '2017-7-22', 7),
       ('2017-7-20', 'Course 4', 2, '2017-7-21', 6),
       ('2017-7-19', 'Course 5', 0, '2017-7-20', 7);

