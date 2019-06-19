package my.examples.pouch.service;

import my.examples.pouch.domain.Message;

import java.util.List;

public interface MessageService {
    public Message save(Message message);

    long countNewMessage(String name);

    List<Message> getMessage(String name);
}
