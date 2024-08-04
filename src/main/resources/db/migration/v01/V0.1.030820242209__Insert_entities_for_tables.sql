insert into _user (email, firstname, lastname, password, role)
values ('mm@gmail.com', 'Mikhail', 'Khosrun', '$2a$10$krEzpaiBQyE8udKa1E4ZW.2IslFmdS1zkui4XOEDGqH9iwoBkNvZ2', 'ADMIN'),
       ('vd@gmail.com', 'Vas', 'Das', '$2a$10$aon4UJoQB.srKuPgUMLir.cx/JD.igXEC13nQv3qlXczY0mT5j8ni', 'USER'),
       ('db@gmail.com', 'Dan', 'Balan', '$2a$10$LO0nCsiFpTkYgr3Qm1ZrNe/NO9W5gxsShIUKjowV6g9SmR142vdlq', 'USER');

insert into account (balance, blocked, user_id)
values (1000, false, 2),
       (2000, false, 2),
       (10000, false, 3);
