package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.CustomTag;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public List<CustomTagDto> findMyTagListByAccountId(String email, Long id) {
        return tagRepository.findMyTagListByAccountId(email,id);
    }

    public Set<CustomTag> customTagsDto(Set<Tag> tags){
        return tags.stream().map(tag -> modelMapper.map(tag, CustomTag.class))
               .collect(Collectors.toSet());
    }

    public List<CustomTagDto> findTagListByCategoryId(Long categoryId) {
        return tagRepository.findTagListByCategoryId(categoryId);
    }
}
