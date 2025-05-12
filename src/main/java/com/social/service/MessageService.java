package com.social.service;

import com.social.models.MessageModel;
import com.social.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    public MessageModel createMessage(MessageModel message, UserModel user, UUID chatId) throws Exception;

    public List<MessageModel> findChatsMessages(UUID chatId) throws Exception;


}
