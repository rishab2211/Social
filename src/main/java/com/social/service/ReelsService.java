package com.social.service;

import com.social.models.ReelsModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface ReelsService {

    public ReelsModel createReel(ReelsModel reel, UserModel user);

    public List<ReelsModel> findAllReels();

    public List<ReelsModel> findUsersReel(UUID userId);
}
