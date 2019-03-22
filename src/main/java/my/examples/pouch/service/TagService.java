package my.examples.pouch.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> findMyTagListByAccountId(String email) {
        return tagRepository.findMyTagListByAccountId(email);
    }
}
