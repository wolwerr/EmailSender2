package com.example.emailsender.messages;

import com.example.emailsender.model.User;

public class EmailMessages {

    public static String createTitle(User user){
        return
        user.getName() + " your data was received!";
    }

    public static String messageToNewUser(User user, String password){
        return

                "Hi " + user.getName() +"! Welcome to Ataria. "
                +"Your data and password for access is below.\n\n"
                +"==============================================================\n"
                +"Name: " + user.getName() + "\n"
                +"Phone: " + user.getPhone() + "\n"
                +"E-mail: " + user.getEmail() + "\n"
                +"Mensagem: " + user.getMessage() + "\n"
                +"==============================================================\n\n"
                +"Is very important access your account and change your password.\n"
                +"www.ataria.dev";
    }
    public static String messageToNewUserLogo(User user){
        return

                "<div>Olá " + user.getName() +"! Obrigado pelo contato. "
                        +"Os dados enviados estão logo abaixo.</div>"
                        +"<br>"
                        +"<div>==============================================================</div>"
                        +"<div><strong>Nome: <strong>" + user.getName() + "</div>"
                        +"<div><strong>Phone: <strong>" + user.getPhone() + "</div>"
                        +"<div><strong>E-mail: <strong>" + user.getEmail() + "</div>"
                        +"<div><strong>Mensagem: <strong>" + user.getMessage() + "</div>"
                        +"<div>==============================================================</div>"
                        +"<br>"
                        +"<div>Responderei o mais breve possível.</div>"
                        +"<div>www.ricardolm.dev.br</div>";
    }
}

