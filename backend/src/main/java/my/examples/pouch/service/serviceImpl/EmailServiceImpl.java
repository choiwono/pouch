package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    //private final JavaMailSender emailSender;

    /*@Override
    public void sendEmail(Account account, String password) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pouchproject2019@gmail.com");
        message.setTo(account.getEmail());
        message.setSubject("[POUCH] 비밀번호를 알려드립니다");

        message.setText(password);
        emailSender.send(message);
    }*/
}
