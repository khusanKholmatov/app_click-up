package com.example.app_click_up.attachment;

import com.example.app_click_up.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends BaseAbstractEntity {

    private String name;

    private String originalName;

    private Long type;

    private String contentType;
}
