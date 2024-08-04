create table _user (
                               id bigserial not null,
                               email varchar(255),
                               firstname varchar(255),
                               lastname varchar(255),
                               password varchar(255),
                               role varchar(255) check (role in ('USER','ADMIN')),
                               primary key (id)
);

create table account (
                                 balance numeric(38,2),
                                 blocked boolean,
                                 id bigserial not null,
                                 user_id bigint,
                                 primary key (id)
);
alter table if exists account.account
    add constraint FKcw93h2ubdxfaq2jqapsqaqcs3
    foreign key (user_id)
    references account._user;