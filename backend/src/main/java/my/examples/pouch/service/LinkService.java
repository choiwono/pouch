package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.custom.CustomLink;
import my.examples.pouch.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface LinkService {
    Link getLinkById(Long id);
    List<CustomLink> getLinkByTagName(Long categoryId, String tagName);
    //List<CustomLink> getSearchPouchByCategory(Long categoryId);
    List<CustomLink> getCustomLinks(List<Link> links);
    void share(Link link, Account account, Long categoryId);
    void deleteTagMappingByLinkId(Long id);
    void deleteLink(Long id);
    int updateLink(Long id, String name);
}
