create table workspace
(
    id uuid not null primary key,
    created_at timestamp not null,
    update_at timestamp,
    color varchar(255),
    initial_letter varchar(255) not null,
    name varchar(255) not null,
    created_by_id uuid
        constraint workspace_created_by_fk
            references users(id),
    updated_by_id uuid
        constraint workspace_updated_by_fk
            references users(id),
    avatar_id uuid
        constraint avatar_id_fk
            references attachment(id),
    owner_id uuid
        constraint owner_id_fk
            references users(id)
);
create index idx_workspace_id on workspace (id);


create table workspace_role
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    extended_role varchar(255),
    name varchar(255) not null,
    created_by_id uuid
        constraint fk_workspace_role_created_by_id
            references users(id),
    updated_by_id uuid
        constraint fk_workspace_role_updated_by_id
            references users(id),
    workspace_id uuid not null
        constraint fk_workspace_id
            references workspace (id)
);