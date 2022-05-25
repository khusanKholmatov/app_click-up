package com.example.app_click_up.workspace.workspacePermission;

import com.example.app_click_up.workspace.workspaceRole.WorkspaceRole;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRoleName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkspacePermissionService {
    private final WorkspacePermissionRepository workspacePermissionRepository;

    public WorkspacePermissionService(WorkspacePermissionRepository workspacePermissionRepository) {
        this.workspacePermissionRepository = workspacePermissionRepository;
    }

    public void addWorkspacePermissionList(List<WorkspaceRole> workspaceRoleList,
                                           List<WorkSpacePermissionName> workSpacePermissionNameList)
    {
        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();

        for (WorkspaceRole workspaceRole: workspaceRoleList) {
            for (WorkSpacePermissionName workspacePermissionName : workSpacePermissionNameList) {
                if (workspacePermissionName.getWorkspaceRoleNames().contains(workspaceRole.getWorkspaceRoleName())) {
                    workspacePermissionList.add(new WorkspacePermission(
                            workspaceRole,
                            workspacePermissionName
                    ));
                }
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);
    }

}
