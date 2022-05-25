create table users
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    account_non_expired boolean not null,
    account_non_locked boolean not null,
    color varchar(255),
    credentials_non_expired boolean not null,
    email varchar(255) not null unique,
    enabled boolean not null,
    full_name varchar(255),
    initial_letter varchar(255),
    password varchar(255),
    system_role_name varchar(255),
--     created_by_id uuid references users,
--     updated_by_id uuid references users,
--     avatar_id uuid references attachment,
    email_code varchar(255)
);