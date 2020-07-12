-- noinspection SqlWithoutWhereForFile

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

CREATE TABLE IF NOT EXISTS authorities
(
    id_authority serial primary key,
    authority    varchar(50) unique not null
);

CREATE TABLE IF NOT EXISTS users
(
    id_user      serial primary key,
    username     varchar(2000) unique NOT NULL,
    password     varchar(2000)        NOT NULL,
    enable       boolean default true,
    authority_id int4                 NOT NULL
        references authorities (id_authority) ON DELETE CASCADE,
    UNIQUE (username, password)
);
CREATE TABLE IF NOT EXISTS post
(
    id_post     serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    author      varchar(2000)               NOT NULL
        REFERENCES users (username) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);
CREATE TABLE IF NOT EXISTS message
(
    id_message  serial primary key,
    description text,
    post_id     int4,
    created     timestamp without time zone not null unique default now(),
    author      varchar(2000)               NOT NULL
        REFERENCES users (username) ON DELETE CASCADE,
    CONSTRAINT post_id_fk FOREIGN KEY (post_id)
        REFERENCES post (id_post) ON DELETE CASCADE
);

DELETE
FROM message;
DELETE
FROM post;
DELETE
FROM users;
DELETE
FROM authorities;
insert into authorities(authority)
values ('ROLE_USER'),
       ('ROLE_ADMIN');
insert into users(username, password, authority_id)
values ('admin', '$2a$10$xgd7DC3.fpquD6blnjPBUuGmICZ2dEIblENDO6EnVY.sum0y2b8Wi',
        (select id_authority from authorities where authority = 'ROLE_ADMIN')),
       ('user', '$2a$10$xgd7DC3.fpquD6blnjPBUuGmICZ2dEIblENDO6EnVY.sum0y2b8Wi',
        (select id_authority from authorities where authority = 'ROLE_USER')),
       ('any', '$2a$10$xgd7DC3.fpquD6blnjPBUuGmICZ2dEIblENDO6EnVY.sum0y2b8Wi',
        (select id_authority from authorities where authority = 'ROLE_USER'));
insert into post(name, description, author)
values ('A', 'куплю А ради А', 'user'),
       ('B', 'куплю B ради B', 'any');
insert into message(description, author, post_id, created)
values ('хочу купить А', 'user', 1, '2020-06-29 09:09:02'),
       ('не хочу купить А', 'any', 1, '2020-06-29 09:09:03'),
       ('хочу купить B', 'user', 2, '2020-06-29 09:09:04'),
       ('не хочу купить B', 'any', 2, '2020-06-29 09:09:05');
