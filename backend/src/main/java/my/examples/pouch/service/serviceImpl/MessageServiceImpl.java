package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Message;
import my.examples.pouch.repository.MessageRepository;
import my.examples.pouch.service.MessageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;


    @Override
    public Message save(Message message) {
       return messageRepository.save(message);
    }
}