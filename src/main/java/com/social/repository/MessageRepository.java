package com.social.repository;

import com.social.models.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {

    public List<MessageModel> findByChatId(UUID chatId);

}
