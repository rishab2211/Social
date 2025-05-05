package com.social.service;

import com.social.models.PostModel;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostModel createNewPost(PostModel post, UUID userId) throws Exception;

    String deletePost(UUID postId, UUID userId) throws Exception;

    List<PostModel> findPostByUserId(UUID userId);

    PostModel findPostById(UUID postId) throws Exception;

    List<PostModel> findAllPost();

    PostModel savedPost(UUID postId, UUID userId) throws Exception;

    PostModel likePost(UUID postId, UUID userId) throws Exception;
}
