package com.social.service;

import com.social.models.ChatModel;
import com.social.models.UserModel;
import com.social.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatServiceImplementation implements ChatService{


    @Autowired
    private ChatRepository chatRepository;

    @Override
    public ChatModel createChat(UserModel reqUser, UserModel user2) {

        ChatModel doExist = chatRepository.findChatByUsersId(reqUser, user2);

        if(doExist!=null){
            return doExist;
        }

        ChatModel createdChat = new ChatModel();

        createdChat.getUsers().add(user2);
        createdChat.getUsers().add(reqUser);
        createdChat.setTimestamp(LocalDateTime.now());

        chatRepository.save(createdChat);

        return createdChat;
    }

    @Override
    public ChatModel findChatById(UUID chatId) throws Exception {

        Optional<ChatModel> chat = chatRepository.findById(chatId);
        if(chat.isEmpty()){
            throw new Exception("Chat does not exist with this ID : "+chatId);
        }

        return chat.get();
    }

    @Override
    public List<ChatModel> findUsersChat(UUID userID) {

        return chatRepository.findByUsersId(userID);
    }
}
