package com.social.controllers;

import com.social.models.CommentModel;
import com.social.models.UserModel;
import com.social.service.CommentService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/comments/post/{postId}")
    public CommentModel createComment(@RequestBody CommentModel comment, @RequestHeader("Authorization") String jwt, @PathVariable UUID postId) throws Exception{

        UserModel user = userService.findUserByJwt(jwt);

        CommentModel createdComment = commentService.createComment(comment, postId,jwt);

        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public CommentModel likeComment(@RequestHeader("Authorization") String jwt, @PathVariable UUID commentId) throws Exception{

        UserModel user = userService.findUserByJwt(jwt);

        CommentModel likedComment = commentService.likeComment(commentId,user.getId());

        return likedComment;
    }
}
