package com.social.controllers;

import com.social.models.MessageModel;
import com.social.models.UserModel;
import com.social.service.MessageService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats/{chatId}")
    public MessageModel createMessage(@RequestBody MessageModel message, @RequestHeader("Authorization") String jwt, @PathVariable UUID chatId) throws Exception {

        UserModel user = userService.findUserByJwt(jwt);
        MessageModel createdMessage = messageService.createMessage(message, user, chatId);

        return createdMessage;
    }

    @GetMapping("/api/chats/{chatId}")
    public List<MessageModel> findUserChatMessages(@PathVariable UUID chatId) throws Exception {
        return messageService.findChatsMessages(chatId);
    }

}
