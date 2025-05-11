package com.social.service;

import com.social.models.PostModel;
import com.social.models.UserModel;
import com.social.repository.PostRepository;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public PostModel createNewPost(PostModel post, String jwt) throws Exception {
         PostModel newPost = new PostModel();

         newPost.setCaption(post.getCaption());
         newPost.setImageUrl(post.getImageUrl());
         newPost.setVideoUrl(post.getVideoUrl());
         newPost.setCreatedAt(LocalDateTime.now());

         newPost.setUser(userService.findUserByJwt(jwt));





        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(UUID postId, String jwt) throws Exception {

        PostModel post = findPostById(postId);
        UserModel user = userService.findUserByJwt(jwt);

        if(post.getUser().getId()!=user.getId()){
            throw new Exception("Cannot delete others post");
        }

        postRepository.delete(post);

        return  "Post deleted successfully!";

    }

    @Override
    public List<PostModel> findPostByUserToken(String jwt) throws Exception {
        UserModel user = userService.findUserByJwt(jwt);
        return postRepository.findPostsById(user.getId());
    }

    @Override
    public PostModel findPostById(UUID postId) throws Exception {

        Optional<PostModel> foundPost =postRepository.findById(postId);

        if(foundPost.isPresent()){
            return foundPost.get();
        }

        throw new Exception("Post not found with the provided postId: "+postId);
    }

    @Override
    public List<PostModel> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public PostModel savedPost(UUID postId, String jwt) throws Exception {

        PostModel post = findPostById(postId);
        UserModel user = userService.findUserByJwt(jwt);

        if(user.getSaved().contains(post)){
            user.getSaved().remove(post);
        }else{
            user.getSaved().add(post);
        }

        return postRepository.save(post);
    }

    @Override
    public PostModel likePost(UUID postId, String jwt) throws Exception {


        PostModel post = findPostById(postId);
        UserModel user = userService.findUserByJwt(jwt);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else{
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
