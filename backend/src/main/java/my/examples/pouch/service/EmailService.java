package my.examples.pouch.service;

import my.examples.pouch.domain.Account;

public interface EmailService {
    public void sendEmail(Account account, String password);
}
