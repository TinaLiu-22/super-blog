package com.superblog.service.mapper;

import com.superblog.repository.jpa.PostEntity;
import com.superblog.service.pojo.PostModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MapperModelToEntity {

    @Mapping(target = "id", ignore = true)
    PostEntity postModelToEntity(PostModel postModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    PostEntity postModelToEntity(@MappingTarget PostEntity postEntity, PostModel postModel);
}
