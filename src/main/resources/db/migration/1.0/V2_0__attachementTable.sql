create table attachment
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    content_type varchar(255),
    name varchar(255),
    original_name varchar(255),
    type bigint,
    created_by_id uuid constraint created_by_users_table_attachment references users(id),
    updated_by_id uuid constraint updated_by_users_table_attachment references users(id)
);

create index attachment_id_idx on attachment(id);