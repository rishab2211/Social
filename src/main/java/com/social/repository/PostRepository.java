package com.social.repository;

import com.social.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {

@Query("SELECT p FROM PostModel p WHERE p.user.id=:userId")
List<PostModel> findPostsById(@Param("userId") UUID postID);

}
