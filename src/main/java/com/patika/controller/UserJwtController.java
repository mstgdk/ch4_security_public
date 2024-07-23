package com.patika.controller;

import com.patika.dto.request.LoginRequest;
import com.patika.dto.request.RegisterRequest;
import com.patika.dto.response.LoginResponse;
import com.patika.message.PatikaResponse;
import com.patika.message.ResponseMessage;
import com.patika.security.Jwt.JwtUtils;
import com.patika.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserJwtController {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public UserJwtController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<PatikaResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.saveUser(registerRequest);

        PatikaResponse response = new PatikaResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken upAt = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(upAt);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails);

        LoginResponse loginResponse = new LoginResponse(token);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);

    }
}
