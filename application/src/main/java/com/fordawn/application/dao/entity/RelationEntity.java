package com.fordawn.application.dao.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RelationEntity {

    private String fromId;
    private String fromType;
    private String toId;
    private String toType;
    private String relationType;
    private String relationTypeGroup;
    private LocalDateTime createTime;
}
