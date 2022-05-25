package com.example.app_click_up.workspace.workspaceRole;

import com.example.app_click_up.base.BaseAbstractEntity;
import com.example.app_click_up.workspace.workspace.Workspace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceRole extends BaseAbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName workspaceRoleName;

    @Enumerated(EnumType.STRING)
    private WorkspaceRoleName extendedRoleName;
}
