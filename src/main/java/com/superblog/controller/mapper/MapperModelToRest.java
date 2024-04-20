package com.superblog.controller.mapper;

import com.superblog.controller.generated.api.rest.model.Post;
import com.superblog.service.pojo.PostModel;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MapperModelToRest {

    Post postModelToRest(PostModel postModel);

}
