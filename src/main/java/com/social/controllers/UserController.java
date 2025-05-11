package com.social.controllers;

import com.social.models.UserModel;
import com.social.repository.UserRepository;
import com.social.service.UserService;
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

    @PutMapping("/api/users/follow/{user2}")
    public UserModel followUser(@RequestHeader("Authorization") String jwt, @PathVariable UUID user2) throws  Exception{
        UserModel followingUser = userService.followUser(jwt,user2);

        return followingUser;
    }

    @GetMapping("/api/users/email/{email}")
    public UserModel getUserByEmail(@PathVariable String email) throws  Exception  {

        Optional<UserModel> userFound = userRepository.findByEmail(email);

        return userFound.get();

    }


    @GetMapping("/api/users/search")
    public List<UserModel> searchUser(@RequestParam("query") String query){

        List<UserModel> users = userService.searchUser((query));

        return users;
    }


    @GetMapping("/api/users/profile")
    public UserModel getUserFromToken(@RequestHeader("Authorization") String jwt) throws Exception{

        UserModel user = userService.findUserByJwt(jwt);

        return user;
    }

    @PutMapping("/api/users")
    public UserModel alterUser(@RequestHeader("Authorization") String jwt, @RequestBody UserModel user) throws  Exception{

        UserModel reqUser = userService.findUserByJwt(jwt);

        UserModel updatedUser = userService.updateUser(user, reqUser.getId());

        return updatedUser;
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUser(@RequestHeader("Authorization") String jwt) throws Exception{

        UserModel userToDelete = userService.findUserByJwt(jwt);
        if(userToDelete==null){
            throw  new Exception("User to delete does not exist in the first place");
        }

        userRepository.deleteById(userToDelete.getId());

        return "User deleted successfully!";
    }
}
