package com.superblog.service;

import com.superblog.SuperBlogApplication;
import com.superblog.factory.EntityObjectFactory;
import com.superblog.factory.ModelObjectFactory;
import com.superblog.factory.TestFactory;
import com.superblog.repository.PostRepository;
import com.superblog.service.pojo.PostModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = SuperBlogApplication.class)
@ExtendWith(SpringExtension.class)
public class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    private ModelObjectFactory modelObjectFactory;
    private EntityObjectFactory entityObjectFactory;

    private OffsetDateTime dateCreated;

    @BeforeEach
    void setup() {
        modelObjectFactory = new ModelObjectFactory();
        entityObjectFactory = new EntityObjectFactory();

        dateCreated = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        entityObjectFactory.POST.setDateCreated(dateCreated);
        postRepository.saveAndFlush(entityObjectFactory.POST);
    }

    @AfterEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    void testCreatePost() {

        // INIT
        modelObjectFactory.POST.setDateCreated(dateCreated);
        modelObjectFactory.POST.setPostId("newPostId");

        // ACT
        PostModel out = postService.createPost(modelObjectFactory.POST);

        // ASSERT
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(modelObjectFactory.POST);
    }

    @Test
    void testGetPost() {

        // INIT
        modelObjectFactory.POST.setDateCreated(dateCreated);

        // ACT
        PostModel out = postService.getPost(TestFactory.POST_ID);

        // ASSERT
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(modelObjectFactory.POST);
    }

    @Test
    void testDeletePost() {

        // ACT
        postService.deletePost(TestFactory.POST_ID);

        // ASSERT
        assertNull(postRepository.findByPostId(TestFactory.POST_ID));
    }

    @Test
    void testUpdatePost() {

        // INIT
        OffsetDateTime dateModified = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        modelObjectFactory.UPDATED_POST.setDateModified(dateModified);

        // ACT
        PostModel out = postService.updatePost(TestFactory.POST_ID, modelObjectFactory.UPDATED_POST);

        // ASSERT
        modelObjectFactory.UPDATED_POST.setDateCreated(dateCreated);
        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(modelObjectFactory.UPDATED_POST);
    }

    @Test
    void testGetBlog() {

        modelObjectFactory.POST.setDateCreated(dateCreated);
//        List<PostModel> expected = new java.util.ArrayList<>();
//        expected.add(modelObjectFactory.POST);

        List<PostModel> out = postService.getBlog();

        assertThat(out)
                .usingRecursiveComparison()
                .isEqualTo(List.of(modelObjectFactory.POST));
    }
}
