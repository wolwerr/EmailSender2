package com.example.emailsender.services;

import com.example.emailsender.email.SendEmailService;
import com.example.emailsender.messages.EmailMessages;
import com.example.emailsender.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class UserService {

    @Autowired
    private SendEmailService sendEmailService;

    public User save(User user) throws MessagingException {

        var newUser = new User(user.getName(), user.getEmail(), user.getPhone(), user.getMessage());

        this.sendEmailService.enviarEmailComAnexo(
                user.getEmail(),
                EmailMessages.createTitle(user),
                EmailMessages.messageToNewUserLogo(user, user.getPhone()) + "\n\n", "/logo/mario.gif");



        return newUser;
    }


}
