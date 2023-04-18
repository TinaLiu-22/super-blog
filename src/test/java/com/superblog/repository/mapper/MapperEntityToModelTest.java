package com.superblog.repository.mapper;

import com.superblog.factory.EntityObjectFactory;
import com.superblog.factory.ModelObjectFactory;
import com.superblog.service.pojo.PostModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperEntityToModelTest {

    private MapperEntityToModel mapperEntityToModel;

    private EntityObjectFactory entityObjectFactory;
    private ModelObjectFactory modelObjectFactory;

    @BeforeEach
    void setup() {
        mapperEntityToModel = new MapperEntityToModelImpl();

        entityObjectFactory = new EntityObjectFactory();
        modelObjectFactory = new ModelObjectFactory();
    }

    @Test
    void testPostEntityToModel() {
        PostModel out = mapperEntityToModel.postEntityToModel(entityObjectFactory.POST);
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(modelObjectFactory.POST);
    }

    @Test
    void testNull() {
        assertNull(mapperEntityToModel.postEntityToModel(null));
    }
}
