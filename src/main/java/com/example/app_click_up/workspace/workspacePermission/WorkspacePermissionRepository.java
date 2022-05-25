package com.example.app_click_up.workspace.workspacePermission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
}
