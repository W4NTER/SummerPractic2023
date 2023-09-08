Hibernate: create table events (
    author_id integer,
    id integer not null,
    participants integer,
    adress varchar(255) not null,
    content varchar(2048) not null,
    filename varchar(255),
    title varchar(255) not null,
    primary key (id))
    engine=InnoDB


Hibernate: create table events_seq (next_val bigint) engine=InnoDB
Hibernate: insert into events_seq values ( 1 )

Hibernate: create table user_interests (
    age integer not null,
    id integer not null,
    level_sport integer not null,
    user_id integer ,
    name varchar(255) not null,
    sport varchar(255) not null,
    primary key (id))
    engine=InnoDB


Hibernate: create table user_interests_seq (next_val bigint) engine=InnoDB
Hibernate: insert into user_interests_seq values ( 1 )


Hibernate: create table user_role (
    user_id integer not null,
    roles enum ('ADMIN','ORG','USER'))
    engine=InnoDB


Hibernate: create table usr (
    active bit,
    id integer not null,
    activation_code varchar(255),
    email varchar(255),
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id))
    engine=InnoDB


Hibernate: create table usr_seq (next_val bigint) engine=InnoDB
Hibernate: insert into usr_seq values ( 1 )


Hibernate: alter table user_interests
    add constraint user_id_uni unique (user_id)

Hibernate: alter table events
    add constraint events_user_fk
        foreign key (author_id) references usr (id)

Hibernate: alter table user_interests
    add constraint user_interests_user_fk
        foreign key (user_id) references usr (id)

Hibernate: alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr (id)