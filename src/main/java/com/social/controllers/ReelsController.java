package com.social.controllers;

import com.social.models.ReelsModel;
import com.social.models.UserModel;
import com.social.service.ReelsService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/reels")
    public ReelsModel createReels(@RequestBody ReelsModel reel, @RequestHeader("Authorization") String jwt) throws Exception{
        UserModel reqUser =  userService.findUserByJwt(jwt);
        ReelsModel createdReel = reelsService.createReel(reel,reqUser);

        return createdReel;
    }

    @GetMapping("/api/reels")
    public List<ReelsModel> findAllReels(){
        List<ReelsModel> reels = reelsService.findAllReels();

        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<ReelsModel> findUsersReels(@PathVariable UUID userId){

        List<ReelsModel> reels = reelsService.findUsersReel(userId);

        return reels;

    }
}
