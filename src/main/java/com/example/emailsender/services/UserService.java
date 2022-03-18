package com.example.emailsender.services;

import com.example.emailsender.repositories.UserRepository;
import com.example.emailsender.email.SendEmailService;
import com.example.emailsender.messages.EmailMessages;
import com.example.emailsender.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.Id;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private  UserService(UserRepository userRepository){ this.userRepository = userRepository;}

    @Autowired
    private SendEmailService sendEmailService;

    public User save(User user) throws MessagingException {

        var newUser = new User(user.getUserId(), user.getName(), user.getEmail(), user.getPhone(), user.getMessage());

        this.sendEmailService.enviarEmailComAnexo(
                user.getEmail(),
                EmailMessages.createTitle(user),
                EmailMessages.messageToNewUserLogo(user, user.getPhone()) + "\n\n", "/logo/mario.gif");

        return newUser;
    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Optional<User> findById(UUID userId){
        return userRepository.findById(userId);
    }



}
