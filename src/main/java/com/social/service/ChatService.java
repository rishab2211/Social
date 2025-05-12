package com.social.service;

import com.social.models.ChatModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface ChatService {

    public ChatModel createChat(UserModel reqUser, UserModel user2);

    public ChatModel findChatById(UUID chatId) throws Exception;

    public List<ChatModel> findUsersChat(UUID userID);
}
