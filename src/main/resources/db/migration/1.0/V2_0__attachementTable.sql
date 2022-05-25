create table attachment
(
    id            uuid      not null
        primary key,
    created_at    timestamp not null,
    updated_at    timestamp,
    content_type  varchar(255),
    name          varchar(255),
    original_name varchar(255),
    type          bigint,
    created_by_id uuid
        constraint createdByUsersTable_attachment
            references users,
    updated_by_id uuid
        constraint updatedByUsersTable_attachment
            references users
);