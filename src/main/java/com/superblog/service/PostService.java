package com.superblog.service;

import com.superblog.repository.jpa.PostEntity;
import com.superblog.repository.mapper.MapperEntityToModel;
import com.superblog.service.mapper.MapperModelToEntity;
import com.superblog.service.pojo.PostModel;
import com.superblog.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MapperModelToEntity mapperModelToEntity;

    @Autowired
    private MapperEntityToModel mapperEntityToModel;

    public PostModel createPost(PostModel postModel) {

        PostModel result = mapperEntityToModel.postEntityToModel(
                postRepository.saveAndFlush(mapperModelToEntity.postModelToEntity(postModel)));

        log.debug("Created new post id={} {}", result.getPostId(), result);
        return result;
    }

    public PostModel getPost(String postId) {

        PostEntity postEntity = postRepository.findByPostId(postId)
                .orElseThrow(EntityNotFoundException::new);

        PostModel postModel = mapperEntityToModel.postEntityToModel(postEntity);

        log.debug("Retrieved post id={} {}", postId, postModel);
        return postModel;
    }

    public PostModel updatePost(String postId, PostModel postModel) {

        PostEntity postEntity = postRepository.findByPostId(postId)
                .orElseThrow(EntityNotFoundException::new);

        PostModel updatedPostModel =
                mapperEntityToModel.postEntityToModel(
                        postRepository.saveAndFlush(
                                mapperModelToEntity.postModelToEntity(postEntity, postModel)));

        log.debug("Updated post id={} {}", updatedPostModel.getPostId(), updatedPostModel);
        return updatedPostModel;
    }

    public void deletePost(String postId) {

        PostEntity postEntity = postRepository.findByPostId(postId)

                .orElseThrow(EntityNotFoundException::new);
        postRepository.delete(postEntity);

        log.debug("Deleted post id={} {}", postId, postEntity);
    }

    public List<PostModel> getBlog() {
        return postRepository.findAll()
                .stream()
                .map(mapperEntityToModel::postEntityToModel)
                .collect(Collectors.toList());
    }
}
