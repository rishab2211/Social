package com.social.service;

import com.social.exceptions.UserException;
import com.social.models.UserModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public UserModel registerUser(UserModel user);

    public UserModel findUserById(UUID userId) throws UserException;

    public UserModel findUserByEmail(String email) throws  UserException;

    public UserModel followUser(String jwt, UUID userId2) throws UserException;

    public UserModel updateUser(UserModel user, UUID id) throws UserException;

    public List<UserModel> searchUser(String query);

    public UserModel findUserByJwt(String jwt) throws UserException;
}
