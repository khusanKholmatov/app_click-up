package com.example.app_click_up.workspace.workspace;

import com.example.app_click_up.attachment.Attachment;
import com.example.app_click_up.base.BaseAbstractEntity;
import com.example.app_click_up.user.User;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Workspace extends BaseAbstractEntity {

    @Column(nullable = false)
    private String name;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @PrePersist
    @PreUpdate
    public void setInitialLetterFromName() {
        this.setInitialLetter(this.name.substring(0,1));
    }
}
