package com.superblog.controller.mapper;

import com.superblog.factory.ModelObjectFactory;
import com.superblog.factory.RestObjectFactory;
import com.superblog.factory.TestFactory;
import com.superblog.service.pojo.PostModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperRestToModelTest {

    private MapperRestToModel mapperRestToModel;
    private RestObjectFactory restObjectFactory;
    private ModelObjectFactory modelObjectFactory;

    @BeforeEach
    void setup() {
        mapperRestToModel = new MapperRestToModelImpl();
        restObjectFactory = new RestObjectFactory();
        modelObjectFactory = new ModelObjectFactory();
    }

    @Test
    void testPostRestToModel() {

        PostModel out = mapperRestToModel.postRestToModel(restObjectFactory.POST);

        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(modelObjectFactory.POST);
    }

    @Test
    void testNull() {
        assertNull(mapperRestToModel.postRestToModel(null));
    }
}
