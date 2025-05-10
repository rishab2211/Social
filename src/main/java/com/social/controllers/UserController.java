package com.social.controllers;

import com.social.models.UserModel;
import com.social.repository.UserRepository;
import com.social.service.UserService;
import com.social.service.UserServiceImplementation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public UserModel createUser(@RequestBody UserModel user){

        UserModel createdUser = userService.registerUser(user);
        return  createdUser;
    }

    @GetMapping("/api/users")
    public List<UserModel> getAllUsers() {
        List<UserModel> allUsers = userRepository.findAll();

        return allUsers;
    }

    @GetMapping("/api/users/{id}")
    public UserModel getUserById(@PathVariable UUID id) throws  Exception  {

        UserModel userFound = userService.findUserById(id);

        return userFound;

    }

    @PutMapping("/api/users/{user1}/follow/{user2}")
    public UserModel followUser(@PathVariable UUID user1, @PathVariable UUID user2) throws  Exception{
        UserModel followingUser = userService.followUser(user1,user2);

        return followingUser;
    }

    @GetMapping("/api/users/email/{email}")
    public UserModel getUserByEmail(@PathVariable String email) throws  Exception  {

        Optional<UserModel> userFound = userRepository.findByEmail(email);

        return userFound.get();

    }



    @PutMapping("/api/users/{id}")
    public UserModel alterUser(@PathVariable UUID id, @RequestBody UserModel user) throws  Exception{

        UserModel updatedUser = userService.updateUser(user, id);

        return updatedUser;
    }

    @GetMapping("/api/users/search")
    public List<UserModel> searchUser(@RequestParam("query") String query){

        List<UserModel> users = userService.searchUser((query));

        return users;
    }


    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@PathVariable UUID id) throws Exception{

        Optional<UserModel> userToDelete = userRepository.findById(id);
        if(userToDelete.isEmpty()){
            throw  new Exception("User to delete does not exist in the first place");
        }

        userRepository.deleteById(id);

        return "User deleted successfully!";
    }
}
