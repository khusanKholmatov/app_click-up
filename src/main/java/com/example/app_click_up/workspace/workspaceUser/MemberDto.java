package com.example.app_click_up.workspace.workspaceUser;

import lombok.Data;

import java.util.UUID;

@Data
public class MemberDto {

    private UUID id;
    private UUID workspaceRoleId;
    private UserManagementType addType;

}
