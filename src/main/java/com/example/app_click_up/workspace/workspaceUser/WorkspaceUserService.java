package com.example.app_click_up.workspace.workspaceUser;

import com.example.app_click_up.base.ApiResponse;
import com.example.app_click_up.exception.NotFoundException;
import com.example.app_click_up.user.User;
import com.example.app_click_up.user.UserRepository;
import com.example.app_click_up.workspace.workspace.Workspace;
import com.example.app_click_up.workspace.workspace.WorkspaceRepository;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRole;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.example.app_click_up.workspace.workspaceUser.UserManagementType.*;

@Service
@RequiredArgsConstructor
public class WorkspaceUserService {

    private final WorkspaceUserRepository workspaceUserRepository;
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceRoleRepository workspaceRoleRepository;
    private final UserRepository userRepository;

    public void saveWorkspaceUser(Workspace workspace, User user, List<WorkspaceRole> workspaceRoleList) {
        for (WorkspaceRole workspaceRole: workspaceRoleList) {
            workspaceUserRepository.save(new WorkspaceUser(
                    workspace,
                    user,
                    workspaceRole,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis())
            ));
        }
    }

    public ApiResponse manageUser(UUID workSpaceId, MemberDto memberDto) {
        if (memberDto.getAddType().equals(ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(workSpaceId).orElseThrow(()->new NotFoundException("workspace not found", "id")),
                    userRepository.findById(memberDto.getId()).orElseThrow(()->new NotFoundException("user not found", "id")),
                    workspaceRoleRepository.findById(memberDto.getWorkspaceRoleId()).orElseThrow(()->new NotFoundException("workspace role not found", "id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDto.getAddType().equals(EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(workSpaceId, memberDto.getId()).orElseThrow(()->new ResourceNotFoundException("workspace with this user not found"));
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDto.getWorkspaceRoleId()).orElseThrow(()->new ResourceNotFoundException("workspace role not found")));

        } else if (memberDto.getAddType().equals(DELETE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(workSpaceId, memberDto.getId());
        }
        return new ApiResponse("successfully completed", true);
    }
}
