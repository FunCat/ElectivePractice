-- Add USERS

INSERT INTO userprofile (birthday, name, lastname, surname, login, password, enabled)
VALUES ('1987-11-5', 'Leonardo', 'Stewart', '', 'leonardo.stewart',  'Carrots/*891', TRUE),
  ('1985-12-2', 'Ashley', 'Fowler', '', 'ashley.fowler',  'Network-+3259', TRUE),
  ('1992-8-12', 'Peter', 'Austin', '', 'peter.austin',  'Knock.+6885', TRUE),
  ('1987-10-12', 'Eleanor', 'Peterson', '', 'eleanor.peterson',  'Pomegranates.+6987', TRUE),
  ('1994-7-20', 'UserI', 'UserF', 'UserO', 'user',  'user', TRUE),
  ('1986-11-12', 'James', 'James', '', 'james',  'james', TRUE),
  ('1986-11-12', 'James', 'James', '', 'james2',  'james2', TRUE),
  ('1986-11-12', 'Ivan', 'Ivanov', '', 'admin',  'admin', TRUE);

-- Add COURSES

INSERT INTO course (startdate, name, description, status, enddate, teacher_id)
VALUES ('2017-7-16', 'Course 1', 'Description about Course 1', 0, '2017-7-20', 6),
  ('2017-7-15', 'Course 2', 'Description about Course 2', 1, '2017-7-23', 6),
  ('2017-7-18', 'Course 3', 'Description about Course 3', 0, '2017-7-22', 7),
  ('2017-7-20', 'Course 4', 'Description about Course 4', 2, '2017-7-21', 6),
  ('2017-7-19', 'Course 5', 'Description about Course 5', 0, '2017-7-20', 7),
  ('2017-7-16', 'Course 6', 'Description about Course 6', 0, '2017-7-20', 6),
  ('2017-7-15', 'Course 7', 'Description about Course 7', 1, '2017-7-23', 6),
  ('2017-7-18', 'Course 8', 'Description about Course 8', 0, '2017-7-22', 7),
  ('2017-7-20', 'Course 9', 'Description about Course 9', 0, '2017-7-21', 6),
  ('2017-7-19', 'Course 10', 'Description about Course 10', 0, '2017-7-20', 7),
  ('2017-7-16', 'Course 11', 'Description about Course 11', 0, '2017-7-20', 6),
  ('2017-7-15', 'Course 12', 'Description about Course 12', 0, '2017-7-23', 6),
  ('2017-7-18', 'Course 13', 'Description about Course 13', 0, '2017-7-22', 7),
  ('2017-7-20', 'Course 14', 'Description about Course 14', 0, '2017-7-21', 6),
  ('2017-7-19', 'Course 15', 'Description about Course 15', 0, '2017-7-20', 7);


-- Add ROLES
INSERT INTO userrole (authority, user_id)
VALUES  ('ROLE_USER', 5),
        ('ROLE_USER', 6),
        ('ROLE_TEACHER', 6),
        ('ROLE_USER', 8),
        ('ROLE_TEACHER', 8),
        ('ROLE_ADMIN', 8);