package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    //TODO 확인하지 않은 메세지가 있는지 체크
    // 지금은 count를 리턴하는데 굳이 그럴 필요가 있나 싶음
    @GetMapping
    public ResponseEntity<Long> getMessage(Principal principal) {
        long count = messageService.countNewMessage(principal.getName());
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
