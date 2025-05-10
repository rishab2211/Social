package com.social.controllers;

import com.social.models.PostModel;
import com.social.models.UserModel;
import com.social.response.ApiResponse;
import com.social.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/api/posts/user/{userId}")
    public ResponseEntity<PostModel> createPost(@RequestBody PostModel post,@PathVariable UUID userId) throws Exception {

        PostModel postCreated = postService.createNewPost(post,userId);

        return new ResponseEntity<>(postCreated, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/api/posts/user/{userId}/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable UUID postId,@PathVariable UUID userId) throws Exception {
         String message = postService.deletePost(postId, userId);

         ApiResponse response = new ApiResponse(message, true);
         return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostModel> findPostByIdHandler(@PathVariable UUID postId) throws Exception {

        PostModel post = postService.findPostById(postId);

        return new ResponseEntity<PostModel>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user/bulk/{userId}")
    public ResponseEntity<List<PostModel>> findPostsByUserId(@PathVariable UUID userId){

        List<PostModel> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<PostModel>>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostModel>> findAllPost(){

        List<PostModel> posts = postService.findAllPost();

        return new ResponseEntity<List<PostModel>>(posts, HttpStatus.OK);
    }


    @PutMapping("/api/posts/save/{postId}/user/{userId}")
    public ResponseEntity<PostModel> savePost(@PathVariable UUID postId, @PathVariable UUID userId) throws Exception {

        PostModel postSaved = postService.savedPost(postId, userId);

        return new ResponseEntity<PostModel>(postSaved, HttpStatus.ACCEPTED);
    }


    @PutMapping("/api/posts/like/{postId}/user/{userId}")
    public ResponseEntity<PostModel> likePost(@PathVariable UUID postId, @PathVariable UUID userId) throws Exception {

        PostModel postSaved = postService.likePost(postId, userId);

        return new ResponseEntity<PostModel>(postSaved, HttpStatus.ACCEPTED);
    }

}
