package com.example.app_click_up.workspace.workspace;

import com.example.app_click_up.attachment.AttachmentRepository;
import com.example.app_click_up.base.CustomMapper;
import com.example.app_click_up.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorkspaceMapper implements CustomMapper<Workspace, WorkspaceDto> {

    private final AttachmentRepository attachmentRepository;

    public Workspace dtoToObject(WorkspaceDto workSpaceDto, User user) {
        Workspace workspace = Workspace.builder()
                .name(workSpaceDto.getName())
                .color(workSpaceDto.getColor())
                .owner(user)
                .avatar(workSpaceDto.getAvatarId() == null ? null :
                        attachmentRepository.findById(workSpaceDto.getAvatarId()).orElseThrow(
                                () -> new ResourceNotFoundException("attachment not found")
                        )).build();
        return workspace;
    }

    @Override
    public Workspace dtoToObject(WorkspaceDto workSpaceDto) {
        return null;
    }

    @Override
    public WorkspaceDto objectToDto(Workspace workspace) {
        return null;
    }

    @Override
    public Workspace dtoToObject(WorkspaceDto workSpaceDto, Workspace workspace) {
        return null;
    }
}
