package com.social.controllers;

import com.social.models.PostModel;
import com.social.response.ApiResponse;
import com.social.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/api/posts/user")
    public ResponseEntity<PostModel> createPost(@RequestBody PostModel post,@RequestHeader("Authorization") String jwt) throws Exception {

        PostModel postCreated = postService.createNewPost(post,jwt);

        return new ResponseEntity<>(postCreated, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/api/posts/user/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable UUID postId,@RequestHeader("Authorization") String jwt) throws Exception {
         String message = postService.deletePost(postId, jwt);

         ApiResponse response = new ApiResponse(message, true);
         return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<PostModel> findPostByIdHandler(@PathVariable UUID postId) throws Exception {

        PostModel post = postService.findPostById(postId);

        return new ResponseEntity<PostModel>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user")
    public ResponseEntity<List<PostModel>> findPostsByUserToken(@RequestHeader("Authorization") String jwt) throws Exception{

        List<PostModel> posts = postService.findPostByUserToken(jwt);

        return new ResponseEntity<List<PostModel>>(posts, HttpStatus.OK);
    }

    @GetMapping("/api/posts/bulk")
    public ResponseEntity<List<PostModel>> findAllPost(){

        List<PostModel> posts = postService.findAllPost();

        return new ResponseEntity<List<PostModel>>(posts, HttpStatus.OK);
    }


    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<PostModel> savePost(@PathVariable UUID postId, @RequestHeader("Authorization") String jwt) throws Exception {

        PostModel postSaved = postService.savedPost(postId, jwt);

        return new ResponseEntity<PostModel>(postSaved, HttpStatus.ACCEPTED);
    }


    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<PostModel> likePost(@PathVariable UUID postId, @RequestHeader("Authorization") String jwt) throws Exception {

        PostModel postSaved = postService.likePost(postId, jwt);

        return new ResponseEntity<PostModel>(postSaved, HttpStatus.ACCEPTED);
    }

}
