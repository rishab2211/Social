package com.social.service;

import com.social.models.ReelsModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public class ReelsServiceImplementation implements ReelsService{


    @Override
    public ReelsModel createReel(ReelsModel reel, UserModel user) {
        
        return null;
    }

    @Override
    public List<ReelsModel> findAllReels() {
        return List.of();
    }

    @Override
    public List<ReelsModel> findUsersReel(UUID userId) {
        return List.of();
    }
}
