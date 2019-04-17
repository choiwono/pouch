package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkController {

    //링크 추가하기
    @PostMapping
    public void addLink(){}

    // 링크 수정하기
    @PutMapping(value = "/{id}")
    public void editLink(@PathVariable(value="id") Long id){}

    //링크 삭제하기
    @DeleteMapping(value = "/{id}")
    public void deleteLink(@PathVariable(value="id") Long id){}

    //태그 이름에 해당하는 링크 가져오기
    @GetMapping
    public void linksByTag(@RequestParam(value = "categoryId") String categoryId,
                           @RequestParam(value = "tagName") String tagName){}
}
