package my.examples.pouch.service;

import my.examples.pouch.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendEmail(Account account, String password) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pouchproject2019@gmail.com");
        message.setTo(account.getEmail());
        message.setSubject("[POUCH] 비밀번호를 알려드립니다");

        message.setText(password);
        emailSender.send(message);
    }
}
