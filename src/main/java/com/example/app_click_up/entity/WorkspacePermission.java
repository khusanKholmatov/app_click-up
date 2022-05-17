package com.example.app_click_up.entity;

import com.example.app_click_up.entity.base.BaseAbstractEntity;
import com.example.app_click_up.entity.enums.WorkSpacePermissionName;
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
public class WorkspacePermission extends BaseAbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole;

    @Enumerated(EnumType.STRING)
    private WorkSpacePermissionName permissionName;
}
