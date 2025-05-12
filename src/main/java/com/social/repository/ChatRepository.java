package com.social.repository;

import com.social.models.ChatModel;
import com.social.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatModel, UUID> {
    public List<ChatModel> findByUsersId(UUID userId);


    @Query("SELECT c FROM ChatModel c WHERE :user MEMBER of c.users AND :reqUser MEMBER of c.users")
    public ChatModel findChatByUsersId(@Param("reqUser")UserModel reqUser, @Param("user")UserModel user);
}
