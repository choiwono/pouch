package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.custom.CustomTag;
import my.examples.pouch.dto.custom.CustomTagDto;
import my.examples.pouch.dto.custom.CustomTagItem;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomTagDto> findMyTagListByAccountId(String email, Long id) {
        return tagRepository.findMyTagListByAccountId(email,id);
    }

    @Override
    public List<CustomTag> customTagList(List<Tag> tags) {
        List<CustomTag> list = new ArrayList<>();

        for(int i=0; i<tags.size(); i++){
            CustomTag CustomTag = new CustomTag();
            CustomTag.setId(tags.get(i).getId());
            CustomTag.setTagName(tags.get(i).getTagName());
            list.add(CustomTag);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomTag> findTagListByLinksId(Long linkId) {
        return customTagList(tagRepository.findTagListByLinksId(linkId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomTagDto> findTagListByCategoryId(Long categoryId) {
        return tagRepository.findTagListByCategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag getTagById(Long id) {
        return tagRepository.findTagById(id);
    }

    @Override
    @Transactional
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    @Transactional
    public Tag saveOne(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void saveTags(Link link, CustomTagItem customTagItem, String email) {
        Set<Link> linkset = new HashSet<>();
        linkset.add(link);

        for(String str : customTagItem.getTags()){
            Tag tag = new Tag();
            tag.setLinks(linkset);
            tag.setTagName(str);
            tag.setRegDate(new Date());
            tag.setEmail(email);
            saveOne(tag);
        }
    }
}
