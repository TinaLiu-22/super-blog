package com.superblog.service.mapper;

import com.superblog.factory.EntityObjectFactory;
import com.superblog.factory.ModelObjectFactory;
import com.superblog.repository.jpa.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MapperModelToEntityTest {

    private MapperModelToEntity mapperModelToEntity;

    private ModelObjectFactory modelObjectFactory;
    private EntityObjectFactory entityObjectFactory;

    @BeforeEach
    void setup() {
        mapperModelToEntity = new MapperModelToEntityImpl();

        modelObjectFactory = new ModelObjectFactory();
        entityObjectFactory = new EntityObjectFactory();
    }

    @Test
    void testPostModelToEntity() {
        PostEntity out = mapperModelToEntity.postModelToEntity(modelObjectFactory.POST);
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(entityObjectFactory.POST);
        assertNull(out.getId());
    }

    @Test
    void testPostModelToEntity2() {

        // INIT
        OffsetDateTime date = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        modelObjectFactory.POST.setDateCreated(date);
        modelObjectFactory.POST.setDateModified(date);

        // ACT
        PostEntity out = mapperModelToEntity.postModelToEntity(entityObjectFactory.POST, modelObjectFactory.UPDATED_POST);

        // ASSERT
        assertThat(out)
                .usingRecursiveComparison()
                .ignoringFields("dateCreated")
                .isEqualTo(entityObjectFactory.UPDATED_POST);

        assertNull(out.getId());
        assertNull(out.getDateCreated());
    }

    @Test
    void testNull() {
        assertNull(mapperModelToEntity.postModelToEntity(null));
        assertNull(mapperModelToEntity.postModelToEntity(null, null));
    }
}
