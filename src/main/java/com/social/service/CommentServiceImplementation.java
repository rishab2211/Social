package com.social.service;

import com.social.models.CommentModel;
import com.social.models.PostModel;
import com.social.models.UserModel;
import com.social.repository.CommentRepository;
import com.social.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImplementation implements CommentService{


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;


    @Override
    public CommentModel createComment(CommentModel comment, UUID postId, String jwt) throws Exception {

        UserModel user = userService.findUserByJwt(jwt);

        PostModel post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        CommentModel savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public CommentModel likeComment(UUID commentId, UUID userId) throws Exception {

        CommentModel comment = findCommentById(commentId);

        UserModel user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else{
            comment.getLiked().remove(user);
        }


        return commentRepository.save(comment);
    }

    @Override
    public CommentModel findCommentById(UUID commentId) throws Exception {

        Optional<CommentModel> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()){
           throw  new Exception("Comment not found");
        }

        return comment.get();
    }
}
