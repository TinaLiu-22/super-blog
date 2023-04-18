package com.superblog.controller;

import com.superblog.controller.generated.api.rest.SuperBlogApi;
import com.superblog.controller.generated.api.rest.model.Post;
import com.superblog.controller.mapper.MapperRestToModel;
import com.superblog.service.PostService;
import com.superblog.service.mapper.MapperModelToRest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("${app.base-path}")
public class SuperBlogController implements SuperBlogApi {

    @Autowired
    private PostService postService;

    @Autowired
    private MapperRestToModel mapperRestToModel;

    @Autowired
    private MapperModelToRest mapperModelToRest;

    @Override
    public ResponseEntity<Post> createPost(Post post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapperModelToRest.postModelToRest(
                        postService.createPost(
                                mapperRestToModel.postRestToModel(post))));
    }

    @Override
    public ResponseEntity<Void> deletePost(String postId) {
        postService.deletePost(postId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Override
    public ResponseEntity<List<Post>> getBlog() {
        return ResponseEntity.ok(
                postService.getBlog()
                        .stream()
                        .map(mapperModelToRest::postModelToRest)
                        .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Post> updatePost(String postId, Post post) {
        return ResponseEntity.ok(
                mapperModelToRest.postModelToRest(
                        postService.updatePost(
                                postId,
                                mapperRestToModel.postRestToModel(post))));
    }

    @Override
    public ResponseEntity<Post> getPost(String postId) {
        return ResponseEntity.ok(
                mapperModelToRest.postModelToRest(
                        postService.getPost(postId)));
    }

    @Override
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }
}
