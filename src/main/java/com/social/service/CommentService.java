package com.social.service;

import com.social.models.CommentModel;
import java.util.UUID;

public interface CommentService {

    public CommentModel createComment(CommentModel comment, UUID postId, String jwt) throws Exception;

    public CommentModel likeComment(UUID CommentId, UUID userId) throws Exception;

    public CommentModel findCommentById(UUID commentId) throws Exception;



}
