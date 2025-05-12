package com.social.service;

import com.social.config.JwtProvider;
import com.social.exceptions.UserException;
import com.social.models.UserModel;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserModel registerUser(UserModel user) {

        UserModel newUser = new UserModel();

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public UserModel findUserById(UUID userId) throws UserException{

        Optional<UserModel> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }

        throw new UserException("User does not exist with the userId: "+userId);
    }

    @Override
    public UserModel findUserByEmail(String email) throws UserException{

        Optional<UserModel> foundUser = userRepository.findByEmail(email);

        if(foundUser.isPresent()){
            return foundUser.get();
        }

        throw new UserException("User does not exist with the given email id: "+email);

    }



    @Override
    public UserModel followUser(String jwt, UUID userId2) throws UserException {

        UserModel reqUser = findUserByJwt(jwt);

        UserModel user2 = findUserById(userId2);

        if (user2.getFollowers() == null) {
            user2.setFollowers(new ArrayList<>());
        }
        if (reqUser.getFollowing() == null) {
            reqUser.setFollowing(new ArrayList<>());
        }


        if(!user2.getFollowers().contains(reqUser.getId())){
            user2.getFollowers().add(reqUser.getId());
            reqUser.getFollowing().add(user2.getId());
        }else {
            user2.getFollowers().remove(reqUser.getId());
            reqUser.getFollowing().remove(user2.getId());
        }

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public UserModel updateUser(UserModel user, UUID id) throws UserException {

        Optional<UserModel> userToUpdate = userRepository.findById(id);

        if(userToUpdate.isEmpty()){
            throw  new UserException("User does not exist with userId: "+id);
        }

        UserModel oldUser = userToUpdate.get();


        if(user.getName()!=null){
            oldUser.setName(user.getName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null){
            oldUser.setPassword(user.getPassword());
        }

        UserModel updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<UserModel> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public UserModel findUserByJwt(String jwt) throws UserException {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        Optional<UserModel> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UserException("Invalid Authorization token, cannot find the user");
        }

        return user.get();
    }
}