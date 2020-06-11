package com.fordawn.application.dao.mapper;

import com.fordawn.application.dao.entity.RelationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationMapper {

    int insert(RelationEntity relationEntity);

    int batchInsert(List<RelationEntity> relationEntities);

    List<RelationEntity> findOne();
}
