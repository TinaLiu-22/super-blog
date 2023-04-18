package com.superblog.service.mapper;

import com.superblog.controller.generated.api.rest.model.Post;
import com.superblog.factory.ModelObjectFactory;
import com.superblog.factory.RestObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperModelToRestTest {

    private MapperModelToRest mapperModelToRest;

    private ModelObjectFactory modelObjectFactory;
    private RestObjectFactory restObjectFactory;

    @BeforeEach
    void setup() {
        mapperModelToRest = new MapperModelToRestImpl();

        modelObjectFactory = new ModelObjectFactory();
        restObjectFactory = new RestObjectFactory();
    }

    @Test
    void testPostModelToRest() {
        Post out = mapperModelToRest.postModelToRest(modelObjectFactory.POST);
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(restObjectFactory.POST);
    }

    @Test
    void testNull() {
        assertNull(mapperModelToRest.postModelToRest(null));
    }
}
