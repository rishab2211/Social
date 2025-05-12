package com.social.controllers;

import com.social.models.ChatModel;
import com.social.models.UserModel;
import com.social.request.CreateChatRequest;
import com.social.service.ChatService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/chats")
    public ChatModel createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {

        UserModel reqUser = userService.findUserByJwt(jwt);
        UserModel user2 = userService.findUserById(req.getUserId());
        ChatModel chat = chatService.createChat(reqUser, user2);

        return chat;
    }

    @GetMapping("/api/chats")
    public List<ChatModel> findUsersChat(@RequestHeader("Authorization") String jwt) throws Exception{

        UserModel reqUser = userService.findUserByJwt(jwt);
        List<ChatModel> usersChats = chatService.findUsersChat(reqUser.getId());

        return usersChats;
    }

}
