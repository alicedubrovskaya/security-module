package com.dubrouskaya.springsecurity.controller;

import com.dubrouskaya.springsecurity.model.UserEntity;
import com.dubrouskaya.springsecurity.repository.UserRepository;
import com.dubrouskaya.springsecurity.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class MyController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;

    @GetMapping("/info")
    public String info() {
        return "MVC application";
    }

    @GetMapping("/about")
    public String getAboutInfo() {
        return "about";
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }
    @GetMapping("/blocked")
    public ResponseEntity<Map<String, LocalDateTime>> blocked() {
        List<UserEntity> userEntities = userRepository.findAll();
        Map<String, LocalDateTime> blockedUsers = userEntities.stream()
                .map(UserEntity::getUserName)
                .filter(userName -> loginAttemptService.isBlocked(userName))
                .collect(Collectors.toMap(user -> user, user -> loginAttemptService.getCachedValue(user).getBlockedTimestamp()));
        return new ResponseEntity<>(blockedUsers, HttpStatus.OK);
    }

}
