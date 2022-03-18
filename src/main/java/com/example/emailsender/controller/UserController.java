package com.example.emailsender.controller;

import com.example.emailsender.exception.ResourcesNotFoundException;
import com.example.emailsender.model.User;
import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getbyId(@PathVariable(value = "id") long id)
            throws ResourcesNotFoundException {
        User user = userService.getById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("User not found: " + id));
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody User userDetails ) throws ResourcesNotFoundException {
        User user = userService.getById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("User not found for this Id: " + id));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setMessage(userDetails.getMessage());
        user.setPhone(userDetails.getPhone());
        final User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id) throws ResourcesNotFoundException{
        User user = userService.getById(id)
                .orElseThrow(()-> new ResourcesNotFoundException("User not found for this Id: " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Usuário deletado", Boolean.TRUE);
        return response;
    }

}
