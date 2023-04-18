package com.superblog.controller;

import com.superblog.controller.generated.api.rest.model.Post;
import com.superblog.controller.mapper.MapperRestToModelImpl;
import com.superblog.factory.ModelObjectFactory;
import com.superblog.factory.RestObjectFactory;
import com.superblog.factory.TestFactory;
import com.superblog.service.PostService;
import com.superblog.service.mapper.MapperModelToRestImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperBlogControllerTest {

    @Mock
    private PostService postService;

    private SuperBlogController superBlogController;

    private RestObjectFactory restObjectFactory;
    private ModelObjectFactory modelObjectFactory;

    @BeforeEach
    void setup() {
        superBlogController = new SuperBlogController(
                postService,
                new MapperRestToModelImpl(),
                new MapperModelToRestImpl());

        restObjectFactory = new RestObjectFactory();
        modelObjectFactory = new ModelObjectFactory();
    }

    @Test
    void testCreatePost() {

        // INIT
        OffsetDateTime dateCreated = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        restObjectFactory.POST.setDateCreated(dateCreated);
        modelObjectFactory.POST.setDateCreated(dateCreated);

        when(postService.createPost(modelObjectFactory.POST)).thenReturn(modelObjectFactory.POST);

        // ACT
        ResponseEntity<Post> response = superBlogController.createPost(restObjectFactory.POST);

        // ASSERT
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(restObjectFactory.POST));
    }

    @Test
    void testGetPost() {

        // INIT
        when(postService.getPost(TestFactory.POST_ID)).thenReturn(modelObjectFactory.POST);

        // ACT
        ResponseEntity<Post> response = superBlogController.getPost(TestFactory.POST_ID);

        // ASSERT
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.ok(restObjectFactory.POST));
    }

    @Test
    void testDeletePost() {

        // ACT
        ResponseEntity<Void> response = superBlogController.deletePost(TestFactory.POST_ID);

        // ASSERT
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @Test
    void testUpdatePost() {

        // INIT
        when(postService.updatePost(TestFactory.POST_ID, modelObjectFactory.POST))
                .thenReturn(modelObjectFactory.UPDATED_POST);

        // ACT
        ResponseEntity<Post> response = superBlogController.updatePost(TestFactory.POST_ID, restObjectFactory.POST);

        // ASSERT
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.ok(restObjectFactory.UPDATED_POST));
    }

    @Test
    void testGetBlog() {

        // INIT
        when(postService.getBlog()).thenReturn(List.of(modelObjectFactory.POST));

        // ACT
        ResponseEntity<List<Post>> response = superBlogController.getBlog();

        // ASSERT
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.ok(List.of(restObjectFactory.POST)));
    }

    @Test
    void testHelloWorld() {
        ResponseEntity<String> response = superBlogController.helloWorld();
        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.ok("Hello World!"));
    }
}
