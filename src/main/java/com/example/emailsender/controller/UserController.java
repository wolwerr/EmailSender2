package com.example.emailsender.controller;

import com.example.emailsender.model.User;
import com.example.emailsender.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody User users) throws MessagingException {
        return new ResponseEntity<>(userService.save(users), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(page = 0, size = 5)Pageable pageable){
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value ="userDto")UUID userId){
        Optional<User> userOptional=userService.findById((userId));
        Optional<Object> userOptinal = null;
        if(!userOptinal.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }
}
