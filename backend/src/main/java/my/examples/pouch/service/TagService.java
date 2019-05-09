package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.custom.CustomTag;
import my.examples.pouch.dto.custom.CustomTagItem;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.dto.custom.CustomTagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

public interface TagService {

    public List<CustomTagDto> findMyTagListByAccountId(String email, Long id);
    public List<CustomTag> customTagList(List<Tag> tags);
    public List<CustomTag> findTagListByLinksId(Long linkId);
    public List<CustomTagDto> findTagListByCategoryId(Long categoryId);
    public Tag getTagById(Long id);
    public void deleteTag(Tag tag);
    public Tag saveOne(Tag tag);
    public void saveTags(Link link, CustomTagItem customTagItem, String email);
}
