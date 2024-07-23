package com.patika.service;


import com.patika.dto.request.RegisterRequest;
import com.patika.entities.Role;
import com.patika.entities.User;

import com.patika.entities.enums.RoleType;
import com.patika.exception.ConflictException;
import com.patika.message.ErrorMessage;
import com.patika.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException(
                        String.format(ErrorMessage.USER_NOT_FOUND_EXCEPTION, email)));
        return user;

    }


    public void saveUser(RegisterRequest registerRequest) {
       //bu email ile daha önce kayoıt yapılmış mı??
        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE ,registerRequest.getEmail()));
        }
        //role
        Role role = roleService.findByType(RoleType.ROLE_EMPLOYEE);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        //şifre
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        //yeni oluşturma
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        userRepository.save(user);


    }
}
