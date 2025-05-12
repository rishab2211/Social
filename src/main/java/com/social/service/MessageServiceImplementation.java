package com.social.service;

import com.social.models.ChatModel;
import com.social.models.MessageModel;
import com.social.models.UserModel;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImplementation implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public MessageModel createMessage(MessageModel message, UserModel user, UUID chatId) throws Exception {

        MessageModel createdMessage = new MessageModel();

        ChatModel chat = chatService.findChatById(chatId);

        createdMessage.setChat(chat);
        createdMessage.setUser(user);
        createdMessage.setContent(message.getContent());
        createdMessage.setImage(message.getImage());
        createdMessage.setTimestamp(LocalDateTime.now());

        MessageModel savedMessage = messageRepository.save(createdMessage);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<MessageModel> findChatsMessages(UUID chatId) throws Exception {

        return messageRepository.findByChatId(chatId);
    }
}
