package com.social.repository;

import com.social.models.ReelsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReelsRepository extends JpaRepository<ReelsModel, UUID>{

    public List<ReelsModel> findByUserId(UUID userId);


}