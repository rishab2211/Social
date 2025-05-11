package com.social.controllers;

import com.social.models.StoryModel;
import com.social.models.UserModel;
import com.social.service.StoryService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/story")
    public StoryModel createStory(@RequestBody StoryModel story, @RequestHeader("Authorization") String jwt) throws Exception {

        UserModel reqUser = userService.findUserByJwt(jwt);

        StoryModel createdStory = storyService.createStory(story,reqUser);

        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<StoryModel> findUserStoryById(@PathVariable UUID userId) throws Exception {
        List<StoryModel> userStories= storyService.findStoryByUserId(userId);

        return userStories;
    }
}
