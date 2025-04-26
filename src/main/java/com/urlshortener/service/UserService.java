package com.urlshortener.service;

import com.urlshortener.dto.UserDto;
import com.urlshortener.entity.User;
import com.urlshortener.entity.UserSecret;
import com.urlshortener.repository.UserRepository;
import com.urlshortener.repository.UserSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSecretRepository UrlSecretRepository;

    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public List<UserDto> findAll() {

        return userRepository.findAllUsers();
    }

    public void saveUserSecret(UserSecret userSecret) {
        UrlSecretRepository.saveAndFlush(userSecret);
    }

    public Optional<User> getUser(String email) {

        return userRepository.getUser(email);
    }
}
