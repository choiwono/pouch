package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
}
