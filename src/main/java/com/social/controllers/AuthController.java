package com.social.controllers;

import com.social.config.JwtProvider;
import com.social.models.UserModel;
import com.social.repository.UserRepository;
import com.social.request.LoginRequest;
import com.social.response.AuthResponse;
import com.social.service.CustomUserDetailsService;
import com.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody UserModel user) throws Exception{

        Optional<UserModel> doExist = userRepository.findByEmail(user.getEmail());

        if(doExist.isPresent()){
            throw new Exception("User already exist with this email.");
        }

        UserModel newUser = new UserModel();

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        UserModel savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "User registered successfully.");

        return res ;
    }



    @PostMapping("/login")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Logged in successfully");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(userDetails==null){
            throw  new BadCredentialsException("Invalid username...");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("Password is not correct.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}