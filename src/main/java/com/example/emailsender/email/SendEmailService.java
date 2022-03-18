package com.example.emailsender.email;

import com.example.emailsender.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

@Getter
@Setter
@AllArgsConstructor
@Service
@Slf4j
public class SendEmailService {

    private final JavaMailSender envioEmailDoJava;

    public void enviarEmailComAnexo(String para, String titulo, String message, String logo)
            throws MessagingException {
        log.info("Sendind email to confirm the data..");
        var mensagem = envioEmailDoJava.createMimeMessage();

        var helper = new MimeMessageHelper(mensagem, true); // html definido

        helper.setTo(para);
        helper.setSubject(titulo);
        helper.setText(message, true);
        helper.setBcc("ricardo@dtmm.com.br");

        helper.addAttachment("mario.gif", new ClassPathResource(logo));

        envioEmailDoJava.send(mensagem);
        log.info("Email com anexo enviado com sucesso");
    }
}
