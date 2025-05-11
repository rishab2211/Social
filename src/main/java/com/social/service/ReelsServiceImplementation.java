package com.social.service;

import com.social.models.ReelsModel;
import com.social.models.UserModel;
import com.social.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReelsServiceImplementation implements ReelsService{

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;


    @Override
    public ReelsModel createReel(ReelsModel reel, UserModel user) {

        ReelsModel createdReel = new ReelsModel();

        createdReel.setTitle(reel.getTitle());
        createdReel.setVideo(reel.getVideo());
        createdReel.setUser(user);

        return reelsRepository.save(createdReel);
    }

    @Override
    public List<ReelsModel> findAllReels() {

        List<ReelsModel> allReels = reelsRepository.findAll();

        return allReels;
    }

    @Override
    public List<ReelsModel> findUsersReel(UUID userId) {

        return reelsRepository.findByUserId(userId);
    }
}
