package com.social.service;

import com.social.models.UserModel;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserModel findUserById(UUID userId) throws Exception{

        Optional<UserModel> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("User does not exist with the userId: "+userId);
    }

    @Override
    public UserModel findUserByEmail(String email) throws Exception{

        Optional<UserModel> foundUser = userRepository.findByEmail(email);

        if(foundUser.isPresent()){
            return foundUser.get();
        }

        throw new  Exception("User does not exist with the given email id: "+email);

    }

    @Override
    public UserModel followUser(UUID userId1, UUID userId2) throws Exception {

        UserModel user1 = findUserById(userId1);

        UserModel user2 = findUserById(userId2);

        if (user2.getFollowers() == null) {
            user2.setFollowers(new ArrayList<>());
        }
        if (user1.getFollowing() == null) {
            user1.setFollowing(new ArrayList<>());
        }


        user2.getFollowers().add(user1.getId());

        user1.getFollowing().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public UserModel updateUser(UserModel user, UUID id) throws Exception {

        Optional<UserModel> userToUpdate = userRepository.findById(id);

        if(userToUpdate.isEmpty()){
            throw  new Exception("User does not exist with userId: "+id);
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
}
