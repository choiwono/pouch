package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;

    public List<Link> getMyPouchByCategory(Long categoryId, String email) {
        return linkRepository.getMyPouchByCategory(categoryId,email);
    }

    public Link getLinkById(Long id) {
        return linkRepository.getOne(id);
    }

    public List<Link> getLinkByTagName(Long categoryId, String tagName) {
        return linkRepository.getLinkByTagName(categoryId,tagName);
    }

    public List<Link> getSearchPouchByCategory(Long categoryId) {
        return linkRepository.getSearchPouchByCategory(categoryId);
    }

    /*public List<Tag> getTags(Long id) {
        return linkRepository.getTagsByLinkId(id);
    }*/
}
