package com.example.emailsender.controller;

import com.example.emailsender.model.User;
import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value ="userDto")Long id){
        Optional<User> userOptional=userService.findById((id));
        Optional<Object> userOptinal = null;
        if(!userOptinal.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
        }
    }
}
