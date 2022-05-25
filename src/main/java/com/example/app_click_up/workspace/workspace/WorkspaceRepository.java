package com.example.app_click_up.workspace.workspace;

import com.example.app_click_up.workspace.workspace.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
    boolean existsByOwnerIdAndName(UUID owner_id, String name);
}
