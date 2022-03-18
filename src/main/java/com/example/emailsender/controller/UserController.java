package com.example.emailsender.controller;

import com.example.emailsender.model.User;
import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.services.UserService;
import com.rabbitmq.tools.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserService userService;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody User users) throws MessagingException {
        return new ResponseEntity<>(userService.save(users), HttpStatus.CREATED);
    }

//    @GetMapping("/users")
//    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(page = 0, size = 5)Pageable pageable){
//        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(userService.getById(id).orElseThrow(() -> new NoSuchElementException("Not found")));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
