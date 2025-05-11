package com.social.repository;

import com.social.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentModel, UUID> {



}
