package com.example.app_click_up.workspace.workspace;

import com.example.app_click_up.base.ApiResponse;
import com.example.app_click_up.workspace.workspacePermission.WorkSpacePermissionName;
import com.example.app_click_up.user.User;
import com.example.app_click_up.workspace.workspacePermission.WorkspacePermission;
import com.example.app_click_up.workspace.workspacePermission.WorkspacePermissionService;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRole;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRoleService;
import com.example.app_click_up.workspace.workspaceUser.MemberDto;
import com.example.app_click_up.workspace.workspaceUser.WorkspaceUser;
import com.example.app_click_up.workspace.workspaceUser.WorkspaceUserRepository;
import com.example.app_click_up.workspace.workspaceUser.WorkspaceUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceUserService workspaceUserService;
    private final WorkspaceUserRepository workspaceUserRepository;
    private final WorkspaceRoleService workspaceRoleService;
    private final WorkspacePermissionService workspacePermissionService;

    public ApiResponse addWorkspace(WorkspaceDto workSpaceDto, User user) {
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workSpaceDto.getName())) {
            return new ApiResponse("workspace already exists", false);
        }

        Workspace workspace = workspaceMapper.dtoToObject(workSpaceDto, user);
        // ADDING WORKSPACE
        workspaceRepository.save(workspace);

        // ADDING WORKSPACE ROLE
        List<WorkspaceRole> workspaceRoleList = workspaceRoleService.saveAllWorkspaceRole(workspace);

        // ADDING WORKSPACE PERMISSION OF USER
        workspacePermissionService.addWorkspacePermissionList(workspaceRoleList, Arrays.asList(WorkSpacePermissionName.values()));

        // ADDING WORKSPACE USER
        workspaceUserService.saveWorkspaceUser(workspace, user, workspaceRoleList);



        return new ApiResponse("workspace saved successfully", true);
    }

    public ApiResponse editWorkspace(WorkspaceDto workSpaceDto) {
        return null;
    }

    public ApiResponse changeWorkspaceOwner(UUID workSpaceId, UUID ownerId) {
        return null;
    }

    public ApiResponse deleteWorkspace(UUID workSpaceId) {
        try {
            workspaceRepository.deleteById(workSpaceId);
            return new ApiResponse("workspace deleted successfully", true);
        }
        catch (Exception e) {
            return new ApiResponse("something went wrong", false);
        }
    }

    public ApiResponse joinToWorkspace(UUID id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setJoinedDate(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }
}
