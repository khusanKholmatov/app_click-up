package com.example.app_click_up.workspace.workspaceUser;

import com.example.app_click_up.base.BaseAbstractEntity;
import com.example.app_click_up.user.User;
import com.example.app_click_up.workspace.workspaceRole.WorkspaceRole;
import com.example.app_click_up.workspace.workspace.Workspace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkspaceUser extends BaseAbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private WorkspaceRole workspaceRole;

    @Column(nullable = false)
    private Timestamp invitedDate;

    private Timestamp joinedDate;

}
