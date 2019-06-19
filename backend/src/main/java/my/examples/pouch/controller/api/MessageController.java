package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Message;
import my.examples.pouch.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequestMapping("/api/messages")
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    //TODO 확인하지 않은 메세지가 있는지 체크
    // 지금은 count를 리턴하는데 굳이 그럴 필요가 있나 싶음
    @GetMapping(value = "/new")
    public ResponseEntity<Long> checkNewMessage(Principal principal) {
        long count = messageService.countNewMessage(principal.getName());
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List> getMessage(Principal principal){
        List<Message> messages = messageService.getMessage(principal.getName());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    //@PostMapping(value = "/send")
    //    public ResponseEntity<ResponseDto> sendCategory(@RequestParam(name = "id") Long id,
    //                                                    @RequestParam(name = "email") String email) {
    //        Message message = new Message();
    //        message.setReceiveId(email);
    //        message.setCategoryId(id);
    //        message.setUseYn("N");
    //        ResponseDto responseDto = new ResponseDto();
    //        if (messageService.save(message) != null) {
    //            responseDto.setMessage("OK, created");
    //            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    //        } else {
    //            responseDto.setMessage("Error, not created");
    //            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    //        }

}
