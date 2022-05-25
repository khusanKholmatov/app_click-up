drop table users CASCADE;

create table users
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    account_non_expired boolean not null,
    account_non_locked boolean not null,
    color varchar(255),
    credentials_non_expired boolean not null,
    email varchar(255) not null constraint users_email_uk  unique,
    enabled boolean not null default true,
    full_name varchar(255),
    initial_letter varchar(255),
    password varchar(255),
    system_role_name varchar(255),
    created_by_id uuid constraint users_created_by_id_fk references users(id),
    updated_by_id uuid constraint users_updated_by_id_fk references users(id),
    avatar_id uuid constraint avatar_id_fk references attachment(id),
    email_code varchar(255)
);

create index user_id_idx on users(id);
create index user_email_password_idx on users(email, password);