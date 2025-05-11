package com.social.service;

import com.social.models.StoryModel;
import com.social.models.UserModel;
import com.social.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class StoryServiceImplementation implements StoryService{

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserService userService;

    @Override
    public StoryModel createStory(StoryModel story, UserModel user) {

        StoryModel createdStory = new StoryModel();

        createdStory.setImage(story.getImage());
        createdStory.setCaption(story.getCaption());
        createdStory.setTimestamp(LocalDateTime.now());
        createdStory.setUser(user);

        StoryModel savedStory = storyRepository.save(createdStory);

        return savedStory;
    }

    @Override
    public List<StoryModel> findStoryByUserId(UUID userId) throws Exception {

        UserModel user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }
}
