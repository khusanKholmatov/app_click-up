package com.example.app_click_up.workspace.workspaceUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {

    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(UUID workspace_id, UUID user_id);

    @Transactional
    @Modifying
    void deleteByWorkspaceIdAndUserId(UUID workspace_id, UUID user_id);

}
