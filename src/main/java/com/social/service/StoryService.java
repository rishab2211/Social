package com.social.service;

import com.social.models.StoryModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface StoryService {

    public StoryModel createStory(StoryModel story, UserModel user);

    public List<StoryModel> findStoryByUserId(UUID userId) throws Exception;
}
