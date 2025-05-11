package com.social.repository;

import com.social.models.StoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoryRepository extends JpaRepository<StoryModel, UUID> {

    public List<StoryModel> findByUserId(UUID userId);

}
