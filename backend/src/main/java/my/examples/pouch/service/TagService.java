package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.Custom.CustomTag;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
