package com.example.app_click_up.workspace.workspaceRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {



}
