package com.urlshortener.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.urlshortener.dto.UserRequestDto;
import com.urlshortener.dto.UserResponseDto;
import com.urlshortener.entity.Url;
import com.urlshortener.entity.User;
import com.urlshortener.entity.UserSecret;
import com.urlshortener.service.UrlService;
import com.urlshortener.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/api")
public class UserController {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private Environment environment;

    @PostMapping("/createUser")// Remove the csrf config file after you have configured the application to use token
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequestDto) throws JsonProcessingException {
        String email = userRequestDto.getEmail();
        Optional<User> user = userService.getUser(email);

        if (user.isPresent())
            return ResponseEntity.badRequest().body("User with email id : " + email + " is already present please provide " +
                    "unique email id");

        String name = userRequestDto.getName();
        log.debug("Creating user with name : {}", name);
        log.debug("Mapping user {} to User Entity", name);
        UserSecret userSecret = objectMapper.convertValue(userRequestDto, UserSecret.class);
        List<Url> urls = objectMapper.convertValue(userRequestDto.getUrl(), new TypeReference<>() {
        });
        User newUser = objectMapper.convertValue(userRequestDto, User.class);
        log.debug("Mapping completed {} to User Entity", name);
        userService.saveUser(newUser);
        urls.forEach(e -> e.setUser(newUser));
        userSecret.setUser(newUser);
        newUser.setUserSecret(userSecret);
        newUser.setUrls(urls);
        userService.saveUser(newUser);
        log.debug("User created successfully");
        return ResponseEntity.ok("User created successfully!");

    }

    @GetMapping("/getUser")
    public ResponseEntity<List<UserResponseDto>> getUser() {
        List<UserResponseDto> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }
}
