package com.example.app_click_up.workspace;

import com.example.app_click_up.base.ApiResponse;
import com.example.app_click_up.user.CurrentUser;
import com.example.app_click_up.user.User;
import com.example.app_click_up.workspace.workspace.WorkspaceDto;
import com.example.app_click_up.workspace.workspace.WorkspaceService;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRoleService;
import com.example.app_click_up.workspace.workspaceUser.MemberDto;
import com.example.app_click_up.workspace.workspaceUser.WorkspaceUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspace")
public class WorkspaceController {

    private final WorkspaceService workSpaceService;
    private final WorkspaceUserService workspaceUserService;

    @PostMapping
    public HttpEntity<?> addWorkSpace(@Valid @RequestBody WorkspaceDto workSpaceDto, @CurrentUser User user) {
        ApiResponse apiResponse = workSpaceService.addWorkspace(workSpaceDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PutMapping("/{id}")
//    public HttpEntity<?> editWorkSpace(@PathVariable UUID id, @RequestBody WorkspaceDto workSpaceDto) {
//        ApiResponse apiResponse = workSpaceService.editWorkspace(workSpaceDto);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
//    }

//    @PutMapping("/changeOwner/{id}")
//    public HttpEntity<?> changeOwnerWorkSpace(@PathVariable UUID id, @PathVariable UUID ownerId) {
//        ApiResponse apiResponse = workSpaceService.changeWorkspaceOwner(id, ownerId);
//        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
//    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable UUID id) {
        ApiResponse apiResponse = workSpaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable UUID id, @RequestBody MemberDto memberDto) {
        ApiResponse apiResponse = workspaceUserService.manageUser(id, memberDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam UUID id,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = workSpaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
