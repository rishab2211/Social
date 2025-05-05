package com.social.service;

import com.social.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public UserModel registerUser(UserModel user);

    public UserModel findUserById(UUID userId) throws Exception;

    public UserModel findUserByEmail(String email) throws  Exception;

    public UserModel followUser(UUID userId1, UUID userId2) throws Exception;

    public UserModel updateUser(UserModel user, UUID id) throws Exception;

    public List<UserModel> searchUser(String query);
}
