package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
}
