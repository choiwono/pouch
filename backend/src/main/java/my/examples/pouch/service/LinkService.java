package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.CustomLink;
import my.examples.pouch.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            customLink.setRegDate(links.get(i).getRegDate());
            customLink.setTagName(links.get(i).getTagsName());
            customLinks.add(customLink);
        }
        return customLinks;
    }

}
