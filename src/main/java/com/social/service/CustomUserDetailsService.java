package com.social.service;

import com.social.models.UserModel;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserModel> user = userRepository.findByEmail(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: "+username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        
        return new User(user.get().getEmail(), user.get().getPassword(),authorities);
    }
}
