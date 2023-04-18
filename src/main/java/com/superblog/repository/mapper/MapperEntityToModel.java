package com.superblog.repository.mapper;

import com.superblog.repository.jpa.PostEntity;
import com.superblog.service.pojo.PostModel;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MapperEntityToModel {

    PostModel postEntityToModel(PostEntity postEntity);

}
