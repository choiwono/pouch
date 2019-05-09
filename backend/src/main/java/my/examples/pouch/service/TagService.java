package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.Custom.CustomTag;
import my.examples.pouch.dto.Custom.CustomTagItem;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public List<CustomTagDto> findMyTagListByAccountId(String email, Long id) {
        return tagRepository.findMyTagListByAccountId(email,id);
    }

    /*public List<CustomTagDto> customTagList(List<Tag> tags){
        return tags.stream().map(tag -> modelMapper.map(tag, CustomTagDto.class))
                .collect(Collectors.toList());
    }*/

    /*public Set<CustomTag> customTagSet(Set<Tag> tags){
        Set<CustomTag> list = new HashSet<>();
        Iterator<Tag> itr = tags.iterator();
        while(itr.hasNext()){
            CustomTag customTag = new CustomTag();
            customTag.setId(itr.next().getId());
            customTag.setTagName(itr.next().getTagName());
            list.add(customTag);
            System.out.println(itr.next());
        }
        return list;
    }*/

    public List<CustomTag> customTagList(List<Tag> tags){
        List<CustomTag> list = new ArrayList<>();

        for(int i=0; i<tags.size(); i++){
            CustomTag CustomTag = new CustomTag();
            CustomTag.setId(tags.get(i).getId());
            CustomTag.setTagName(tags.get(i).getTagName());
            list.add(CustomTag);
        }
        return list;
    }

    public List<CustomTag> findTagListByLinksId(Long linkId){
        return customTagList(tagRepository.findTagListByLinksId(linkId));
    }

    public List<CustomTagDto> findTagListByCategoryId(Long categoryId) {
        return tagRepository.findTagListByCategoryId(categoryId);
    }

    public Tag getTagById(Long id) {
        return tagRepository.findTagById(id);
    }

    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

    public Tag saveOne(Tag tag) {
        return tagRepository.save(tag);
    }

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
