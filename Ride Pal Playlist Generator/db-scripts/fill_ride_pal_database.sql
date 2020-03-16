use ride_pal;

insert into users(username, email, first_name, last_name)
values ('gavril1234567', 'gavril1234567@gmail.com', 'Gavril', 'Samodivekov'),
       ('denitza1234567', 'denitza1234567@gmail.com', 'Denitza', 'Ruseva'),
       ('user1237','user1237@gmail.com', 'User', 'Userov');


insert into authorities(username, authority)
values ('gavril1234567', 'ADMIN'),
       ('gavril1234567', 'USER'),
       ('denitza1234567', 'ADMIN'),
       ('denitza1234567', 'USER'),
       ('user1237', 'USER');
