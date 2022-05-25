create table workspace_user
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    invited_date timestamp not null,
    joined_date timestamp,
    created_by_id uuid
        constraint fk_created_by_id_workspace_user
            references users(id),
    updated_by_id uuid
        constraint fk_updated_by_id_workspace_user
            references users(id),
    user_id uuid not null
        constraint fk_user_id_workspace_user
            references users(id),
    workspace_id uuid not null
        constraint fk_workspace_id_workspace_user
            references workspace(id),
    workspace_role_id uuid not null
        constraint fk_workspace_role_id_workspace_user
            references workspace_role
);

create table workspace_permission
(
    id uuid not null primary key,
    created_at timestamp not null,
    updated_at timestamp,
    permission_name varchar(255),
    created_by_id uuid
        constraint fk_created_by_id_workspace_permission
            references users,
    updated_by_id uuid
        constraint fk_updated_by_id_workspace_permission
            references users,
    workspace_role_id uuid not null
        constraint fk_workspace_role_id_workspace_permission
            references workspace_role
);