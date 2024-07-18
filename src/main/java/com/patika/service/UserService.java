package com.patika.service;


import com.patika.entities.User;

import com.patika.message.ErrorMessage;
import com.patika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired UserRepository userRepository;




    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException(
                        String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
        return user;

    }


}
