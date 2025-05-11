package com.social.service;

import com.social.models.PostModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostModel createNewPost(PostModel post, String jwt) throws Exception;

    String deletePost(UUID postId, String jwt) throws Exception;

    List<PostModel> findPostByUserToken(String jwt) throws Exception;

    PostModel findPostById(UUID postId) throws Exception;

    List<PostModel> findAllPost();

    PostModel savedPost(UUID postId, String jwt) throws Exception;

    PostModel likePost(UUID postId, String jwt) throws Exception;
}
