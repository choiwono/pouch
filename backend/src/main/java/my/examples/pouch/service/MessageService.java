package my.examples.pouch.service;

import my.examples.pouch.domain.Message;

public interface MessageService {
    public Message save(Message message);

    long countNewMessage(String name);
}
