package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.Custom.CustomLink;
import my.examples.pouch.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    //private final LinkService linkService;
    private final LinkRepository linkRepository;
    private final TagService tagService;

    public Link getLinkById(Long id) {
        return linkRepository.getOne(id);
    }

    public List<CustomLink> getLinkByTagName(Long categoryId, String tagName) {
        List<CustomLink> list =
                getCustomLinks(linkRepository.getLinkByTagName(categoryId,tagName));
        return list;
    }

    public List<CustomLink> getSearchPouchByCategory(Long categoryId) {
        List<Link> list = linkRepository.getSearchPouchByCategory(categoryId);
        return getCustomLinks(list);
    }

    public List<CustomLink> getCustomLinks(List<Link> links) {
        List<CustomLink> customLinks = new ArrayList<>();
        for(int i=0; i<links.size(); i++){
            CustomLink customLink = new CustomLink();
            customLink.setId(links.get(i).getId());
            customLink.setTitle(links.get(i).getTitle());
            customLink.setUrl(links.get(i).getUrl());
            customLink.setSrc(links.get(i).getSrc());
            customLink.setContent(links.get(i).getContent());
            customLink.setRegDate(links.get(i).getRegDate());
            customLink.setTagName(links.get(i).getTagsName());
            customLinks.add(customLink);
        }
        return customLinks;
    }

    public void share(Link link, Account account, Long categoryId ) {
         linkRepository.share(link.getTitle(), link.getUrl(), account.getEmail(), account.getId(), categoryId);
    }


}
