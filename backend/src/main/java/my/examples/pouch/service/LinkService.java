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
    public Link getLinkById(Long id);
    public List<CustomLink> getLinkByTagName(Long categoryId, String tagName);
    public List<CustomLink> getSearchPouchByCategory(Long categoryId);
    public List<CustomLink> getCustomLinks(List<Link> links);
    public void share(Link link, Account account, Long categoryId);
    public void deleteTagMappingByLinkId(Long id);
    public void deleteLink(Long id);
}
