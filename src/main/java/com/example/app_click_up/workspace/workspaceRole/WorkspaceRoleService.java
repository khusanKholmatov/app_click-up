package com.example.app_click_up.workspace.workspaceRole;

import com.example.app_click_up.workspace.workspace.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceRoleService {

    private final WorkspaceRoleRepository workspaceRoleRepository;

    public WorkspaceRole saveWorkspaceRole(Workspace workspace,
                                           WorkspaceRoleName workspaceRoleName,
                                           WorkspaceRoleName extendedRoleName) {
        return workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                workspaceRoleName,
                extendedRoleName
        ));
    }
    @Transactional
    public List<WorkspaceRole> saveAllWorkspaceRole(Workspace workspace) {
        List<WorkspaceRole> workspaceRoleList = new ArrayList<>();
        for(WorkspaceRoleName workspaceRoleName: WorkspaceRoleName.values()) {
            workspaceRoleList.add(new WorkspaceRole(
                    workspace,
                    workspaceRoleName,
                    null
            ));
        }
        return workspaceRoleRepository.saveAll(workspaceRoleList);
    }
}
